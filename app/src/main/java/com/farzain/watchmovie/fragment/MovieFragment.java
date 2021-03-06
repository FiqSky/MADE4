package com.farzain.watchmovie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.farzain.watchmovie.Movie;
import com.farzain.watchmovie.R;
import com.farzain.watchmovie.adapter.ListMovieAdapter;
import com.farzain.watchmovie.viewmodel.MovieViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private RecyclerView rvMovies;
    private ArrayList<Movie> list = new ArrayList<>();
    private MovieViewModel movieViewModel;
    private ListMovieAdapter adapter;
    private ProgressBar progressBar;
    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movie) {
            if (movie != null) {
                adapter.setData(movie);
            }

            Loading(false);

        }
    };

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        progressBar = view.findViewById(R.id.progressBar);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        adapter = new ListMovieAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(getViewLifecycleOwner(), getMovie);
        movieViewModel.setMovies("EXTRA_MOVIE");

        Loading(true);
    }

    private void Loading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
