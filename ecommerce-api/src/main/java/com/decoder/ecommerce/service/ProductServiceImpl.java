package com.decoder.ecommerce.service;


import com.decoder.ecommerce.exception.ProductException;
import com.decoder.ecommerce.model.Category;
import com.decoder.ecommerce.model.Product;
import com.decoder.ecommerce.repository.CategoryRepository;
import com.decoder.ecommerce.repository.ProductRepository;
import com.decoder.ecommerce.request.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(CreateProductRequest request) {
        Category topLevel = categoryRepository.findByName(request.getTopLevelCategory());
        if(topLevel == null) {
            Category topLevelCategory = new Category();
            topLevelCategory.setName(request.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topLevel = categoryRepository.save(topLevelCategory);
        }

        Category secondLevel = categoryRepository.findByNameAndParent(request.getSecondLevelCategory(), topLevel.getName());
        if(secondLevel == null) {
            Category secondLevelCategory = new Category();
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);

            secondLevel = categoryRepository.save(secondLevelCategory);
        }

        Category thirdLevel = categoryRepository.findByNameAndParent(request.getThirdLevelCategory(), secondLevel.getName());
        if(thirdLevel == null) {
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setLevel(3);

            thirdLevel = categoryRepository.save(thirdLevelCategory);
        }

        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setColor(request.getColor());
        product.setDiscountedPrice(request.getDiscountedPrice());
        product.setDiscountPercent(request.getDiscountPercent());
        product.setImageUrl(request.getImageUrl());
        product.setBrand(request.getBrand());
        product.setPrice(request.getPrice());
        product.setSizes(request.getSizes());
        product.setQuantity(request.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Product product = findById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "product deleted successfully !1";
    }

    @Override
    public Product updateProduct(Long productId, Product product) throws ProductException {
        Product product1 = findById(productId);

        if(product.getQuantity() != 0) {
            product1.setQuantity(product.getQuantity());
        }
        return productRepository.save(product1);
    }

    @Override
    public Product findById(Long id) throws ProductException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElseThrow(() -> new ProductException("Product not found with id "+id));
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

        // creating page:
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // finding all products:
        List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);

        // filtering products by multiple colors:
        if(colors.isEmpty()) {
            products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
        }

        if(stock != null) {
            if(stock.equals("in_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            } else if (stock.equals("out_of_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 1).toList();
            }
        }

        int startIndex = (int) pageable.getOffset();
        int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

        List<Product> pageContent = products.subList(startIndex, endIndex);
        Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());
        return filteredProducts;
    }
}
