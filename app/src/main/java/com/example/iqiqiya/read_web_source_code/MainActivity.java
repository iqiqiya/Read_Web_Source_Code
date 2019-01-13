package com.example.iqiqiya.read_web_source_code;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);

        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... params) {
                try {
                    InputStream in = new URL("http://music.77sec.cn").openStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));

                    String line = null;
                    StringBuffer content = new StringBuffer();
                    while((line=reader.readLine())!=null){
                        content.append(line);
                    }
                    reader.close();
                    System.out.println(content.toString());

                    //tv.setText(content.toString());
                    return content.toString();
                }catch (IOException e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s!=null){
                    tv.setText(s);
                }
            }
        }.execute();

    }
}
