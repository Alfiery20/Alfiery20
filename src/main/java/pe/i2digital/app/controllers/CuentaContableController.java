package pe.i2digital.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.i2digital.app.models.dto.CuentaContableDTO;
import pe.i2digital.app.models.dto.projection.CuentaContableCustom;
import pe.i2digital.app.models.dto.projection.CuentaContableVista;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.request.CuentaContableRequest;
import pe.i2digital.app.service.CuentaContableService;

import java.util.List;
import java.util.Objects;

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
    @Operation(summary = "Devuelve la cuenta contable a partir de un identificador")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Devuelve el registro de la Cuenta Contable",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CuentaContable.class))),
                    @ApiResponse(responseCode = "400", description = "Invalido", content = @Content),
                    @ApiResponse(responseCode = "404", description = "No se encuentra la cuenta contable", content = @Content)
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> devolver(@ApiParam(value = "Identificador de la CuentaContable", example = "1")
                                      @PathVariable Integer id) {
        var oCuentaContable = service.findById(id);
        if (Objects.nonNull(oCuentaContable)) {
            return ResponseEntity.ok(oCuentaContable); //Respuesta 200
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar toda la tabla Cuenta Contable a partir de un numero", operationId = "GET_LISTAR_1",
            description = "- Se lista comenzando con el filtro parte numero inicial")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Devuelve el registro de la Cuenta Contable",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = CuentaContableDTO.class)))),
                    @ApiResponse(responseCode = "400", description = "Invalido", content = @Content),
                    @ApiResponse(responseCode = "404", description = "No se encuentra registros de cuenta contable", content = @Content)
            }
    )
    @GetMapping(value = "/numero/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> devolverPorNumero(
            @Parameter(description = "Parte del numero inicial", example = "10")
            @PathVariable String numero) {
        var lista = service.findByNumeroStartingWithOrderById(numero);
        if (Objects.nonNull(lista)) {
            return ResponseEntity.ok(lista); //Respuesta 200
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar toda la tabla Cuenta Contable a partir de un numero (Filtro tipo 1)", operationId = "GET_LISTAR_1",
            description = "- Se lista comenzando con el filtro parte numero inicial.\n - Primera tipo de filtro")
    @GetMapping(value = "/filtro-numero/1/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CuentaContableDTO> busquedaPersonalizadaNumero(@PathVariable String numero) {
        return service.busquedaPersonalizadaNumero(numero);
    }

    /*
    public ResponseEntity<?> buscarParteNumero(@PathVariable String numero) {
        return ResponseEntity.ok(service.busquedaPersonalizadaNumero(numero)); //Respuesta 200
    }
    */
    @Operation(summary = "Listar toda la tabla Cuenta Contable a partir de un numero (Filtro tipo 2)",
            description = "- Se lista comenzando con el filtro parte numero inicial.\n - Segundo tipo de filtro. \n - Filtro si usa documento")
    @GetMapping(value = "/filtro-numero/2/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CuentaContableVista> buscarPersonalizadaNumero(@PathVariable String numero) {
        return service.findByNumeroStartingWithAndUsaDocumentoTrueOrderById(numero);
    }

    @Operation(summary = "Listar toda la tabla Cuenta Contable a partir de un numero (Filtro tipo 3)",
            description = "- Se lista comenzando con el filtro parte numero inicial.\n - Tercer tipo de filtro. \n - Filtro si usa documento")
    @GetMapping(value = "/filtro-numero/3/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<CuentaContableCustom> buscarNumeroOperacionTesoreria(@PathVariable String numero) {
        return service.busquedaNumeroOperacionTesoreria(numero);
    }

    @Operation(summary = "Realiza accion (I/U/D) para el mantenimiento de cuenta contable y algunos procesos mas.",
            description = "- I: Para insertar. \n- U: Para actualizar. \n- D: Para elimianr")
    @PutMapping(value = "/{ruc}/iud/{accion}", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String iudJson(
            @Parameter(description = "RUC de la empresa de usuario que se ha logeado")
            @PathVariable String ruc,
            @ApiParam(value = "Tipo de accion a realizar el mantenimiento", defaultValue = "00", example = "I", allowableValues = "I,U,D")
            @PathVariable String accion,

            @RequestBody CuentaContableRequest request) throws Exception {
        System.out.println("OBJETO: " + new ObjectMapper().writeValueAsString(request.getOCuentaContable()));
        return service.iudJson(ruc, accion, request);
    }

}
