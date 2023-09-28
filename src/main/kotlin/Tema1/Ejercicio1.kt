import java.io.File
import java.util.Scanner

fun main(args: Array<String>) {
    val f = File.listRoots()[0]
    println("----------------------------------------------")
    println("Ruta del fichero: " + f.path)
    if (f.exists()) {
        if (f.isDirectory()) {
            ListaDirectorio(f)
        } else
            println("No es un directorio")
    } else
        println("No existe el directorio")
}

fun ListaDirectorio(f: File) {
    val s = "Lista de ficheros y directorios del directorio " + f.getCanonicalPath()
    println(s)
    println("-".repeat(s.length))

    val directorioPadre = f.parentFile
    if (directorioPadre != null) {
        println("0. Directorio Padre: $directorioPadre")
    }

    val filesAndDirectories = f.listFiles()?.sorted()
    val scanner = Scanner(System.`in`)
    while (true) {
        for ((index, e) in filesAndDirectories?.withIndex() ?: emptyList<File>().withIndex()) {
            val enumeration = index + 1
            if (e.isFile()) {
                println("$enumeration. ${e.name}\t ${e.length()}")
            }
            if (e.isDirectory()) {
                println("$enumeration. ${e.name}\t <Directorio>")
            }
        }

        print("Ingrese un número para navegar o -1 para salir: ")
        val numero = scanner.nextInt()

        if (numero == -1) {
            println("Saliendo del programa")
            break
        } else if (numero == 0 && directorioPadre != null) {
            ListaDirectorio(directorioPadre)
        } else if (numero >= 1 && numero <= (filesAndDirectories?.size ?: 0)) {
            val seleccionado = filesAndDirectories?.get(numero - 1)
            if (seleccionado?.isDirectory() == true) {
                ListaDirectorio(seleccionado)
            } else {
                println("No se puede navegar a un archivo. Intente nuevamente.")
            }
        } else {
            println("Número inválido. Intente nuevamente.")
        }
    }
}
