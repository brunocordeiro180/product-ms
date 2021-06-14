package uol.compasso.productms.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import uol.compasso.productms.entity.Product;
import uol.compasso.productms.mock.ProductMock;
import uol.compasso.productms.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    public void setup(){
        productService = new ProductService(productRepository);
        Mockito.lenient().when(productRepository.findAll()).thenReturn(ProductMock.createProducts());
    }

    @Test
    @DisplayName("Should return all products")
    void getProducts(){
        List<Product> products = productService.getProducts();
        assertEquals(3, products.size());
        assertNotNull(products);
    }

    @Test
    @DisplayName("Should delete product")
    void deleteProduct(){
        List<Product> products = productService.getProducts();
        productRepository.deleteById("2");

        assertEquals(2, products.size() - 1);
        assertNotNull(products);
    }

    @Test
    @DisplayName("Should find products by query parameters")
    void search(){
        BigDecimal minPrice = new BigDecimal("10");
        BigDecimal maxPrice = new BigDecimal("60");
        String q = "Banana";

        List<Product> products = productService.searchProducts(minPrice, maxPrice, q);

        assertNotNull(products);
    }
}
