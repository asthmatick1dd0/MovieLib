package com.asthmkd.app.MovieLib.Service;


import com.fasterxml.jackson.annotation.JsonProperty;

public class OMDbResponse {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Year")
    private Integer year;

    @JsonProperty("Response")
    private String response;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getReleaseDate() {
        return year;
    }

    public void setReleaseDate(Integer year) {
        this.year = year;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
