package Proyectos;

import java.io.*;
import java.util.Scanner;

public class Disco implements Serializable {
    private static final long serialVersionUID = 1L;

    private int codigo;
    private String titulo;
    private String autor;
    private double precio;
    private int cantidad;

    public Disco() {
    }

    public Disco(int codigo, String titulo, String autor, double precio, int cantidad) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "TITULO: " + this.titulo + ". CODIGO: " + this.codigo + "\n" +
                "AUTOR: " + this.autor + "\n" +
                "PRECIO: " + this.precio + ". CANTIDAD: " + this.cantidad;
    }

    public void leeDeTeclado() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código del disco: ");
        this.codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea pendiente

        System.out.print("Ingrese el título del disco: ");
        this.titulo = scanner.nextLine();

        System.out.print("Ingrese el autor o grupo del disco: ");
        this.autor = scanner.nextLine();

        System.out.print("Ingrese el precio del disco: ");
        this.precio = scanner.nextDouble();

        System.out.print("Ingrese la cantidad de ejemplares disponibles: ");
        this.cantidad = scanner.nextInt();
    }

    public void escribeEnFichero(RandomAccessFile stream) throws IOException {
        stream.writeInt(this.codigo);
        stream.writeUTF(this.titulo);
        stream.writeUTF(this.autor);
        stream.writeDouble(this.precio);
        stream.writeInt(this.cantidad);
    }

    public static Disco leeDeFichero(RandomAccessFile stream) throws EOFException, IOException {
        int codigo = stream.readInt();
        String titulo = stream.readUTF();
        String autor = stream.readUTF();
        double precio = stream.readDouble();
        int cantidad = stream.readInt();

        return new Disco(codigo, titulo, autor, precio, cantidad);
    }
}
