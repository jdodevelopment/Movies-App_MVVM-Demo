package ar.com.jdodevelopment.moviesapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import ar.com.jdodevelopment.moviesapp.database.movie.MovieDatabase;
import ar.com.jdodevelopment.moviesapp.datasource.MovieDataSourceFactory;
import ar.com.jdodevelopment.moviesapp.model.Movie;
import ar.com.jdodevelopment.moviesapp.network.RetrofitServiceGenerator;
import ar.com.jdodevelopment.moviesapp.network.service.MovieService;
import ar.com.jdodevelopment.moviesapp.util.Consts;

public class MovieRepository {


    private MovieDatabase database;
    private MovieService service;


    private LiveData<Boolean> loading;
    private LiveData<String> networkErrors;
    private LiveData<PagedList<Movie>> pagedListLiveData;


    public MovieRepository(Application application) {
        database = MovieDatabase.getInstance(application);
        service = RetrofitServiceGenerator.create(MovieService.class);
        buildLivePagedList();
    }

    private void buildLivePagedList() {
        MovieDataSourceFactory dataSourceFactory = new MovieDataSourceFactory(database.dao(), service);
        PagedList.Config pagedListConfig = new PagedList.Config.Builder()
                .setPageSize(Consts.PAGE_SIZE)
                .setEnablePlaceholders(true)
                .build();

        networkErrors = dataSourceFactory.getNetworkErrors();
        loading = dataSourceFactory.getLoading();
        pagedListLiveData = new LivePagedListBuilder<>(dataSourceFactory, pagedListConfig).build();
    }


    /**
     * LiveData Getters
     */

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }

    public LiveData<PagedList<Movie>> getPagedListLiveData() {
        return pagedListLiveData;
    }


}
