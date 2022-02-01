package pe.i2digital.app.models.udt;

import com.impossibl.postgres.api.jdbc.PGType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinoCompraUDT implements SQLData {
    private String movimiento;
    private double porcentaje;
    private Integer origen_id;
    private Integer destino_id;
    public static final String TYPE_NAME = "%s.tp_at_destinocompra";

    @Override
    public String getSQLTypeName() throws SQLException {
        return TYPE_NAME;
    }

    @Override
    public void readSQL(SQLInput in, String typeName) throws SQLException {
        this.movimiento = in.readString();
        this.porcentaje = in.readObject(Double.class);
        this.origen_id = in.readObject(Integer.class);
        this.destino_id = in.readObject(Integer.class);
    }

    @Override
    public void writeSQL(SQLOutput out) throws SQLException {
        out.writeObject(this.movimiento, PGType.BPCHAR);
        out.writeObject(this.porcentaje, JDBCType.NUMERIC);
        out.writeObject(this.origen_id, JDBCType.INTEGER);
        out.writeObject(this.destino_id, JDBCType.INTEGER);
    }
}
