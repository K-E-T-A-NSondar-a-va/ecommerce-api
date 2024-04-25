package com.decoder.ecommerce.controller;

import com.decoder.ecommerce.model.Category;
import com.decoder.ecommerce.model.Product;
import com.decoder.ecommerce.repository.ProductRepository;
import com.decoder.ecommerce.request.CreateProductRequest;
import com.decoder.ecommerce.request.ProductRequest;
import com.decoder.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        return ResponseEntity.ok(productRepository.save(productService.createProduct(createProductRequest)));
    }


}
