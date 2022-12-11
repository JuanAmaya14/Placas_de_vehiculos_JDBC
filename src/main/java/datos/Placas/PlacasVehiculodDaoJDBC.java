package datos.Placas;

import datos.Conexion;
import models.Placa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;

public class PlacasVehiculodDaoJDBC implements PlacasVehiculosDAO{

    private Connection conexionTransaccional;

    private static final String GET = "SELECT idPlacaVehiculo, placa FROM placasvehiculos";

    private static final String POST = "INSERT INTO placasvehiculos(placa) VALUE(?)";

    private static final String PUT = "UPDATE placasvehiculos SET placa = ? WHERE idPlacaVehiculo = ?";

    private static final String DELETE = "DELETE FROM placasvehiculos WHERE idPlacaVehiculo  = ?";

    public PlacasVehiculodDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }


    @Override
    public List<Placa> GET() throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Placa placa = null;
        List<Placa> placas = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            pstmt = conn.prepareStatement(GET);

            rs = pstmt.executeQuery();

            while (rs.next()){

                int idPlaca = rs.getInt("idPlacaVehiculo");
                String placaVeh = rs.getString("placa");

                placa = new Placa(idPlaca, placaVeh);

                placas.add(placa);
            }

        }finally {

            close(pstmt);
            close(rs);

            if (this.conexionTransaccional == null){

                close(conn);

            }

        }


        return placas;
    }

    @Override
    public int POST(Placa placa) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int registro = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            pstmt = conn.prepareStatement(POST);

            pstmt.setString(1, placa.getPlaca());

            registro = pstmt.executeUpdate();

            System.out.println("La placa del vehiculo fue agregada con exito");

        }finally {

            close(pstmt);

            if (this.conexionTransaccional == null){

                close(conn);

            }


        }


        return registro;
    }

    @Override
    public int PUT(Placa placa) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        int actualizar = 0;

        try{

            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();

            pstmt = conn.prepareStatement(PUT);

            pstmt.setString(1, placa.getPlaca());
            pstmt.setInt(2, placa.getIdPlacaVehiculo());

            actualizar = pstmt.executeUpdate();

            System.out.println("La placa " + placa.getIdPlacaVehiculo() + " se actualizo correctamente");

        }finally{

            close(pstmt);

            if (this.conexionTransaccional == null){

                close(conn);

            }
        }

        return actualizar;
    }

    @Override
    public int DELETE(Placa placa) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int eliminado = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            pstmt = conn.prepareStatement(DELETE);

            pstmt.setInt(1, placa.getIdPlacaVehiculo());

            eliminado = pstmt.executeUpdate();

            System.out.println("La placa " + placa.getIdPlacaVehiculo() + " fue eliminado con exito");

        }finally {

            close(pstmt);

            if (this.conexionTransaccional == null){

                close(conn);

            }


        }


        return eliminado;
    }
}
