package pe.i2digital.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pe.i2digital.app.models.dto.CuentaContableDTO;
import pe.i2digital.app.models.dto.projection.CuentaContableCustom;
import pe.i2digital.app.models.dto.projection.CuentaContableVista;
import pe.i2digital.app.models.entity.CuentaContable;

import java.util.List;

public interface CuentaContableRepository extends CrudRepository<CuentaContable, Integer> {
    //Uso de DTO basado en clase    //Ejemplo de query con Keywords
    public List<CuentaContableDTO> findByNumeroStartingWithOrderById(String numero);
    //Uso de DTO basado en clases pero por expresion constructor    //Cuando uso JPQL
    @Query("select new pe.i2digital.app.models.dto.CuentaContableDTO(c.id, c.numero, c.nombre)" +
            "from CuentaContable c where UPPER(c.numero) like concat('', UPPER(?1), '%')" +
            "order by c.id asc")
    public List<CuentaContableDTO> busquedaPersonalizadaNumero(String numero);
    /*DTO basado en proyeccion / interfaces : Cerrado y abiertas*/
    //Uso de DTO basaddo en interfaces / proyeccion -> proyeccion cerrada
    public List<CuentaContableVista> findByNumeroStartingWithAndUsaDocumentoTrueOrderById(String numero);
    //Usar JPQL
    @Query("select c.id as id, c.numero as numero, c.usaDocumento as usaDocumento, c.nombre as cuentaContableNombre," +
            "o.nombre as operacionTesoreriaNombre from CuentaContable c " +
            "INNER JOIN c.operacionTesoreria o " +
            "where UPPER(c.numero) like concat('', UPPER(?1), '%') order by c.numero")
    public List<CuentaContableCustom> busquedaNumeroOperacionTesoreria(String numero);

    @Query(value = "select * from sh_empresa_20441636831.fn_at_select_cuenta_diferenciacambio_documentos()", nativeQuery = true)
    public List<Object[]> cuentaDiferenciaCambioDocumentos();

}
