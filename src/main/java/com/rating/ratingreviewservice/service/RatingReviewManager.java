package com.rating.ratingreviewservice.service;


import com.rating.ratingreviewservice.clients.InventoryServiceClient;
import com.rating.ratingreviewservice.dao.RatingReviewRepository;
import com.rating.ratingreviewservice.exceptions.NotFoundException;
import com.rating.ratingreviewservice.models.Paginator;
import com.rating.ratingreviewservice.models.RatingReview;
import com.rating.ratingreviewservice.models.Response;
import com.rating.ratingreviewservice.models.UserContext;
import com.rating.ratingreviewservice.utils.ResponseHandler;
import com.rating.ratingreviewservice.utils.Utility;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class RatingReviewManager {

    @Autowired
    RatingReviewRepository ratingReviewRepository;

    @Autowired
    InventoryServiceClient inventoryServiceClient;


    public ResponseEntity<Object> userRatingReview(String userId, int pageNo, int pageSize, String sortBy,
                                                   String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<RatingReview> ratingReviews = ratingReviewRepository.findByUserId(userId,  pageable);
        List<RatingReview> ratingReviewList = ratingReviews.getContent();

        Paginator paginator = new Paginator(ratingReviews.getNumber(), ratingReviews.getSize(),
                ratingReviews.getTotalElements(), ratingReviews.getTotalPages(), ratingReviews.isLast());
        return ResponseHandler.generateResponse(ratingReviewList, paginator);
    }

    public RatingReview ratingAndReviewProducts(RatingReview ratingReview) {
        Integer productId = ratingReview.getProductId();
        boolean isValidProduct = isValidProduct(productId);
        if (isValidProduct == true) {
            return ratingReviewRepository.save(ratingReview);
        }
        throw new NotFoundException("Product Not Found");
    }

    public boolean isValidProduct(Integer productId) {
        Response response = inventoryServiceClient.getProductById(productId);
        boolean validProduct = true;
       try {
            Object responseData = Utility.extractApiResponse(response);
        } catch (RuntimeException e) {
           validProduct = false;
        }
       return validProduct;
    }
}
