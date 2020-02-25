package ar.com.jdodevelopment.moviesapp.ui.activity.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ar.com.jdodevelopment.moviesapp.model.Movie;

public class MainActivityViewModel extends ViewModel {


    MutableLiveData<Movie> selectedMovie;


    public MainActivityViewModel(){
        selectedMovie = new MutableLiveData<>();
    }


    public MutableLiveData<Movie> getSelectedMovie() {
        return selectedMovie;
    }
}
