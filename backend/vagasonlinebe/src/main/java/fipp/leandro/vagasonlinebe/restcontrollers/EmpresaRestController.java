package fipp.leandro.vagasonlinebe.restcontrollers;

import fipp.leandro.vagasonlinebe.entities.Empresa;
import fipp.leandro.vagasonlinebe.services.EmpresaService;
import fipp.leandro.vagasonlinebe.util.Erro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "apis")
public class EmpresaRestController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("empresas/get-all")
    public ResponseEntity<Object> getAllEmpresas(){
       try{
           List<Empresa> empresas = empresaService.getAll();
           return ResponseEntity.ok(empresas);
       }
       catch (Exception e){
           Erro erro = new Erro("Erro ao recuperar empresas: " + e.getMessage());
           return ResponseEntity.badRequest().body(erro);
       }
    }
}
