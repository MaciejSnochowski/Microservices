package com.microservices.productService.service;

import com.microservices.productService.dto.ProductRequest;
import com.microservices.productService.dto.ProductResponse;
import com.microservices.productService.model.Product;
import com.microservices.productService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = new Product().builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice()).build();

        try {
            productRepository.save(product);
            log.info("Product with id:{} has been created",product.getId());
        } catch (Exception e) {
            log.info("Product unable to be saved");
        }
    }
    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        log.info("DONE");
        return products.stream().map(ProductService::mapToProductResponse).toList();
    }
    private static ProductResponse mapToProductResponse(Product product){
        return new ProductResponse().builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
