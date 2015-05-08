package com.example.ashraf.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.springframework.web.client.RestTemplate;


public class MainActivity2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        Button button = (Button) findViewById(R.id.ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = ((EditText) findViewById(R.id.nameF)).getText().toString();
                String email = ((EditText) findViewById(R.id.emailF)).getText().toString();
                String password = ((EditText) findViewById(R.id.passwordF)).getText().toString();
                String word = name + "/" + email + "/" + password + "/";
                String url = "https://revox.herokuapp.com/adduser/" + word;
                new HttpRequestTask(url).execute();
            }
        });
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Boolean> {
        String url;

        public HttpRequestTask(String Url) {
            this.url = Url;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            startActivity(intent);

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                RestTemplate r = new RestTemplate();
                return r.getForObject(url, Boolean.class);
            } catch (Exception e) {
            }
            return null;
        }
    }
}
