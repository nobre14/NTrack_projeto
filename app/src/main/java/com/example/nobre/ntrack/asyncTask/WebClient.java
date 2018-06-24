package com.example.nobre.ntrack.asyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class WebClient {

    private static HttpsURLConnection conectar() throws IOException {
        URL url = new URL("https://demo0868847.mockable.io/ntrack");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setReadTimeout(10 * 1000);
        connection.setConnectTimeout(15 * 1000);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(false);
        System.out.println(connection.getURL());
        connection.connect();
        return connection;
    }


    public static List<String> lerJsonMarcas(JSONObject json) throws JSONException {
        List<String> listaMarcas = new ArrayList<>();
        JSONArray jsonMarcas = json.getJSONArray("marcas");
        for (int x = 0; x < jsonMarcas.length(); x++) {
            listaMarcas.add(jsonMarcas.getString(x));
        }
        return listaMarcas;
    }

    public static List<String> carregarMarcasJson(){
        try {
            HttpsURLConnection conexao = conectar();
            int resposta = conexao.getResponseCode();
            System.out.println(resposta);
            if (resposta == HttpsURLConnection.HTTP_OK) {
                InputStream is = conexao.getInputStream();
                JSONObject json = new JSONObject(bytesParaString(is));
                return lerJsonMarcas(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String bytesParaString(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream bufferzao = new ByteArrayOutputStream();
        int bytesLidos;
        while ((bytesLidos = is.read(buffer)) != -1) {
            bufferzao.write(buffer, 0, bytesLidos);
        }
        return new String(bufferzao.toByteArray(), "UTF-8");
    }
}
