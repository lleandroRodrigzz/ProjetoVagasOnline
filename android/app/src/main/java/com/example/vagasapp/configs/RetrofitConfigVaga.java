package com.example.vagasapp.configs;

import com.example.vagasapp.services.VagaService;
import com.example.vagasapp.utils.IpUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfigVaga {

    private final Retrofit retrofit;

    public RetrofitConfigVaga() {
        //String IPv4 = IpUtils.getLocalIPv4();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/apis/vagas/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public VagaService getVagasService()
    {
        return this.retrofit.create(VagaService.class);
    }
}
