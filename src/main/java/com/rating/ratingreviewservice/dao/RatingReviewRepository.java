package com.rating.ratingreviewservice.dao;

import com.rating.ratingreviewservice.models.RatingReview;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;


@Repository
public interface RatingReviewRepository extends JpaRepository<RatingReview, Integer> {


    Page<RatingReview> findByUserId(String userId, Pageable pageable);
}
