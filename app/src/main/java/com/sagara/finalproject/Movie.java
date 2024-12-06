package com.sagara.finalproject;

public class Movie {
    private String title;
    private String releaseDate;
    private double rating;
    private String synopsis;
    private String posterUrl;
    private boolean isFavorite; // New field for favorite state

    public Movie(String title, String releaseDate, double rating, String synopsis, String posterUrl) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.synopsis = synopsis;
        this.posterUrl = posterUrl;
        this.isFavorite = false; // Default value
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
        return posterUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}