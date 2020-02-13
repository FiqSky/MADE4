package com.farzain.watchmovie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.farzain.watchmovie.R;
import com.farzain.watchmovie.Series;

public class SeriesInfoActivity extends AppCompatActivity {

    public static final String EXTRA_SERIES = "extra_series";
//    public static String url = "https://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_info);

        TextView movieRelease = findViewById(R.id.content_release);
        TextView movieName = findViewById(R.id.content_name);
        TextView movieSynopsis = findViewById(R.id.content_sinopsis);
        ImageView moviePoster = findViewById(R.id.moviePoster);

        ProgressBar progressBar = findViewById(R.id.progressBarSeries);
        progressBar.setVisibility(View.VISIBLE);

        Series dataSeries = getIntent().getParcelableExtra(EXTRA_SERIES);
//        String url_image = url + dataSeries.getPhoto();

        Series dataMovie = getIntent().getParcelableExtra(EXTRA_SERIES);
        movieName.setText(dataMovie.getName());
        movieSynopsis.setText(dataMovie.getSynopsis());
        movieRelease.setText(dataMovie.getRelease());
        Glide.with(this)
                .load(dataSeries.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(moviePoster);
        progressBar.setVisibility(View.GONE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorite_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
