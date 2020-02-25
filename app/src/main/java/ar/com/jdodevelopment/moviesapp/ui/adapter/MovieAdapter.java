package ar.com.jdodevelopment.moviesapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import ar.com.jdodevelopment.moviesapp.BR;
import ar.com.jdodevelopment.moviesapp.R;
import ar.com.jdodevelopment.moviesapp.databinding.MovieItemBinding;
import ar.com.jdodevelopment.moviesapp.model.Movie;
import ar.com.jdodevelopment.moviesapp.ui.adapter.listener.OnItemClickListener;


public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.MovieViewHolder> {


    private OnItemClickListener<Movie> onItemClickListener;


    public MovieAdapter(OnItemClickListener<Movie> onItemClickListener) {
        super(ITEM_CALLBACK);
        this.onItemClickListener = onItemClickListener;
    }

    private static final DiffUtil.ItemCallback<Movie> ITEM_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return newItem.getId() == oldItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getOriginalTitle().equals(newItem.getOriginalTitle());
        }
    };


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MovieItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.movie_item, viewGroup, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder viewHolder, int index) {
        Movie movie = getItem(index);
        if(movie != null){
            viewHolder.bindTo(movie, onItemClickListener);
        }
    }


    class MovieViewHolder extends RecyclerView.ViewHolder {


        private MovieItemBinding binding;

        MovieViewHolder(@NonNull MovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(Movie movie, OnItemClickListener<Movie> onItemClickListener) {
            String imageUrl = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
            binding.setVariable(BR.imageUrl, imageUrl);
            binding.setMovie(movie);
            binding.cardView.setOnClickListener(view ->
                    onItemClickListener.onItemClick(view, movie)
            );
        }

    }

}
