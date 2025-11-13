package com.example.vagasapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vagasapp.R;
import com.example.vagasapp.configs.RetrofitConfigInteresse;
import com.example.vagasapp.entities.Candidato;
import com.example.vagasapp.entities.Interesse;
import com.example.vagasapp.entities.Vaga;
import com.example.vagasapp.services.InteresseService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VagaAdapter extends BaseAdapter {

    private Context context;
    private List<Vaga> vagas;
    private Candidato candidato;
    private LayoutInflater inflater;

    public VagaAdapter(Context context, List<Vaga> vagas, Candidato candidato) {
        this.context = context;
        this.vagas = vagas;
        this.candidato = candidato;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return vagas.size();
    }

    @Override
    public Object getItem(int position) {
        return vagas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_vaga, parent, false);
            holder = new ViewHolder();
            holder.txtCargo = convertView.findViewById(R.id.txtCargo);
            holder.txtEmpresa = convertView.findViewById(R.id.txtEmpresa);
            holder.txtCidade = convertView.findViewById(R.id.txtCidade);
            holder.txtFormacao = convertView.findViewById(R.id.txtFormacao);
            holder.txtRemuneracao = convertView.findViewById(R.id.txtRemuneracao);
            holder.btnCandidatar = convertView.findViewById(R.id.btnCandidatar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Vaga vaga = vagas.get(position);

        holder.txtCargo.setText(vaga.getCargo());
        holder.txtEmpresa.setText(vaga.getEmpresa() != null ? vaga.getEmpresa().getNome_fantasia() : "N/A");
        holder.txtCidade.setText(vaga.getCidade() + " - " + vaga.getEstado());
        holder.txtFormacao.setText("Formação: " + vaga.getFormacao());
        holder.txtRemuneracao.setText("R$ " + vaga.getRemuneracao());

        holder.btnCandidatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                candidatarVaga(vaga);
            }
        });

        return convertView;
    }

    private void candidatarVaga(Vaga vaga) {
        Interesse interesse = new Interesse(candidato, vaga);

        RetrofitConfigInteresse retrofitConfig = new RetrofitConfigInteresse();
        InteresseService interesseService = retrofitConfig.getInteressesService();

        Call<Interesse> call = interesseService.addInteresse(interesse);
        call.enqueue(new Callback<Interesse>() {
            @Override
            public void onResponse(Call<Interesse> call, Response<Interesse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Candidatura realizada com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Erro ao se candidatar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Interesse> call, Throwable t) {
                Toast.makeText(context, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    static class ViewHolder {
        TextView txtCargo;
        TextView txtEmpresa;
        TextView txtCidade;
        TextView txtFormacao;
        TextView txtRemuneracao;
        Button btnCandidatar;
    }
}
