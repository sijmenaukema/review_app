package nl.capgemini.review.controller;

import nl.capgemini.review.model.MusicSet;
import nl.capgemini.review.model.Review;
import nl.capgemini.review.service.ReviewService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
public class ReviewController {

    final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    protected ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviewList = reviewService.getAllReviews();
        if (reviewList.isEmpty()) return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok().body(reviewList);
        }
    }

//    @PostMapping("/")
//    protected ResponseEntity<Review> postReview(@RequestBody String text, int rating, long musicSetId) {
//        if (getMusicSet(musicSetId).getId() == musicSetId) {
//            Review review = reviewService.postNewReview(text, rating);
//            return ResponseEntity.ok().body(review);
//        } else return ResponseEntity.notFound().build();
//    }

    @PostMapping("/")
    protected ResponseEntity<Review> postReview(@RequestBody @Valid Map<String, Object> body) {
        int rating = Integer.parseInt(body.get("rating").toString());
        Review review = reviewService.postNewReview(body.get("text").toString(), rating, body.get("id").toString());
        if( review == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(review);
        }
    }
}
