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

    private String ArtistName;

    private String musicSetName;

    private long musicSetId;

    public long getMusicSetId() {
        return musicSetId;
    }

    public void setMusicSetId(long musicSetId) {
        this.musicSetId = musicSetId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public String getMusicSetName() {
        return musicSetName;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public Review() {}

    public Review(Integer rating, String text, String artistName, String musicSetName, long musicSetId) {
        this.rating = rating;
        this.text = text;
        ArtistName = artistName;
        this.musicSetName = musicSetName;
        this.musicSetId = musicSetId;
    }
}
