package ar.com.jdodevelopment.moviesapp.ui.fragment.upcoming;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;


import ar.com.jdodevelopment.moviesapp.model.Movie;
import ar.com.jdodevelopment.moviesapp.repository.MovieRepository;

public class UpcomingViewModel extends AndroidViewModel {


    private MovieRepository repository;



    LiveData<Boolean> loading;
    LiveData<String> networkErrors;
    LiveData<PagedList<Movie>> pagedListLiveData;


    public UpcomingViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        initLiveDataFields();
    }

    private void initLiveDataFields() {
        loading = repository.getLoading();
        networkErrors = repository.getNetworkErrors();
        pagedListLiveData = repository.getPagedListLiveData();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }

}