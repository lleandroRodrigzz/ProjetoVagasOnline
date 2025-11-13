package com.example.vagasapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vagasapp.entities.Candidato;

public class MainActivity extends AppCompatActivity {

    private EditText edtNome, edtCpf, edtEmail, edtTelefone, edtFormacao;
    private Button btnMostrarVagas;
    private Candidato candidato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.edtNome);
        edtCpf = findViewById(R.id.edtCpf);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtFormacao = findViewById(R.id.edtFormacao);
        btnMostrarVagas = findViewById(R.id.btnMostrarVagas);

        btnMostrarVagas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    candidato = new Candidato(
                            edtNome.getText().toString(),
                            edtCpf.getText().toString(),
                            edtEmail.getText().toString(),
                            edtTelefone.getText().toString(),
                            edtFormacao.getText().toString()
                    );

                    Intent intent = new Intent(MainActivity.this, VagasActivity.class);
                    intent.putExtra("nome", candidato.getNome());
                    intent.putExtra("cpf", candidato.getCpf());
                    intent.putExtra("email", candidato.getEmail());
                    intent.putExtra("telefone", candidato.getTelefone());
                    intent.putExtra("formacao", candidato.getFormacao());
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validarCampos() {
        if (edtNome.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o nome", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtCpf.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o CPF", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtEmail.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtTelefone.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o telefone", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (edtFormacao.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor, preencha a formação", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
