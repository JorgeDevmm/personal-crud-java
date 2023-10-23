package vista;

import controlador.ControladorEmpleados;

import java.util.Scanner;

/**
 * @author Jorge Monzón
 * @project personal-crud-java
 */
public class VistaMenu {

    public void getMenu() {
        Scanner scanner = new Scanner(System.in);
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
                    ControladorEmpleados controladorEmpleados = new ControladorEmpleados();
                    controladorEmpleados.insertarRegistros(controladorEmpleados.entradaDatosEmpleados());
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opción no valida");
                    break;

            }

//            validar si desea continuar
            System.out.println("Desea continuar [s/n]");
            mensaje = scanner.nextLine().charAt(0);

            if (mensaje == 's') {
                continuar = true;
            } else {
                continuar = false;
                System.out.println("No desea continuar");
            }

        } while (continuar);


        System.out.println("Finalizo programa");

    }


}
