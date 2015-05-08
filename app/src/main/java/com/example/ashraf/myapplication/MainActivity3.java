package com.example.ashraf.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.web.client.RestTemplate;


public class MainActivity3 extends ActionBarActivity {
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3);
        this.email = getIntent().getStringExtra("email");
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.username)).getText().toString();
                String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
                String word = email + "/" + username + "/" + phone + "/";
                String url = "https://revox.herokuapp.com/addnumber/" + word;
                new Save(url).execute();
            }
        });

        findViewById(R.id.list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://revox.herokuapp.com/MyNumber/" + email + "/";
                new ListNumbers(url).execute();
            }
        });


        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) findViewById(R.id.username)).getText().toString();
                String phone = ((EditText) findViewById(R.id.phone)).getText().toString();

                if (username.trim().length()>3) {
                    String url = "https://revox.herokuapp.com/findByUsernameContaining/" + username + "/";
                    new ListNumbers(url).execute();
                } else if (phone.trim().length()>3) {
                    String url = "https://revox.herokuapp.com/findByPhone/" + phone + "/";
                    new ListNumbers(url).execute();

                }
            }
        });

        findViewById(R.id.uplodeall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                while (phones.moveToNext()) {
                    String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace(" ", "");
                    String word = email + "/" + name + "/" + phoneNumber + "/";
                    String url = "https://revox.herokuapp.com/addnumber/" + word;
                    new Save(url).execute();
                }
                phones.close();
            }
        });
    }

    private class Save extends AsyncTask<Void, Void, Boolean> {
        String url;

        public Save(String Url) {
            this.url = Url;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            if (aBoolean) {
                Toast.makeText(MainActivity3.this, "sucess save ", Toast.LENGTH_LONG).show();
                ((EditText) findViewById(R.id.username)).setText("");
                ((EditText) findViewById(R.id.phone)).setText("");
            } else {
                Toast.makeText(MainActivity3.this, "error on save ", Toast.LENGTH_LONG).show();
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

    private class ListNumbers extends AsyncTask<Void, Void, String> {
        String url;

        public ListNumbers(String Url) {
            this.url = Url;
        }

        @Override
        protected void onPostExecute(String numberses) {

            if (numberses.length() < 10)
                Toast.makeText(MainActivity3.this, "no data to disply", Toast.LENGTH_LONG).show();
            else {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                intent.putExtra("data", numberses);
                startActivity(intent);
            }


        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                RestTemplate r = new RestTemplate();
                return r.getForObject(url, String.class);
            } catch (Exception e) {
            }
            return null;
        }
    }
}
