package com.example.market_store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.market_store.Cart.Cart;
import com.example.market_store.Login.Login;
import com.example.market_store.Login.Register;
import com.example.market_store.OBJController.CTDHCtrl;
import com.example.market_store.OBJController.CommentCtrl;
import com.example.market_store.OBJController.DonHangCtrl;
import com.example.market_store.OBJController.ProductCtrl;
import com.example.market_store.OBJController.SpecificationCtrl;
import com.example.market_store.OBJController.UserCtrl;
import com.example.market_store.Object.DonHang;
import com.example.market_store.Object.User;
import com.example.market_store.Product.OneFragment;
import com.example.market_store.Product.TwoFragment;
import com.example.market_store.User.FourFragment;
import com.example.market_store.User.ThreeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.button1,//home_icon_new2
            R.drawable.button1,//search_icon_new
            R.drawable.button1,//account_icon
            R.drawable.button1

          //  R.drawable.info_icon
    };
    public static ProductCtrl productCtrl;
    public static SpecificationCtrl specificationCtrl;
    public static UserCtrl userCtrl;
    public static CommentCtrl commentCtrl;
    public static DonHangCtrl donHangCtrl;
    public static CTDHCtrl ctdhCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        productCtrl = new ProductCtrl();
        specificationCtrl = new SpecificationCtrl();
        userCtrl = new UserCtrl();
        commentCtrl = new CommentCtrl();
        donHangCtrl = new DonHangCtrl();
        ctdhCtrl = new CTDHCtrl();
        try {
            String username;
            String password;
            SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);

            if(sharedPreferences != null){
                username = sharedPreferences.getString("account", null);
                password = sharedPreferences.getString("password", null);
                if(username != null && password != null){
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
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "Home");
        adapter.addFrag(new TwoFragment(), "Search");
        adapter.addFrag(new ThreeFragment(), "Account");
        adapter.addFrag(new FourFragment(), "Info");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void eventMoreAccount(View v){
        LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
        final View loginDialogView = layoutInflater.inflate(R.layout.login, null);
        final AlertDialog loginDialog = new AlertDialog.Builder(v.getContext()).create();

        final EditText etUsername = loginDialogView.findViewById(R.id.etUsername);
        final EditText etPassword = loginDialogView.findViewById(R.id.etPassword);

        loginDialog.setView(loginDialogView);
        Button btRegister = loginDialogView.findViewById(R.id.btRegister);
        Button btLogin = loginDialogView.findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    if (userCtrl.Check(username, password)) {
                        Login.account = username;
                        Login.password = password;
                        Login.loginStatus = 1;
                        loginDialog.cancel();
                    } else {
                        Toast.makeText(MainActivity.this, "Đăng nhập thất bại! Mời bạn đăng nhập lại!", Toast.LENGTH_SHORT).show();
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

