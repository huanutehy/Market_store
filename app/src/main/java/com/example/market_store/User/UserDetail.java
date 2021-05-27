package com.example.market_store.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.project4.Buy.BuyHistory;
import com.example.project4.Login.Login;
import com.example.project4.OBJController.UserCtrl;
import com.example.project4.Object.User;
import com.example.project4.R;

import java.util.Calendar;


public class UserDetail extends AppCompatActivity {
    UserCtrl userCtrl;
    TextView etAccount, etPW, etHoten, etNS, etEmail, etDC, etCMT, etSDT;
    Button btHistory, btLogout;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietuser);
        Init();
        Event();
    }

    public void Init() {
        userCtrl = new UserCtrl();
        etAccount = findViewById(R.id.etUserName_ctuser);
        etPW = findViewById(R.id.etPassword_ctuser);
        etHoten = findViewById(R.id.etHoten_ctuser);
        etNS = findViewById(R.id.etNgaysinh_ctuser);
        etEmail = findViewById(R.id.etEmail_ctuser);
        etDC = findViewById(R.id.etDiachi_ctuser);
        etCMT = findViewById(R.id.etCMT_ctuser);
        etSDT = findViewById(R.id.etSDT_ctuser);
        btHistory = findViewById(R.id.btHistory);
        btLogout = findViewById(R.id.btLogout);
        toolbar = findViewById(R.id.ctuser_toolbar);
    }

    public void Event() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_1);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        try {
            User user = userCtrl.getUser(Login.account);
            etAccount.setText(user.getAccount());
            etPW.setText(user.getPassword());
            etHoten.setText(user.getHoten());
            etNS.setText(user.getNgaysinh());
            etEmail.setText(user.getEmail());
            etDC.setText(user.getDiachi());
            if(user.getLoaitk() == 0) btHistory.setVisibility(View.VISIBLE);
            if(user.getLoaitk() == 1) btHistory.setVisibility(View.INVISIBLE);
            if (String.valueOf(user.getCMT()).length() > 9) {
                if(12 - String.valueOf(user.getCMT()).length() == 1)
                    etCMT.setText("0" + user.getCMT());
                if(12 - String.valueOf(user.getCMT()).length() == 2)
                    etCMT.setText("00" + user.getCMT());
            } else
                etCMT.setText(String.valueOf(user.getCMT()));
            etSDT.setText("0" + user.getSDT());
        } catch (Exception e) {
            e.printStackTrace();
        }
        changePW();
        etHoten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeName();
            }
        });
        etNS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeNS();
            }
        });
        etEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeEmail();
            }
        });
        etDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeDC();
            }
        });
        etCMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeCMT();
            }
        });
        etSDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSDT();
            }
        });
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        btHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyHistory();
            }
        });
    }

    public void logout() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
        Login.account = "";
        Login.password = "";
        Login.loginStatus = 0;
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void buyHistory(){
        Intent in = new Intent(this, BuyHistory.class);
        startActivity(in);
    }

    public void changePW() {
        etPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View alertLayout = layoutInflater.inflate(R.layout.pw_change_dialog, null);
                final EditText etOldPW = (EditText) alertLayout.findViewById(R.id.etOldPassword);
                final EditText etNew1PW = (EditText) alertLayout.findViewById(R.id.etNew1Password);
                final EditText etNew2PW = (EditText) alertLayout.findViewById(R.id.etNew2Password);
                Button btConfirm = alertLayout.findViewById(R.id.btConfirm);

                AlertDialog.Builder builder = new AlertDialog.Builder(UserDetail.this);
                builder.setView(alertLayout);

                final AlertDialog dialog = builder.create();
                dialog.show();
                btConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            String oldPW = etOldPW.getText().toString().trim();
                            String new1PW = etNew1PW.getText().toString().trim();
                            String new2PW = etNew2PW.getText().toString().trim();
                            User user = userCtrl.getUser(Login.account);
                            if (!oldPW.equals(user.getPassword().trim())) {
                                Toast.makeText(UserDetail.this, "Mật khẩu cũ không chính xác!!", Toast.LENGTH_SHORT).show();
                            } else {
                                if (new1PW.length() < 6 || new1PW.length() > 20)
                                    Toast.makeText(UserDetail.this, "Độ dài mật khẩu từ 6-20 ký tự!!", Toast.LENGTH_SHORT).show();
                                else {
                                    if (!new2PW.equals(new1PW)) {
                                        Toast.makeText(UserDetail.this, "Nhập lại mật khẩu không chính xác!!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        user.setPassword(new1PW);
                                        if (userCtrl.Update(user)) {
                                            Toast.makeText(UserDetail.this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                                            Event();
                                            dialog.cancel();
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void changeEmail() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View alertLayout = layoutInflater.inflate(R.layout.custom_change_userinfo, null);
        TextView tvInfo = alertLayout.findViewById(R.id.tvInfo);
        final EditText etInfo = alertLayout.findViewById(R.id.etInfo);
        Button btConfirm = alertLayout.findViewById(R.id.btConfirm);

        tvInfo.setText("Email:");
        AlertDialog.Builder builder = new AlertDialog.Builder(UserDetail.this);
        builder.setView(alertLayout);
        final AlertDialog dialog = builder.create();
        dialog.show();
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    String info = etInfo.getText().toString().trim();
                    if (info.length() < 12 || info.length() > 30) {
                        Toast.makeText(UserDetail.this, "Thông tin nhập vào không hợp lệ!", Toast.LENGTH_SHORT).show();
                    } else {
                        User user = userCtrl.getUser(Login.account);
                        user.setEmail(info);
                        if (userCtrl.Update(user)) {
                            Toast.makeText(UserDetail.this, "Cập nhật thông tin thành công!!", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            Event();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void changeDC() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View alertLayout = layoutInflater.inflate(R.layout.custom_change_userinfo, null);
        TextView tvInfo = alertLayout.findViewById(R.id.tvInfo);
        final EditText etInfo = alertLayout.findViewById(R.id.etInfo);
        Button btConfirm = alertLayout.findViewById(R.id.btConfirm);

        tvInfo.setText("Địa chỉ:");
        AlertDialog.Builder builder = new AlertDialog.Builder(UserDetail.this);
        builder.setView(alertLayout);
        final AlertDialog dialog = builder.create();
        dialog.show();
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    String info = etInfo.getText().toString().trim();
                    if (info.length() < 5 || info.length() > 100) {
                        Toast.makeText(UserDetail.this, "Thông tin nhập vào không hợp lệ!", Toast.LENGTH_SHORT).show();
                    } else {
                        User user = userCtrl.getUser(Login.account);
                        user.setDiachi(info);
                        if (userCtrl.Update(user)) {
                            Toast.makeText(UserDetail.this, "Cập nhật thông tin thành công!!", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            Event();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void changeCMT() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View alertLayout = layoutInflater.inflate(R.layout.custom_change_userinfo, null);
        TextView tvInfo = alertLayout.findViewById(R.id.tvInfo);
        final EditText etInfo = alertLayout.findViewById(R.id.etInfo);
        Button btConfirm = alertLayout.findViewById(R.id.btConfirm);

        tvInfo.setText("Số CMT/CCCD:");
        etInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder builder = new AlertDialog.Builder(UserDetail.this);
        builder.setView(alertLayout);
        final AlertDialog dialog = builder.create();
        dialog.show();
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    String info = etInfo.getText().toString().trim();
                    if (info.length() != 9 && info.length() != 12) {
                        Toast.makeText(UserDetail.this, "Thông tin nhập vào không hợp lệ!", Toast.LENGTH_SHORT).show();
                    } else {
                        User user = userCtrl.getUser(Login.account);
                        user.setCMT(Long.valueOf(info));
                        if (userCtrl.Update(user)) {
                            Toast.makeText(UserDetail.this, "Cập nhật thông tin thành công!!", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            Event();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void changeSDT() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View alertLayout = layoutInflater.inflate(R.layout.custom_change_userinfo, null);
        TextView tvInfo = alertLayout.findViewById(R.id.tvInfo);
        final EditText etInfo = alertLayout.findViewById(R.id.etInfo);
        Button btConfirm = alertLayout.findViewById(R.id.btConfirm);

        tvInfo.setText("Số điện thoại:");
        etInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder builder = new AlertDialog.Builder(UserDetail.this);
        builder.setView(alertLayout);
        final AlertDialog dialog = builder.create();
        dialog.show();
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    String info = etInfo.getText().toString().trim();
                    if (info.length() != 10) {
                        Toast.makeText(UserDetail.this, "Thông tin nhập vào không hợp lệ!", Toast.LENGTH_SHORT).show();
                    } else {
                        User user = userCtrl.getUser(Login.account);
                        user.setSDT(Integer.valueOf(info));
                        if (userCtrl.Update(user)) {
                            Toast.makeText(UserDetail.this, "Cập nhật thông tin thành công!!", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            Event();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void changeNS() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View alertLayout = layoutInflater.inflate(R.layout.custom_change_date, null);
        TextView tvInfo = alertLayout.findViewById(R.id.tvInfo);
        final TextView etInfo = alertLayout.findViewById(R.id.etInfo);
        Button btConfirm = alertLayout.findViewById(R.id.btConfirm);
        tvInfo.setText("Ngày sinh:");
        try {
            User user = userCtrl.getUser(Login.account);
            etInfo.setText(user.getNgaysinh());
            etInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();
                    DatePickerDialog.OnDateSetListener set = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            int a = i1 + 1;
                            etInfo.setText(i2 + "/" + a + "/" + i);
                        }
                    };
                    String s = etInfo.getText().toString().trim();
                    String[] arr = s.split("/");
                    int Day = Integer.parseInt(arr[0]);
                    int Month = Integer.parseInt(arr[1]) - 1;
                    int Year = Integer.parseInt(arr[2]);
                    DatePickerDialog dp = new DatePickerDialog(UserDetail.this, set, Year, Month, Day);
                    dp.setTitle("Chọn ngày:");
                    dp.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(UserDetail.this);
        builder.setView(alertLayout);
        final AlertDialog dialog = builder.create();
        dialog.show();
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String info = etInfo.getText().toString().trim();
                    User user = userCtrl.getUser(Login.account);
                    user.setNgaysinh(info);
                    if (userCtrl.Update(user)) {
                        Toast.makeText(UserDetail.this, "Cập nhật thông tin thành công!!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        Event();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void changeName() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View alertLayout = layoutInflater.inflate(R.layout.custom_change_userinfo, null);
        TextView tvInfo = alertLayout.findViewById(R.id.tvInfo);
        final EditText etInfo = alertLayout.findViewById(R.id.etInfo);
        Button btConfirm = alertLayout.findViewById(R.id.btConfirm);

        tvInfo.setText("Họ tên:");
        AlertDialog.Builder builder = new AlertDialog.Builder(UserDetail.this);
        builder.setView(alertLayout);
        final AlertDialog dialog = builder.create();
        dialog.show();
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    String info = etInfo.getText().toString().trim();
                    if (info.length() < 3 || info.length() > 30) {
                        Toast.makeText(UserDetail.this, "Thông tin nhập vào không hợp lệ!", Toast.LENGTH_SHORT).show();
                    } else {
                        User user = userCtrl.getUser(Login.account);
                        user.setHoten(info);
                        if (userCtrl.Update(user)) {
                            Toast.makeText(UserDetail.this, "Cập nhật thông tin thành công!!", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            Event();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
