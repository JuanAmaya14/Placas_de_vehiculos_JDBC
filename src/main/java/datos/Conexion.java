package datos;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/personasyvehiculos";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    private static BasicDataSource dataSource;

    public static DataSource getDatasource(){

        if(dataSource == null){

            dataSource = new BasicDataSource();

            dataSource.setUrl(URL);
            dataSource.setUsername(USER);
            dataSource.setPassword(PASSWORD);

            dataSource.setInitialSize(5);

        }

        return dataSource;

    }

    public static Connection getConnection() throws SQLException {

        return getDatasource().getConnection();

    }

    public static void close (ResultSet rs) throws SQLException {

        rs.close();

    }

    public static void close (Connection conn) throws SQLException {

        conn.close();

    }

    public static void close (PreparedStatement pstms) throws SQLException {

        pstms.close();

    }



}
