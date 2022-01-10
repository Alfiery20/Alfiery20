package pe.i2digital.app.models.dto.projection;


import org.springframework.beans.factory.annotation.Value;

public interface CuentaContableCustom {
    public Integer getId();
    public String getNumero();
    @Value("#{target.cuentaContableNombre}")
    public String getNombre();
    @Value("#{target.operacionTesoreriaNombre}")
    public String getoperacionTesoreriaNombre();
    public String getusaDocumento();
    @Value("#{target.cuentaContableNombre} / #{target.operacionTesoreriaNombre}")
    public String getDescripcion();
}
