package com.example.market_store.Buy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.market_store.Adapter.BuyLVAdapter;
import com.example.market_store.Cart.Cart;
import com.example.market_store.Login.Login;
import com.example.market_store.OBJController.CTDHCtrl;
import com.example.market_store.OBJController.DonHangCtrl;
import com.example.market_store.OBJController.UserCtrl;
import com.example.market_store.Object.User;
import com.example.market_store.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Buy extends AppCompatActivity {
    TextView tvDC, tvTT, tvTitle;
    Spinner spHTNH, spHTTT;
    Button btConfirm, btCancel;
    ListView lv;
    UserCtrl userCtrl;
    User user;
    DonHangCtrl donHangCtrl;
    CTDHCtrl ctdhCtrl;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_form);
        Init();
        Event();
    }

    public void Init() {
        tvTitle = findViewById(R.id.tvTitle_buy);
        toolbar = findViewById(R.id.buy_toolbar);
        tvDC = findViewById(R.id.tvDC_buy);
        tvTT = findViewById(R.id.tvTongTien_buy);
        spHTNH = findViewById(R.id.spHTNhanHang_buy);
        spHTTT = findViewById(R.id.spHTThanhToan_buy);
        lv = findViewById(R.id.lv_buy);
        btConfirm = findViewById(R.id.btConfirm_buy);
        btCancel = findViewById(R.id.btCancel_buy);
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
        tvTitle.setText("XÁC NHẬN MUA HÀNG");
        tvDC.setText(user.getHoten() + "\n0" + user.getSDT() + "\n" + user.getDiachi());
        int TT = 0;
        for (int i = 0; i < Cart.ctdhList.size(); i++) {
            TT += Cart.ctdhList.get(i).tinhtien();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTT.setText(decimalFormat.format(TT) + "đ");
        spinnerHTNH();
        spinnerHTTT();
        lv();
        btConfirm.setText("Mua hàng");
        btCancel.setVisibility(View.INVISIBLE);
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Confirm();
            }
        });
        tvDC.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.forward_icon_1, 0);
        tvDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeAddress();
            }
        });
    }

    public void changeAddress() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View DialogView = layoutInflater.inflate(R.layout.more_address, null);
        final androidx.appcompat.app.AlertDialog Dialog = new androidx.appcompat.app.AlertDialog.Builder(this).create();
        final EditText etName = DialogView.findViewById(R.id.etName_add);
        final EditText etSDT = DialogView.findViewById(R.id.etSDT_add);
        final EditText etDC = DialogView.findViewById(R.id.etDC_add);
        Button bt = DialogView.findViewById(R.id.btConfirm_add);

        Dialog.setView(DialogView);
        Dialog.show();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().isEmpty() || etSDT.getText().toString().isEmpty() || etDC.getText().toString().isEmpty())
                    Toast.makeText(Buy.this, "Hãy nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                else if (etSDT.getText().toString().length() != 10)
                    Toast.makeText(Buy.this, "Thông tin vừa nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
                else {
                    tvDC.setText(etName.getText() + "\n" + etSDT.getText() + "\n" + etDC.getText());
                    Dialog.cancel();
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
        BuyLVAdapter adapter = new BuyLVAdapter(this, R.layout.custom_sp_buy, Cart.ctdhList);
        lv.setAdapter(adapter);
    }

    public void Confirm() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage("Xác nhận mua hàng?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            Cart.donHang.setIdDH(donHangCtrl.getDonHanglist().size() + 1);
                            if (donHangCtrl.Insert(Cart.donHang))
                                Toast.makeText(Buy.this, "Đặt hàng thành công!! Xin cảm ơn!", Toast.LENGTH_SHORT).show();
                            for (int a = 0; a < Cart.ctdhList.size(); a++) {
                                Cart.ctdhList.get(a).setIdDH(Cart.donHang.getIdDH());
                                ctdhCtrl.Insert(Cart.ctdhList.get(a));
                            }
                            Cart.ctdhList = new ArrayList<>();
                            Intent returnIntent = new Intent();
                            setResult(RESULT_OK,returnIntent);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
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
