package com.mycompany.json;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class NumeroVoleta {

    public NumeroVoleta() {
        guardar();
    }

    private int numero = 1;

    private static void guardar() {
        CrearCarpeta();
        int numero = 1;
        try {
            Gson objJson = new Gson();
            String jsonString = objJson.toJson(numero, int.class);
            FileWriter writer = new FileWriter("Data" + File.separator + "Numero_Boletas.json");
            writer.write(jsonString);
            System.out.println(jsonString);
        } catch (Exception e) {
        }
        leer();
    }

    private static void leer() {
        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("Data" + File.separator + "Numero_Boletas.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int numero = gson.fromJson(br, int.class);
        System.out.println("el numero reculiao es "+ numero);
    }

    private static void CrearCarpeta() {
        File Directorio = new File("Data");
        Directorio.mkdir();

    }
}
