package fipp.leandro.vagasonlinebe.restcontrollers;

import fipp.leandro.vagasonlinebe.entities.Cargo; // <-- Importe
import fipp.leandro.vagasonlinebe.services.CargoService;
import fipp.leandro.vagasonlinebe.util.Erro; // <-- Importe
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // <-- Importe
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List; // <-- Importe

@CrossOrigin
@RestController
@RequestMapping(value = "apis")
public class CargoRestController {
    @Autowired
    private CargoService cargoService;

    @GetMapping("cargos/get-all")
    public ResponseEntity<Object> getAllCargos(){
        try {

            List<Cargo> cargos = cargoService.getAll();
            return ResponseEntity.ok(cargos);
        }
        catch (Exception e) {
            Erro erro = new Erro("Erro ao recuperar cargos: " + e.getMessage());
            return ResponseEntity.badRequest().body(erro);
        }
    }
}