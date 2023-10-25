package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Jorge Monzón
 * @project personal-crud-java
 */
public class Conexion {

    //    definimos en null la variable conexión
    private static Connection connection = null;

    public static Connection establecerConexion() {
//        validamos si no existe una conexión
        if (connection == null) {
            String url = "jdbc:mysql://localhost:3306/empleados";
            String usuario = "root";
            String contrasenia = "abc123*";

            try {
//                establecemos conexión
                connection = DriverManager.getConnection(url, usuario, contrasenia);
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

        return connection;
    }


    public static void cerrarConexion() {
//        validamos si existe una conexión
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Se cerro la conexión");


            } catch (SQLException e) {

                e.printStackTrace();

            } finally {
//            validar cerrar conexión
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("No se encuentra una conexión activa");
        }
    }
}
