package com.sagara.finalproject;
public class Movie {
    private String title;
    private String releaseDate;
    private double rating;
    private String synopsis;
    private String posterUrl; // Add poster URL field

    public Movie(String title, String releaseDate, double rating, String synopsis, String posterUrl) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.synopsis = synopsis;
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getPosterUrl() {
        return posterUrl; // Getter for poster URL
    }
}
