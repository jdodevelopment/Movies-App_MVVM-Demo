package ar.com.jdodevelopment.moviesapp.datasource;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import ar.com.jdodevelopment.moviesapp.database.movie.MovieDao;
import ar.com.jdodevelopment.moviesapp.model.Movie;
import ar.com.jdodevelopment.moviesapp.network.service.MovieService;


public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {


    private MovieDataSource dataSource;

    private LiveData<Boolean> loading;
    private LiveData<String> networkErrors;


    public MovieDataSourceFactory(MovieDao dao, MovieService service) {
        networkErrors = new MutableLiveData<>();
        dataSource = new MovieDataSource(dao, service);
        loading = dataSource.getLoading();
        networkErrors = dataSource.getNetworkErrors();
    }

    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        return dataSource;
    }


    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }
}