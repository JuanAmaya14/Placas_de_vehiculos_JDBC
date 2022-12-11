package datos.Placas;

import models.Placa;

import java.sql.SQLException;
import java.util.List;

public interface PlacasVehiculosDAO {

    public List<Placa> GET() throws SQLException;

    public int POST(Placa placa) throws SQLException;

    public int PUT(Placa placa) throws SQLException;

    public int DELETE(Placa placa) throws SQLException;

}
