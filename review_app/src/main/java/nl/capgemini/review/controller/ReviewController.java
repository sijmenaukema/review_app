package nl.capgemini.review.controller;

import nl.capgemini.review.model.Review;
import nl.capgemini.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mongo/review")
public class ReviewController {

    final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {this.reviewService = reviewService;}

    @GetMapping("/")
    protected ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviewList = reviewService.getAllReviews();
        if (reviewList.isEmpty()) return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok().body(reviewList);
        }
    }
}
