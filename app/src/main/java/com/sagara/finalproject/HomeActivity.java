package com.sagara.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button searchMoviesButton, topRatedMoviesButton, trendingMoviesButton, favoritesButton;
    private ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchMoviesButton = findViewById(R.id.searchMoviesButton);
        topRatedMoviesButton = findViewById(R.id.topRatedMoviesButton);
        trendingMoviesButton = findViewById(R.id.trendingMoviesButton);
        favoritesButton = findViewById(R.id.favoritesButton);
        settingsButton = findViewById(R.id.settingsButton);

        // Mock user data

        searchMoviesButton.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_click);
            v.startAnimation(animation);
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);

            startActivity(intent);

        });

        topRatedMoviesButton.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_click);
            v.startAnimation(animation);
            Intent intent = new Intent(HomeActivity.this, MovieListActivity.class);
            intent.putExtra("query", "top_rated");
            startActivity(intent);

            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        });

        trendingMoviesButton.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_click);
            v.startAnimation(animation);
            Intent intent = new Intent(HomeActivity.this, MovieListActivity.class);
            intent.putExtra("query", "trending");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        });

        favoritesButton.setOnClickListener(v -> {
            // Placeholder for FavoritesActivity
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_click);
            v.startAnimation(animation);
            Intent intent = new Intent(HomeActivity.this, FavoritesActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        });

        settingsButton.setOnClickListener(v -> {
            // Placeholder for SettingsActivity
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_click);
            v.startAnimation(animation);
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        });
    }
}