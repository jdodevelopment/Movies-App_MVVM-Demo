package ar.com.jdodevelopment.moviesapp.ui.activity.moviedetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import ar.com.jdodevelopment.moviesapp.R;
import ar.com.jdodevelopment.moviesapp.databinding.ActivityMovieDetailBinding;
import ar.com.jdodevelopment.moviesapp.model.Movie;
import ar.com.jdodevelopment.moviesapp.util.Consts;

public class MovieDetailActivity extends AppCompatActivity {


    private Movie movie;

    private ActivityMovieDetailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie = (Movie) getIntent().getExtras().getSerializable(Consts.MOVIE_KEY);
        initDataBinding();
        bindViews();
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        binding.setLifecycleOwner(this);
    }

    private void bindViews(){
        Date date =  Date.valueOf(movie.getReleaseDate());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());
        binding.setFormattedDate(dateFormat.format(date));

        String posterUrl = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
        binding.setPosterUrl(posterUrl);

        String backdropUrl = "https://image.tmdb.org/t/p/w500" + movie.getBackdropPath();
        binding.setBackdropUrl(backdropUrl);

        binding.setMovie(movie);
    }
}
