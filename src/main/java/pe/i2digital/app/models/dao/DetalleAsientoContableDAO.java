package pe.i2digital.app.models.dao;

import pe.i2digital.app.models.entity.DetalleAsientoContable;

import java.util.List;

public interface DetalleAsientoContableDAO {
    public List<DetalleAsientoContable> seguimientoDocumentos(String esquema, String periodo, Integer idDocumento);
    public List<DetalleAsientoContable> seguimientoDocumentos2(String esquema, String periodo, Integer idDocumento);
}

