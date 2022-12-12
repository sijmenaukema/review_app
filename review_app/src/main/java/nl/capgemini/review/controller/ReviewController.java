package nl.capgemini.review.controller;

import nl.capgemini.review.model.Review;
import nl.capgemini.review.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
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
    @GetMapping("/{musicSetId}")
    protected ResponseEntity<List<Review>> getReviewsForSet(@PathVariable int musicSetId) {
        List<Review> reviewList = reviewService.getReviews(musicSetId);
        if (reviewList.isEmpty()) return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok().body(reviewList);
        }
    }

//    @GetMapping("/musicsetname/{musicSetId}")
//    protected ResponseEntity<List<Review>> getReviewsForMusicSet(@PathVariable String musicSetId) {
//        List<Review> reviewList = reviewService.getReviews(musicSetId);
//        if (reviewList.isEmpty()) return ResponseEntity.notFound().build();
//        else {
//            return ResponseEntity.ok().body(reviewList);
//        }
//    }

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
        //TODO try / catch
        if( review == null ) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(review);
        }
    }
}
