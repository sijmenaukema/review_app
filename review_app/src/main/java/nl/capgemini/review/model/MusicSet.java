package nl.capgemini.review.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MusicSet {

    private long id;
    private String title;
    private String genre;
    private DiscJockey discJockey;
    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getGenre(){ return genre; }
    public DiscJockey getDiscJockey() { return discJockey; }

    public MusicSet() {}

    private MusicSet(long id, String title, String genre, DiscJockey discJockey){
        this.id = id;
        this.genre = genre;
        this.title = title;
        this.discJockey = discJockey;
    }
}
