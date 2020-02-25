package ar.com.jdodevelopment.moviesapp.database.movie;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ar.com.jdodevelopment.moviesapp.model.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {


    private static MovieDatabase instance;

    public abstract MovieDao dao();

    private LiveData<PagedList<Movie>> pagedListLiveData;

    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = buildDatabase(context.getApplicationContext());
        }
        return instance;
    }

    private static MovieDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                MovieDatabase.class, "movie")
                .fallbackToDestructiveMigration()
                .build();
    }


    public LiveData<PagedList<Movie>> getPagedListLiveData() {
        return pagedListLiveData;
    }

}