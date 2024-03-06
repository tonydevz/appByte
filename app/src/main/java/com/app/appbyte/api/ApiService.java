package com.app.appbyte.api;

import com.app.appbyte.api.response.DonutResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("v1/eeced007-6b29-4c9d-ab63-c115a990d927")
    Call<ArrayList<DonutResponse>> getDonut();
}
