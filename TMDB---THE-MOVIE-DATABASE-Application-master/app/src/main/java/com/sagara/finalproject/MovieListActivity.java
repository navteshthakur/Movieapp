package com.sagara.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieListActivity extends AppCompatActivity {

    private ListView listView;
    private MovieAdapter adapter;
    private ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this, movieList);
        listView.setAdapter(adapter);

        String query = getIntent().getStringExtra("query");
        fetchMovies(query);
    }

    private void fetchMovies(String query) {
        OkHttpClient client = new OkHttpClient();
        String apiUrl;
        if ("top_rated".equals(query)) {
            apiUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=269953fa1b73573a2d9e7a027d37813e";
        } else {
            apiUrl = "https://api.themoviedb.org/3/search/movie?api_key=269953fa1b73573a2d9e7a027d37813e&query=" + query;
        }

        Request request = new Request.Builder().url(apiUrl).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> Toast.makeText(MovieListActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseData = response.body().string();
                    parseMovies(responseData);
                } else {
                    runOnUiThread(() -> Toast.makeText(MovieListActivity.this, "No data found", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }

    private void parseMovies(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray resultsArray = jsonObject.getJSONArray("results");

            movieList.clear();

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject movieObject = resultsArray.getJSONObject(i);

                String title = movieObject.getString("title");
                String releaseDate = movieObject.optString("release_date", "N/A");
                double rating = movieObject.optDouble("vote_average", 0);
                String synopsis = movieObject.optString("overview", "No synopsis available");
                String posterPath = movieObject.optString("poster_path", null);

                // Construct the full poster URL
                String posterUrl = posterPath != null ? "https://image.tmdb.org/t/p/w500/" + posterPath : null;

                movieList.add(new Movie(title, releaseDate, rating, synopsis, posterUrl));
            }

            runOnUiThread(() -> adapter.notifyDataSetChanged());
        } catch (JSONException e) {
            runOnUiThread(() -> Toast.makeText(this, "Error parsing data", Toast.LENGTH_SHORT).show());
            Log.e("PARSE_ERROR", "Error parsing JSON", e);
        }
    }

}
