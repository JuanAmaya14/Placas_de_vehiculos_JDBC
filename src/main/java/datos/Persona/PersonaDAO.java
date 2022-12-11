package datos.Persona;

import models.Persona;

import java.sql.SQLException;
import java.util.List;

public interface PersonaDAO {

    public List<Persona> GET() throws SQLException;

    public int POST(Persona persona) throws SQLException;

    public int PUT(Persona persona) throws SQLException;

    public int DELETE(Persona persona) throws SQLException;

}
