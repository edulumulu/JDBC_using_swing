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
public class PedirDatos {
    /**
     * Método que muestra por pantalla un texto y permite al usuario escoger un número entre el parametro numa y el numb
     * @param texto
     * @param numa
     * @param numb
     * @return 
     */
     public static int pedir_numero_enetero(String texto, int numa, int numb) {
        Scanner sc = new Scanner(System.in);

        int num = 0;
        while (true) {
            System.out.print(texto + ": ");
            try {
                int opc = sc.nextInt();
                if (opc >= numa && opc <= numb) {
                    num = opc;
                    break;
                } else {
                    System.out.println("Error en la entrada de datos, debes introducer el número de una de las opciones");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error en la entrada de datos, debes introducer el número de una de las opciones");
                sc.next();
            }
        }

        return num;
    }
     /**
      * Pide por pantalla al usuario un numero de telefono de 9 digitos exactos
      * @return 
      */
     public static int pedir_num_tlf(){
         Scanner sc = new Scanner(System.in);
        String telefono;
        boolean valido = false;

        do {
            System.out.print("Introduce un número de teléfono de 9 dígitos: ");
            telefono = sc.nextLine();

            
            if (telefono.matches("\\d{9}")) {
                valido = true;
            } else {
                System.out.println("Error: El número debe tener exactamente 9 dígitos y no contener letras.");
            }

        } while (!valido);

        return Integer.parseInt(telefono);
     }
     /**
      * Pide al usuario por pantalla un texto que no debe estar vacío ni compuesto de espacios en blanco
      * @param texto
      * @return 
      */
     public static String texto_no_vacio(String texto){
         Scanner sc = new Scanner(System.in);
         String line;
         while(true){
             System.out.print(texto+":");
              line = sc.nextLine().trim();
              if(!line.isEmpty()){
                  return line;
                  
              }else{
                  System.out.println("Debes escribir algún caracter, no puede quedarse el campo vacío");
              }
         }
     }
}
