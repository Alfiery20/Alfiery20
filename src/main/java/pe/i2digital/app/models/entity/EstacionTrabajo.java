package pe.i2digital.app.models.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_estaciontrabajo",schema = "sh_empresa_20441636831")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstacionTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_estaciontrabajo_id")
    private Integer id;

    @NotNull(message = "CODIGO ES OBLIGATORIO")
    @Column(name = "tb_estaciontrabajo_cod", unique = true)
    @Size(max = 4)
    private String codigo;

    @Size(max = 50)
    @NotNull(message = "NOMBRE ES OBLIGATORIO")
    @Column(name = "tb_estaciontrabajo_nom", unique = true)
    private String nombre;

    @JsonRawValue
    @NotNull(message = "ANFITRION ES OBLIGATORIO")
    @Column(name = "tb_estaciontrabajo_anf")
    private String anfitrion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"estacionesTrabajo", "handler", "hibernateLazyInitializer"})
    @JoinColumn(name = "tb_sucursal_id")
    private Sucursal sucursal;
}
