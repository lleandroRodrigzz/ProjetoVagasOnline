package fipp.leandro.vagasonlinebe.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "interesses")
public class Interesse {
    Candidato candidato;
    Vaga vaga;

    public void Interesse() {
    }

    public Interesse(Candidato candidato, Vaga vaga) {
        this.candidato = candidato;
        this.vaga = vaga;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }
}
