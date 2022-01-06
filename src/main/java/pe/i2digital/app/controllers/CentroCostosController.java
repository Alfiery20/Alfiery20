package pe.i2digital.app.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.i2digital.app.models.entity.CentroCostos;
import pe.i2digital.app.service.CentroCostosService;

import java.util.Objects;

@RequestMapping("/centrocostos")
@RestController(value = "centrocostos")

public class CentroCostosController {
    @Autowired
    private CentroCostosService service;
    //Leer: GET -> Tener en cuenta el RMM: Modelo Richardson
    @GetMapping("/")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(service.findAll()); //Respuesta 200
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> devolver(@PathVariable Integer id){
        return ResponseEntity.ok(service.findById(id)); //Respuesta 200
    }
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CentroCostos Entity){
        var ccDB =service.save(Entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(ccDB);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody CentroCostos Entity,@PathVariable Integer id){
        var ccDB = service.findById(id);
        if (Objects.isNull(ccDB)){
            return ResponseEntity.notFound().build();
        }else{
            ccDB.setNombre(Entity.getNombre());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(ccDB));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
