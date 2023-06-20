package com.agussuhardi.springboot3integrationtest.service;

import com.agussuhardi.springboot3integrationtest.entity.Product;
import com.agussuhardi.springboot3integrationtest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author agus.suhardii@gmail.com
 * @created 20/06/23/06/2023 :20.13
 * @project spring-boot-3-integration-test
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }

    public void update(Product product) {
        requireOne(product.getId());
        productRepository.save(product);
    }

    public Product getById(String id) {
        return requireOne(id);
    }

    public List<Product> query() {
        return productRepository.findAll();
    }


    private Product requireOne(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
