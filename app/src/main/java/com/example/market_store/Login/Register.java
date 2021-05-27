package com.example.market_store.Login;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.market_store.Cart.Cart;
import com.example.market_store.OBJController.UserCtrl;
import com.example.market_store.Object.User;
import com.example.market_store.R;

import java.util.Calendar;

public class Register extends AppCompatActivity {
    UserCtrl userCtrl;
    EditText etAccount, etPW, etHoten, etEmail, etDC, etCMT, etSDT;
    Button btReg;
    TextView etNS;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Init();
        Event();
    }

    public void Init() {
        userCtrl = new UserCtrl();
        etAccount = findViewById(R.id.etUserName_reg);
        etPW = findViewById(R.id.etPassword_reg);
        etHoten = findViewById(R.id.etHoten_reg);
        etNS = findViewById(R.id.etNgaysinh_reg);
        etEmail = findViewById(R.id.etEmail_reg);
        etDC = findViewById(R.id.etDiachi_reg);
        etCMT = findViewById(R.id.etCMT_reg);
        etSDT = findViewById(R.id.etSDT_reg);
        btReg = findViewById(R.id.btRegister_reg);
        toolbar = findViewById(R.id.reg_toolbar);
    }

    public void Event() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_1);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        etNS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowDatePicker_NS(view);
            }
        });
        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }

    public void Register() {
        String account = etAccount.getText().toString().trim();
        String pw = etPW.getText().toString().trim();
        String hoten = etHoten.getText().toString().trim();
        String ns = etNS.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String dc = etDC.getText().toString().trim();
        String cmt = etCMT.getText().toString().trim();
        String sdt = etSDT.getText().toString().trim();

        if (account.length() < 6 || account.length() > 20)
            Toast.makeText(getApplicationContext(), "Tên tài khoản từ 6-20 ký tự!", Toast.LENGTH_SHORT).show();
        else if (pw.length() < 6 || pw.length() > 20)
            Toast.makeText(getApplicationContext(), "Mật khẩu từ 6-20 ký tự!", Toast.LENGTH_SHORT).show();
        else if (hoten.length() < 3 || hoten.length() > 30)
            Toast.makeText(getApplicationContext(), "Thông tin nhập vào không hợp lệ!", Toast.LENGTH_SHORT).show();
        else if (email.length() < 12 || email.length() > 30)
            Toast.makeText(getApplicationContext(), "Thông tin nhập vào không hợp lệ!", Toast.LENGTH_SHORT).show();
        else if (dc.length() < 5 || dc.length() > 100)
            Toast.makeText(getApplicationContext(), "Thông tin nhập vào không hợp lệ!", Toast.LENGTH_SHORT).show();
        else if (cmt.length() != 9 && cmt.length() != 12)
            Toast.makeText(getApplicationContext(), "Thông tin nhập vào không hợp lệ!", Toast.LENGTH_SHORT).show();
        else if (sdt.length() != 10)
            Toast.makeText(getApplicationContext(), "Thông tin nhập vào không hợp lệ!", Toast.LENGTH_SHORT).show();
        else {
            try {
                final Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH) + 1;
                int d = c.get(Calendar.DAY_OF_MONTH);
                String ngaytao = d + "/" + m + "/" + y;
                if (m < 10) ngaytao = d + "/0" + m + "/" + y;
                if (d < 10) ngaytao = "0" + d + "/" + m + "/" + y;
                if (m < 10 && d < 10) ngaytao = "0" + d + "/0" + m + "/" + y;
                User user = new User(account, pw, hoten, ns, email, dc, Long.valueOf(cmt), Integer.valueOf(sdt), ngaytao, 0);
                if (userCtrl.Insert(user)) {
                    Toast.makeText(Register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(Register.this, "Xin hãy nhập tên tài khoản khác!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void ShowDatePicker_NS(View v) {
        final Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH) + 1;
        int d = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener set = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int a = i1 + 1;
                etNS.setText(i2 + "/" + a + "/" + i);
                if (a < 10) etNS.setText(i2 + "/0" + a + "/" + i);
                if (i2 < 10) etNS.setText("0" + i2 + "/" + a + "/" + i);
                if (i2 < 10 && a < 10) etNS.setText("0" + i2 + "/0" + a + "/" + i);
            }
        };
        if (etNS.getText().toString().equals("")) {
            etNS.setText(d + "/" + m + "/" + y);
            if (m < 10) etNS.setText(d + "/0" + m + "/" + y);
            if (d < 10) etNS.setText("0" + d + "/" + m + "/" + y);
            if (d < 10 && m < 10) etNS.setText("0" + d + "/0" + m + "/" + y);
        }
        String s = etNS.getText().toString().trim();
        String[] arr = s.split("/");
        int Day = Integer.parseInt(arr[0]);
        int Month = Integer.parseInt(arr[1]) - 1;
        int Year = Integer.parseInt(arr[2]);

        DatePickerDialog dp = new DatePickerDialog(Register.this, set, Year, Month, Day);
        dp.setTitle("Chọn ngày:");
        dp.show();
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
