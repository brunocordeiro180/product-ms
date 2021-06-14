package uol.compasso.productms.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;

    @Column
    @NotNull(message = "[NotNull.Product.name]")
    private String name;

    @Column
    @NotNull(message = "[NotNull.Product.description]")
    private String description;

    @Column
    @NotNull(message = "[NotNull.Product.price]")
    private BigDecimal price;
}
