package pe.i2digital.app.service;

import pe.i2digital.app.models.entity.CentroCostos;

import java.util.List;

public interface CentroCostosService {
    public List<CentroCostos> findByNombre(String Nombre);
    public CentroCostos findByCodigo(String Codigo);
    public CentroCostos findById(Integer Id);
    public Iterable<CentroCostos> findAll();
    public CentroCostos save(CentroCostos Entity);
    public void deleteById(Integer Id);
}
