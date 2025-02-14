package com.jobApplication.reviewMS.review.controller;


import com.jobApplication.reviewMS.review.model.Review;
import com.jobApplication.reviewMS.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;


    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;

    }

    @GetMapping
    public ResponseEntity<List<Review>> findAll(@RequestParam Long companyId){
        //Company company = companyService.getById(companyId);
        return ResponseEntity.ok(reviewService.findAllReviews(companyId));
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review) {
        boolean isReviewSaved = reviewService.addReview(companyId,review);
        if(isReviewSaved)
            return ResponseEntity.ok("Review Added Successfully!");
        return new ResponseEntity<>("Review is Not Added!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("{reviewId}")
    public ResponseEntity<Review> findByID(@PathVariable Long reviewId){
        return ResponseEntity.ok(reviewService.findReviewsById(reviewId));
    }

    @PutMapping("{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId,
                                               @RequestBody Review review) {
        boolean isReviewSaved = reviewService.updateReview(reviewId,review);
        if(isReviewSaved)
            return ResponseEntity.ok("Review is Updated Successfully!");
        return new ResponseEntity<>("Review is Not Updated!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview( @PathVariable Long reviewId){
        boolean flag = reviewService.deleteReview(reviewId);
        if(!flag) {
            return new ResponseEntity<>("Job is not available to Delete!", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Job is Deleted!");
    }


}
