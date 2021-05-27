package com.example.market_store.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.market_store.Adapter.HistoryLVAdapter;
import com.example.market_store.Adapter.StatusAdapter;
import com.example.market_store.Buy.BuyHistoryDetail;
import com.example.market_store.Login.Login;
import com.example.market_store.OBJController.DonHangCtrl;
import com.example.market_store.Object.DonHang;
import com.example.market_store.R;

import java.util.ArrayList;
import java.util.List;

public class QLDH_admin extends AppCompatActivity {
    Toolbar toolbar;
    EditText etAccount, etName, etSDT, etDC;
    Button bt;
    GridView gv;
    ListView lv;
    DonHangCtrl donHangCtrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qldh_admin);
        Init();
        Event();
    }

    public void Init() {
        toolbar = findViewById(R.id.qldh_admin_toolbar);
        etAccount = findViewById(R.id.etAccount_qldh_admin);
        etName = findViewById(R.id.etName_qldh_admin);
        etSDT = findViewById(R.id.etSDT_qldh_admin);
        etDC = findViewById(R.id.etDC_qldh_admin);
        bt = findViewById(R.id.btSearch_qldh_admin);
        gv = findViewById(R.id.gv_qldh_admin);
        lv = findViewById(R.id.lv_qldh_admin);
        donHangCtrl = new DonHangCtrl();
    }

    public void Event() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_1);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        statusBar();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Search();
            }
        });
    }

    public void Search() {
        String account = etAccount.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String sdt = etSDT.getText().toString().trim();
        String dc = etDC.getText().toString().trim();

        final List<DonHang> donHangs = new ArrayList<>();
        if (account.isEmpty() && name.isEmpty() && sdt.isEmpty() && dc.isEmpty()) {
            Toast.makeText(this, "Hãy điền thông tin cần tìm kiếm!", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < DonHangCtrl.donHangList.size(); i++) {
                if (DonHangCtrl.donHangList.get(i).getAccount().contains(account))
                    donHangs.add(DonHangCtrl.donHangList.get(i));
                else if (DonHangCtrl.donHangList.get(i).getHoten().contains(name))
                    donHangs.add(DonHangCtrl.donHangList.get(i));
                else if (("0" + DonHangCtrl.donHangList.get(i).getSdt()).contains(sdt))
                    donHangs.add(DonHangCtrl.donHangList.get(i));
                else if(DonHangCtrl.donHangList.get(i).getDiachi().contains(dc))
                    donHangs.add(DonHangCtrl.donHangList.get(i));
            }
            if(donHangs.size() == 0) Toast.makeText(this, "Không tìm thấy đơn hàng nào!", Toast.LENGTH_SHORT).show();
            else {
                HistoryLVAdapter adapter = new HistoryLVAdapter(this, R.layout.custom_hist, donHangs);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent in = new Intent(QLDH_admin.this, CTDH_admin.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("idDH", donHangs.get(i).getIdDH());
                        in.putExtras(bundle);
                        startActivityForResult(in, 1);
                    }
                });
            }
        }
    }

    public void statusBar() {
        List<String> stringList = new ArrayList<>();
        stringList.add("Chưa xử lý");
        stringList.add("Đang giao");
        stringList.add("Đã giao");
        stringList.add("Đã hủy");

        StatusAdapter adapter = new StatusAdapter(this, stringList);
        gv.setAdapter(adapter);
        getListDHtoLV(0);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getListDHtoLV(i);
            }
        });
    }

    public void getListDHtoLV(int status) {
        try {
            final List<DonHang> donHangList = donHangCtrl.getDonHanglistwithSTT(status);
            HistoryLVAdapter adapter = new HistoryLVAdapter(this, R.layout.custom_hist, donHangList);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent in = new Intent(QLDH_admin.this, CTDH_admin.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("idDH", donHangList.get(i).getIdDH());
                    in.putExtras(bundle);
                    startActivityForResult(in, 1);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String account = etAccount.getText().toString().trim();
                String name = etName.getText().toString().trim();
                String sdt = etSDT.getText().toString().trim();
                String dc = etDC.getText().toString().trim();
                if (account.isEmpty() && name.isEmpty() && sdt.isEmpty() && dc.isEmpty()){
                    getListDHtoLV(0);
                }else {
                    Search();
                }

            }
            if (resultCode == RESULT_CANCELED) {
            }
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
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
