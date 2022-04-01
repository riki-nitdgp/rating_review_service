package com.rating.ratingreviewservice.utils;

import com.google.gson.Gson;
import com.rating.ratingreviewservice.exceptions.BadRequestException;
import com.rating.ratingreviewservice.exceptions.InternalServerException;
import com.rating.ratingreviewservice.exceptions.NotFoundException;
import com.rating.ratingreviewservice.exceptions.UnAuthorizedException;
import com.rating.ratingreviewservice.models.Response;
import com.rating.ratingreviewservice.models.UserContext;



import java.util.Map;

public class Utility {


    public static Object extractApiResponse(Response apiResponse) {

        Integer httpStatus = apiResponse.getStatus();
        switch(httpStatus){
            case 200:
                return  apiResponse.getData();

            case 400:
                throw new BadRequestException("Bad Request");

            case 404:
                throw new NotFoundException("Not Found");

            default:
                throw new InternalServerException("Something Went Wrong");

        }

    }
}