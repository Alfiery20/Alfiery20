package pe.i2digital.app.service;

import pe.i2digital.app.models.entity.OperacionTesoreria;

public interface OperacionTesoreriaService {
    public Iterable<OperacionTesoreria> findAll();
    public OperacionTesoreria findById(Integer id);
}
