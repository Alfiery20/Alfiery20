package pe.i2digital.app.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.request.CuentaContableRequest;
import pe.i2digital.app.service.CuentaContableService;

import java.util.List;

@Api(tags = "API - Cuenta Contable", value = "API CUENTA CONTABLE", description = "Se encarga de procesos/mantenimiento de la tabla maestra cuenta contable")
@RequestMapping("/cuentacontable")
@RestController(value = "cuentacontable")
public class CuentaContableController {
    @Autowired
    private CuentaContableService service;

    @ApiOperation(value = "Listar toda la tabla Cuenta Contable", notes = "Todos los atributos de cuenta contable")
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CuentaContable> listar() {
        return service.findAll(); //Respuesta 200
    }

    /*
    @GetMapping("/")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.findAll()); //Respuesta 200
    }
    */
    @Operation(summary = "Devuelve la cuenta contable a partir de un numero ")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> devolver(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id)); //Respuesta 200
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<?> devolverPorNumero(@PathVariable String numero) {
        return ResponseEntity.ok(service.findByNumeroStartingWithOrderById(numero)); //Respuesta 200
    }

    @GetMapping("/filtro-numero/1/{numero}")
    public ResponseEntity<?> buscarParteNumero(@PathVariable String numero) {
        return ResponseEntity.ok(service.busquedaPersonalizadaNumero(numero)); //Respuesta 200
    }

    @GetMapping("/filtro-numero/2/{numero}")
    public ResponseEntity<?> buscarPersonalizadaNumero(@PathVariable String numero) {
        return ResponseEntity.ok(service.findByNumeroStartingWithAndUsaDocumentoTrueOrderById(numero)); //Respuesta 200
    }

    @GetMapping("/filtro-numero/3/{numero}")
    public ResponseEntity<?> buscarNumeroOperacionTesoreria(@PathVariable String numero) {
        return ResponseEntity.ok(service.busquedaNumeroOperacionTesoreria(numero)); //Respuesta 200
    }

    @PutMapping("/{ruc}/iud/{accion}")
    public ResponseEntity<?> iudJson(@PathVariable String ruc, @PathVariable String accion, @RequestBody CuentaContableRequest request) throws Exception {
        return ResponseEntity.ok(service.iudJson(ruc, accion, request));
    }

}
