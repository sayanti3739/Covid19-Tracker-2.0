package com.example.covid19tracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Adapter extends ArrayAdapter<ListItem> {

    private Context context;
    private ArrayList<ListItem> list;


    public Adapter(@NonNull Context context, ArrayList<ListItem> list) {
        super(context, R.layout.to_do_item, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.to_do_item, parent, false);
            Log.i("FUCK", "Adapter2");
        }

        Log.i("FUCK", "Adapter3");

        ListItem t = list.get(position);


        TextView sname = convertView.findViewById(R.id.statename);
        TextView total = convertView.findViewById(R.id.total);
        TextView discharge = convertView.findViewById(R.id.discharge);
        TextView death = convertView.findViewById(R.id.deaths);

        sname.setText(t.getSname());
        total.setText(t.getCnf());
        discharge.setText(t.getDis());
        death.setText(t.getDeat());

        return convertView;
    }
}

