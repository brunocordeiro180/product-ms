package uol.compasso.productms.repository.specs;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductSearch {
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String query;
}
