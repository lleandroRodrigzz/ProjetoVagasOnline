package fipp.leandro.vagasonlinebe.repositories;

import fipp.leandro.vagasonlinebe.entities.Vaga;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VagaRepository extends MongoRepository<Vaga, String> {
    Vaga getByRegistro(String registro);

    void deleteByRegistro(String registro);

    Vaga findByRegistro(String registro);
}
