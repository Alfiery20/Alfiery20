package pe.i2digital.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_sucursal",schema = "sh_empresa_20441636831")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_sucursal_id")
    private Integer id;

    @NotNull(message = "CODIGO ES OBLIGATORIO")
    @Column(name = "tb_sucursal_cod", unique = true)
    @Size(max = 4)
    private String codigo;

    @Size(max = 50)
    @NotNull(message = "NOMBRE ES OBLIGATORIO")
    @Column(name = "tb_sucursal_nom", unique = true)
    private String nombre;

    @NotNull(message = "CODIGO DEL ESTABLECIMIENTO ES OBLIGATORIO")
    @Column(name = "tb_sucursal_codestane", unique = true)
    @Size(max = 4)
    private String codigoEstablecimiento;

    @NotNull(message = "DIRECCION COMPLETA ES OBLIGATORIO")
    @Column(name = "tb_sucursal_dircomdet", unique = true)
    @Size(max = 200)
    private String direccionCompleta;

    @Column(name = "tb_sucursal_tel")
    @Size(max = 50)
    private String telefono;

    @Column(name = "tb_sucursal_corele")
    @Size(max = 50)
    private String correoElectronico;

    @NotNull(message = "TIPO ES OBLIGATORIO")
    @Column(name = "tb_sucursal_tip", unique = true)
    @Size(max = 1)
    private String tipo;

    @JsonIgnoreProperties(value = {"sucursal", "handler", "hibernateLazyInitializer"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sucursal", cascade = CascadeType.ALL)
    private List<EstacionTrabajo> estacionesTrabajo;
}
