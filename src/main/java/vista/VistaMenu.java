package vista;

import controlador.ControladorEmpleados;
import database.Conexion;
import modelo.Empleados;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Jorge Monzón
 * @project personal-crud-java
 */
public class VistaMenu {

    Scanner scanner = new Scanner(System.in);
    ControladorEmpleados controladorEmpleados = new ControladorEmpleados();

    public void getMenu() {
        boolean continuar = true;
        char mensaje;
        int opcion;

        do {
            System.out.println("---------Menu----------");
            System.out.println("=======================");
            System.out.println("Opciones a escoger");
            System.out.println("[1] Añadir empleado");
            System.out.println("[2] Buscar empleado");
            System.out.println("[3] Eliminar empleado");
            System.out.println("[4] Mostrar Todos");
            System.out.println("[5] Filtrar por DNI");
            System.out.println("[6] Salir");

            System.out.println("=======================");

            System.out.print("Ingresar la opción:");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    Empleados empleados = this.entradaDatosEmpleados();
                    controladorEmpleados.insertarRegistros(empleados);
                    break;
                case 2:
                    System.out.print("Ingresar DNI: ");
                    String dniBuscar = scanner.nextLine();
                    controladorEmpleados.buscarDni(dniBuscar);

                    break;
                case 3:
                    System.out.println("Eliminar empleado");
                    break;
                case 4:
                    controladorEmpleados.mostrarRegistros();
                    break;
                case 5:
                    System.out.println("Filtrar por DNI");
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opción no valida");
                    break;

            }

//            validar si desea continuar
            System.out.print("Desea continuar [s/n]: ");
            mensaje = scanner.nextLine().charAt(0);

            if (mensaje == 's') {
                continuar = true;
            } else {
                continuar = false;
            }

        } while (continuar);


    }

    public Empleados entradaDatosEmpleados() {
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

    public void cierreOperaciones() {
        Conexion.cerrarConexion();
    }


}
