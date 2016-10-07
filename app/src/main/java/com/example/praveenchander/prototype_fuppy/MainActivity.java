package com.example.praveenchander.prototype_fuppy;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    public class Petfinder_API extends AsyncTask<String,Void,String >
    {


        @Override
        protected String doInBackground(String... urls) {

            String rawResult="";                   // store the raw content downloaded from the API
            URL url;
            HttpURLConnection urlConnection=null;

            try{
                url=new URL(urls[0]);

                urlConnection= (HttpURLConnection) url.openConnection();

                InputStream in=urlConnection.getInputStream();

                InputStreamReader reader= new InputStreamReader(in);

                int data=reader.read();

                while(data !=1)
                {
                    char convertEachByteToChar=(char)data;
                    rawResult+=convertEachByteToChar;
                    data=reader.read();
                }
                return rawResult;

            }
            catch(Exception e){
                e.printStackTrace();
                return "failed";
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Petfinder_API new_API=new Petfinder_API();
        String checkExecution="";

        try {
            checkExecution=new_API.execute("http://www.praveenchander.com").get();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        Log.i("Return",checkExecution);
//        TextView check=(TextView) findViewById(R.id.check);


//        check.setText(checkExecution);
                Log.i("check",checkExecution);

    }
}
