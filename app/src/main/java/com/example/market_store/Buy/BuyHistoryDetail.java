package com.example.market_store.Buy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.market_store.Adapter.HistoryLVAdapter;
import com.example.market_store.Cart.Cart;
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


public class BuyHistoryDetail extends AppCompatActivity {
    TextView tvDC, tvTT, tvTitle;
    Spinner spHTNH, spHTTT;
    Button btConfirm, btCancel;
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
        try {
            Intent in = getIntent();
            Bundle bundle = in.getExtras();
            idDH = bundle.getInt("idDH");
            donHang = donHangCtrl.getDonHangwithID(idDH);
            tvTitle.setText("CẬP NHẬT ĐƠN HÀNG");
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
            btConfirm.setText("Xác nhận");
            if(donHang.getStatus() == 0) {
                spHTNH.setEnabled(true);
                spHTTT.setEnabled(true);
                btCancel.setVisibility(View.VISIBLE);
                tvDC.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.forward_icon_1, 0);
                tvDC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(donHang.getStatus() == 0) changeAddress();
                    }
                });
            } else {
                spHTNH.setEnabled(false);
                spHTTT.setEnabled(false);
                tvDC.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                btConfirm.setVisibility(View.INVISIBLE);
                btCancel.setVisibility(View.INVISIBLE);
            }
            btConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Confirm();
                }
            });
            btCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cancel();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Confirm(){
        final AlertDialog alertDialog = new AlertDialog.Builder(BuyHistoryDetail.this)
                .setTitle("Xác nhận")
                .setMessage("Xác nhận thay đổi?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            if(donHangCtrl.UpdateDonHang(donHang))
                                Toast.makeText(BuyHistoryDetail.this, "Cập nhật đơn hàng thành công!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        Intent returnIntent = new Intent();
                        setResult(RESULT_OK,returnIntent);
                        finish();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }

    public void Cancel(){
        final AlertDialog alertDialog = new AlertDialog.Builder(BuyHistoryDetail.this)
                .setTitle("Xác nhận")
                .setMessage("Xác nhận hủy đơn hàng?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            if(donHangCtrl.UpdateStatus(3, donHang.getIdDH()))
                                Toast.makeText(BuyHistoryDetail.this, "Hủy đơn hàng thành công!", Toast.LENGTH_SHORT).show();
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        Intent returnIntent = new Intent();
                        setResult(RESULT_OK,returnIntent);
                        finish();
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();

    }

    public void lv() {
        try {
            List<CTDH> ctdhList = ctdhCtrl.getCTDHlistwithID(idDH);
            BuyLVAdapter adapter = new BuyLVAdapter(this, R.layout.custom_sp_buy, ctdhList);
            lv.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }
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
                    Toast.makeText(BuyHistoryDetail.this, "Hãy nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                else if (etSDT.getText().toString().length() != 10)
                    Toast.makeText(BuyHistoryDetail.this, "Thông tin vừa nhập không hợp lệ!", Toast.LENGTH_SHORT).show();
                else {
                    tvDC.setText(etName.getText() + "\n" + etSDT.getText() + "\n" + etDC.getText());
                    donHang.setHoten(etName.getText().toString().trim());
                    donHang.setSdt(Integer.valueOf(etSDT.getText().toString().trim()));
                    donHang.setDiachi(etDC.getText().toString().trim());
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
                setResult(RESULT_CANCELED,returnIntent);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
