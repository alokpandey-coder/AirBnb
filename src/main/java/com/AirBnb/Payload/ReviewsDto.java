package com.AirBnb.Payload;

import java.time.LocalDateTime;

public class ReviewsDto {

    private Long reviewId;
    private Integer rating;
    private String comments;
    private LocalDateTime createdAt;
    private Long userId;

    public ReviewsDto(Long reviewId, Integer rating, String comments, LocalDateTime createdAt, Long userId) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.comments = comments;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
