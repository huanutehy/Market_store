package com.example.market_store.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.market_store.Object.Product;
import com.example.market_store.R;

import java.util.List;

public class StatusAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;

    public StatusAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        if (stringList.size() != 0 && !stringList.isEmpty()) {
            return stringList.size();
        }
        return 0;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_gv_status, null);

        TextView tv = convertView.findViewById(R.id.tvStatus_cust_stt);
        tv.setText(stringList.get(position));
        return convertView;
    }
}
