package com.example.dudco.highjinro.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.dudco.highjinro.Datas.SchoolData;
import com.example.dudco.highjinro.Datas.SchoolService;
import com.example.dudco.highjinro.R;
import com.example.dudco.highjinro.databinding.FragmentSchoolBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SchoolFragment extends Fragment {
    private FragmentSchoolBinding binding;

    static final LatLng SEOUL = new LatLng(37.56, 126.97);

    private GoogleMap googleMap;

    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://hacka.iwin247.kr").addConverterFactory(GsonConverterFactory.create()).build();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentSchoolBinding.bind(getView());
        final AQuery aq = new AQuery(getContext());

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

    class getSchoolAsynTask extends AsyncTask<Void, Void, Void>{
        android.support.v7.app.AlertDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            dialog = alertDialogBuilder.setTitle("로딩").setMessage("로딩 중 입니다..").create();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... params) {
            retrofit.create(SchoolService.class).getAllSchool().enqueue(new Callback<SchoolData>() {
                @Override
                public void onResponse(Call<SchoolData> call, Response<SchoolData> response) {
                    Log.d("dudco", response.body() + "");
                }

                @Override
                public void onFailure(Call<SchoolData> call, Throwable t) {

                }
            });
            return null;
        }
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
