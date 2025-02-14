package com.jobApplication.reviewMS.review.service;



import com.jobApplication.reviewMS.review.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAllReviews(Long companyId);

    boolean addReview(Long companyId, Review review);

    Review findReviewsById(Long reviewId);

    boolean updateReview(Long reviewId,Review review);

    boolean deleteReview(Long reviewId);
}
