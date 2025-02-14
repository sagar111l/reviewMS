package com.jobApplication.reviewMS.review.service;


import com.jobApplication.reviewMS.review.model.Review;
import com.jobApplication.reviewMS.review.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;

    }


    @Override
    public List<Review> findAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {

        if(companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review findReviewsById(Long reviewId) {

        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review review) {
        Review reviewUpdated = reviewRepository.findById(reviewId).orElse(null);
        if(reviewUpdated != null){
            reviewUpdated.setCompanyId(review.getCompanyId());
            reviewUpdated.setDescription(review.getDescription());
            reviewUpdated.setRating(review.getRating());
            reviewUpdated.setTitle(review.getTitle());
            reviewRepository.save(reviewUpdated);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview( Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
       if(review != null){

           reviewRepository.delete(review);
           return true;
       }
        return false;
    }
}
