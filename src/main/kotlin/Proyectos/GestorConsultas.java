package Proyectos;

import java.io.*;
import java.util.HashSet;

public class GestorConsultas {
    private RandomAccessFile stream;

    public GestorConsultas() {
        creaFichero("discosDAML.dat");
    }

    private void creaFichero(String nombreFichero) {
        try {
            boolean exists = (new File(nombreFichero)).exists();
            stream = new RandomAccessFile(nombreFichero, "rw");
            if (!exists) {
                creaDiscosPorDefecto();
            }
        } catch (IOException e) {
            System.out.println("Error al abrir el fichero: " + nombreFichero);
            System.exit(0);
        }
    }

    private void creaDiscosPorDefecto() throws IOException {
        Disco disco1 = new Disco(1, "Que voy a hacer", "Los Planetas", 20.0, 5);
        disco1.escribeEnFichero(stream);
        Disco disco2 = new Disco(2, "La voz del presidente", "Viva Suecia", 35.0, 1);
        disco2.escribeEnFichero(stream);
        Disco disco3 = new Disco(3, "La revolución sexual", "La casa azul", 20.0, 10);
        disco3.escribeEnFichero(stream);
        Disco disco4 = new Disco(4, "Finisterre", "Vetusta Morla", 40.0, 5);
        disco4.escribeEnFichero(stream);
        Disco disco5 = new Disco(5, "Paradise", "Coldplay", 35.0, 2);
        disco5.escribeEnFichero(stream);
    }

    public void cierraGestor() {
        try {
            stream.close();
        } catch (IOException e) {
            System.out.println("No se ha podido cerrar el fichero");
        }
    }

    private long buscaCodigo(int codigoBuscado) {
        try {
            stream.seek(0);
            long posicion = 0;
            while (stream.getFilePointer() < stream.length()) {
                Disco disco = Disco.leeDeFichero(stream);
                if (disco.getCodigo() == codigoBuscado) {
                    return posicion;
                }
                posicion = stream.getFilePointer();
            }
        } catch (IOException e) {
            System.out.println("Error al buscar el código del disco");
        }
        return -1;
    }

    private void posicionaFichero(long posicion) {
        try {
            stream.seek(posicion);
        } catch (IOException e) {
            System.out.println("Error posicionando el puntero al inicio del fichero");
            System.exit(0);
        }
    }

    public String[] listaAutores() {
        HashSet<String> autores = new HashSet<>();
        try {
            stream.seek(0);
            while (stream.getFilePointer() < stream.length()) {
                Disco disco = Disco.leeDeFichero(stream);
                autores.add(disco.getAutor());
            }
        } catch (IOException e) {
            System.out.println("Error al generar la lista de autores");
        }
        return hashArray(autores);
    }

    private String[] hashArray(HashSet<String> autores) {
        String[] lista = new String[autores.size()];
        int i = 0;
        for (String val : autores) lista[i++] = val;
        return lista;
    }

    public String[] buscaAutor(String autorBuscado) {
        HashSet<String> discosDelAutor = new HashSet<>();
        try {
            stream.seek(0);
            while (stream.getFilePointer() < stream.length()) {
                Disco disco = Disco.leeDeFichero(stream);
                if (disco.getAutor().equalsIgnoreCase(autorBuscado)) {
                    discosDelAutor.add(disco.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al buscar discos del autor");
        }
        return hashArray(discosDelAutor);
    }

    public String altaDisco(int codigoBuscado) {
        long posicion = buscaCodigo(codigoBuscado);
        if (posicion != -1) {
            try {
                stream.seek(posicion);
                Disco disco = Disco.leeDeFichero(stream);
                disco.setCantidad(disco.getCantidad() + 1);
                stream.seek(posicion);
                disco.escribeEnFichero(stream);
                return disco.toString();
            } catch (IOException e) {
                System.out.println("Error al dar de alta el disco");
            }
        }
        return "";
    }

    public String bajaDisco(int codigoBuscado) {
        long posicion = buscaCodigo(codigoBuscado);
        if (posicion != -1) {
            try {
                stream.seek(posicion);
                Disco disco = Disco.leeDeFichero(stream);
                if (disco.getCantidad() > 0) {
                    disco.setCantidad(disco.getCantidad() - 1);
                    stream.seek(posicion);
                    disco.escribeEnFichero(stream);
                    return disco.toString();
                } else {
                    return "No hay ejemplares disponibles para dar de baja.";
                }
            } catch (IOException e) {
                System.out.println("Error al dar de baja el disco");
            }
        }
        return "";
    }
}
