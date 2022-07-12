/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entity.Unidades;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author esdra
 */
public class servicesUnidades {

    public static ArrayList<Unidades> getAllUnidFromApi() {
        ArrayList<Unidades> lista = new ArrayList<>();
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
                //JOptionPane.showMessageDialog(null, informationString);
                JSONParser parser = new JSONParser();
                JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(informationString));
                for (int i = 0; i < dataObject.size(); i++) {
                    JSONObject obj = (JSONObject) dataObject.get(i);
                    lista.add(getUnidadFromJson(obj));
                }

                return lista;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return lista;
        }
    }

    private static void sendPOST() throws IOException {
        final String POST_URL = "https://localhost:9090/SpringMVCExample/home";
        final String USER_AGENT = "Mozilla/5.0";
        final String POST_PARAMS = "userName=Pankaj";
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }

    private boolean sendRequest(HttpURLConnection con) {
        boolean res = false;
        try {
            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
                res = true;
            } else {
                System.out.println("POST request not worked");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            res = false;
        }
        return res;
    }

    public void sendData() {
        try {
            JSONObject obj = new JSONObject();
            //obj.put("idBus", lista.get(i).getIdbus());
            obj.put("fecha_ingreso", new Date().toString());
            obj.put("modelo", 5555);
            obj.put("placa", "lopl");
            obj.put("marca", "lopes");
            obj.put("tipo", 2);
            obj.put("estado", 0);

            URL url = new URL("http://localhost:8180/ApiTest01/webresources/unidades/addProductos");
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.setDoOutput(true);
            httpcon.setRequestMethod("POST");
            httpcon.setRequestProperty("Accept", "application/json");
            httpcon.setRequestProperty("Content-Type", "application/json");
            //Cookie cookie=new Cookie("user", "abc");
            //cookie.setValue("store");
            //httpcon.setRequestProperty("Accept", "application/json");
            //httpcon.setRequestProperty("Cookie", cookie.getValue());

            OutputStreamWriter output = new OutputStreamWriter(httpcon.getOutputStream());
            System.out.println(obj);
            output.write(obj.toString());
            httpcon.connect();
            String output1 = httpcon.getResponseMessage();
            System.out.println(output1);
            sendRequest(httpcon);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    /*
    public static void postUnidad() throws MalformedURLException, IOException {
        URL url = new URL("https://reqres.in/api/users");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        JSONObject obj = new JSONObject();
            obj.put("idBus", lista.get(i).getIdbus());
            obj.put("fecha_ingreso", lista.get(i).getFecha_ingreso().toString());
            obj.put("modelo", lista.get(i).getModelo());
            obj.put("placa", lista.get(i).getPlaca());
            obj.put("marca", lista.get(i).getMarca());
            obj.put("tipo", lista.get(i).getTipo());
            obj.put("estado", lista.get(i).getActivo());
            String jsonInputString = "{"name": "Upendra", "job": "Programmer"}";
    try ( OutputStream os = con.getOutputStream()) {
            byte[] input = obj.getBytes("UTF-8");
            os.write(input, 0, input.length);
        }
        try ( BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }

    }

     */
    public static Unidades getUnidadFromJson(JSONObject obj) throws ParseException {
        System.out.println(obj.toString());
        Unidades un = new Unidades();
        un.setIdbus(Integer.parseInt(obj.get("idBus").toString()));
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(obj.get("fecha_ingreso").toString());
        un.setFecha_ingreso(date);
        un.setMarca(obj.get("marca").toString());
        un.setActivo(Integer.parseInt(obj.get("estado").toString()));
        un.setTipo(Integer.parseInt(obj.get("tipo").toString()));
        un.setModelo(Integer.parseInt(obj.get("modelo").toString()));
        un.setPlaca(obj.get("placa").toString());
        return un;
    }

}
