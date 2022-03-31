package com.rating.ratingreviewservice.models;

import lombok.Data;

import java.util.List;

@Data
public class UserContext {
    private String userId;
    private List<String> roles;
}
