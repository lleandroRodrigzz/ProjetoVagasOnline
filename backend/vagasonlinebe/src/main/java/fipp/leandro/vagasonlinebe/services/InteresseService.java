package fipp.leandro.vagasonlinebe.services;

import com.mongodb.client.MongoDatabase;
import fipp.leandro.vagasonlinebe.entities.Interesse;
import fipp.leandro.vagasonlinebe.entities.Candidato;
import fipp.leandro.vagasonlinebe.entities.Vaga;
import fipp.leandro.vagasonlinebe.repositories.InteresseRepository;
import fipp.leandro.vagasonlinebe.util.Conexao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteresseService {

    private MongoDatabase database;
    private InteresseRepository repository;

    public InteresseService() {
        this.database = Conexao.getInstance().getDatabase();
    }

    public InteresseService(InteresseRepository repository) {
        this.repository = repository;
    }

    public Interesse add(Interesse interesse) {
        return repository.save(interesse);
    }

    public List<Interesse> getAllByCandidato(Candidato candidato) {
        return repository.getAllByCandidato(candidato);
    }

    public List<Interesse> getAllByVaga(Vaga vaga) {
        return repository.getAllByVaga(vaga);
    }
}
