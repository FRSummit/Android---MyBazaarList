package com.frskynet.mymarketlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by F R Summit on 23th September,2020
 * Simplexhub Limited
 * frsummit@simplexhub.com
 */
public class ListAdapter extends BaseAdapter {

    Context context;
    private final String [] name;
    private final String [] quantity;

    public ListAdapter(Context context, String [] name, String [] quantity){
        this.context = context;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.single_list_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.single_list_item_name);
            viewHolder.quantity = (TextView) convertView.findViewById(R.id.single_list_item_quantity);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.name.setText(name[position]);
        viewHolder.quantity.setText(quantity[position]);

        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView quantity;
    }

}
