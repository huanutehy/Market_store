package com.example.market_store.User;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.market_store.Admin.QLDH_admin;
import com.example.market_store.Cart.Cart;
import com.example.market_store.Login.Login;
import com.example.market_store.Login.Register;
import com.example.market_store.OBJController.DonHangCtrl;
import com.example.market_store.OBJController.UserCtrl;
import com.example.market_store.Object.DonHang;
import com.example.market_store.Object.User;
import com.example.market_store.R;

import java.util.ArrayList;
import java.util.Calendar;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class ThreeFragment extends Fragment {

    public ThreeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Button btmoreaccount, btQLGH, btStatistic;
    UserCtrl userCtrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_layout, container, false);
        Init(view);
        Event(view);

        return view;
    }

    public void Init(View view) {
        btmoreaccount = view.findViewById(R.id.btMoreAccount);
        btQLGH = view.findViewById(R.id.btQLGH);
        btStatistic = view.findViewById(R.id.btStatistic);
        userCtrl = new UserCtrl();
    }

    public void Event(View view) {
        try {
            User user = userCtrl.getUser(Login.account);
            if (Login.loginStatus == 0) btmoreaccount.setText("Tài khoản");
            else {
                btmoreaccount.setText(user.getHoten());
                if (user.getLoaitk() == 1) {
                    btQLGH.setText("Quản lý đơn hàng");
                    btStatistic.setVisibility(View.VISIBLE);
                }
                if (user.getLoaitk() == 0) {
                    btQLGH.setText("Giỏ hàng");
                    btStatistic.setVisibility(View.INVISIBLE);
                }
            }
            eventMoreAccount(view);
            btQLGH.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        User user = userCtrl.getUser(Login.account);
                        if (Login.loginStatus == 0) LoginDialog(view);
                        if (Login.loginStatus == 1 && user.getLoaitk() == 0) {
                            Intent in = new Intent(getContext(), QLGH.class);
                            startActivity(in);
                        }
                        if (Login.loginStatus == 1 && user.getLoaitk() == 1) {
                            Intent in = new Intent(getContext(), QLDH_admin.class);
                            startActivity(in);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            btStatistic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(getContext(), Statistic.class);
                    startActivity(in);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eventMoreAccount(final View v) {
        btmoreaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Login.loginStatus == 0)
                    LoginDialog(v);
                if (Login.loginStatus == 1) {
                    Intent in = new Intent(v.getContext(), UserDetail.class);
                    startActivityForResult(in, 1);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                btmoreaccount.setText("Tài khoản");
                btQLGH.setText("Giỏ hàng");
                btStatistic.setVisibility(View.INVISIBLE);
            }
            if (resultCode == RESULT_CANCELED) {
                try {
                    User user = userCtrl.getUser(Login.account);
                    btmoreaccount.setText(user.getHoten());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void LoginDialog(View view) {
        LayoutInflater layoutInflater = LayoutInflater.from(view.getContext());
        final View loginDialogView = layoutInflater.inflate(R.layout.login, null);
        final android.app.AlertDialog loginDialog = new android.app.AlertDialog.Builder(view.getContext()).create();

        final EditText etUsername = loginDialogView.findViewById(R.id.etUsername);
        final EditText etPassword = loginDialogView.findViewById(R.id.etPassword);

        loginDialog.setView(loginDialogView);
        Button btRegister = loginDialogView.findViewById(R.id.btRegister);
        Button btLogin = loginDialogView.findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                try {
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    if (userCtrl.Check(username, password)) {
                        SharedPreferences sharedPreferences= getContext().getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("account", username);
                        editor.putString("password", password);
                        editor.apply();
                        final Calendar c = Calendar.getInstance();
                        int y = c.get(Calendar.YEAR);
                        int m = c.get(Calendar.MONTH) + 1;
                        int d = c.get(Calendar.DAY_OF_MONTH);
                        Login.account = username;
                        Login.password = password;
                        Login.loginStatus = 1;
                        User user = userCtrl.getUser(username);
                        Cart.ctdhList = new ArrayList<>();
                        Cart.donHang = new DonHang(DonHangCtrl.donHangList.size() + 1, username, user.getHoten(), user.getSDT(), user.getDiachi(), 0, d + "/" + m + "/" + y);
                        if (m < 10) Cart.donHang.setNgaymua(d + "/0" + m + "/" + y);
                        Toast.makeText(getContext(), "Đăng nhập thành công!!", Toast.LENGTH_SHORT).show();
                        loginDialog.cancel();
                        btmoreaccount.setText(user.getHoten());
                        if (user.getLoaitk() == 1) {
                            btQLGH.setText("Quản lý đơn hàng");
                            btStatistic.setVisibility(View.VISIBLE);
                        }
                        if (user.getLoaitk() == 0) {
                            btQLGH.setText("Giỏ hàng");
                            btStatistic.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        Toast.makeText(getContext(), "Đăng nhập thất bại! Mời bạn đăng nhập lại!", Toast.LENGTH_SHORT).show();
                        etUsername.setText("");
                        etPassword.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(view.getContext(), Register.class);
                startActivity(in);
            }
        });
        loginDialog.show();
    }
}
