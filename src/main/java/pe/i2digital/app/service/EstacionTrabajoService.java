package pe.i2digital.app.service;

import pe.i2digital.app.models.dto.projection.EstacionTrabajoVista;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.entity.EstacionTrabajo;

import java.util.List;

public interface EstacionTrabajoService {
    public List<EstacionTrabajo> findAll();
    public EstacionTrabajo findById(Integer id);
    public EstacionTrabajoVista findByCodigo(String codigo);
    public List<EstacionTrabajo> busquedaPersonalizada();
}
