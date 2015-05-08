package com.example.ashraf.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.example.ashraf.myapplication.domain.Numbers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class MainActivity4 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity4);
        String data = getIntent().getStringExtra("data");
        TypeToken<List<Numbers>> token = new TypeToken<List<Numbers>>() {
        };
        Gson gson = new GsonBuilder().create();
        List<Numbers> numbersList = gson.fromJson(data, token.getType());





        ListView listView = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new ListAdapter(getApplicationContext(), numbersList,this);

        listView.setAdapter(adapter);
    }
}
