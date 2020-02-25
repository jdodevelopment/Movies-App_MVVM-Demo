package ar.com.jdodevelopment.moviesapp.ui.fragment.toprated;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import ar.com.jdodevelopment.moviesapp.R;
import ar.com.jdodevelopment.moviesapp.databinding.FragmentTopRatedBinding;

public class TopRatedFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentTopRatedBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_rated, container, false);

        return binding.getRoot();
    }

}