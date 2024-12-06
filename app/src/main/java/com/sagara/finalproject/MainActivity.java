package com.sagara.finalproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText searchInput;
    private Button searchButton, topRatedButton;
    private ListView listView;
    private MovieAdapter adapter;
    private ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchInput = findViewById(R.id.searchInput);
        searchButton = findViewById(R.id.searchButton);
        topRatedButton = findViewById(R.id.topRatedButton);
        listView = findViewById(R.id.listView);

        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this, movieList);
        listView.setAdapter(adapter);

        searchButton.setOnClickListener(v -> {
            String query = searchInput.getText().toString();
            if (!query.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, MovieListActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Enter a movie name", Toast.LENGTH_SHORT).show();
            }
        });

        topRatedButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MovieListActivity.class);
            intent.putExtra("query", "top_rated");
            startActivity(intent);
        });
    }
}