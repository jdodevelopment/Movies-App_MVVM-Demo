package ar.com.jdodevelopment.moviesapp.datasource;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ar.com.jdodevelopment.moviesapp.database.movie.MovieDao;
import ar.com.jdodevelopment.moviesapp.model.Movie;
import ar.com.jdodevelopment.moviesapp.model.MoviesResponse;
import ar.com.jdodevelopment.moviesapp.network.service.MovieService;
import ar.com.jdodevelopment.moviesapp.util.Consts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {


    private final MutableLiveData<Boolean> loading;
    private final MutableLiveData<String> networkErrors;

    private MovieService service;
    private MovieDao dao;


    public MovieDataSource(MovieDao dao, MovieService service) {
        this.dao = dao;
        this.service = service;
        loading = new MutableLiveData<>(false);
        networkErrors = new MutableLiveData<>();
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        Integer currentPage = Consts.FISRT_PAGE;
        loading.postValue(true);
        service.discover(Consts.FISRT_PAGE)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<MoviesResponse> call, @NotNull Response<MoviesResponse> response) {
                        loading.postValue(false);
                        if (response.isSuccessful()) {
                            List<Movie> list = response.body().getResults();
                            callback.onResult(list, null, currentPage + 1);
                            AsyncTask.execute(() -> dao.insert(list)); // Saved in cache
                        } else {
                            loadInitialFromCache(callback, currentPage + 1);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                        loading.postValue(false);
                        loadInitialFromCache(callback, currentPage + 1);
                    }
                });
    }

    private void loadInitialFromCache(LoadInitialCallback<Integer, Movie> callback, Integer currentPage) {
        networkErrors.postValue("Datos cargados desde la cache"); //FIXME implementar mensajes mas convenientes
        AsyncTask.execute(() -> {
                    List<Movie> list = dao.getAll(Consts.PAGE_SIZE, currentPage * Consts.PAGE_SIZE);
                    callback.onResult(list, null, currentPage + 1);
                }
        );
    }


    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        Integer currentPage = params.key;
        loading.postValue(true);
        service.discover(currentPage)
                .enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<MoviesResponse> call, @NotNull Response<MoviesResponse> response) {
                        loading.postValue(false);
                        if (response.isSuccessful()) {
                            List<Movie> list = response.body().getResults();
                            callback.onResult(list, currentPage + 1);
                            AsyncTask.execute(() -> dao.insert(list));
                        } else {
                            loadFromCahce(callback, currentPage);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MoviesResponse> call, @NonNull Throwable t) {
                        loading.postValue(false);
                        loadFromCahce(callback, currentPage);
                    }
                });

    }


    private void loadFromCahce(LoadCallback<Integer, Movie> callback, Integer currentPage) {
        networkErrors.postValue("Datos cargados desde la cache"); //FIXME implementar mensajes mas convenientes
        AsyncTask.execute(() -> {
                    List<Movie> list = dao.getAll(Consts.PAGE_SIZE, currentPage * Consts.PAGE_SIZE);
                    callback.onResult(list, currentPage + 1);
                }
        );
    }


    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }


    public MutableLiveData<String> getNetworkErrors() {
        return networkErrors;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

}
