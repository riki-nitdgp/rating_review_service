package com.rating.ratingreviewservice.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="rating_review", indexes = {@Index(name="idx_user_id", columnList = "userId"),
@Index(name="idx_date", columnList = "created_at DESC")})
@Data
public class RatingReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "User Id is Mandatory")
    private String userId;

    @NotNull(message = "Product Id is Mandatory")
    private Integer productId;

    @NotNull(message = "Rating is Mandatory")
    @Max(5)
    @Min(1)
    private Integer rating;

    @NotBlank
    private String review;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp //this adds the default timestamp on save
    private Timestamp createdAt;


}
