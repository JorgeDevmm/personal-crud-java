package controlador;

import database.Conexion;
import modelo.Empleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author Jorge Monzón
 * @project personal-crud-java
 */
public class ControladorEmpleados {

    private Connection con = null;

    public Empleados entradaDatosEmpleados() {
        Scanner scanner = new Scanner(System.in);
        String dni = "", nombre, apellidoPaterno, apellidoMaterno;

        System.out.println("Ingresar datos");
        System.out.println("==============");
        System.out.print("Ingresar Dni: ");
        dni = scanner.nextLine();
        System.out.print("Ingresar Nombre: ");
        nombre = scanner.nextLine();
        System.out.print("Ingresar Apellido Paterno: ");
        apellidoPaterno = scanner.nextLine();
        System.out.print("Ingresar Apellido Materno: ");
        apellidoMaterno = scanner.nextLine();

        Empleados empleados = new Empleados();
        empleados.setDni(dni);
        empleados.setNombre(nombre);
        empleados.setApellidoPaterno(apellidoPaterno);
        empleados.setApellidoMaterno(apellidoMaterno);

        return empleados;

    }

    public void insertarRegistros(Empleados empleados) {

        try {

            con = Conexion.establecerConexion();

            String sql = "INSERT INTO empleados.empleados (dni,nombre,apellido_paterno,apellido_materno)  VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, empleados.getDni());
            ps.setString(2, empleados.getNombre());
            ps.setString(3, empleados.getApellidoPaterno());
            ps.setString(4, empleados.getApellidoMaterno());

            ps.execute();
            System.out.println("Se Ingreso el registro");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarRegistros() {

    }

    public String buscarDni() {

        return "hola";
    }


}
