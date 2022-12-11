package tests;

import datos.Relacion.RelacionDaoJDBC;
import models.Relacion;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static datos.Conexion.getConnection;

public class RelacionTest {

    public static void main(String[] args) {

        Connection connection = null;

        try {
            connection = getConnection();

            if (connection.getAutoCommit()){

                connection.setAutoCommit(false);

            }

            RelacionDaoJDBC relacionDao = new RelacionDaoJDBC(connection);

            List<Relacion> relaciones = relacionDao.GET();

            for (Relacion relacion : relaciones) {

                System.out.println(relacion);

            }

            Relacion relacionPOST = new Relacion(7, 4);

            Relacion relacionPOST1 = new Relacion(8, 7);

            Relacion relacionPOST2 = new Relacion(9, 6);

//            relacionDao.POST(relacionPOST);
//            relacionDao.POST(relacionPOST1);
//            relacionDao.POST(relacionPOST2);


            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace(System.out);

            try {
                connection.rollback();

                System.out.println("Entramos a rollback");

            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }


    }

}
