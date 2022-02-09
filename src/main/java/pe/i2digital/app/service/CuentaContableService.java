package pe.i2digital.app.service;

import pe.i2digital.app.models.dto.CuentaContableDTO;
import pe.i2digital.app.models.dto.projection.CuentaContableCustom;
import pe.i2digital.app.models.dto.projection.CuentaContableVista;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.request.CuentaContableRequest;

import java.util.*;

public interface CuentaContableService {
    public List<CuentaContable> findAll();
    public CuentaContable findById(Integer id);
    public List<CuentaContableDTO> findByNumeroStartingWithOrderById(String numero);
    public List<CuentaContableDTO> busquedaPersonalizadaNumero(String numero);
    public List<CuentaContableVista> findByNumeroStartingWithAndUsaDocumentoTrueOrderById(String numero);
    public List<CuentaContableCustom> busquedaNumeroOperacionTesoreria(String numero);
    public String iudJson(String ruc, String accion, CuentaContableRequest objeto) throws Exception;

}
