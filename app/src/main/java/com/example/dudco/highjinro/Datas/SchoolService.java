package com.example.dudco.highjinro.Datas;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dudco on 2017. 2. 15..
 */

public interface SchoolService {
    @GET("schools")
    Call<SchoolData> getAllSchool();
}
