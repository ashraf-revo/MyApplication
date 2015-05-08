package com.example.ashraf.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.web.client.RestTemplate;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.subscribe);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        Button l = (Button) findViewById(R.id.login);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = ((EditText) findViewById(R.id.emailF)).getText().toString();
                String password = ((EditText) findViewById(R.id.passwordF)).getText().toString();
                String word = email + "/" + password + "/";
                String url = "https://revox.herokuapp.com/checkuser/" + word;
                new HttpRequestTask(url, email).execute();
            }
        });

    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Boolean> {
        String url;
        String email;

        public HttpRequestTask(String Url, String email) {
            this.url = Url;
            this.email = email;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            if (aBoolean) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                intent.putExtra("email", email);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Error Email Or Password", Toast.LENGTH_LONG).show();
            }
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
