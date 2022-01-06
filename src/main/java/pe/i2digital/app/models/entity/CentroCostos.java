/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author alfie
 */

@NamedQueries({
        @NamedQuery(name = "CentroCostos.findAllJPQL",
                query =" select c from CentroCostos c order by c.codigo desc"),
        @NamedQuery(name = "CentroCostos.buscarxCodigoJPQL",
                query =" select c from CentroCostos c where c.codigo = ?1")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "CentroCostos.findAllNative",
                query = "select * from sh_empresa_20441636831.tb_at_centrocostos order by tb_centrocostos_cod DESC",
        resultClass = CentroCostos.class)
})
@Entity
@Table(name = "tb_at_centrocostos", schema = "sh_empresa_20441636831")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CentroCostos  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tb_centrocostos_id")
    private Integer id;

    @Size(max = 50)
    @NotNull(message = "NOMBRE ES OBLIGATORIO")
    @Column(name = "tb_centrocostos_nom", unique = true)
    private String nombre;

    @NotNull(message = "CODIGO ES OBLIGATORIO")
    @Column(name = "tb_centrocostos_cod", unique = true)
    @Size(max = 2)
    private String codigo;
}
