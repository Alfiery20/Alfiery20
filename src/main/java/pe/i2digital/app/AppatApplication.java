package pe.i2digital.app;

import java.util.List;
import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.i2digital.app.models.entity.CentroCostos;
import pe.i2digital.app.models.repository.CentroCostosRepository;
import pe.i2digital.app.service.CentroCostosService;

@SpringBootApplication
public class AppatApplication implements CommandLineRunner {
    //Solo era iniciarlo?SiAhhhVale vale Gracias
    @Autowired
    private CentroCostosRepository repository;
    @Autowired
    private CentroCostosService service;

    public static void main(String[] args) {
        SpringApplication.run(AppatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //testReadCentroCostos();
        //testCRUDCentroCostos();
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

        List<CentroCostos> list5 = repository.findByIdAfterAndNombreStartingWithIgnoreCase(04,"GRI");
        for (CentroCostos c : list5) {
            System.out.println("Lista5 - " + c.getCodigo() + " - " + c.getNombre());
        }

        var list6 = repository.busquedaJPQLxCodigo("04").orElse(null);
        System.out.println("Lista6 - " + list6.getNombre());

        List<CentroCostos> list7 = repository.busquedaJPQLxIdxNombrePerzonalizado(04,"GRI");
        for (CentroCostos c : list7) {
            System.out.println("Lista7 - " + c.getCodigo() + " - " + c.getNombre());
        }

        var list8 = repository.busquedaxCodigo("01").orElse(null);
        System.out.println("Lista8 - " + list8.getNombre());

        List<CentroCostos> list9 = repository.busquedaxIdxNombrePerzonalizado(04,"GRI");
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
