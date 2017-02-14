package com.example.dudco.highjinro.Fragment;


import android.databinding.DataBindingComponent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dudco.highjinro.R;
import com.example.dudco.highjinro.databinding.FragmentTalkBinding;


public class TalkFragment extends Fragment {
    private FragmentTalkBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_talk, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentTalkBinding.bind(getView());
    }

}
