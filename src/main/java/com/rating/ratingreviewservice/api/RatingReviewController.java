package com.rating.ratingreviewservice.api;


import com.google.gson.Gson;
import com.rating.ratingreviewservice.exceptions.BadRequestException;
import com.rating.ratingreviewservice.exceptions.UnAuthorizedException;
import com.rating.ratingreviewservice.models.UserContext;
import com.rating.ratingreviewservice.utils.Utility;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class RatingReviewController {

    @GetMapping("/hello")
    UserContext getUserContext(@RequestHeader Map requestHeaders)  {
        return Utility.extractUserContextFromRequestHeaders(requestHeaders);
    }
}
