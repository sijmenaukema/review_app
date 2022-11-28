package nl.capgemini.review.service;

import nl.capgemini.review.model.Review;
import nl.capgemini.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReviewService {
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    private final ReviewRepository reviewRepository;

    public ArrayList<Review> getAllReviews(){
        ArrayList<Review> reviewArrayList = new ArrayList<>();
        reviewRepository.findAll().forEach(reviewArrayList::add);
        return reviewArrayList;
    }
}
