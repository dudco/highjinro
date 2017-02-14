package com.example.dudco.highjinro.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dudco.highjinro.R;
import com.example.dudco.highjinro.databinding.FragmentSchoolBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;


public class SchoolFragment extends Fragment {
    private FragmentSchoolBinding binding;

    static final LatLng SEOUL = new LatLng(37.56, 126.97);

    private GoogleMap googleMap;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentSchoolBinding.bind(getView());

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 3);
        }

        binding.map.onCreate(savedInstanceState);

        binding.map.onResume();

        binding.map.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_school, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.map.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.map.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        binding.map.onLowMemory();
    }
}
