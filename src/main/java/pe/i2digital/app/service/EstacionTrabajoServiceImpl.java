package pe.i2digital.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.i2digital.app.models.dto.mapper.EstacionTrabajoMapper;
import pe.i2digital.app.models.dto.projection.EstacionTrabajoVista;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.entity.EstacionTrabajo;
import pe.i2digital.app.models.entity.OperacionTesoreria;
import pe.i2digital.app.models.repository.EstacionTrabajoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstacionTrabajoServiceImpl implements EstacionTrabajoService{
    @Autowired
    private EstacionTrabajoRepository repository;

    @Override
    public List<EstacionTrabajo> findAll() {
        return (List<EstacionTrabajo>) repository.findAll();
    }

    @Override
    public EstacionTrabajo findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public EstacionTrabajoVista findByCodigo(String codigo) {
        return repository.findByCodigo(codigo).orElse(null);
    }

    @Override
    public List<EstacionTrabajo> busquedaPersonalizada() {
        var listMap = repository.busquedaPersonalizada();
        List<EstacionTrabajo> lista = new ArrayList<>();
        listMap.forEach( r -> lista.add(new EstacionTrabajoMapper().mapperRowbusquedaPerzonalizada(r)));
        return lista;
    }
}
