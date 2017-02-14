package com.example.dudco.highjinro.Fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dudco.highjinro.R;
import com.example.dudco.highjinro.RoundedAvatarDrawable;
import com.example.dudco.highjinro.databinding.FragmentMyInfoBinding;

public class MyInfoFragment extends Fragment {
    private FragmentMyInfoBinding binding;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentMyInfoBinding.bind(getView());
        BitmapDrawable drawable1 = (BitmapDrawable) ContextCompat.getDrawable(getContext(), R.drawable.default_profile);
        binding.myinfoProfile.setImageBitmap(new RoundedAvatarDrawable(drawable1.getBitmap()).getBitmap());
        BitmapDrawable drawable2 = (BitmapDrawable) ContextCompat.getDrawable(getContext(), R.drawable.camera);
        binding.myinfoCamera.setImageBitmap(new RoundedAvatarDrawable(drawable2.getBitmap()).getBitmap());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_info, container, false);
    }
}
