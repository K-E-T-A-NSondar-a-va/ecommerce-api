package com.decoder.ecommerce.service;

import com.decoder.ecommerce.exception.ProductException;
import com.decoder.ecommerce.model.Product;
import com.decoder.ecommerce.request.CreateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductRequest request);
    String deleteProduct(Long productId) throws ProductException;
    Product updateProduct(Long productId, Product product) throws ProductException;
    Product findById(Long id) throws ProductException;
    List<Product> findProductByCategory(String category);
    Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice,
                                 Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber,
                                 Integer pageSize);
}
