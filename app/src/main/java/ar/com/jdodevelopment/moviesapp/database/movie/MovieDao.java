package ar.com.jdodevelopment.moviesapp.database.movie;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ar.com.jdodevelopment.moviesapp.model.Movie;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Movie> list);


    @Query("DELETE FROM movie")
    void deleteAll();

    @Query("SELECT * FROM movie ORDER BY id DESC LIMIT :pageSize OFFSET :page ")
    List<Movie> getAll(long pageSize, long page);

}

