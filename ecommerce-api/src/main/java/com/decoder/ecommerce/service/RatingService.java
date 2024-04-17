package com.decoder.ecommerce.service;

import com.decoder.ecommerce.exception.ProductException;
import com.decoder.ecommerce.model.Rating;
import com.decoder.ecommerce.model.User;
import com.decoder.ecommerce.request.RatingRequest;

import java.util.List;

public interface RatingService {
    Rating createRating(RatingRequest request, User user) throws ProductException;
    List<Rating> getProductsRating(Long productId);
}
