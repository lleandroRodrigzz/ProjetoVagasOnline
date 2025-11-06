package fipp.leandro.vagasonlinebe.restcontrollers;

import fipp.leandro.vagasonlinebe.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "apis")
public class EmpresaRestController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("empresas/get-all")
    public ResponseEntity<Object> getAllEmpresas(){
        return ResponseEntity.ok(empresaService.getAll());
    }
}
