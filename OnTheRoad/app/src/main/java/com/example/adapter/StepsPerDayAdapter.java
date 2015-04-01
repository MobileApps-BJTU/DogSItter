package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.entity.StepsPerDay;
import com.example.ontheroad.R;

import java.util.List;

/**
 * Created by 俊成 on 2015/4/1.
 */
public class StepsPerDayAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<StepsPerDay> list;
    private Context context;

    public StepsPerDayAdapter(Context context, List<StepsPerDay> list) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public StepsPerDay getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_list, null);
        TextView date = (TextView)view.findViewById(R.id.id_date);
        TextView steps = (TextView)view.findViewById(R.id.id_steps);

        date.setText(list.get(position).getDate());
        steps.setText(list.get(position).getSteps());

        return view;
    }
}
