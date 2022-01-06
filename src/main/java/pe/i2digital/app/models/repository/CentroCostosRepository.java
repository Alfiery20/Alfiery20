/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.i2digital.app.models.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.i2digital.app.models.entity.CentroCostos;

/**
 *
 * @author alfie
 */
public interface CentroCostosRepository extends CrudRepository<CentroCostos, Integer> {

    //Creacion query por Nombres en Keywords de JPQL
    public List<CentroCostos> findByNombre(String nombre);

    public Optional<CentroCostos> findByCodigo(String Codigo);

    public List<CentroCostos> findByNombreStartingWithOrderByCodigoDesc(String Codigo);

    public List<CentroCostos> findByIdAfterAndNombreStartingWithIgnoreCase(Integer id, String nombre);

    //Creation query por definition SQL en JPQL

    @Query("select c from CentroCostos c where c.codigo=?1")
    public Optional<CentroCostos> busquedaJPQLxCodigo(String Codigo);

    @Query("select c from CentroCostos c where c.id>?1 and UPPER(c.nombre) LIKE CONCAT('',UPPER(?2),'%')")
    public List<CentroCostos> busquedaJPQLxIdxNombrePerzonalizado(Integer id, String nombre);

    //Querey por sql nativo
    @Query(value = "select * from sh_empresa_20441636831.tb_at_centrocostos where tb_centrocostos_cod = ?1", nativeQuery = true)
    public Optional<CentroCostos> busquedaxCodigo(String Codigo);

    @Query(value = "select * from sh_empresa_20441636831.tb_at_centrocostos where tb_centrocostos_id>:id and UPPER(tb_centrocostos_nom) LIKE CONCAT('',UPPER(:nombre),'%')", nativeQuery = true)
    public List<CentroCostos> busquedaxIdxNombrePerzonalizado(@Param("id") Integer id,@Param("nombre") String nombre);

    //Creacion query mediante nombres de queries: 1.- Archivo externo .properties, 2.- Archivo externo .xml, 3.- Notacion
    //1.-x JPQL: Busqueda x codigo
    public Optional<CentroCostos> findByCodigoNamedFile(String Codigo);
    //1.-x JPQL: Listar
    public List<CentroCostos> findAllNamedFile();
    //1.-x JPQL: Cantidad de caracteres del nombre de un Centro de Costos en Base del Codigo
    public int sampleNamedQuery(String codigo);

    //1.-x SQL Nativo: Listar
    @Query(nativeQuery = true)
    public List<CentroCostos> findAllNamedNativeFile();
    @Query(nativeQuery = true)
    public int sampleNamedNativeQuery(String codigo);

    //2.-x JPQL: Busqueda x codigo
    public Optional<CentroCostos> buscarxCodigoXML(String Codigo);
    //2.-x JPQL: Listar
    public List<CentroCostos> findAllXML();

    //2.-x SQL Nativo: Listar
    @Query(nativeQuery = true)
    public List<CentroCostos> findAllNativeXML();

    //3.-x JPQL: Busqueda x codigo
    public Optional<CentroCostos> buscarxCodigoJPQL(String Codigo);
    //3.-x JPQL: Listar
    public List<CentroCostos> findAllJPQL();

    //3.-x SQL Nativo: Listar
    @Query(nativeQuery = true)
    public List<CentroCostos> findAllNative();
}
                                                         

