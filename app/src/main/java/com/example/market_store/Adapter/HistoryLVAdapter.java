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
import com.example.market_store.OBJController.CTDHCtrl;
import com.example.market_store.Object.CTDH;
import com.example.market_store.Object.DonHang;
import com.example.market_store.Object.DonHang;
import com.example.market_store.Object.Product;
import com.example.market_store.R;

import java.text.DecimalFormat;
import java.util.List;

public class HistoryLVAdapter extends ArrayAdapter<DonHang> {

    public HistoryLVAdapter(@NonNull Context context, int resource, @NonNull List<DonHang> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_hist, null);
        }
        TextView tvDate = convertView.findViewById(R.id.tvBuyDate_cust_hist);
        TextView tvDC = convertView.findViewById(R.id.tvDC_cust_hist);
        TextView tvStatus = convertView.findViewById(R.id.tvStatus_cust_hist);
        TextView tvTT = convertView.findViewById(R.id.tvTT_cust_hist);
        try {
            CTDHCtrl ctdhCtrl = new CTDHCtrl();

            DonHang donHang = getItem(position);
            tvDate.setText(donHang.getNgaymua());
            switch (donHang.getStatus()) {
                case 0:
                    tvStatus.setText("Chưa xử lý");
                    break;
                case 1:
                    tvStatus.setText("Đang giao");
                    break;
                case 2:
                    tvStatus.setText("Đã giao");
                    break;
                case 3:
                    tvStatus.setText("Đã hủy");
                    break;
            }
            List<CTDH> ctdhList = ctdhCtrl.getCTDHlistwithID(donHang.getIdDH());
            int TT = 0;
            for (int i = 0; i < ctdhList.size(); i++){
                TT += ctdhList.get(i).tinhtien();
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            tvTT.setText(decimalFormat.format(TT)+"đ");
            tvDC.setText(donHang.getHoten() + "\n0" + donHang.getSdt() + "\n" + donHang.getDiachi());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
