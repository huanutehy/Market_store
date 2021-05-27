package com.example.market_store.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.market_store.Adapter.BuyLVAdapter;
import com.example.market_store.Login.Login;
import com.example.market_store.OBJController.CTDHCtrl;
import com.example.market_store.OBJController.DonHangCtrl;
import com.example.market_store.OBJController.UserCtrl;
import com.example.market_store.Object.CTDH;
import com.example.market_store.Object.DonHang;
import com.example.market_store.Object.User;
import com.example.market_store.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CTDH_admin extends AppCompatActivity {
    TextView tvDC, tvTT, tvTitle;
    Spinner spHTNH, spHTTT;
    Button btChange;
    ListView lv;
    UserCtrl userCtrl;
    User user;
    DonHangCtrl donHangCtrl;
    CTDHCtrl ctdhCtrl;
    Toolbar toolbar;
    int idDH;
    DonHang donHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dhdetail_statuschange);
        Init();
        Event();
    }

    public void Init() {
        tvTitle = findViewById(R.id.tvTitle_sttchange);
        toolbar = findViewById(R.id.sttchange_toolbar);
        tvDC = findViewById(R.id.tvDC_sttchange);
        tvTT = findViewById(R.id.tvTongTien_sttchange);
        spHTNH = findViewById(R.id.spHTNhanHang_sttchange);
        spHTTT = findViewById(R.id.spHTThanhToan_sttchange);
        lv = findViewById(R.id.lv_sttchange);
        btChange = findViewById(R.id.btChange_sttchange);
        try {
            donHangCtrl = new DonHangCtrl();
            ctdhCtrl = new CTDHCtrl();
            userCtrl = new UserCtrl();
            user = userCtrl.getUser(Login.account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Event() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_1);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        try {
            Intent in = getIntent();
            Bundle bundle = in.getExtras();
            idDH = bundle.getInt("idDH");
            donHang = donHangCtrl.getDonHangwithID(idDH);
            tvDC.setText(donHang.getHoten() + "\n0" + donHang.getSdt() + "\n" + donHang.getDiachi());
            List<CTDH> ctdhList = ctdhCtrl.getCTDHlistwithID(idDH);
            int TT = 0;
            for (int i = 0; i < ctdhList.size(); i++) {
                TT += ctdhList.get(i).tinhtien();
            }
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            tvTT.setText(decimalFormat.format(TT) + "đ");
            spinnerHTNH();
            spinnerHTTT();
            lv();
            spHTNH.setEnabled(false);
            spHTTT.setEnabled(false);
            if (donHang.getStatus() == 2 || donHang.getStatus() == 3)
                btChange.setVisibility(View.INVISIBLE);
            btChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateStatus();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int stt;

    public void UpdateStatus() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.custom_update_status, null);
        Spinner sp = view.findViewById(R.id.sp_update_status);
        Button bt = view.findViewById(R.id.btConfirm_update_status);
        stt = -1;
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Chưa xử lý");
        arr.add("Đang giao");
        arr.add("Đã giao");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.customspinner, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stt = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        final AlertDialog dialog = alert.create();
        dialog.show();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (donHangCtrl.UpdateStatus(stt, donHang.getIdDH())) {
                        Toast.makeText(CTDH_admin.this, "Cập nhật trạng thái thành công!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        Intent returnIntent = new Intent();
                        setResult(RESULT_OK, returnIntent);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void spinnerHTNH() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Giao hàng tận nơi");
        arr.add("Tại cửa hàng");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.customspinner, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spHTNH.setAdapter(adapter);
    }

    public void spinnerHTTT() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Thanh toán khi nhận hàng");
        arr.add("Thanh toán qua chuyển khoản");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.customspinner, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spHTTT.setAdapter(adapter);
    }

    public void lv() {
        try {
            List<CTDH> ctdhList = ctdhCtrl.getCTDHlistwithID(idDH);
            BuyLVAdapter adapter = new BuyLVAdapter(this, R.layout.custom_sp_buy, ctdhList);
            lv.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.extra_common_menu, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent returnIntent = new Intent();
                setResult(RESULT_CANCELED, returnIntent);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
