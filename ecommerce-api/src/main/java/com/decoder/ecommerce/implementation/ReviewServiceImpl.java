package com.decoder.ecommerce.implementation;

import com.decoder.ecommerce.exception.ProductException;
import com.decoder.ecommerce.model.Product;
import com.decoder.ecommerce.model.Review;
import com.decoder.ecommerce.model.User;
import com.decoder.ecommerce.repository.ProductRepository;
import com.decoder.ecommerce.repository.ReviewRepository;
import com.decoder.ecommerce.request.ReviewRequest;
import com.decoder.ecommerce.service.ProductService;
import com.decoder.ecommerce.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Review createReview(ReviewRequest reviewRequest, User user) throws ProductException {
        Product product = productService.findById(reviewRequest.getProductId());
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(reviewRequest.getReview());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {
        return reviewRepository.getAllReviewByProductId(productId );
    }
}
