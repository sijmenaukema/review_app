package nl.capgemini.review.service;

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
        Review review = new Review(text, rating);
        if(getMusicSet(musicSetId)) {
            reviewRepository.save(review);
            return review;
        }
        return null;
    }

    private static boolean getMusicSet(String musicSetId) {
        final String uri = "http://localhost:9090/musicset/id";
//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("id", musicSetId);
        String requestBody = "{ \"id\":"
                + musicSetId +
                "}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
        if(response.getStatusCode().is2xxSuccessful()) {
            return true;
        }else {
            return false;
        }
    }
//        private static boolean getMusicSet(String musicSetId) {
//            int response;
//            String jsonInputString = "{\"id:\""+ musicSetId +"}";
//            try {
//                URL url = new URL("http://localhost:9090/musicset/id");
//                HttpURLConnection con = (HttpURLConnection)url.openConnection();
//                con.setRequestMethod("GET");
//                con.setRequestProperty("Content-Type", "application/json");
//                con.setRequestProperty("Accept", "application/json");
//
//                response = con.getResponseCode();
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            if ( response == 200 ) { return true ;}
//            return false;
//        }

}

