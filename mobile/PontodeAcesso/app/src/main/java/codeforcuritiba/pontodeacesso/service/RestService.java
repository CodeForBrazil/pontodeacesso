package codeforcuritiba.pontodeacesso.service;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import codeforcuritiba.pontodeacesso.model.Pontos;

/**
 * Created by gleisser on 24/10/2015.
 */
public class RestService extends AsyncTask<String, Void, List<Pontos>>{

    public List<Pontos> getMarkers(){
        String retorno = "";
        try {
            URL url = new URL("http://ec2-52-26-233-67.us-west-2.compute.amazonaws.com:8080/markers/all");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            retorno = getStringFromInputStream(urlConnection.getInputStream());
            //InputStream in = new BufferedInputStream(urlConnection.getInputStream());

        } catch(Exception e){
            e.printStackTrace();
        }
        if(retorno.isEmpty()){
            return new ArrayList<Pontos>();
        }else {
            return new Gson().fromJson(retorno, new TypeToken<List<Pontos>>() {
            }.getType());
        }
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    @Override
    protected List<Pontos> doInBackground(String... params) {
        return getMarkers();
    }
}
