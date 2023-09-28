package Tema2.Ejercicios

import javax.swing.*
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

class Exercici_2_2_Pantalla : JFrame() {
    val et_f = JLabel("Fitxer:")
    val fitxer = JTextField(25)
    val obrir = JButton("Obrir")
    val guardar = JButton("Guardar")
    val et_a = JLabel("Contingut:")
    val area = JTextArea(10, 50)
    val scrollPane = JScrollPane(area)

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        setLayout(GridLayout(2, 1))
        setTitle("Editor de text")

        val panell1 = JPanel(GridLayout(0, 1))
        val panell1_1 = JPanel(FlowLayout())
        panell1.add(panell1_1)
        panell1_1.add(et_f)
        panell1_1.add(fitxer)

        val panell1_2 = JPanel(FlowLayout())
        panell1.add(panell1_2)
        panell1_2.add(obrir)
        panell1_2.add(guardar)
        val panell2 = JPanel(GridLayout(0, 1))
        panell2.add(scrollPane)
        area.isEditable = true

        add(panell1)
        add(panell2)
        pack()

        obrir.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {
                val nomFitxer = fitxer.text
                try {
                    val reader = BufferedReader(FileReader(nomFitxer))
                    val contingut = StringBuilder()
                    var linia: String?
                    while (reader.readLine().also { linia = it } != null) {
                        contingut.append(linia).append("\n")
                    }
                    area.text = contingut.toString()
                    reader.close()
                } catch (ex: IOException) {
                    ex.printStackTrace()
                    JOptionPane.showMessageDialog(
                        this@Exercici_2_2_Pantalla,
                        "Error al obrir el fitxer: ${ex.message}",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    )
                }
            }
        })

        guardar.addActionListener(object : ActionListener {
            override fun actionPerformed(e: ActionEvent) {
                val nomFitxer = fitxer.text
                try {
                    val writer = BufferedWriter(FileWriter(nomFitxer))
                    writer.write(area.text)
                    writer.close()
                    JOptionPane.showMessageDialog(
                        this@Exercici_2_2_Pantalla,
                        "Fitxer guardat amb èxit",
                        "Èxit",
                        JOptionPane.INFORMATION_MESSAGE
                    )
                } catch (ex: IOException) {
                    ex.printStackTrace()
                    JOptionPane.showMessageDialog(
                        this@Exercici_2_2_Pantalla,
                        "Error al guardar el fitxer: ${ex.message}",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    )
                }
            }
        })
    }
}

private fun crearMostrarFinestra() {
    val frame = Exercici_2_2_Pantalla()
    frame.isVisible = true
}

fun main(args: Array<String>) {
    EventQueue.invokeLater(::crearMostrarFinestra)
}
