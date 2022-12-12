package nl.capgemini.review.repository;

import nl.capgemini.review.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, Long> {
    @Query("{musicSetName:?0}")
    Optional<Review[]> getReviewByMusicSetId(String musicSetName);
}
