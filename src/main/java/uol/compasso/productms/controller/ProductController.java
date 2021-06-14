package uol.compasso.productms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uol.compasso.productms.dto.ProductDto;
import uol.compasso.productms.entity.Product;
import uol.compasso.productms.service.ProductService;
import uol.compasso.productms.util.CustomExceptionMessage;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RepositoryRestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        log.info("ProductController: getProducts");
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProductById(@PathVariable("id") String productId){
        log.info("ProductController: getProductById");
        try{
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(product);
        }catch (Exception exception){
            CustomExceptionMessage customExceptionMessage = new CustomExceptionMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
            return new ResponseEntity<>(customExceptionMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        log.info("ProductController: createProduct -> " + productDto);
        Product newProduct = productService.createProduct(productDto);

        return ResponseEntity.ok(newProduct);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String productId){
        log.info("ProductController: deleteProduct -> id: " + productId);
        try{
            productService.deleteProduct(productId);
            return ResponseEntity.ok().build();
        }catch (Exception exception){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String productId, @RequestBody ProductDto productDto){
        log.info("ProductController: updateProduct -> id: " + productId + " , productDto: " + productDto);
        try{
            Product product = productService.updateProduct(productId, productDto);
            return ResponseEntity.ok(product);
        }catch (Exception exception){
            CustomExceptionMessage customExceptionMessage = new CustomExceptionMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage());
            return new ResponseEntity<>(customExceptionMessage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(value = "min_price" , required = false) BigDecimal minPrice,
            @RequestParam(value = "max_price", required = false) BigDecimal maxPrice,
            @RequestParam(value = "q", required = false) String q
    ){
        log.info("ProductController: searchProducts -> min_price: " + minPrice + " , maxPrice: " + maxPrice + ", q: " + q);
        List<Product> products = productService.searchProducts(minPrice, maxPrice, q);
        return ResponseEntity.ok(products);
    }
}
