package com.example.dudco.highjinro.Fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.dudco.highjinro.R;
import com.example.dudco.highjinro.databinding.FragmentSchoolBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SchoolFragment extends Fragment implements GoogleMap.OnMarkerClickListener{
    private FragmentSchoolBinding binding;

    static final LatLng SEOUL = new LatLng(37.56, 126.97);

    private GoogleMap googleMap;

    AQuery aq;
    String img;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentSchoolBinding.bind(getView());
        aq = new AQuery(getContext());

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 3);
        }

        binding.map.onCreate(savedInstanceState);

        binding.map.onResume();

        binding.map.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMarkerClickListener(SchoolFragment.this);
                aq.ajax("http://hacka.iwin247.kr/schools/",JSONArray.class, new AjaxCallback<JSONArray>(){
                    @Override
                    public void callback(String url, JSONArray objects, AjaxStatus status) {
                        if(objects!=null){

                            for(int i = 0; i < objects.length() ; i++){
                                try {
                                    JSONObject item = (JSONObject) objects.get(i);
                                    double x = item.getDouble("location_x");
                                    double y = item.getDouble("location_y");

                                    MarkerOptions options = new MarkerOptions();
                                    options.position(new LatLng(x, y));
                                    options.title(item.getString("name"));
                                    googleMap.addMarker(options);

                                    Log.d("dudco", item.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.school_layout, null);
        view.setLayoutParams(new FrameLayout.LayoutParams(500, 500));
        final TextView text = (TextView) view.findViewById(R.id.school_title);
        final ImageView alertimage = (ImageView) view.findViewById(R.id.school_logo);
        aq.ajax("http://hacka.iwin247.kr/schools/"+marker.getTitle(),JSONObject.class, new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                try {
                    Log.d("dudco", object.getString("img_url"));
                    img = object.getString("img_url");
                    Picasso.with(getContext()).load(img).into(alertimage);
                    text.setText(marker.getTitle());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        final AlertDialog alert = alertDialogBuilder.setView(view).create();
        view.findViewById(R.id.alert_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        alert.getWindow().setLayout(400, 300);
        alert.show();

        return true;
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
