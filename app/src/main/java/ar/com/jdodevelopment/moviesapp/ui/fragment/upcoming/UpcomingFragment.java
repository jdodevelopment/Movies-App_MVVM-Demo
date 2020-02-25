package ar.com.jdodevelopment.moviesapp.ui.fragment.upcoming;

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
import ar.com.jdodevelopment.moviesapp.model.Movie;
import ar.com.jdodevelopment.moviesapp.ui.activity.main.MainActivityViewModel;
import ar.com.jdodevelopment.moviesapp.ui.adapter.MovieAdapter;
import ar.com.jdodevelopment.moviesapp.databinding.FragmentUpcomingBinding;

public class UpcomingFragment extends Fragment {


    private FragmentUpcomingBinding binding;

    private UpcomingViewModel viewModel;
    private MainActivityViewModel mainActivityViewModel;

    private MovieAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initViewModels();
        initDataBinding(inflater, container);
        initAdapter();
        initObservers();
        return binding.getRoot();
    }

    private void initViewModels() {
        viewModel = new ViewModelProvider(this).get(UpcomingViewModel.class);
        mainActivityViewModel =  new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
    }

    private void initDataBinding(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming, container, false);
        binding.setLifecycleOwner(this);
    }

    private void initObservers() {
        viewModel.networkErrors.observe(getViewLifecycleOwner(), error ->
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show()
        );
        viewModel.loading.observe(getViewLifecycleOwner(), loading -> {}
            //TODO show/hide loading indicator
        );
        viewModel.pagedListLiveData.observe(getViewLifecycleOwner(), list ->
                adapter.submitList(list)
        );
    }

    private void initAdapter() {
        adapter = new MovieAdapter((view, obj) -> {
            openDetailFragment(view, obj);
        });
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }

    private void openDetailFragment(View view, Movie obj) {
        mainActivityViewModel.getSelectedMovie().postValue(obj);
    }


}