/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author esdra
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        getAllUnidFromApi();
    }
    public static ArrayList<unidades> getAllUnidFromApi() {
        ArrayList<unidades> lista = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8180/ApiTest01/webresources/unidades/unidadJson");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int res = conn.getResponseCode();
            if (res != 200) {
                throw new RuntimeException("HttpResponseCode " + res);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JOptionPane.showMessageDialog(null, informationString);
                JSONParser parser = new JSONParser();
                JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(informationString));
                JSONObject obj = (JSONObject) dataObject.get(0);
                System.out.println(obj.toString());
            }
        } catch (Exception e) {
        }

        return lista;
    }
}
