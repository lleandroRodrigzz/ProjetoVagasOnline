package fipp.leandro.vagasonlinebe.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cargos")
public class Cargo {
    private String nome;

    public Cargo(String nome) {
        this.nome = nome;
    }

    public Cargo() {
        this("");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
