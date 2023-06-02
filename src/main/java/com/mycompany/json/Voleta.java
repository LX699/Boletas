package com.mycompany.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Voleta {

    private static String directorio;
    private static File carpetaBoletas;
    private static String datos;
    private static File CarpetaDeHoy;

    public Voleta(String datos) {

        try {
            getDirectorioDesdeArchivo();
            Voleta.datos = datos;
            carpetasDatos();
            carpetaDiaria();
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }

    private static void carpetasDatos() {
        Voleta.carpetaBoletas = new File(directorio + "Boletas");

        if (carpetaBoletas.exists()) {

        } else {
            carpetaBoletas.mkdirs();
            System.out.println("Carperta Creada");
        }

    }

    private static void carpetaDiaria() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-YYYY");
        String nombreCarpeta = formatoFecha.format(fecha);
        CarpetaDeHoy = new File(carpetaBoletas + File.separator + nombreCarpeta);
        if (CarpetaDeHoy.exists()) {

        } else {
            CarpetaDeHoy.mkdir();

        }
        generarVoleta();
    }

    private static void generarVoleta() {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("HH-mm-ss");
        String nombreArchivo = formatoFecha.format(fecha);
        try {
            FileWriter boletaActual = new FileWriter(CarpetaDeHoy + File.separator + nombreArchivo + ".txt");
            boletaActual.write(Voleta.datos);
            boletaActual.close();
        } catch (IOException ex) {
            System.out.println("Error");
        }

    }

    public static void setDirectorio() { //Cambia el directorio donde se guardan las boletas ademas lo guarda en Data/Directorio.txt
        File carpetaData = new File("Data");
        if (carpetaData.exists()) {

        } else {
            carpetaData.mkdir();
        }
        //JOptionPane.showMessageDialog(null, "Recuerde al final del directorio Agregar el separador \n de archivos correpondiente a  su sistema operativo \n Ej: / , \\ ");
        String nuevoDirectorio = JOptionPane.showInputDialog("ingrese El Directorio Donde se guardaran las Boletas");
        File ruta = new File(nuevoDirectorio);
        if (ruta.isDirectory()) {
            Voleta.directorio = nuevoDirectorio+File.separator;
            

            try {

                FileWriter nota = new FileWriter("Data" + File.separator + "Directorio.txt");
                nota.write(directorio);
                nota.close();
            } catch (IOException ex) {
                System.out.println("Error");
            }
        } else {

            JOptionPane.showMessageDialog(null, "Ingrese un directorio Valido");
            setDirectorio();

        }
    }

    private static void getDirectorioDesdeArchivo() throws IOException {
        File archivoConDirectorio = new File("Data" + File.separator + "Directorio.txt");
        if (archivoConDirectorio.exists()) {
            FileReader archivo = new FileReader("Data" + File.separator + "Directorio.txt");
            BufferedReader buffer = new BufferedReader(archivo);
            String rutaEnArchivo = buffer.readLine();
            File posibleRuta = new File(rutaEnArchivo);
            if (posibleRuta.isDirectory()) {
                Voleta.directorio = rutaEnArchivo;
            } else {
                setDirectorio();
            }
        } else {
            setDirectorio();
        }
    }
}
