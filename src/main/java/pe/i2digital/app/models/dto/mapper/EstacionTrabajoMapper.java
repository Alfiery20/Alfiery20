package pe.i2digital.app.models.dto.mapper;

import pe.i2digital.app.models.entity.EstacionTrabajo;

import java.util.Map;
import java.util.Objects;

public class EstacionTrabajoMapper {
    public EstacionTrabajo mapperRowbusquedaPerzonalizada(Map<String, Object> value){
        Integer id = Objects.nonNull(value.get("tb_estaciontrabajo_id"))
                ? (Integer) value.get("tb_estaciontrabajo_id") : null;
        String cod = Objects.nonNull(value.get("tb_estaciontrabajo_cod"))
                ? value.get("tb_estaciontrabajo_cod").toString() : null;
        String nombre = Objects.nonNull(value.get("tb_estaciontrabajo_nom"))
                ? (String) value.get("tb_estaciontrabajo_nom") : null;
        EstacionTrabajo oMapper = EstacionTrabajo.builder()
                .id(id)
                .codigo(cod)
                .nombre(nombre)
                .build();
        return oMapper;
    };
}
