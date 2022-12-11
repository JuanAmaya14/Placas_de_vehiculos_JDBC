package datos.Persona;

import models.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class PersonaDaoJDBC implements PersonaDAO{

    private Connection conexionTransaccional;

    private static final String GET = "SELECT * FROM personas";

    private static final String POST = "INSERT INTO personas(nombre, cedula) VALUE(?, ?)";

    private static final String PUT = "UPDATE personas SET nombre = ?, cedula = ? WHERE idPersona = ?";

    private static final String DELETE = "DELETE FROM personas WHERE idPersona  = ?";

    public PersonaDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<Persona> GET() throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();


        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            pstmt = conn.prepareStatement(GET);
            rs = pstmt.executeQuery();

            while (rs.next()){

                int idPersona = rs.getInt("idPersona");
                String nombre = rs.getString("nombre");
                int cedula = rs.getInt("cedula");

                persona = new Persona(idPersona, nombre, cedula);

                personas.add(persona);
            }
        }finally{

            close(pstmt);
            close(rs);
            if (this.conexionTransaccional == null) {
                close(conn);
            }

        }
        return personas;
    }

    @Override
    public int POST(Persona persona) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int registro = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            pstmt = conn.prepareStatement(POST);

            pstmt.setString(1, persona.getNombre());
            pstmt.setInt(2, persona.getCedula());

            registro = pstmt.executeUpdate();

            System.out.println("la persona fue agregado con exito");

        }finally{

            close(pstmt);

            if (this.conexionTransaccional == null){

                close(conn);

            }

        }

        return registro;
    }

    @Override
    public int PUT(Persona persona) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int actualizado = 0;

        try {

            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();

            pstmt = conn.prepareStatement(PUT);

            pstmt.setString(1, persona.getNombre());
            pstmt.setInt(2, persona.getCedula());
            pstmt.setInt(3, persona.getIdPersona());

            actualizado = pstmt.executeUpdate();

            System.out.println("Persona " + persona.getIdPersona() + " actualizada correctamente");

        }finally{

            close(pstmt);

            if (this.conexionTransaccional == null){

                close(conn);

            }


        }

        return actualizado;
    }

    @Override
    public int DELETE(Persona persona) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int eliminado = 0;

        try{

            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            pstmt = conn.prepareStatement(DELETE);

            pstmt.setInt(1, persona.getIdPersona());

            eliminado = pstmt.executeUpdate();

        }finally{

            close(pstmt);

            if (this.conexionTransaccional == null){

                close(conn);

            }

        }





        return eliminado;
    }

}
