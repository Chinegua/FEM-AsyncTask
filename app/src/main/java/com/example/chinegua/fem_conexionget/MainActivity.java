package com.example.chinegua.fem_conexionget;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chinegua.fem_conexionget.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tvContenido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Log.i("MIW","Arrancamos");

        if(hayConexion() == true){
            Log.i("MIW","Hay conexion");


            Toast toast = Toast.makeText(context, "Hay conexión", duration);
            toast.show();
        }
        else{
            Log.i("MIW","No hay conexion");

            Toast toast = Toast.makeText(context, "No Hay conexion", duration);
            toast.show();
        }

        setContentView(R.layout.activity_main);

        tvContenido = (TextView)findViewById(R.id.tvContenido);
        tvContenido.setText("Hola");


        new AsyncDownload().execute(tvContenido); //Pasar vista que vamos a modificar

        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);



       /* try {

            URL url = new URL("http://estaticos.marca.com/robots.txt");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            //readStream(in);
            Log.i("MIW",readStream(in));
            urlConnection.disconnect();



        } catch (Exception e){
            Log.i("MIW","Ha ocurrido un error: "+ e );
        }*/

    }
    private String readStream(InputStream is) {
        Log.i("MIW","Seguimos");

        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    public boolean hayConexion() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conMgr.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }

}
