package datos.Relacion;

import datos.Conexion;
import models.Relacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class RelacionDaoJDBC implements RelacionDAO {

    private Connection conexionTransaccional;

    private static final String GET = "SELECT * FROM relacion";
    private static final String POST = "INSERT INTO relacion(idPersona, idPlacaVehiculo) VALUE(?, ?)";
    private static final String PUT = "UPDATE relacion SET idPersona = ?, idPlacaVehiculo = ? WHERE idRelacion = ?";
    private static final String DELETE = "DELETE FROM relacion WHERE idRelacion  = ?";

    public RelacionDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }


    @Override
    public List<Relacion> GET() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Relacion relacion = null;
        List<Relacion> relaciones = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            pstmt = conn.prepareStatement(GET);

            rs = pstmt.executeQuery();

            while(rs.next()){

                int idRelacion = rs.getInt("idRelacion");
                int idPersona = rs.getInt("idPersona");
                int idPlacaVehiculo = rs.getInt("idPlacaVehiculo");

                relacion = new Relacion(idRelacion, idPersona, idPlacaVehiculo);

                relaciones.add(relacion);

            }
        }finally {

            close(pstmt);
            close(rs);
            if (this.conexionTransaccional == null){

                close(conn);

            }

        }


        return relaciones;
    }

    @Override
    public int POST(Relacion relacion) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int registro = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            pstmt = conn.prepareStatement(POST);

            pstmt.setInt(1, relacion.getIdPersona());
            pstmt.setInt(2, relacion.getIdPlaca());

            registro = pstmt.executeUpdate();

            System.out.println("La relacion fue agregada con exito");

        }finally {

            close(pstmt);
            if (this.conexionTransaccional == null){

                close(conn);
            }
        }

        return registro;
    }

    @Override
    public int PUT(Relacion relacion) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int actualizar = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            pstmt = conn.prepareStatement(PUT);

            pstmt.setInt(1, relacion.getIdPersona());
            pstmt.setInt(2, relacion.getIdPlaca());
            pstmt.setInt(3, relacion.getIdRelacion());

            actualizar = pstmt.executeUpdate();

            System.out.println("La relacion " + relacion.getIdRelacion() + " se actualizo correctamente");

        }finally{

            close(pstmt);
            if (this.conexionTransaccional == null){

                close(conn);

            }

        }


        return actualizar;
    }

    @Override
    public int DELETE(Relacion relacion) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int Eliminar = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            pstmt = conn.prepareStatement(DELETE);

            pstmt.setInt(1, relacion.getIdRelacion());

            Eliminar = pstmt.executeUpdate();

            System.out.println("La relacion " + relacion.getIdRelacion() + " se elimino correctamente");

        }finally{

            close(pstmt);
            if (this.conexionTransaccional == null){

                close(conn);

            }
        }
        return Eliminar;
    }
}
