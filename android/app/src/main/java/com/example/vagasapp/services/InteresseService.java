package com.example.vagasapp.services;

import com.example.vagasapp.entities.Interesse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InteresseService {
    @POST("interesses")
    Call<Interesse> addInteresse(@Body Interesse interesses);
}
