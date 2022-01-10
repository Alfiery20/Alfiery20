package pe.i2digital.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.i2digital.app.service.CuentaContableService;

@RequestMapping("/cuentacontable")
@RestController(value = "cuentacontable")
public class CuentaContableController {
    @Autowired
    private CuentaContableService service;

    @GetMapping("/")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(service.findAll()); //Respuesta 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> devolver(@PathVariable Integer id){
        return ResponseEntity.ok(service.findById(id)); //Respuesta 200
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<?> devolverPorNumero(@PathVariable String numero){
        return ResponseEntity.ok(service.findByNumeroStartingWithOrderById(numero)); //Respuesta 200
    }

    @GetMapping("/filtro-numero/1/{numero}")
    public ResponseEntity<?> buscarParteNumero(@PathVariable String numero){
        return ResponseEntity.ok(service.busquedaPersonalizadaNumero(numero)); //Respuesta 200
    }

    @GetMapping("/filtro-numero/2/{numero}")
    public ResponseEntity<?> buscarPersonalizadaNumero(@PathVariable String numero){
        return ResponseEntity.ok(service.findByNumeroStartingWithAndUsaDocumentoTrueOrderById(numero)); //Respuesta 200
    }

    @GetMapping("/filtro-numero/3/{numero}")
    public ResponseEntity<?> buscarNumeroOperacionTesoreria(@PathVariable String numero){
        return ResponseEntity.ok(service.busquedaNumeroOperacionTesoreria(numero)); //Respuesta 200
    }


}
