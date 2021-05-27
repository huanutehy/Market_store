package com.example.market_store.Buy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.market_store.Adapter.HistoryLVAdapter;
import com.example.market_store.Adapter.StatusAdapter;
import com.example.market_store.Login.Login;
import com.example.market_store.OBJController.DonHangCtrl;
import com.example.market_store.OBJController.UserCtrl;
import com.example.market_store.Object.DonHang;
import com.example.market_store.R;

import java.util.ArrayList;
import java.util.List;

public class BuyHistory extends AppCompatActivity {
    Toolbar toolbar;
    ListView lv;
    DonHangCtrl donHangCtrl;
    UserCtrl userCtrl;
    GridView gv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        Init();
        Event();
    }

    public void Init(){
        toolbar = findViewById(R.id.hist_toolbar);
        lv = findViewById(R.id.lvHistory);
        donHangCtrl = new DonHangCtrl();
        userCtrl = new UserCtrl();
        gv = findViewById(R.id.gv);
    }

    public void Event(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_1);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        statusBar();
    }

    public void statusBar(){
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

    public void getListDHtoLV(int status){
        try {
            final List<DonHang> donHangList = donHangCtrl.getDonHanglistwithAccountandSTT(Login.account, status);
            HistoryLVAdapter adapter = new HistoryLVAdapter(this, R.layout.custom_hist, donHangList);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent in = new Intent(BuyHistory.this, BuyHistoryDetail.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("idDH", donHangList.get(i).getIdDH());
                    in.putExtras(bundle);
                    startActivityForResult(in, 1);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                getListDHtoLV(0);
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
