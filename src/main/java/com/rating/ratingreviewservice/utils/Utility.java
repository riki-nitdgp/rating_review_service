package com.rating.ratingreviewservice.utils;

import com.google.gson.Gson;
import com.rating.ratingreviewservice.exceptions.BadRequestException;
import com.rating.ratingreviewservice.exceptions.InternalServerException;
import com.rating.ratingreviewservice.exceptions.UnAuthorizedException;
import com.rating.ratingreviewservice.models.UserContext;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

public class Utility {

    public static UserContext extractUserContextFromRequestHeaders(Map requestHeader) {

        String userContextHeader = (String) requestHeader.get("x-user-context");
        if (userContextHeader == null) {
            throw new BadRequestException("x-user-context is required");
        }
        Gson gson = new Gson();
        UserContext userContext;
        try {
            userContext = gson.fromJson(userContextHeader, UserContext.class);
        } catch (Exception e) {
            throw new InternalServerException("Something Went Wrong");
        }
        if (userContext.getUserId() == null || userContext.getUserId().isEmpty()) {
            throw new UnAuthorizedException("Unauthorized User");
        }

        return userContext;


    }
}
