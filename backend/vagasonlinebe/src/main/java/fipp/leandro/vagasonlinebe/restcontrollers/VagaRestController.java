package fipp.leandro.vagasonlinebe.restcontrollers;

import fipp.leandro.vagasonlinebe.entities.Vaga;
import fipp.leandro.vagasonlinebe.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "apis")
public class VagaRestController {
    @Autowired
    VagaService vagasService;

    @GetMapping("vagas/get-all")
    public ResponseEntity<Object> getAllVagas(){
        return ResponseEntity.ok(vagasService.getAll());
    }

    @PostMapping("vagas")
    public ResponseEntity<Object> save(@RequestBody Vaga vaga) {
        Vaga novaVaga = vagasService.add(vaga);
        if(novaVaga != null) {
            return ResponseEntity.ok(novaVaga);
        }
        return ResponseEntity.badRequest().body("Erro ao incluir vaga!");
    }

    @PutMapping("vagas")
    public ResponseEntity<Object> update(@RequestBody Vaga vaga) {
        Vaga vaga1 = vagasService.getOne(vaga.getRegistro());
        if(vaga1 != null) {
            vaga.setId(vaga1.getId());
            vagasService.add(vaga);
            return ResponseEntity.ok("Vaga de registro " + vaga.getRegistro() + " foi atualizada com sucesso!!!");
        }
        return ResponseEntity.badRequest().body("Erro ao atualizar vaga de registro: " + vaga.getRegistro());
    }

    @DeleteMapping("vagas/{registro}")
    public ResponseEntity<Object> delete(@PathVariable String registro) {
        boolean flag = vagasService.delete(registro);
        if (flag)
            return ResponseEntity.ok("Vaga excluída com sucesso!");
        return ResponseEntity.badRequest().body("Erro ao excluir vaga!");
    }

    @GetMapping("vagas/get-one/{registro}")
    public ResponseEntity<Object> getOneVaga(@PathVariable String registro){
        Vaga vaga = vagasService.getOne(registro);
        if (vaga != null) {
            return ResponseEntity.ok(vaga);
        }
        else {
            //404
            return ResponseEntity.notFound().build();
        }
    }
}
