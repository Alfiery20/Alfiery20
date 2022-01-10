package pe.i2digital.app.service;

import pe.i2digital.app.models.entity.Sucursal;

import java.util.List;

public interface SucursalService {
    public List<Sucursal> findAll();
    public Sucursal findById(Integer id);
}
