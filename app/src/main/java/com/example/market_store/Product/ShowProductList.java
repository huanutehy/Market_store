package com.example.market_store.Product;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.market_store.Adapter.GridviewAdapter;
import com.example.market_store.OBJController.CTDHCtrl;
import com.example.market_store.OBJController.ProductCtrl;
import com.example.market_store.Object.Product;
import com.example.market_store.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ShowProductList extends AppCompatActivity {
    TextView tvShow;
    GridView gvShow;
    Toolbar toolbar;
    ProductCtrl productCtrl;
    CTDHCtrl ctdhCtrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showproduclist);
        Init();
        Event();
    }

    public void Init() {
        tvShow = findViewById(R.id.tvShowing);
        gvShow = findViewById(R.id.gvShowing);
        toolbar = findViewById(R.id.showing_toolbar);
        productCtrl = new ProductCtrl();
        ctdhCtrl = new CTDHCtrl();
    }

    public void Event() {
        Intent in = getIntent();
        Bundle bundle = in.getExtras();
        String key = null;
        if (bundle != null) key = bundle.getString("key");
        switch (key) {
            case "hot":
                tvShow.setText(" SẢN PHẨM HOT!!!");
                tvShow.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.button1, 0);//fire2
                HottoGridView();
                break;
            case "new":
                tvShow.setText(" SẢN PHẨM MỚI!!!");
                tvShow.setCompoundDrawablesWithIntrinsicBounds(R.drawable.button1, 0, 0, 0);//new_icon_1
                NewtoGridView();
                break;
            default:
                break;
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_1);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void HottoGridView() {
        try {
            final List<Product> hotList = new ArrayList<>();
            int[] count = new int[ProductCtrl.productList.size()];
            int[] index = new int[count.length];
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
                index[i] = i + 1;
            }
            for (int i = 0; i < count.length; i++) {
                count[i] = ctdhCtrl.getCTDHlistwithProduct(index[i]).size();
            }
            for (int i = 0; i < count.length - 1; i++) {
                for (int j = count.length - 1; j > i; j--) {
                    if (count[j - 1] < count[j]) {
                        int temp = count[j - 1];
                        count[j - 1] = count[j];
                        count[j] = temp;
                        temp = index[j - 1];
                        index[j - 1] = index[j];
                        index[j] = temp;
                    }
                }
            }

            for (int i = 0; i < productCtrl.getProductlist().size(); i++) {
                if (hotList.size() < 21) hotList.add(productCtrl.getProduct(index[i]));
            }

            GridviewAdapter adapter = new GridviewAdapter(this, hotList);
            gvShow.setAdapter(adapter);
            gvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Bundle bundle = new Bundle();
                    Intent in = new Intent(ShowProductList.this, ToDetail.class);
                    bundle.putInt("idProduct", hotList.get(i).getIdProduct());
                    in.putExtras(bundle);
                    startActivity(in);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void NewtoGridView() {
        final List<Product> newProductList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int recentYear = c.get(Calendar.YEAR);
        int recentMonth = c.get(Calendar.MONTH) + 1;

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            for (int i = 0; i < ProductCtrl.productList.size(); i++) {
                for (int j = ProductCtrl.productList.size() - 1; j > i; j--) {
                    Date date1 = df.parse(ProductCtrl.productList.get(j).getNgaynhap());
                    Date date2 = df.parse(ProductCtrl.productList.get(j - 1).getNgaynhap());
                    if (date1.after(date2)) {
                        Collections.swap(ProductCtrl.productList, j, j - 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < ProductCtrl.productList.size(); i++) {
            if (newProductList.size() < 21) {
                String[] arr = ProductCtrl.productList.get(i).getNgaynhap().split("/");
                int month = Integer.valueOf(arr[1]);
                int year = Integer.valueOf(arr[2]);
                if (year == recentYear) {
                    if ((recentMonth - month) < 2) {
                        newProductList.add(ProductCtrl.productList.get(i));
                    }
                } else {
                    if ((recentMonth + 12 - month) < 2) {
                        newProductList.add(ProductCtrl.productList.get(i));
                    }
                }
            }
        }
        if (newProductList.size() < 21) {
            for (int i = 0; i < ProductCtrl.productList.size(); i++) {
                if (newProductList.size() < 21) {
                    String[] arr = ProductCtrl.productList.get(i).getNgaynhap().split("/");
                    int month = Integer.valueOf(arr[1]);
                    int year = Integer.valueOf(arr[2]);
                    if (year == recentYear) {
                        if ((recentMonth - month) <= 3 && (recentMonth - month) > 1) {
                            newProductList.add(ProductCtrl.productList.get(i));
                        }
                    } else {
                        if ((recentMonth + 12 - month) <= 3 && (recentMonth + 12 - month) > 1) {
                            newProductList.add(ProductCtrl.productList.get(i));
                        }
                    }
                }
            }
        }

        GridviewAdapter adapter = new GridviewAdapter(this, newProductList);
        gvShow.setAdapter(adapter);
        gvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                Intent in = new Intent(ShowProductList.this, ToDetail.class);
                bundle.putInt("idProduct", newProductList.get(i).getIdProduct());
                in.putExtras(bundle);
                startActivity(in);
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
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
