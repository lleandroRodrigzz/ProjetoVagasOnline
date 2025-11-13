package com.example.vagasapp.configs;

import com.example.vagasapp.services.InteresseService;
import com.example.vagasapp.utils.IpUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfigInteresse{

    private final Retrofit retrofit;

    public RetrofitConfigInteresse() {
        //String IPv4 = IpUtils.getLocalIPv4();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/apis/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public InteresseService getInteressesService()
    {
        return this.retrofit.create(InteresseService.class);
    }

}
