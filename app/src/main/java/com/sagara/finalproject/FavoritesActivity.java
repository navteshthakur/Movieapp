package com.sagara.finalproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private ListView favoritesListView;
    private MovieAdapter adapter;
    private ArrayList<Movie> favoriteMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favoritesListView = findViewById(R.id.favoritesListView);

        // Load favorite movies
        favoriteMovies = loadFavoriteMovies();

        // Check if the list is empty
        if (favoriteMovies.isEmpty()) {
            Toast.makeText(this, "No favorite movies found!", Toast.LENGTH_SHORT).show();
        }

        // Set up the adapter
        adapter = new MovieAdapter(this, favoriteMovies);
        favoritesListView.setAdapter(adapter);
    }

    private ArrayList<Movie> loadFavoriteMovies() {
        SharedPreferences preferences = getSharedPreferences("FavoritesPrefs", MODE_PRIVATE);
        String json = preferences.getString("favoriteMovies", "[]");
        Type type = new TypeToken<ArrayList<Movie>>() {}.getType();
        return new Gson().fromJson(json, type);
    }
}