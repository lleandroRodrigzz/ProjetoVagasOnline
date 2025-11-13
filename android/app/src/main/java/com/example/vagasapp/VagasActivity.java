package com.example.vagasapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vagasapp.VagaAdapter;
import com.example.vagasapp.configs.RetrofitConfigVaga;
import com.example.vagasapp.entities.Candidato;
import com.example.vagasapp.entities.Vaga;
import com.example.vagasapp.services.VagaService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VagasActivity extends AppCompatActivity {

    private ListView listViewVagas;
    private ProgressBar progressBar;
    private VagaAdapter vagaAdapter;
    private List<Vaga> vagasList;
    private Candidato candidato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vagas);

        listViewVagas = findViewById(R.id.listViewVagas);
        progressBar = findViewById(R.id.progressBar);

        candidato = new Candidato(
                getIntent().getStringExtra("nome"),
                getIntent().getStringExtra("cpf"),
                getIntent().getStringExtra("email"),
                getIntent().getStringExtra("telefone"),
                getIntent().getStringExtra("formacao")
        );

        vagasList = new ArrayList<>();
        vagaAdapter = new VagaAdapter(this, vagasList, candidato);
        listViewVagas.setAdapter(vagaAdapter);

        carregarVagas();
    }

    private void carregarVagas() {
        progressBar.setVisibility(View.VISIBLE);

        RetrofitConfigVaga retrofitConfig = new RetrofitConfigVaga();
        VagaService vagaService = retrofitConfig.getVagasService();

        Call<List<Vaga>> call = vagaService.getAll();
        call.enqueue(new Callback<List<Vaga>>() {
            @Override
            public void onResponse(Call<List<Vaga>> call, Response<List<Vaga>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    vagasList.clear();
                    vagasList.addAll(response.body());
                    vagaAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(VagasActivity.this, "Erro ao carregar vagas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Vaga>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(VagasActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
