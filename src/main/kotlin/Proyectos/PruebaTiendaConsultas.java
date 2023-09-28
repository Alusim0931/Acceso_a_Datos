package Proyectos;

import Proyectos.GestorConsultas;

import java.util.Scanner;

import java.util.Scanner;

public class PruebaTiendaConsultas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorConsultas gestor = new GestorConsultas();
        boolean haComprado = false; // Variable para rastrear si el usuario ha comprado un disco

        int numero;

        do {
            System.out.println("Menu: ");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("1. Salir del programa cerrando el gestor");
            System.out.println("2. Listar los autores en el catálogo de la tienda");
            System.out.println("3. Buscar un autor o grupo con un nombre dado y mostrar sus discos en la tienda");
            System.out.println("4. Comprar un disco con un código dado y mostrar sus datos");
            System.out.println("5. Revender a la tienda un ejemplar de un disco de su catálogo con un código dado y mostrar sus datos");

            numero = scanner.nextInt();

            switch (numero) {
                case 1:
                    System.out.println("Saliendo del programa...");
                    gestor.cierraGestor();
                    break;
                case 2:
                    System.out.println("Listando autores en el catálogo de la tienda:");
                    String[] autores = gestor.listaAutores();
                    for (String autor : autores) {
                        System.out.println(autor);
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el nombre del autor o grupo a buscar:");
                    scanner.nextLine();
                    String autorBuscado = scanner.nextLine();
                    System.out.println("Buscando discos de " + autorBuscado + ":");
                    String[] discosDelAutor = gestor.buscaAutor(autorBuscado);
                    for (String disco : discosDelAutor) {
                        System.out.println(disco);
                    }
                    break;
                case 4:
                    if (haComprado) {
                        System.out.println("Ya has comprado un disco y no puedes comprar otro.");
                    } else {
                        System.out.println("Ingrese el código del disco a comprar:");
                        int codigoCompra = scanner.nextInt();
                        String resultadoCompra = gestor.altaDisco(codigoCompra);
                        if (!resultadoCompra.isEmpty()) {
                            System.out.println("Disco comprado:\n" + resultadoCompra);
                            haComprado = true; // Actualizar el estado de compra
                        } else {
                            System.out.println("No se pudo realizar la compra.");
                        }
                    }
                    break;
                case 5:
                    if (!haComprado) {
                        System.out.println("No has comprado ningún disco, no puedes vender ninguno.");
                    } else {
                        System.out.println("Ingrese el código del disco a revender:");
                        int codigoRevender = scanner.nextInt();
                        String resultadoRevender = gestor.bajaDisco(codigoRevender);
                        if (!resultadoRevender.isEmpty()) {
                            System.out.println("Disco revendido:\n" + resultadoRevender);
                        } else {
                            System.out.println("No se pudo realizar la venta.");
                        }
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción válida.");
            }
        } while (numero != 1);

        scanner.close();
    }
}

