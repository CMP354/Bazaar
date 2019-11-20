package com.example.CMP354.bazaar.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.CMP354.bazaar.R;

public class CustomListAdapter extends ArrayAdapter<String> {
    Context context;
    int[] images;
    String title;
    String description;

    public CustomListAdapter(Context c, String title, int imgs[], String desc) {
        super(c, R.layout.fragment_shopitem);
        this.context = c;
        this.images = imgs;
        this.title = title;
        this.description = desc;
        // TODO Auto-generated constructor stub
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_shopitem, parent, false);

            viewHolder.title = (TextView) convertView.findViewById(R.id.shop_name);
            viewHolder.description = (TextView) convertView.findViewById(R.id.shop_des);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.shopTypeImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

            if (title != null) {
               // viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
                viewHolder.title = (TextView) convertView.findViewById(R.id.shop_name);
                viewHolder.description = (TextView) convertView.findViewById(R.id.shop_des);
            }
        }
        return convertView;
    }

    private class ViewHolder {
        TextView title, description;
        ImageView image;
    }
}