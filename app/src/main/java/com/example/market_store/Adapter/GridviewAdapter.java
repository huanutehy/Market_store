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

import java.text.DecimalFormat;
import java.util.List;

public class GridviewAdapter extends BaseAdapter {
    private Context context;
    private List<Product> productList;

    public GridviewAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        if (productList.size() != 0 && !productList.isEmpty()) {
            return productList.size();
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
        convertView = inflater.inflate(R.layout.custom_sp_gv, null);

        TextView tv1 = convertView.findViewById(R.id.tv_name);
        TextView tv2 = convertView.findViewById(R.id.tv_price);
        ImageView img = convertView.findViewById(R.id.img_gv_sp);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tv1.setText(productList.get(position).getTensp());
        tv2.setText(decimalFormat.format(productList.get(position).getGia())+"đ");
        Glide.with(convertView.getContext()).load(productList.get(position).getHinhanh()).into(img);
        return convertView;
    }



}
