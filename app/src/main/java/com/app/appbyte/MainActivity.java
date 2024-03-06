package com.app.appbyte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.app.appbyte.api.ApiAdapter;
import com.app.appbyte.api.response.DonutResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<ArrayList<DonutResponse>> {

    private Button btnLogout;
    private ByteDataBase dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.logout();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        dbHelper = new ByteDataBase(this);


        Call<ArrayList<DonutResponse>> call = ApiAdapter.getApiService().getDonut();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<DonutResponse>> call, Response<ArrayList<DonutResponse>> response) {
        if (response.isSuccessful()) {
            ArrayList<DonutResponse> donutResponses = response.body();
            Log.d("onResponse Donut", "Size of donut => " + donutResponses.size());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<DonutResponse>> call, Throwable t) {

    }
}