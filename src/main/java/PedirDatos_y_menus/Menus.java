/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PedirDatos_y_menus;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author eduardolucasmunozdelucas
 */
public class Menus {
    /**
     * Muestra un menu por pantalla que da la opción al usuario de cargar datos
     * de ejemplo en la BBDD o no
     *
     * @return --> boleano
     */
    public static boolean mostrar_menu_1() {
        Scanner sc = new Scanner(System.in);
        boolean cargardatos = true;

        while (true) {
            try {
                System.out.println("-------------------------");
                System.out.println("----- ELIGE UNA OPCIÓN: -----");
                System.out.println("(1) Quiero añadir por defecto varios empleados prefejados");
                System.out.println("(2) Quiero trabajar con la Base de datos vacía");
                System.out.print("Elige una opción: ");
                int opcion = sc.nextInt();
                if (opcion == 1) {
                    System.out.println("");
                    System.out.println("-------------------------");
                    System.out.println("Se está procediendo a añadir los datos a la BBDD.");
                    System.out.println("");
                    return true;
                } else if (opcion == 2) {
                    System.out.println("has elegido la opción 2, trabjaras con la BBDD vacía.");
                    return false;
                } else {
                    System.out.println("La opción no es válida, debe ser un 1 o un 2. Inténtelo de nuevo");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error en la entrada de datos, debes introducer el num 1 o el 2");
                sc.next();
            }
        }

    }
    /**
     * Muestra por pantalla el menú principal de la aplicación
     */
    public static void menu_principal(){
        System.out.println("----- ELIGE UNA OPCION -----");
            System.out.println("(1) - Insertar empleado");
            System.out.println("(2) - Modificar contraseña de un empleado existente");
            System.out.println("(3) - Modificar usuario-perfil de un empleado existente");
            System.out.println("(4) - Eliminar un empleado");
            System.out.println("(5) - Mostrar un listado de empleados");
            System.out.println("(6) - Salir del programa");
    }
    
    public static void menu_2(){
        System.out.println("-------------------------");
                System.out.println("----- ELIGE UNA OPCIÓN: -----");
                System.out.println("(1) Quiero utilizar la interfaz gráfica");
                System.out.println("(2) Quiero utilizar la consola de java");
    }
    
    
}
