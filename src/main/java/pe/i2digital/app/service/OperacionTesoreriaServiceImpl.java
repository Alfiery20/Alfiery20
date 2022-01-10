package pe.i2digital.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.i2digital.app.models.entity.OperacionTesoreria;
import pe.i2digital.app.models.repository.OperacionTesoreriaRepository;

import java.util.List;

@Service
public class OperacionTesoreriaServiceImpl implements OperacionTesoreriaService{
    @Autowired
    OperacionTesoreriaRepository repository;

    @Override
    public Iterable<OperacionTesoreria> findAll() {
        return (List<OperacionTesoreria>) repository.findAll();
    }

    @Override
    public OperacionTesoreria findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
