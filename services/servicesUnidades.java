/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entity.Unidades;
import entity.Usuario;
import entity.apiCode;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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
import org.apache.commons.io.IOUtils;

//https://commons.apache.org/proper/commons-io/download_io.cgi
/**
 *
 * @author esdra
 */
public class servicesUnidades {

    public ArrayList<Unidades> getAllUnidFromApi() {
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
                    //JOptionPane.showMessageDialog(null, obj.toString());
                    lista.add(getUnidadFromJson(obj));
                }
                conn.disconnect();
                return lista;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: " + e.getMessage());
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

    private ArrayList<Unidades> sendRequest(HttpURLConnection con) {
        ArrayList<Unidades> lista = new ArrayList<Unidades>();
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
                JSONParser parser = new JSONParser();
                JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(response));
                for (int i = 0; i < dataObject.size(); i++) {
                    JSONObject json = (JSONObject) parser.parse(String.valueOf(dataObject.get(i)));
                    lista.add(getUnidadFromJson(json));
                }
                in.close();
            } else {
                System.out.println("POST request not worked");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return lista;
    }

    private Usuario sendLoginRequest(HttpURLConnection con) {
        Usuario result = new Usuario();
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
                JSONParser parser = new JSONParser();
                JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(response));
                JSONObject json = (JSONObject) dataObject.get(0);
                apiCode code = getApiCodeFromJson(json);
                if (code.isAccepted()) {
                    result = getJsonUserFromJsonString(dataObject.get(1).toString());
                }else{
                    result = null;
                }

                in.close();
                return result;
            } else {
                System.out.println("POST request not worked");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return result;
    }

    private Usuario getJsonUserFromJsonString(String us) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(us);
            Usuario un = new Usuario();
            un.setNombreUsuario(json.get("nombreUsuario").toString());
            un.setContrasenya(json.get("contrasenya").toString());
            return un;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        }
    }

    private JSONObject getJsonUnidadToPost(Unidades un) {
        JSONObject json = new JSONObject();
        //obj.put("idBus", lista.get(i).getIdbus());
        json.put("fecha_ingreso", un.getFecha_ingreso().toString());
        json.put("modelo", un.getModelo());
        json.put("placa", un.getModelo());
        json.put("marca", un.getMarca());
        json.put("tipo", un.getTipo());
        json.put("estado", un.getActivo());
        return json;
    }

    public void Post_JSON01(Usuario us, Unidades un) {
        String query_url = "http://localhost:8180/ApiTest01/webresources/unidades/addProductos";
        //String json = "{ \"method\" : \"guru.test\", \"params\" : [ \"jinu awad\" ], \"id\" : 123 }";
        JSONObject json = getJsonUnidadToPost(un);
        JSONArray jsonData = new JSONArray();
        jsonData.add(getJsonUserToPost(us));
        jsonData.add(json);
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(jsonData.toString().getBytes("UTF-8"));
            os.close();
            // read the response
            ArrayList<Unidades> lista = sendRequest(conn);
            for (int i = 0; i < lista.size(); i++) {
                System.out.println("unidad " + lista.get(i).getIdbus());
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Usuario login(Usuario us) {
        String query_url = "http://localhost:8180/ApiTest01/webresources/unidades/login";
        //String json = "{ \"method\" : \"guru.test\", \"params\" : [ \"jinu awad\" ], \"id\" : 123 }";
        JSONObject json = getJsonUserToPost(us);
        Usuario currentUsser = new Usuario();
        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.toString().getBytes("UTF-8"));
            os.close();
            // read the response
            currentUsser = sendLoginRequest(conn);
            conn.disconnect();
            return currentUsser;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Unidades getUnidadFromJson(JSONObject obj) {
        //System.out.println(obj.toString());
        Unidades un = new Unidades();
        try {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    private JSONObject getJsonUserToPost(Usuario us) {
        JSONObject json = new JSONObject();
        //obj.put("idBus", lista.get(i).getIdbus());
        json.put("usser", us.getNombreUsuario());
        json.put("contrasenya", us.getContrasenya());
        return json;
    }

    private JSONObject getJsonResponseCode(boolean response) {
        JSONObject json = new JSONObject();
        //obj.put("idBus", lista.get(i).getIdbus());
        json.put("accepted", response);
        json.put("message", "accepted");
        return json;
    }

    public static apiCode getApiCodeFromJson(JSONObject obj) {

        try {
            apiCode un = new apiCode();
            un.setAccepted(Boolean.parseBoolean(obj.get("accepted").toString()));
            un.setMessage(obj.get("message").toString());
            return un;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

}
