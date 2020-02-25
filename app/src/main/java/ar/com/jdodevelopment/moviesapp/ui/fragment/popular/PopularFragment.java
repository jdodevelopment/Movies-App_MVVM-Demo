package ar.com.jdodevelopment.moviesapp.ui.fragment.popular;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ar.com.jdodevelopment.moviesapp.R;
import ar.com.jdodevelopment.moviesapp.databinding.FragmentPopularBinding;
import ar.com.jdodevelopment.moviesapp.databinding.FragmentUpcomingBinding;
import ar.com.jdodevelopment.moviesapp.model.Movie;
import ar.com.jdodevelopment.moviesapp.ui.activity.main.MainActivityViewModel;
import ar.com.jdodevelopment.moviesapp.ui.adapter.MovieAdapter;

public class PopularFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentPopularBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false);

        return binding.getRoot();
    }


}