package com.example.market_store.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.market_store.Object.Product;
import com.example.market_store.R;

import java.text.DecimalFormat;
import java.util.List;
import java.util.zip.Inflater;

public class SearchLVAdapter extends ArrayAdapter<Product> {

    public SearchLVAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_sp_lv_search, null);
        }
        ImageView img = convertView.findViewById(R.id.img_lv_sp);
        TextView tvTensp = convertView.findViewById(R.id.tvProdName_lv_sp);
        TextView tvGia = convertView.findViewById(R.id.tvPrice_lv_sp);
        Product product = getItem(position);
        Glide.with(getContext()).load(product.getHinhanh()).into(img);
        tvTensp.setText(product.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGia.setText(decimalFormat.format(product.getGia())+"đ");
        return convertView;
    }
}
