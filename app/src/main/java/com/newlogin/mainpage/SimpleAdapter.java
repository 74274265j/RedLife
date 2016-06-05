package com.newlogin.mainpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.newlogin.R;

import java.util.List;

public class SimpleAdapter extends ArrayAdapter<Contact> {

    private List<Contact> itemList;
    private Context context;

    public SimpleAdapter(List<Contact> itemList, Context ctx) {
        super(ctx, android.R.layout.simple_list_item_1, itemList);
        this.itemList = itemList;
        this.context = ctx;
    }

    public int getCount() {
        if (itemList != null)
            return itemList.size();
        return 0;
    }

    public Contact getItem(int position) {
        if (itemList != null)
            return itemList.get(position);
        return null;
    }

    public long getItemId(int position) {
        if (itemList != null)
            return itemList.get(position).hashCode();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_items, null);
        }

        Contact c = itemList.get(position);
        TextView text = (TextView) v.findViewById(R.id.name);
        text.setText(c.getName());

        TextView text1 = (TextView) v.findViewById(R.id.bloodgrp);
        text1.setText(c.getSurname());

        TextView text2 = (TextView) v.findViewById(R.id.address);
        text2.setText(c.getEmail());

        TextView text3 = (TextView) v.findViewById(R.id.contactno);
        text3.setText(c.getPhoneNum());

        return v;

    }

    public List<Contact> getItemList() {
        return itemList;
    }

    public void setItemList(List<Contact> itemList) {
        this.itemList = itemList;
    }


}