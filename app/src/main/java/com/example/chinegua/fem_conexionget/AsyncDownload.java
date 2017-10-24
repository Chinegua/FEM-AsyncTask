package com.example.chinegua.fem_conexionget;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by chinegua on 24/10/17.
 */

public class AsyncDownload extends AsyncTask<TextView,Void,String> {
//CREAMOS VARIABLE LOCAL CON LA VISTA
    TextView tvContenidoTarea;
    @Override
    protected String doInBackground(TextView... textViews) {
        tvContenidoTarea = textViews[0];
        try {

            URL url = new URL("http://estaticos.marca.com/robots.txt");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            //readStream(in);
            //Log.i("MIW",readStream(in));
            urlConnection.disconnect();

            return readStream(in);



        } catch (Exception e){
            Log.i("MIW","Ha ocurrido un error: "+ e );

            return "null";
        }
    }

    private String readStream(InputStream is) {

        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                try{
                    Thread.sleep(100);

                }
                catch (Exception e){

                }

                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        //TextView txt = (TextView) findViewById(); --> Esto es lo que hay que pasar
        tvContenidoTarea.setText(result); // txt.setText(result);
        // might want to change "executed" for the returned string passed
        // into onPostExecute() but that is upto you
    }

    //Falta el oncancelled

}
