package pe.i2digital.app;

import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.i2digital.app.models.dao.*;
import pe.i2digital.app.models.entity.CentroCostos;
import pe.i2digital.app.models.entity.CuentaContable;
import pe.i2digital.app.models.repository.CentroCostosRepository;
import pe.i2digital.app.models.repository.CuentaContableRepository;
import pe.i2digital.app.models.repository.EstacionTrabajoRepository;
import pe.i2digital.app.models.udt.DestinoCompraUDT;
import pe.i2digital.app.service.CentroCostosService;
import pe.i2digital.app.service.EstacionTrabajoService;


@SpringBootApplication
public class AppatApplication implements CommandLineRunner {

    @Autowired
    private CentroCostosRepository repository;
    @Autowired
    private CentroCostosService service;
    @Autowired
    private GenericDAO genericDAO;
    @Autowired
    private AsientoContableDAO asientoContableDAO;
    @Autowired
    private DetalleAsientoContableDAO detalleAsientoContableDAO;
    @Autowired
    private CuentaContableRepository cuentaContableRepository;
    @Autowired
    private EstacionTrabajoService estacionTrabajoService;
    @Autowired
    private CentroCostosDAO centroCostosDAO;
    @Autowired
    private CuentaContableDAO cuentaContableDAO;

    public static void main(String[] args) {
        SpringApplication.run(AppatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testUDTArray();
    }

    private void testUDTArray() throws Exception {
        String respuesta = null;
        ///-------Probar iudJson con udt: 2° forma de transaccion en DAO
        //AQUI NO FUNCIONA
        var cuentaContable3 = new CuentaContable();
        cuentaContable3.setId(3376);
        cuentaContable3.setNumero("603411");
        cuentaContable3.setNombre("COMBUST");
        cuentaContable3.setMoneda("A");
        DestinoCompraUDT[] aDestinoCompra = new DestinoCompraUDT[]{
                new DestinoCompraUDT("D", 50.0, 3376, 423),
                new DestinoCompraUDT("H", 70.0, 3376, 2138)
        };
        respuesta = cuentaContableDAO.iudJson("sh_empresa_20441636831", "U", cuentaContable3, false, aDestinoCompra);

        ///-------Probar iudJson con udt: 1° forma de transaccion en DAO
        /*var cuentaContable3 = new CuentaContable();
        cuentaContable3.setNumero("603411");
        cuentaContable3.setNombre("COMBUSTIBLE");
        cuentaContable3.setMoneda("A");

        DestinoCompraUDT[] aDestinoCompra = new DestinoCompraUDT[]{
                new DestinoCompraUDT("D", 100.0, null, 423),
                new DestinoCompraUDT("H", 100.0, null, 2138)
        };
        respuesta = cuentaContableDAO.iudJsonV2("sh_empresa_20441636831", "I", cuentaContable3, true, aDestinoCompra);
         */
        ///-------Probar iudJsonV2 sin udt: 1° forma de transaccion en DAO
        /*var cuentaContable2  = new CuentaContable();
        cuentaContable2.setNumero("60341");
        cuentaContable2.setNombre("COMBUSTIBLE");
        respuesta = cuentaContableDAO.iudJson("sh_empresa_20441636831", "I", cuentaContable2,null, null);
        */
        /*
        var cuentaContable1  = new CuentaContable();
        cuentaContable1.setNumero("6034");
        cuentaContable1.setNombre("COMBUSTIBLE");
        respuesta = cuentaContableDAO.iudJsonV2("sh_empresa_20441636831", "I", cuentaContable1,null, null);
        */
        ///-------Probar iudJsonV2 sin udt: 2° forma de transaccion en DAO
        /*var cuentaContable1  = new CuentaContable();
        cuentaContable1.setNumero("603411");
        cuentaContable1.setNombre("COMBUSTIBLE");
        cuentaContable1.setMoneda("A");
        DestinoCompraUDT[] aDestinoCompra = new DestinoCompraUDT[]{
                new DestinoCompraUDT("D", 100.00, 0,423),
                new DestinoCompraUDT("H", 100.00, 0,2138)
        };
        respuesta = cuentaContableDAO.iudJsonV2("sh_empresa_20441636831", "I", cuentaContable1,true, aDestinoCompra);
        */
        /*Eliminar tampoco funciona
        var cuentaContable3 = new CuentaContable();
        cuentaContable3.setId(3376);
        respuesta = cuentaContableDAO.iudJson("sh_empresa_20441636831", "D", CuentaContable.builder().id(3376).numero("603411").build(), null, null);
        */
        System.out.println(respuesta);
    }

    private void testIudDAO() throws Exception {
        String respuesta = null;
        respuesta = centroCostosDAO.iudJson(new CentroCostos(9, null, null), "D", "sh_empresa_20441636831");
        List<CentroCostos> lista = (List<CentroCostos>) repository.findAll();
        for (CentroCostos c : lista) {
            System.out.println(c.getId() + " - " + c.getCodigo() + " - " + c.getNombre());
        }
    }

    private void testIudFuncion() {
        String Respuesta = repository.generateIUDRow("I", null, "06", "TEST ");
        System.out.println(Respuesta);
        List<CentroCostos> lista = (List<CentroCostos>) repository.findAll();
        for (CentroCostos c : lista) {
            System.out.println(c.getId() + " - " + c.getCodigo() + " - " + c.getNombre());
        }
    }

    private void testIudFuncionProcedure() {
        String Respuesta = repository.generateIUDRowProcedure("D", 8, null, null);
        System.out.println(Respuesta);
        List<CentroCostos> lista = (List<CentroCostos>) repository.findAll();
        for (CentroCostos c : lista) {
            System.out.println(c.getId() + " - " + c.getCodigo() + " - " + c.getNombre());
        }
    }

    private void testListarArtificio() {
        var list = estacionTrabajoService.busquedaPersonalizada();
        for (var item : list) {
            System.out.println(item.getId() + " - " + item.getCodigo() + " - " + item.getNombre());
        }
    }

    ;

    private void testListarSinTipificar() {
        var lista = cuentaContableRepository.cuentaDiferenciaCambioDocumentos();
        for (var item : lista) {
            System.out.println(item[0] + " - " + item[1] + " - " + item[2]);
        }
    }

    private void testSeguimientoDocumentos() {
        /*var lista = detalleAsientoContableDAO.seguimientoDocumentos("sh_empresa_20441636831", "2020",38);
        for (var e :lista) {
            System.out.println("-----DA PROPOSITO" + e.getProposito()
                    + " DA TIPO: " +e.getTipo()
                    + " DA IMPORTE SOLES " + e.getImporteSoles()
                    + " AC ID: " + e.getOAsientoContable().getId()
                    + " AC GLOSA: "+ e.getOAsientoContable().getGlosa()
                    + " TOC CODIGO: " + e.getOAsientoContable().getOTipoOperacionContable().getCodigo()
                    + " D MONEDA: " + e.getODocumento().getMoneda() + "------");
        }
        */
        var lista = detalleAsientoContableDAO.seguimientoDocumentos2("sh_empresa_20441636831", "2020", 38);
        for (var e : lista) {
            System.out.println("-----DA PROPOSITO" + e.getProposito()
                    + " DA TIPO: " + e.getTipo()
                    + " DA IMPORTE SOLES " + e.getImporteSoles()
                    + " AC ID: " + e.getOAsientoContable().getId()
                    + " AC GLOSA: " + e.getOAsientoContable().getGlosa()
                    + " TOC CODIGO: " + e.getOAsientoContable().getOTipoOperacionContable().getCodigo()
                    + " D MONEDA: " + e.getODocumento().getMoneda() + "------");
        }
    }

    private void TestAsientoContable() {
        var lista = asientoContableDAO.busquedaExcel("2020", 38);
        for (var e : lista) {
            System.out.println(e.getGlosa() + " - "
                    + e.getNumero() + " - "
                    + e.getCuentaContable() + " - "
                    + e.getDebeSoles());
        }
    }

    private void testVersion() {
        String version = repository.getVersion();
        System.out.println("VERSION F1: " + version);
        version = repository.getVersionProcedure();
        System.out.println("VERSION F2: " + version);
        version = genericDAO.getVersion();
        System.out.println("VERSION F3: " + version);
        version = genericDAO.getFechaActual();
        System.out.println("FECHA ACTUAL F1: " + version);
        //Test con hibernate
        version = genericDAO.version();
        System.out.println("VERSION F4: " + version);
    }

    private void testCRUDCentroCostos() {
        //CRUD simple - Crud repository - Sprind Data
        /*
        //Read
        List<CentroCostos>lista1 = (List<CentroCostos>) service.findAll();
        for (CentroCostos c: lista1) {
            System.out.println(" - " + c.getId() + " - " + c.getNombre() + " - " +c.getCodigo());
        }
        */

        /*
        //CREATE
        System.out.println("INICIO");
        List<CentroCostos> lista = (List<CentroCostos>) repository.findAll();
        for (CentroCostos C: lista) {
        System.out.println(C.getId() +" - " + C.getCodigo() + " - " + C.getNombre());
        }
        var CC1 = new CentroCostos();
        CC1.setId(6);
        CC1.setCodigo("06");
        CC1.setNombre("GRIFO-SULLANA");
        var ccBD = repository.save(CC1);
        System.out.println("FIN");
        */

        /*
        //UPDATE
        var cc = service.findByCodigo("06");
        if (Objects.nonNull(cc)){
            System.out.println("ANTES");
            System.out.println(cc.getId() + " - " + cc.getCodigo() + " - " + cc.getNombre());
            cc.setNombre("CC Test");
            var ccDB = repository.save(cc);
            System.out.println("DESPUES");
            System.out.println(ccDB.getId() + " - " + ccDB.getCodigo() + " - " + ccDB.getNombre());
        }else System.out.println("NO EXISTE");
        */
        /*
        //DELETE
        var cc = service.findByCodigo("06");
        System.out.println("ANTES");
        System.out.println(cc.getId() + " - " + cc.getCodigo() + " - " + cc.getNombre());
        service.deleteById(cc.getId());
        List<CentroCostos>lista1 = (List<CentroCostos>) service.findAll();
        for (CentroCostos c: lista1) {
            System.out.println(" - " + c.getId() + " - " + c.getNombre() + " - " +c.getCodigo());
        }
        */
    }

    private void testReadCentroCostos() {
        System.out.println("Hola mundo");
        List<CentroCostos> lista = (List<CentroCostos>) repository.findAll();
        for (CentroCostos c : lista) {
            System.out.println("Lista1 - " + c.getCodigo() + " - " + c.getNombre());
        }

        List<CentroCostos> lista2 = repository.findByNombre("GENERAL");
        for (CentroCostos c : lista2) {
            System.out.println("Lista2 - " + c.getCodigo() + " - " + c.getNombre());
        }

        var lista3 = repository.findByCodigo("04").orElse(null);
        System.out.println("Lista3 - " + lista3.getNombre());

        List<CentroCostos> list4 = repository.findByNombreStartingWithOrderByCodigoDesc("GRI");
        for (CentroCostos c : list4) {
            System.out.println("Lista4 - " + c.getCodigo() + " - " + c.getNombre());
        }

        List<CentroCostos> list5 = repository.findByIdAfterAndNombreStartingWithIgnoreCase(04, "GRI");
        for (CentroCostos c : list5) {
            System.out.println("Lista5 - " + c.getCodigo() + " - " + c.getNombre());
        }

        var list6 = repository.busquedaJPQLxCodigo("04").orElse(null);
        System.out.println("Lista6 - " + list6.getNombre());

        List<CentroCostos> list7 = repository.busquedaJPQLxIdxNombrePerzonalizado(04, "GRI");
        for (CentroCostos c : list7) {
            System.out.println("Lista7 - " + c.getCodigo() + " - " + c.getNombre());
        }

        var list8 = repository.busquedaxCodigo("01").orElse(null);
        System.out.println("Lista8 - " + list8.getNombre());

        List<CentroCostos> list9 = repository.busquedaxIdxNombrePerzonalizado(04, "GRI");
        for (CentroCostos c : list9) {
            System.out.println("Lista9 - " + c.getCodigo() + " - " + c.getNombre());
        }

        var list10 = repository.findByCodigoNamedFile("05").orElse(null);
        System.out.println("Lista10 - " + list10.getNombre());

        List<CentroCostos> list11 = repository.findAllNamedFile();
        for (CentroCostos c : list11) {
            System.out.println("Lista11 - " + c.getCodigo() + " - " + c.getNombre());
        }

        List<CentroCostos> list12 = repository.findAllNamedNativeFile();
        for (CentroCostos c : list12) {
            System.out.println("Lista12 - " + c.getCodigo() + " - " + c.getNombre());
        }

        System.out.println(repository.sampleNamedQuery("04"));
        System.out.println(repository.sampleNamedNativeQuery("05"));

        List<CentroCostos> list13 = repository.findAllXML();
        for (CentroCostos c : list13) {
            System.out.println("Lista13 - " + c.getCodigo() + " - " + c.getNombre());
        }

        var list14 = repository.buscarxCodigoXML("05").orElse(null);
        System.out.println("Lista14 - " + list14.getNombre());

        List<CentroCostos> list15 = repository.findAllNativeXML();
        for (CentroCostos c : list15) {
            System.out.println("Lista15 - " + c.getCodigo() + " - " + c.getNombre());
        }
        List<CentroCostos> list16 = repository.findAllJPQL();
        for (CentroCostos c : list16) {
            System.out.println("Lista16 - " + c.getCodigo() + " - " + c.getNombre());
        }

        var list17 = repository.buscarxCodigoJPQL("05").orElse(null);
        System.out.println("Lista17 - " + list14.getNombre());

        List<CentroCostos> list18 = repository.findAllNative();
        for (CentroCostos c : list18) {
            System.out.println("Lista18 - " + c.getCodigo() + " - " + c.getNombre());
        }
    }
}
