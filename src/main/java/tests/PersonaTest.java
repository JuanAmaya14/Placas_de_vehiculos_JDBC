package tests;

import datos.Persona.PersonaDaoJDBC;
import models.Persona;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static datos.Conexion.getConnection;

public class PersonaTest {

    public static void main(String[] args) {

        Connection connection = null;

        try{

            connection = getConnection();

            if (connection.getAutoCommit()){

                connection.setAutoCommit(false);

            }

            PersonaDaoJDBC personaDaoJDBC = new PersonaDaoJDBC(connection);

            Persona personaPOST1 = new Persona("Juan", 23456432);

            Persona personaPOST2 = new Persona("Nati", 2344342);

            Persona personaPOST3 = new Persona("Jose", 34554378);

            Persona personaPOST4 = new Persona("David", 343423555);

//            personaDaoJDBC.POST(personaPOST1);
//            personaDaoJDBC.POST(personaPOST2);
//            personaDaoJDBC.POST(personaPOST3);
//            personaDaoJDBC.POST(personaPOST4);


            List<Persona> personas = personaDaoJDBC.GET();

            for (Persona persona : personas) {

                System.out.println(persona);

            }

            connection.commit();

            System.out.println("No hubo problemas durante el proceso");


        }catch (SQLException e){

            e.printStackTrace(System.out);

            try {

                connection.rollback();

                System.out.println("Algo sucedio, entramos al rollback");


            } catch (SQLException ex) {

                ex.printStackTrace(System.out);

            }

        }



    }

}

