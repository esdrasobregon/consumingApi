/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entity.Unidades;
import entity.tipoUnidades;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static services.servicesUnidades.getUnidadFromJson;

/**
 *
 * @author esdra
 */
public class serviceTipoUnidades {

    public static ArrayList<tipoUnidades> getAllTposFromApi() {
        ArrayList<tipoUnidades> lista = new ArrayList<>();
        try {
            URL url = new URL("http://localhost:8180/ApiTest01/webresources/tipoUnidades/TipoUnidadesJson");
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
                //JOptionPane.showMessageDialog(null, informationString);
                JSONParser parser = new JSONParser();
                JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(informationString));
                for (int i = 0; i < dataObject.size(); i++) {
                    JSONObject obj = (JSONObject) dataObject.get(i);
                    //JOptionPane.showMessageDialog(null, obj.toString());
                    lista.add(services.serviceTipoUnidades.getTipoUnidadFromJson(obj));
                }
                conn.disconnect();
                return lista;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: " + e.getMessage());
            return lista;
        }
    }

    public static tipoUnidades getTipoUnidadFromJson(JSONObject obj) {
        //System.out.println(obj.toString());
        tipoUnidades un = new tipoUnidades();
        try {
            un.setIdtipo(Integer.parseInt(obj.get("idtipo").toString()));
            un.setPuertas(Integer.parseInt(obj.get("puertas").toString()));
            un.setDescripcion(obj.get("descripcion").toString());
            return un;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

}
