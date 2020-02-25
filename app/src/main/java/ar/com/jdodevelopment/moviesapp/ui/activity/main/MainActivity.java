package ar.com.jdodevelopment.moviesapp.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ar.com.jdodevelopment.moviesapp.R;
import ar.com.jdodevelopment.moviesapp.databinding.ActivityMainBinding;
import ar.com.jdodevelopment.moviesapp.model.Movie;
import ar.com.jdodevelopment.moviesapp.ui.activity.moviedetail.MovieDetailActivity;
import ar.com.jdodevelopment.moviesapp.util.Consts;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initNavigationView();
        initObservers();
    }

    private void initNavigationView() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration
                .Builder(
                R.id.navigation_upcoming,
                R.id.navigation_popular,
                R.id.navigation_top_rated)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navigationView, navController);
    }

    private void initObservers() {
        viewModel.getSelectedMovie().observe(this, movie ->
                openDetailActivity(movie)
        );
    }

    private void openDetailActivity(Movie movie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(Consts.MOVIE_KEY, movie);
        startActivity(intent);
    }



}
