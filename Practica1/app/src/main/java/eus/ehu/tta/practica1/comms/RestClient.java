package eus.ehu.tta.practica1.comms;

import android.util.Base64;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by edwin on 3/01/18.
 */

public class RestClient {
    public static String user;
    public static String idUser;
    public static final String URL_SERVER = "http://u017633.ehu.eus:28080/ServidorTta";//EHU PUBLIC
    public final String REQUEST_INFO_USER = "rest/tta/getStatus?dni=";
    public final String REQUEST_TEST_ID = "rest/tta/getTest?id=";
    public final String REQUEST_EXERCISE = "rest/tta/getExercise?id=";
    public final String UPLOAD_EXERCISE = "rest/tta/postExercise?";
    public final String UPLOAD_TEST_SOLVE = "rest/tta/postChoice";


    private final static String AUTH = "Authorization";
    private final String baseUrl;
    private final Map<String,String> properties = new HashMap<>();

    public RestClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setHttpBasicAuth(String user, String password){
        String basicAuth = Base64.encodeToString(String.format("%s:%s",user,password).getBytes(),Base64.DEFAULT);
        properties.put(AUTH,String.format("Basic %s",basicAuth));
    }

    public String getAuthorization(){
        return properties.get(AUTH);
    }

    public void setAuthorization(String auth){
        properties.put(AUTH,auth);
    }

    private HttpURLConnection getConnection(String path) throws IOException {
        URL url = new URL(String.format("%s/%s",baseUrl,path));
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        for(Map.Entry<String,String> property : properties.entrySet())
            conn.setRequestProperty(property.getKey(),property.getValue());
        conn.setUseCaches(false);

        return conn;
    }


    public String getStringJson(String path){
        HttpURLConnection conn = null;
        String linea = null;
        try {
            conn = getConnection(path);
            System.out.println(conn.getResponseMessage());
            try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
                linea = br.readLine();
                System.out.println(linea);
                return linea;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(conn != null)
                conn.disconnect();
        }
        return linea;
    }
    public String postJsonConnection(final JSONObject json, String path) throws IOException {
        HttpURLConnection conn = null;

        conn = getConnection(path);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        writer.write(json.toString());
        writer.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        //if(br.readLine()!=null)
            System.out.println(conn.getResponseMessage());

        return br.readLine();
    }

}
