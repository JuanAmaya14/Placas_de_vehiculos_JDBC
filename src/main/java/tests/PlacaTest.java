package tests;

import datos.Placas.PlacasVehiculodDaoJDBC;
import models.Placa;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static datos.Conexion.getConnection;

public class PlacaTest {

    public static void main(String[] args) {

        Connection connection = null;

        try {
            connection = getConnection();

            if (connection.getAutoCommit()){

                connection.setAutoCommit(false);

            }

            PlacasVehiculodDaoJDBC placasVehiculos = new PlacasVehiculodDaoJDBC(connection);

            List<Placa> placas = placasVehiculos.GET();

            for (Placa placa : placas) {

                System.out.println(placa);

            }

            Placa placaPOST = new Placa("345-NGR");

            Placa placaPOST1 = new Placa("974-LOT");

            Placa placaPOST2 = new Placa("463-ETT");

            Placa placaPOST3 = new Placa("110-ZXC");

//            placasVehiculos.POST(placaPOST);
//            placasVehiculos.POST(placaPOST1);
//            placasVehiculos.POST(placaPOST2);
//            placasVehiculos.POST(placaPOST3);


            connection.commit();

        } catch (SQLException e) {

            e.printStackTrace(System.out);

            try {

                connection.rollback();

            } catch (SQLException ex) {

                ex.printStackTrace(System.out);
            }

        }


    }

}
