package com.rating.ratingreviewservice.api;


import com.rating.ratingreviewservice.models.RatingReview;
import com.rating.ratingreviewservice.models.UserContext;
import com.rating.ratingreviewservice.service.RatingReviewManager;
import com.rating.ratingreviewservice.utils.Constant;
import com.rating.ratingreviewservice.utils.ResponseHandler;
import com.rating.ratingreviewservice.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.Map;

@RequestMapping("/api/v1")
@RestController
public class RatingReviewController {

    @Autowired
    RatingReviewManager ratingReviewManager;

    @GetMapping("/rating-review/{userId}")
    ResponseEntity<Object> getUserRatingReviewAndRating(
            @NotNull(message = "UserId is Required") @PathVariable String userId,
            @RequestParam(value = "pageNo", defaultValue = Constant.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = Constant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constant.DEFAULT_PRODUCT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constant.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        return ratingReviewManager.userRatingReview(userId, pageNo, pageSize, sortBy, sortDir);
    }

    @PostMapping("/rating-review")
    ResponseEntity<Object> rateAndReviewProduct(
            @RequestHeader Map requestHeaders, @Valid @RequestBody RatingReview ratingReview) {

        return ResponseHandler.generateResponse(ratingReviewManager.ratingAndReviewProducts(ratingReview));

    }

}
