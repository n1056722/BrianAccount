package com.example.asuper.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Super on 2017/2/22.
 */

public class RecordListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Account> list;

    public RecordListAdapter(Context context, ArrayList<Account> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_record, null);
            holder = new Holder();
            holder.desc = (TextView) convertView.findViewById(R.id.rk);
            holder.money = (TextView) convertView.findViewById(R.id.money2);
            holder.createddate = (TextView) convertView.findViewById(R.id.datetime2);


            convertView.setTag(holder);


        } else {

            holder = (Holder) convertView.getTag();
        }

        holder.desc.setText(list.get(position).getDesc());
        holder.money.setText(list.get(position).getMoney()+"");
        holder.createddate.setText(list.get(position).getCreateDate());


        return convertView;
    }

    class Holder {
        TextView desc;
        TextView money;
        TextView createddate;


    }

}
