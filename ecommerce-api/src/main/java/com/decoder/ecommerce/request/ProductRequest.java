package com.decoder.ecommerce.request;

import com.decoder.ecommerce.model.Category;
import com.decoder.ecommerce.model.Rating;
import com.decoder.ecommerce.model.Review;
import com.decoder.ecommerce.model.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductRequest {
    private Long id;
    private String title;
    private String description;
    private int price;
    private int discountedPrice;
    private int discountPercent;
    private int quantity;
    private String brand;
    private String color;
    private Set<Size> sizes = new HashSet<>();
    private String imageUrl;
    private List<Rating> ratings = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();
    private int numRatings;
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
    private LocalDateTime createdAt;
}
