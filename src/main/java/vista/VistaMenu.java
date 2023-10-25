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
    String dni = "", nombre, apellidoPaterno, apellidoMaterno;

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
            System.out.println("[5] Actualizar datos");
            System.out.println("[6] Salir");

            System.out.println("=======================");

            System.out.print("Ingresar la opción:");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    Empleados empleados = entradaDatosEmpleados();
                    if (controladorEmpleados.insertarRegistros(empleados)) {
                        System.out.println("Se Ingreso el registro");
                    } else {
                        System.out.println("DNI: " + empleados.getDni() + " ya existe");
                    }
                    break;
                case 2:

                    String dniABuscar = IngresoDni();

                    if (controladorEmpleados.buscarDni(dniABuscar)) {
                        System.out.println("Se encontró DNI");
                        controladorEmpleados.mostrarRegistro(dniABuscar);
                    } else {
                        System.out.println("No se encontró el DNI");
                    }
                    break;
                case 3:
                    String dniAEliminar = IngresoDni();
                    if (controladorEmpleados.eliminarRegistro(dniAEliminar)) {
                        System.out.println("Se elimino el registro");
                    } else {
                        System.out.println("No se pudo eliminar registro");
                    }
                    break;
                case 4:
                    controladorEmpleados.mostrarRegistros();
                    break;
                case 5:
                    String dniActualizar = IngresoDni();
                    if(controladorEmpleados.buscarDni(dniActualizar)){
                        DatosActualizar(new Empleados());
                    }else{
                        System.out.println("Dni encontrado");
                    }


                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no valida");
                    break;

            }

            if (opcion != 6) {
                //            validar si desea continuar
                System.out.print("Desea continuar [s/n]: ");
                mensaje = scanner.nextLine().charAt(0);

                if (mensaje == 's') {
                    continuar = true;
                } else {
                    continuar = false;
                }
            }


        } while (continuar);


    }

    public Empleados entradaDatosEmpleados() {
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

    public String IngresoDni() {
        System.out.print("Ingresar DNI: ");
        String dniBuscar = scanner.nextLine();

        return dniBuscar;
    }

    public Empleados DatosActualizar(Empleados empleados) {
        System.out.println("Ingresar la opción de datos que desea actualizar");
        int opcionDatos = scanner.nextInt();
        scanner.nextLine();

        switch (opcionDatos) {
            case 1:
                System.out.println("Actualizar DNI");
                System.out.print("Ingresar Dni: ");
                dni = scanner.nextLine();
                empleados.setDni(dni);
                break;
            case 2:
                System.out.println("Actualizar Nombre");
                System.out.print("Ingresar Nombre: ");
                nombre = scanner.nextLine();
                empleados.setNombre(nombre);
                break;
            case 3:
                System.out.println("Actualizar Apellido Paterno");
                System.out.print("Ingresar Apellido Paterno: ");
                apellidoPaterno = scanner.nextLine();
                empleados.setApellidoPaterno(apellidoPaterno);
                break;
            case 4:
                System.out.println("Actualizar Apellido Paterno");
                System.out.print("Ingresar Apellido Materno: ");
                apellidoMaterno = scanner.nextLine();
                empleados.setApellidoMaterno(apellidoMaterno);
                break;
            default:
                break;
        }

        return empleados;

    }

}
