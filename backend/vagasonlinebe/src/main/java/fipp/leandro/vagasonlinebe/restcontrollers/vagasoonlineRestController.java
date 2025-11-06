package fipp.leandro.vagasonlinebe.restcontrollers;

import fipp.leandro.vagasonlinebe.entities.Vaga;
import fipp.leandro.vagasonlinebe.services.VagasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "apis")
public class vagasoonlineRestController {
    @Autowired
    VagasService vagasService;

    @GetMapping("vagas/get-all")
    public ResponseEntity<Object> getAllVagas(){
        return ResponseEntity.ok(vagasService.getAll());
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
