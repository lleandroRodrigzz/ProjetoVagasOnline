package com.example.vagasapp.services;

import com.example.vagasapp.entities.Vaga;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VagaService {
    @GET("get-all")
    Call<List<Vaga>> getAll();
    @GET("get-one/{registro}")
    Call<Vaga> getOne(@Path("registro") String registro);
}
