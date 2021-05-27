package com.example.market_store.User;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.market_store.Adapter.CartLVAdapter;
import com.example.market_store.Buy.Buy;
import com.example.market_store.Cart.Cart;
import com.example.market_store.R;

import java.util.ArrayList;

public class QLGH extends AppCompatActivity {
    Toolbar toolbar;
    ListView lv;
    Button btBuy, btDel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qlgh);
        Init();
        Event();
    }

    public void Init(){
        toolbar = findViewById(R.id.qlgh_toolbar);
        lv = findViewById(R.id.lvCart);
        btBuy = findViewById(R.id.btMuaHang);
        btDel = findViewById(R.id.btXoaDH);
    }

    public void Event(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_1);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        CustomLV();
        DelDH();
        Buy();
    }

    public void CustomLV(){
        CartLVAdapter adapter = new CartLVAdapter(this, R.layout.custom_sp_lv, Cart.ctdhList);
        lv.setAdapter(adapter);
    }

    public void DelDH(){
        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog alertDialog = new AlertDialog.Builder(QLGH.this)
                        .setTitle("Xác nhận")
                        .setMessage("Bạn có chắc chắn muốn xóa tất cả sản phẩm khỏi giỏ hàng?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Cart.ctdhList = new ArrayList<>();
                                CustomLV();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
            }
        });
    }

    public void Buy(){
        btBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Cart.ctdhList.size() > 0) {
                    Intent in = new Intent(QLGH.this, Buy.class);
                    startActivityForResult(in, 1);
                }
                else Toast.makeText(QLGH.this, "Bạn chưa có sản phẩm nào trong giỏ hàng!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                CustomLV();
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
