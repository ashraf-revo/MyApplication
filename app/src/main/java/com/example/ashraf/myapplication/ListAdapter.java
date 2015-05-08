package com.example.ashraf.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ashraf.myapplication.domain.Numbers;

import java.util.List;

/**
 * Created by revo on 16/04/15.
 */
public class ListAdapter extends BaseAdapter {
    List<Numbers> data;
    Context context;
    MainActivity4 mainActivity4;
    public ListAdapter(Context contextVal, List<Numbers> dataVal, MainActivity4 mainActivity4) {
        context = contextVal;
        data = dataVal;
        this.mainActivity4=mainActivity4;
    }



    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context.getApplicationContext());
        View view = inflater.inflate(R.layout.list_item, null);
        TextView name = (TextView) view.findViewById(R.id.namer);
        name.setText(data.get(position).getUsername());
        TextView phone = (TextView) view.findViewById(R.id.phoner);
        phone.setText(data.get(position).getPhone());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + data.get(position).getPhone()));
               mainActivity4.startActivity(intent);
            }
        });
        return view;
    }
}
