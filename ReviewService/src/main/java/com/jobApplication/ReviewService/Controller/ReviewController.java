package com.jobApplication.ReviewService.Controller;

import com.jobApplication.ReviewService.Entity.Review;
import com.jobApplication.ReviewService.Services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController
{
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService)
    {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReview(@RequestParam Long companyId)
    {
        return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review loReview, @RequestParam Long companyId)
    {
        return new ResponseEntity<>(reviewService.addReview(companyId,loReview), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getRevieById(@PathVariable Long id)
    {
        Review loReview = reviewService.getReviewById(id);
        if(loReview!=null)
        {
            return new ResponseEntity<>(loReview,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id,
                                               @RequestBody Review review)
    {
        return new ResponseEntity<>(reviewService.updateReview(id, review),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id)
    {
        if(reviewService.deleteReview(id))
        {
            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Delete failed",HttpStatus.NOT_FOUND);
    }
}
