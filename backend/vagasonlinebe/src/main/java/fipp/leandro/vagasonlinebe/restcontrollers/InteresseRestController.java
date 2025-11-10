package fipp.leandro.vagasonlinebe.restcontrollers;

import fipp.leandro.vagasonlinebe.entities.Interesse;
import fipp.leandro.vagasonlinebe.entities.Candidato;
import fipp.leandro.vagasonlinebe.entities.Vaga;
import fipp.leandro.vagasonlinebe.services.InteresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "apis")
public class InteresseRestController {

    @Autowired
    InteresseService interesseService;

    @GetMapping("interesses-pessoa")
    ResponseEntity<Object> getAllByCandidato(@RequestBody Candidato candidato) {
        List<Interesse> interesses = interesseService.getAllByCandidato(candidato);
        if(interesses.isEmpty())
            return ResponseEntity.badRequest().body("Não há interesses para: " + candidato.getNome());
        return ResponseEntity.ok(interesses);
    }

    @GetMapping("interesses-vaga")
    ResponseEntity<Object> getAllByVaga(@RequestBody Vaga vaga) {
        List<Interesse> interesses = interesseService.getAllByVaga(vaga);
        if(interesses.isEmpty())
            return ResponseEntity.badRequest().body("Não há interesses para a vaga: " + vaga.getCargo());
        return ResponseEntity.ok(interesses);
    }

    @PostMapping("interesses")
    ResponseEntity<Object> gravar(@RequestBody Interesse interesse) {
        Interesse novoInteresse = interesseService.add(interesse);
        if(novoInteresse != null)
            return ResponseEntity.ok(interesse);
        return ResponseEntity.badRequest().body("Erro ao cadastrar interesse!");
    }
}
