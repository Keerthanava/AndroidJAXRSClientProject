package com.keerthana.androidjaxrsclient;

import android.os.Bundle;
import android.app.Activity;
import android.os.StrictMode;
import android.view.Menu;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        setContentView(R.layout.activity_main);

        TextView jaxrs=(TextView)findViewById(R.id.tvjaxrs);
          try{
              HttpClient httpclient = new DefaultHttpClient();
              //HttpGet request = new HttpGet("http://api.openkeyval.org/PrimeNumber");
              HttpGet request = new HttpGet("http://api.openkeyval.org/location");
              //http://api.openkeyval.org/location
              request.addHeader("Accept","text/html");
              HttpResponse response = httpclient.execute(request);
               HttpEntity entity =response.getEntity();
              InputStream instream =entity.getContent();
                  String jaxrsmessage = read(instream);
                  jaxrs.setText(jaxrsmessage);
              } catch (ClientProtocolException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }

    private static String read(InputStream instream) {
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            BufferedReader r = new BufferedReader(new InputStreamReader(
                    instream));
            for (String line = r.readLine(); line != null; line = r.readLine()) {
                sb.append(line);
            }

            instream.close();

        } catch (IOException e) {
        }
        return sb.toString();

    }

    }

