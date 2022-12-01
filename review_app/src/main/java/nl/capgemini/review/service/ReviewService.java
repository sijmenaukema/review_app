package nl.capgemini.review.service;

import nl.capgemini.review.model.DiscJockey;
import nl.capgemini.review.model.MusicSet;
import nl.capgemini.review.model.Review;
import nl.capgemini.review.repository.ReviewRepository;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public Review postNewReview(String text, int rating, String musicSetId){
        MusicSet musicSet = getMusicSet(musicSetId);
        if(musicSet != null) {
            DiscJockey discJockey = musicSet.getDiscJockey();
            Review review = new Review(rating, text, discJockey.getName(), musicSet.getTitle());
            reviewRepository.save(review);
            return review;
        }
        return null;
    }

    private static MusicSet getMusicSet(String musicSetId) {
        final String uri = "http://localhost:9090/musicset/id";
        String requestBody = "{ \"id\":" + musicSetId + "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<MusicSet> response = restTemplate.postForEntity(uri, request, MusicSet.class);
        if(response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }else {
            return null;
        }
    }
}

