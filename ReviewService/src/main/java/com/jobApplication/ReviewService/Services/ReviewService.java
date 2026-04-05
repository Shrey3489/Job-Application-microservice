package com.jobApplication.ReviewService.Services;

import com.jobApplication.ReviewService.Entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReview(Long companyId);
    String addReview(Long companyId, Review review);
    Review getReviewById(Long id);
    String updateReview(Long id, Review review);
    boolean deleteReview(Long id);
}
