package nl.capgemini.review.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document("reviews")
public class Review {

    @MongoId
    private ObjectId id;
    @NotNull(message = "rating is mandatory")
    @NotEmpty(message = "rating is mandatory")
    private Integer rating;
    @NotNull(message = "text is required")
    private String text;

    public ObjectId getId() { return id; }
    public Integer getRating(){ return rating; }
    public String getText(){ return text; }
    public void setText(String text){
        this.text = text;
    }
    public void setRating(Integer rating) { this.rating = rating; }

    public Review(){};

    public Review(String text, Integer rating){
        this.text = text;
        this.rating = rating;
    }
}
