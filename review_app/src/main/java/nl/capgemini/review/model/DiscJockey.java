package nl.capgemini.review.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscJockey {
    @JsonProperty("id")
    private long id;
    @JsonProperty("genre")
    private String genre;
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public DiscJockey() {
    }

    private DiscJockey(long id, String name, String genre){
        this.id = id;
        this.name = name;
        this.genre = genre;
    }
}
