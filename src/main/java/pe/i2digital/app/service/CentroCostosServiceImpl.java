package pe.i2digital.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.i2digital.app.models.entity.CentroCostos;
import pe.i2digital.app.models.repository.CentroCostosRepository;

import java.util.List;

@Service
public class CentroCostosServiceImpl implements CentroCostosService{
    @Autowired
    private CentroCostosRepository repository;

    @Override
    public List<CentroCostos> findByNombre(String Nombre) {
        return repository.findByNombre(Nombre);
    }

    @Override
    public CentroCostos findByCodigo(String Codigo) {
        return repository.findByCodigo(Codigo).orElse(null);
    }

    @Override
    public CentroCostos findById(Integer Id) {
        return repository.findById(Id).orElse(null);
    }

    @Override
    public Iterable<CentroCostos> findAll() {
        return repository.findAll();
    }

    @Override
    public CentroCostos save(CentroCostos Entity) {
        return repository.save(Entity);
    }

    @Override
    public void deleteById(Integer Id) {
        repository.deleteById(Id);
    }
}
