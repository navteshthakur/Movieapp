package com.sagara.finalproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import com.bumptech.glide.Glide;
public class    MovieAdapter extends BaseAdapter {

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

        Movie movie = movies.get(position);

        titleView.setText(movie.getTitle());
        releaseDateView.setText("Release Date: " + movie.getReleaseDate());
        ratingView.setText("Rating: " + movie.getRating());
        synopsisView.setText(movie.getSynopsis());

        // Load the movie poster using Glide
        if (movie.getPosterUrl() != null) {
            Glide.with(context)
                    .load(movie.getPosterUrl())
                             // Optional: Error image
                    .into(posterView);
        } else {
            posterView.setImageResource(R.drawable.placeholder_image); // Default image if URL is null
        }

        return convertView;
    }


}

