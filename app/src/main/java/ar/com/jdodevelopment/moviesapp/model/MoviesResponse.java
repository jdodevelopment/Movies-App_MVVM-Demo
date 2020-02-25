package ar.com.jdodevelopment.moviesapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ar.com.jdodevelopment.moviesapp.model.Movie;

public class MoviesResponse {


    @SerializedName("page")
    private long page;

    @SerializedName("results")
    private List<Movie> results;

    @SerializedName("total_pages")
    private long totalPages;

    @SerializedName("total_results")
    private long totalResults;

    public long getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}