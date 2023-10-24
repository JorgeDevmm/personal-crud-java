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


    public void insertarRegistros(Empleados empleados) {

        try {

            con = Conexion.establecerConexion();

            String sql = "INSERT INTO empleados.empleados (dni,nombre,apellido_paterno,apellido_materno)  VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, empleados.getDni());
            ps.setString(2, empleados.getNombre());
            ps.setString(3, empleados.getApellidoPaterno());
            ps.setString(4, empleados.getApellidoMaterno());

//            validar si existe dni
            if (this.buscarDni(empleados.getDni())) {
                System.out.println("DNI: " + empleados.getDni() + " ya existe");
            } else {
                ps.execute();
                System.out.println("Se Ingreso el registro");
            }


        } catch (SQLException e) {
            e.printStackTrace();
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
                System.out.println("Se encontró el: " + dniEncontrado);
            } else {
                System.out.println("DNI no encontrado");
            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return encontrado;
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
}
