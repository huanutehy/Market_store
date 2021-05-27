package com.example.market_store.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.market_store.Cart.Cart;
import com.example.market_store.OBJController.ProductCtrl;
import com.example.market_store.Object.CTDH;
import com.example.market_store.R;

import java.text.DecimalFormat;
import java.util.List;

public class CartLVAdapter extends ArrayAdapter<CTDH> {

    public CartLVAdapter(@NonNull Context context, int resource, @NonNull List<CTDH> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_sp_lv, null);
        }
        ImageView img = convertView.findViewById(R.id.img_cust_sp);
        TextView tvProdName = convertView.findViewById(R.id.tvProdName_cust_sp);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice_cust_sp);
        TextView tvQuantity = convertView.findViewById(R.id.tvQuantity_cust_sp);
        ImageButton btRemoveSP = convertView.findViewById(R.id.btRemoveSP);
        CTDH ctdh = getItem(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        for(int i = 0; i < ProductCtrl.productList.size(); i++){
            if(ProductCtrl.productList.get(i).getIdProduct() == ctdh.getIdProduct()){
                Glide.with(getContext()).load(ProductCtrl.productList.get(i).getHinhanh()).into(img);
                tvProdName.setText(ProductCtrl.productList.get(i).getTensp());
                tvPrice.setText("Giá: " + decimalFormat.format(ctdh.getGia()) +"đ");
                tvQuantity.setText("Số lượng: " + ctdh.getSoluong());
            }
        }

        btRemoveSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Xác nhận")
                        .setMessage("Bạn có chắc chắn muốn xóa sản phẩm này khỏi giỏ hàng?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Cart.ctdhList.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
            }
        });

        return convertView;
    }
}
