package pe.i2digital.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.i2digital.app.service.SucursalService;

@RequestMapping("/sucursal")
@RestController(value = "sucursal")
public class SucursalController {
    @Autowired
    private SucursalService service;

    @GetMapping("/")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(service.findAll()); //Respuesta 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> devolver(@PathVariable Integer id){
        return ResponseEntity.ok(service.findById(id)); //Respuesta 200
    }
}
