package controlador;

import database.Conexion;
import modelo.Empleados;

import java.sql.*;

/**
 * @author Jorge Monzón
 * @project personal-crud-java
 */
public class ControladorEmpleados {

    private Connection con = null;


    public Boolean insertarRegistros(Empleados empleados) {

        try {

            con = Conexion.establecerConexion();

//            validación si existe el dni con el método buscar
            if (!buscarDni(empleados.getDni())) {
                String sql = "INSERT INTO empleados.empleados (dni,nombre,apellido_paterno,apellido_materno)  VALUES(?,?,?,?)";
//                preparamos la consulta
                PreparedStatement ps = con.prepareStatement(sql);
//                asignamos la posicion y los valores a asignar a los parametros
                ps.setString(1, empleados.getDni());
                ps.setString(2, empleados.getNombre());
                ps.setString(3, empleados.getApellidoPaterno());
                ps.setString(4, empleados.getApellidoMaterno());

                ps.execute();
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void actualizarRegistros() {


    }

    public Boolean buscarDni(String dni) {
        Boolean encontrado = false;

        try {
            con = Conexion.establecerConexion();

            String sql = "SELECT dni FROM empleados WHERE dni =?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet resultSet = ps.executeQuery();

//            mover curso al siguiente dato encontrado
            if (resultSet.next()) {
//
                String dniEncontrado = resultSet.getString("dni");
//                comparo si dni encontrado con el parametro ingresado
                encontrado = dniEncontrado.equals(dni);
            } else {
                //(System.out.println("DNI no encontrado");
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return encontrado;
    }

    public Boolean buscarDni(String dni,Empleados empleados) {
        Boolean encontrado = false;

        try {
            con = Conexion.establecerConexion();

            String sql = "SELECT dni FROM empleados WHERE dni =?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet resultSet = ps.executeQuery();

//            mover curso al siguiente dato encontrado
            if (resultSet.next()) {
//
                String dniEncontrado = resultSet.getString("dni");
//                comparo si dni encontrado con el parametro ingresado
                encontrado = dniEncontrado.equals(dni);
            } else {
                //(System.out.println("DNI no encontrado");
                return false;
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return encontrado;
    }

    public void mostrarRegistro(String dni) {

        try {
            con = Conexion.establecerConexion();

            String sql = "SELECT * FROM empleados WHERE dni = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id_empleado") + " - " + resultSet.getString("dni") + " - " + resultSet.getString("nombre") + " - " + resultSet.getString("apellido_paterno") + " - " + resultSet.getString("apellido_materno") + " - " + resultSet.getString("fecha_creacion") + " - " + resultSet.getString("fecha_actualizacion"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void mostrarRegistros() {

        try {
            con = Conexion.establecerConexion();

            String sql = "SELECT * FROM empleados";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id_empleado") + " - " + resultSet.getString("dni") + " - " + resultSet.getString("nombre") + " - " + resultSet.getString("apellido_paterno") + " - " + resultSet.getString("apellido_materno") + " - " + resultSet.getString("fecha_creacion") + " - " + resultSet.getString("fecha_actualizacion"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public Boolean eliminarRegistro(String dni) {
        if (buscarDni(dni)) {
            try {
                con = Conexion.establecerConexion();

                String sql = "DELETE FROM empleados WHERE dni = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, dni);
                ps.execute();

                return true;

            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    // Manejo de excepción SQLIntegrityConstraintViolationException
                    System.err.println("Error de violación de restricción de integridad: " + e.getMessage());
                } else {
                    // Manejo de otras excepciones SQLException
                    System.err.println("Error SQL: " + e.getMessage());
                }
                return false;
            }
        } else {
            return false;
        }
    }
}
