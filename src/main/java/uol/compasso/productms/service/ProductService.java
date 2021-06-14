package uol.compasso.productms.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uol.compasso.productms.dto.ProductDto;
import uol.compasso.productms.entity.Product;
import uol.compasso.productms.repository.ProductRepository;
import uol.compasso.productms.repository.specs.ProductSpecification;
import uol.compasso.productms.repository.specs.SearchCriteria;
import uol.compasso.productms.repository.specs.SearchOperation;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public List<Product> getProducts(ProductSpecification productSpecification){
        return productRepository.findAll(productSpecification);
    }

    public List<Product> searchProducts(BigDecimal minPrice, BigDecimal maxPrice, String nameOrDescription) {
        ProductSpecification productSpecification = new ProductSpecification();

        if(minPrice != null){
            productSpecification.add(new SearchCriteria("price", minPrice, SearchOperation.GREATER_THAN_EQUAL));
        }

        if(maxPrice != null) {
            productSpecification.add(new SearchCriteria("price", maxPrice, SearchOperation.LESS_THAN_EQUAL));
        }

        if(nameOrDescription != null){
            productSpecification.add(new SearchCriteria("q", nameOrDescription, SearchOperation.MATCH));
        }

        return getProducts(productSpecification);
    }

    public Product getProductById(String productId){

        return productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Product Not Found")
        );
    }

    public Product createProduct(ProductDto productDto) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productDto, Product.class);

        return productRepository.save(product);
    }

    public void deleteProduct(String productId){
        Product product = getProductById(productId); //will return RunTimeException if not found
        productRepository.delete(product);
    }

    public Product updateProduct(String productId, ProductDto productDto){
        Product productToUpdate = getProductById(productId);
        mapDtoToEntity(productDto, productToUpdate);
        return productRepository.save(productToUpdate);
    }

    private void mapDtoToEntity(ProductDto productDto, Product productToUpdate) {
        productToUpdate.setName(productDto.getName());
        productToUpdate.setDescription(productDto.getDescription());
        productToUpdate.setPrice(productDto.getPrice());
    }
}
