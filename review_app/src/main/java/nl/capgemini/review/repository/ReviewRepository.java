package nl.capgemini.review.repository;

import nl.capgemini.review.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, Long> {

}
