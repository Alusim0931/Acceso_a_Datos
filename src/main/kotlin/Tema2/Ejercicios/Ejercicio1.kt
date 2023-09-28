package Tema2.Ejercicios

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class FitxerImatge(fEnt: File) {
    private val f: File

    init {
        // Constructor
        if (!fEnt.exists() || !fEnt.isFile || !fEnt.name.endsWith(".bmp")) {
            throw IllegalArgumentException("El fitxer no existeix o no té l'extensió .bmp")
        }

        f = fEnt
    }

    fun transformaNegatiu() {
        // Transformar a negativo y guardar en _n.bmp
        val input = FileInputStream(f)
        val outputFileName = f.nameWithoutExtension + "_n.bmp"
        val output = FileOutputStream(outputFileName)

        try {
            // Copiar los primeros 54 bytes sin modificar
            val header = ByteArray(54)
            input.read(header)
            output.write(header)

            // Transformar y copiar los bytes RGB
            var byteRead = input.read()
            while (byteRead != -1) {
                val negatedByte = 255 - byteRead
                output.write(negatedByte)
                byteRead = input.read()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            input.close()
            output.close()
        }
    }

    fun transformaObscur() {
        // Transformar a una imagen más oscura y guardar en _o.bmp
        val input = FileInputStream(f)
        val outputFileName = f.nameWithoutExtension + "_o.bmp"
        val output = FileOutputStream(outputFileName)

        try {
            // Copiar los primeros 54 bytes sin modificar
            val header = ByteArray(54)
            input.read(header)
            output.write(header)

            // Transformar y copiar los bytes RGB
            var byteRead = input.read()
            while (byteRead != -1) {
                val darkenedByte = byteRead / 2
                output.write(darkenedByte)
                byteRead = input.read()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            input.close()
            output.close()
        }
    }
}

fun main() {
    val inputFile = File("penyagolosa.bmp") // Ruta relativa al archivo
    val imagen = FitxerImatge(inputFile)

    println("Realizando transformación a negativo...")
    imagen.transformaNegatiu()
    println("Transformación a negativo completada.")

    println("Realizando transformación a imagen más oscura...")
    imagen.transformaObscur()
    println("Transformación a imagen más oscura completada.")
}
