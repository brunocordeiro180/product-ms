package uol.compasso.productms.mock;

import uol.compasso.productms.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductMock {

    public static List<Product> createProducts(){
        List<Product> productList = new ArrayList<>();

        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Banana");
        product1.setPrice(new BigDecimal("10.00"));
        product1.setDescription("Banana Prata");

        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Maça");
        product2.setPrice(new BigDecimal("20.00"));
        product2.setDescription("Maça Prata");

        Product product3 = new Product();
        product3.setId("3");
        product3.setName("Uva");
        product3.setPrice(new BigDecimal("30.00"));
        product3.setDescription("Uva sem sementes");

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);

        return productList;
    }
}
