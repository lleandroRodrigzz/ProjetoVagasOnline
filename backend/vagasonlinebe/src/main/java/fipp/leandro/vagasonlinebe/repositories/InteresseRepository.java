package fipp.leandro.vagasonlinebe.repositories;

import fipp.leandro.vagasonlinebe.entities.Interesse;
import fipp.leandro.vagasonlinebe.entities.Candidato;
import fipp.leandro.vagasonlinebe.entities.Vaga;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InteresseRepository extends MongoRepository<Interesse, String> {
    List<Interesse> getAllByCandidato(Candidato candidato);

    List<Interesse> getAllByVaga(Vaga vaga);
}
