package ar.com.jdodevelopment.moviesapp.network.service;


import ar.com.jdodevelopment.moviesapp.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {


    @GET("discover/movie")
    Call<MoviesResponse> discover(@Query("page") long page);


    @GET("movie/top_rated")
    Call<MoviesResponse> topRated(@Query("page") long page);


    @GET("movie/upcoming")
    Call<MoviesResponse> upcoming(@Query("page") long page);


}
