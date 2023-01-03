package nl.capgemini.review.service;

import nl.capgemini.review.model.DiscJockey;
import nl.capgemini.review.model.MusicSet;
import nl.capgemini.review.model.Review;
import nl.capgemini.review.repository.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public List<Review> getReviews(int musicSetId){
        Optional<Review[]> optional = reviewRepository.getReviewByMusicSetId(musicSetId);
        return optional.map(reviews -> Arrays.stream(reviews).toList()).orElse(null);
    }

    public Review postNewReview(String text, int rating, String musicSetId){
        MusicSet musicSet = getMusicSet(musicSetId);
        if(musicSet != null) {
            DiscJockey discJockey = musicSet.getDiscJockey();
            Review review = new Review(rating, text, discJockey.getName(), musicSet.getTitle(), musicSet.getId());
            reviewRepository.save(review);
            return review;
        }
        return null;
    }

    private static MusicSet getMusicSet(String musicSetId) {
        final String uri = String.format("http://festival-app:9090/musicset/%s", musicSetId);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MusicSet> response = restTemplate.getForEntity(uri, MusicSet.class);
        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return null;
        }
    }
}