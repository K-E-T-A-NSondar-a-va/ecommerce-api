package com.decoder.ecommerce.implementation;

import com.decoder.ecommerce.exception.ProductException;
import com.decoder.ecommerce.model.Product;
import com.decoder.ecommerce.model.Rating;
import com.decoder.ecommerce.model.User;
import com.decoder.ecommerce.repository.RatingRepository;
import com.decoder.ecommerce.request.RatingRequest;
import com.decoder.ecommerce.service.ProductService;
import com.decoder.ecommerce.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceimpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Rating createRating(RatingRequest request, User user) throws ProductException {
        Product product = productService.findById(request.getProductId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(request.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.findAllRatingByProductId(productId);
    }
}
