package com.sagara.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private void saveFavorites() {
        ArrayList<Movie> favoriteMovies = new ArrayList<>();
        for (Movie m : movies) {
            if (m.isFavorite()) {
                favoriteMovies.add(m);
            }
        }

        SharedPreferences preferences = context.getSharedPreferences("FavoritesPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String json = new Gson().toJson(favoriteMovies);
        editor.putString("favoriteMovies", json);
        editor.apply();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        }

        ImageView posterView = convertView.findViewById(R.id.moviePoster);
        TextView titleView = convertView.findViewById(R.id.title);
        TextView releaseDateView = convertView.findViewById(R.id.releaseDate);
        TextView ratingView = convertView.findViewById(R.id.rating);
        TextView synopsisView = convertView.findViewById(R.id.synopsis);
        ImageButton favoriteButton = convertView.findViewById(R.id.favoriteButtonNew);

        Movie movie = movies.get(position);

        titleView.setText(movie.getTitle());
        releaseDateView.setText("Release Date: " + movie.getReleaseDate());
        ratingView.setText("Rating: " + movie.getRating());
        synopsisView.setText(movie.getSynopsis());

        // Load the movie poster using Glide
        Glide.with(context)
                .load(movie.getPosterUrl())
                .placeholder(R.drawable.placeholder_image)
                .into(posterView);

        // Update favorite button icon based on the movie's favorite state
        if (movie.isFavorite()) {
            favoriteButton.setImageResource(R.drawable.ic_favorite);
        } else {
            favoriteButton.setImageResource(R.drawable.ic_favorite_border);
        }

        // Handle favorite button click
        favoriteButton.setOnClickListener(v -> {
            boolean isFavorite = !movie.isFavorite(); // Toggle favorite state
            movie.setFavorite(isFavorite);
            saveFavorites(); // Save updated favorites to SharedPreferences
            notifyDataSetChanged(); // Refresh the adapter to update the UI
        });


        return convertView;
    }
}