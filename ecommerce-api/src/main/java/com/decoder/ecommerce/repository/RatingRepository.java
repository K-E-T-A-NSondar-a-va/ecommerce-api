package com.decoder.ecommerce.repository;

import com.decoder.ecommerce.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("SELECT r FROM Rating r WHERE r.product.id = :productId")
    List<Rating> findAllRatingByProductId(@Param("productId") Long productId);
}
