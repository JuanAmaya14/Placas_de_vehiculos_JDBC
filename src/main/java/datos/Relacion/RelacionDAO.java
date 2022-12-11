package datos.Relacion;

import models.Relacion;

import java.sql.SQLException;
import java.util.List;

public interface RelacionDAO {

    public List<Relacion> GET() throws SQLException;

    public int POST(Relacion relacion) throws SQLException;

    public int PUT(Relacion relacion) throws SQLException;

    public int DELETE(Relacion relacion) throws SQLException;
}
