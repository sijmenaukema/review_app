package nl.capgemini.review.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("reviews")
public class Review {

    // TODO
    // eerst raten van MusicSet
    //
    @MongoId
    private long id;

    private String text;

//    public long getId() {
//        return id;
//    }

    public String getText() {
        return text;
    }

    public void setText(String text){
        this.text = text;
    }

    public Review(){};

    public Review(String text){
        this.text = text;
    }
}
