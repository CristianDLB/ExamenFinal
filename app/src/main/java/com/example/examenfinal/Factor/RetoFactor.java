package com.example.examenfinal.Factor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetoFactor {

    public static Retrofit LinkReto(){
        return new Retrofit.Builder()
                .baseUrl("https://628f6c570e69410599dc230c.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
