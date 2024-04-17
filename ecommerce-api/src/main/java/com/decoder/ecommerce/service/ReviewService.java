package com.decoder.ecommerce.service;

import com.decoder.ecommerce.exception.ProductException;
import com.decoder.ecommerce.model.Review;
import com.decoder.ecommerce.model.User;
import com.decoder.ecommerce.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewRequest reviewRequest, User user) throws ProductException;
    List<Review> getAllReview(Long productId);
}
