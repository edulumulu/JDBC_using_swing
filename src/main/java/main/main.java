/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package main;

import GestionBBDD.Gestion_BBDD;
import Interfaz_gráfica.MenuPrincipal;
import PedirDatos_y_menus.Menus;
import static PedirDatos_y_menus.Menus.menu_principal;
import static PedirDatos_y_menus.Menus.mostrar_menu_1;
import PedirDatos_y_menus.PedirDatos;
import static PedirDatos_y_menus.PedirDatos.pedir_numero_enetero;
import java.sql.Connection;
import java.util.ArrayList;
import modelo.Empleado;

/**
 *
 * @author eduardolucasmunozdelucas
 */
public class main {

    static Connection conexion = null;
    private static ArrayList<Empleado> lista_empleados = new ArrayList<>();

    public static void main(String[] args) {

        Gestion_BBDD gestion_conexion = new Gestion_BBDD();

        // Cone ste método además de conectarse se crea automáticamente la BbDD
        conexion = gestion_conexion.Conectarse();

        lista_empleados = gestion_conexion.cargar_listado_empleados(conexion);
        texto_listado_empleados();
        if (lista_empleados.isEmpty()) {
            System.out.println("");
            System.out.println("Actualmente no hay ninguún registro.");
            //Menú que da la opción de cargar datos de ejmpo a la base de datos
            if (mostrar_menu_1() == true) {
                // Con este método se cargan por defecto varios Empleados en la BBDD
                gestion_conexion.Insertar_empleados_predeterminado(conexion);
                lista_empleados = gestion_conexion.cargar_listado_empleados(conexion);
                System.out.println("Datos de ejemplo añadidos con éxito:");
                System.out.println("");

                recorer_lista_empleados();
            }
        } else {
            recorer_lista_empleados();

        }

        System.out.println("");
        System.out.println("-------------------------");
        System.out.println("COMIENZA EL PROGRMA DE M07 - TEMA2 -JDBC");

        Menus.menu_2();
        if (pedir_numero_enetero("Escoge una opción", 1, 2) == 1) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setLocationRelativeTo(null);
            menu.setVisible(true);
        } else {
            boolean ok = true;
            while (ok) {
                System.out.println("");

                menu_principal();

                int opc = pedir_numero_enetero("Elige una opcion", 1, 6);
                switch (opc) {
                    case 1:
                        System.out.println("-------------------------");
                        System.out.println("----- ESCRIBE LOS SIGUIENTES DATOS: ------");
                        System.out.println("");
                        String user = PedirDatos.texto_no_vacio("Escribe el usuario del empleado");
                        String password = PedirDatos.texto_no_vacio("Escribe la contraseña del empleado");
                        String name = PedirDatos.texto_no_vacio("Escribe el nombre del empleado");
                        String surname = PedirDatos.texto_no_vacio("Escribe el apellido del empleado");
                        int tlf = PedirDatos.pedir_num_tlf();
                        String email = PedirDatos.texto_no_vacio("Escribe el email del empleado");
                        System.out.println("");

                        Empleado emp = new Empleado(user, password, name, surname, tlf, email);
                        if (lista_empleados.add(emp)) {
                            if (gestion_conexion.Insertar_empleado(conexion, emp)) {
                                System.out.println("Empleado " + emp.getName() + " " + emp.getSurname() + " ha sido añadido a la BBDD con éxito");
                                texto_listado_empleados();
                                recorer_lista_empleados();

                            } else {
                                System.out.println("No se ha podido añadir el empleado a la BBDD");
                            }
                        }

                        break;
                    case 2:
                        if (!lista_empleados.isEmpty()) {
                            texto_listado_empleados();
                            // Mostramos la lista actual de empleados con sus índices
                            for (int i = 0; i < lista_empleados.size(); i++) {
                                System.out.println("Empleado " + (i + 1) + " --> " + lista_empleados.get(i).getName() + " "
                                        + lista_empleados.get(i).getSurname() + " Contraseña --> " + lista_empleados.get(i).getPassword());
                            }

                            System.out.println("");
                            int indice = pedir_numero_enetero("Escribe el nº del empleado cuya contraseña quieres cambiar", 1, lista_empleados.size() + 1);
                            System.out.println("");

                            int idEmpleadoBBDD = lista_empleados.get(indice - 1).getId();
                            String nombreEmpleado = lista_empleados.get(indice - 1).getName() + " " + lista_empleados.get(indice - 1).getSurname();
                            String vieja_contrasena = lista_empleados.get(indice - 1).getPassword();
                            String nueva_contrasena;
                            while (true) {
                                nueva_contrasena = PedirDatos.texto_no_vacio("Escribe la nueva contraseña del empleado");

                                if (!nueva_contrasena.equalsIgnoreCase(vieja_contrasena)) {
                                    lista_empleados.get(indice - 1).setPassword(nueva_contrasena);
                                    break;
                                } else {
                                    System.out.println("La contraseña debe ser diferente de --> " + vieja_contrasena);
                                }

                            }

                            if (lista_empleados.get(indice - 1).getPassword().equalsIgnoreCase(nueva_contrasena)
                                    && gestion_conexion.modificar_campo(conexion, nueva_contrasena, idEmpleadoBBDD, "password")) {
                                System.out.println("");
                                System.out.println("Empleado --> " + nombreEmpleado);
                                System.out.println("\tContraseña Antigua --> " + vieja_contrasena);
                                System.out.println("\tContraseña Modificada --> " + lista_empleados.get(indice - 1).getPassword());
                                texto_listado_empleados();
                                recorer_lista_empleados();
                            } else {
                                System.out.println("Error: No se pudo cambiar la contraseña.");
                            }

                        } else {
                            System.out.println("-------------------------");
                            System.out.println("Actualmente no hay ningún empleado registrado.");
                        }

                        break;
                    case 3:
                        if (!lista_empleados.isEmpty()) {
                            texto_listado_empleados();
                            // Mostramos la lista actual de empleados con sus índices
                            for (int i = 0; i < lista_empleados.size(); i++) {
                                System.out.println("Empleado " + (i + 1) + " --> " + lista_empleados.get(i).getName() + " "
                                        + lista_empleados.get(i).getSurname() + " Usuario --> " + lista_empleados.get(i).getUser());
                            }

                            System.out.println("");
                            int indice = pedir_numero_enetero("Escribe el nº del empleado cuyo usuario quieres cambiar", 1, lista_empleados.size() + 1);
                            System.out.println("");

                            int idEmpleadoBBDD = lista_empleados.get(indice - 1).getId();
                            String nombreEmpleado = lista_empleados.get(indice - 1).getName() + " " + lista_empleados.get(indice - 1).getSurname();
                            String viejo_usuario = lista_empleados.get(indice - 1).getUser();

                            String nuevo_usuario;
                            while (true) {
                                nuevo_usuario = PedirDatos.texto_no_vacio("Escribe el nuevo usuario del empleado");

                                if (!nuevo_usuario.equalsIgnoreCase(viejo_usuario)) {
                                    lista_empleados.get(indice - 1).setUser(nuevo_usuario);
                                    break;
                                } else {
                                    System.out.println("La nuevo usuario debe ser diferente de --> " + viejo_usuario);
                                }

                            }

                            if (lista_empleados.get(indice - 1).getUser().equalsIgnoreCase(nuevo_usuario)
                                    && gestion_conexion.modificar_campo(conexion, nuevo_usuario, idEmpleadoBBDD, "user")) {
                                System.out.println("Empleado --> " + nombreEmpleado);
                                System.out.println("\tUsuario Antiguo --> " + viejo_usuario);
                                System.out.println("\tUsuario Modificado --> " + lista_empleados.get(indice - 1).getUser());
                                texto_listado_empleados();
                                recorer_lista_empleados();
                            } else {
                                System.out.println("Error: No se pudo cambiar el usuario.");
                            }

                        } else {
                            System.out.println("-------------------------");
                            System.out.println("Actualmente no hay ningún empleado registrado.");
                        }

                        break;
                    case 4:

                        if (!lista_empleados.isEmpty()) {
                            texto_listado_empleados();
                            // Mostramos la lista actual de empleados con sus índices
                            for (int i = 0; i < lista_empleados.size(); i++) {
                                System.out.println("Empleado " + (i + 1) + " --> " + lista_empleados.get(i).getName() + " " + lista_empleados.get(i).getSurname());
                            }

                            System.out.println("");
                            int indice = pedir_numero_enetero("Escribe el nº del empleado que quieras borrar", 1, lista_empleados.size() + 1);
                            System.out.println("");

                            int idEmpleadoBBDD = lista_empleados.get(indice - 1).getId();
                            String nombreEmpleado = lista_empleados.get(indice - 1).getName() + " " + lista_empleados.get(indice - 1).getSurname();

                            if (gestion_conexion.borrar_empleado(conexion, idEmpleadoBBDD)) {
                                System.out.println("El empleado --> " + nombreEmpleado + " con id " + idEmpleadoBBDD + " ha sido borrado.");

                                // Eliminamos el empleado de la lista en memoria
                                lista_empleados.remove(indice - 1);

                                texto_listado_empleados();
                                recorer_lista_empleados();
                            } else {
                                System.out.println("Error: No se pudo borrar el empleado.");
                            }
                        } else {
                            System.out.println("-------------------------");
                            System.out.println("Actualmente no hay ningún empleado registrado.");
                        }

                        break;
                    case 5:
                        texto_listado_empleados();
                        recorer_lista_empleados();

                        break;
                    case 6:
                        System.out.println("");
                        System.out.println("-------------------------");
                        System.out.println("Muchas gracias, hasta la próxima");
                        ok = false;
                        break;
                    default:

                }
            }
        }

    }

    /**
     * Muestra por pantalla la cabecera previa a mostrar listado de empleado
     */
    public static void texto_listado_empleados() {
        System.out.println("");
        System.out.println("-------------------------");
        System.out.println("LISTADO ACTUAL DE EMPLEADOS (Total --> " + lista_empleados.size() + ")");
        System.out.println("");
    }

    /**
     * Muestra por pantalla un listado de todos los datos de los empleados
     */
    public static void recorer_lista_empleados() {
        for (Empleado empl : lista_empleados) {
            System.out.println(empl.toString());
        }
    }

}
