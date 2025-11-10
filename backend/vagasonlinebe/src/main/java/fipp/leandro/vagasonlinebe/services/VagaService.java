package fipp.leandro.vagasonlinebe.services;

import fipp.leandro.vagasonlinebe.entities.Vaga;
import fipp.leandro.vagasonlinebe.repositories.VagaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    private VagaRepository repository;

    public VagaService(VagaRepository repository) {
        this.repository = repository;
    }

    public Vaga add(Vaga vaga) {
        return repository.save(vaga);
    }

    public boolean delete(String registro) {
        try {
            Vaga vaga = repository.findByRegistro(registro);
            if(vaga != null) {
                repository.deleteByRegistro(registro);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Vaga> getAll() {
        return repository.findAll();
    }

    public Vaga getOne(String registro) {
        Vaga vaga = repository.findByRegistro(registro);
        if(vaga != null)
            return vaga;
        return null;
    }
}
