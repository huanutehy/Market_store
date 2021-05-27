package com.example.market_store.Product;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.market_store.Adapter.GridviewAdapter;
import com.example.market_store.OBJController.CTDHCtrl;
import com.example.market_store.OBJController.ProductCtrl;
import com.example.market_store.Object.Product;
import com.example.market_store.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class OneFragment extends Fragment {

    public OneFragment() {
    }
    Button bt1, bt2, btBw, btFw;
    GridView gv1, gv2, gvFilter;
    Spinner spFilter;
    CTDHCtrl ctdhCtrl;
    ProductCtrl productCtrl;
    int page;
    int maxPage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);
        Init(view);
        Event(view);
        return view;
    }

    public void Init(View v){
        bt1 = v.findViewById(R.id.btMore1);
        bt2 = v.findViewById(R.id.btMore2);
        btBw = v.findViewById(R.id.btBackward);
        btFw = v.findViewById(R.id.btForward);
        gv1 = v.findViewById(R.id.gv1);
        gv2 = v.findViewById(R.id.gv2);
        gvFilter = v.findViewById(R.id.gv_filter);
        spFilter = v.findViewById(R.id.sp_filter);
        ctdhCtrl = new CTDHCtrl();
        productCtrl = new ProductCtrl();
        page = 0;
        btFw.setEnabled(false);
        btFw.setTextColor(Color.parseColor("#88000000"));
        btFw.setBackgroundResource(R.drawable.button1);//button1_grey
        btFw.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.forward_icon_1,0);//forward_icon_1_grey
        btBw.setEnabled(false);
        btBw.setTextColor(Color.parseColor("#88000000"));//button1_grey
        btBw.setBackgroundResource(R.drawable.button1);
        btBw.setCompoundDrawablesWithIntrinsicBounds( R.drawable.back_icon_1, 0,0 ,0);//back_icon_1_grey
    }

    public void Event(View view){
        maxPage = ProductCtrl.productList.size()/12;
        if((ProductCtrl.productList.size()%12) == 0) maxPage--;
        GridView1Event(view);
        GridView2Event(view);
        Spinner(view);
        if(page < maxPage) {
            btFw.setEnabled(true);
            btFw.setTextColor(Color.parseColor("#000000"));
            btFw.setBackgroundResource(R.drawable.button1);
            btFw.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.forward_icon_1, 0);
        }
        btFw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Forward(view);
            }
        });
        if(page > 0){
            btBw.setEnabled(true);
            btBw.setTextColor(Color.parseColor("#000000"));
            btBw.setBackgroundResource(R.drawable.button1);
            btBw.setCompoundDrawablesWithIntrinsicBounds(R.drawable.back_icon_1, 0, 0, 0);
        }
        btBw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Backward(view);
            }
        });
    }
    public void GridView1Event(final View v){
        try {
            List<Product> hotList = new ArrayList<>();
            int[] count = new int[ProductCtrl.productList.size()];
            int[] index = new int[count.length];
            for(int i = 0; i < count.length; i++){
                count[i] = 0;
                index[i] = i+1;
            }
            for(int i = 0; i < count.length; i++){
                count[i] = ctdhCtrl.getCTDHlistwithProduct(index[i]).size();
            }
            for(int i = 0; i < count.length - 1; i++){
                for(int j = count.length - 1; j > i; j--){
                    if(count[j-1] < count[j]){
                        int temp = count[j-1];
                        count[j-1] = count[j];
                        count[j] = temp;
                        temp = index[j-1];
                        index[j-1] = index[j];
                        index[j] = temp;
                    }
                }
            }

            for(int i = 0; i < count.length; i++){
                hotList.add(productCtrl.getProduct(index[i]));
            }

            final List<Product> showHotList = new ArrayList<>();
            for(int i = 0; i < 6; i++){
                showHotList.add(hotList.get(i));
            }
            GridviewAdapter adapter = new GridviewAdapter(v.getContext(), showHotList);
            gv1.setAdapter(adapter);
            gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Bundle bundle = new Bundle();
                    Intent in = new Intent(v.getContext(), ToDetail.class);
                    bundle.putInt("idProduct", showHotList.get(i).getIdProduct());
                    in.putExtras(bundle);
                    startActivity(in);
                }
            });

            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Intent in = new Intent(v.getContext(), ShowProductList.class);
                    bundle.putString("key", "hot");
                    in.putExtras(bundle);
                    startActivity(in);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void GridView2Event(final View v){
        final List<Product> newProductList = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        int recentYear = c.get(Calendar.YEAR);
        int recentMonth = c.get(Calendar.MONTH) + 1;
        for(int i = 0; i < ProductCtrl.productList.size(); i++){
            String[] arr = ProductCtrl.productList.get(i).getNgaynhap().split("/");
            int month = Integer.valueOf(arr[1]);
            int year = Integer.valueOf(arr[2]);
            if(year == recentYear){
                if((recentMonth - month) < 2){
                    newProductList.add(ProductCtrl.productList.get(i));
                }
            } else {
                if((recentMonth + 12 - month) < 2){
                    newProductList.add(ProductCtrl.productList.get(i));
                }
            }
        }
        if(newProductList.size() < 21){
            for(int i = 0; i < ProductCtrl.productList.size(); i++){
                if(newProductList.size() < 21){
                    String[] arr = ProductCtrl.productList.get(i).getNgaynhap().split("/");
                    int month = Integer.valueOf(arr[1]);
                    int year = Integer.valueOf(arr[2]);
                    if(year == recentYear){
                        if((recentMonth - month) <= 3 && (recentMonth - month) > 1){
                            newProductList.add(ProductCtrl.productList.get(i));
                        }
                    } else {
                        if((recentMonth +12 - month) <= 3 && (recentMonth + 12 - month) > 1){
                            newProductList.add(ProductCtrl.productList.get(i));
                        }
                    }
                }
            }
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            for(int i = 0; i < newProductList.size(); i++){
                for(int j = newProductList.size() - 1; j > i; j--){
                    Date date1 = df.parse(newProductList.get(j).getNgaynhap());
                    Date date2 = df.parse(newProductList.get(j-1).getNgaynhap());
                    if(date1.after(date2)){
                        Collections.swap(newProductList, j, j-1);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        final List<Product> showNewList = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            showNewList.add(newProductList.get(i));
        }
        GridviewAdapter adapter = new GridviewAdapter(v.getContext(), showNewList);
        gv2.setAdapter(adapter);
        gv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                Intent in = new Intent(v.getContext(), ToDetail.class);
                bundle.putInt("idProduct", showNewList.get(i).getIdProduct());
                in.putExtras(bundle);
                startActivity(in);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                Intent in = new Intent(v.getContext(), ShowProductList.class);
                bundle.putString("key", "new");
                in.putExtras(bundle);
                startActivity(in);
            }
        });
    }
    List<Product> homeList;
    public void Spinner(final View v){
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Tất cả sản phẩm");
        arr.add("Sắp xếp A-Z");
        arr.add("Giá tăng dần");
        arr.add("Giá giảm dần");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(v.getContext(),R.layout.customspinner, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spFilter.setAdapter(adapter);
        spFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        page = 0;
                        break;
                    case 1:
                        page = 0;
                        Collections.sort(ProductCtrl.productList, new Comparator<Product>() {
                            @Override
                            public int compare(Product first, Product second) {
                                return first.getTensp().compareToIgnoreCase(second.getTensp());
                            }
                        });
                        break;
                    case 2:
                        page = 0;
                        for(int a = 0; a < ProductCtrl.productList.size() - 1; a++){
                            for(int b = ProductCtrl.productList.size() - 1; b > a; b--){
                                if(ProductCtrl.productList.get(b-1).getGia() > ProductCtrl.productList.get(b).getGia()){
                                    Product product = ProductCtrl.productList.get(b-1);
                                    ProductCtrl.productList.set(b-1, ProductCtrl.productList.get(b));
                                    ProductCtrl.productList.set(b, product);
                                }
                            }
                        }
                        break;
                    case 3:
                        page = 0;
                        for(int a = 0; a < ProductCtrl.productList.size() - 1; a++){
                            for(int b = ProductCtrl.productList.size() - 1; b > a; b--){
                                if(ProductCtrl.productList.get(b-1).getGia() < ProductCtrl.productList.get(b).getGia()){
                                    Product product = ProductCtrl.productList.get(b-1);
                                    ProductCtrl.productList.set(b-1, ProductCtrl.productList.get(b));
                                    ProductCtrl.productList.set(b, product);
                                }
                            }
                        }
                        break;
                }
                homeList = new ArrayList<>();
                for(int j = 0; j < ProductCtrl.productList.size(); j++){
                    if((j/12) == page) homeList.add(ProductCtrl.productList.get(j));
                }
                GridViewFilter(v, homeList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void Forward(View v){
        if(page < maxPage){
            page++;
            homeList = new ArrayList<>();
            for(int j = 0; j < ProductCtrl.productList.size(); j++){
                if((j/12) == page) homeList.add(ProductCtrl.productList.get(j));
            }
            btBw.setEnabled(true);
            btBw.setTextColor(Color.parseColor("#000000"));
            btBw.setBackgroundResource(R.drawable.button1);
            btBw.setCompoundDrawablesWithIntrinsicBounds(R.drawable.back_icon_1, 0, 0, 0);
            if(page >= maxPage){
                btFw.setTextColor(Color.parseColor("#88000000"));
                btFw.setEnabled(false);
                btFw.setBackgroundResource(R.drawable.button1);//button1_grey
                btFw.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.forward_icon_1 ,0);//forward_icon_1_grey
            }
            GridViewFilter(v, homeList);
        }
    }

    public void Backward(View v){
        if(page > 0){
            page--;
            homeList = new ArrayList<>();
            for(int j = 0; j < ProductCtrl.productList.size(); j++){
                if((j/12) == page) homeList.add(ProductCtrl.productList.get(j));
            }
            btFw.setEnabled(true);
            btFw.setTextColor(Color.parseColor("#000000"));
            btFw.setBackgroundResource(R.drawable.button1);
            btFw.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.forward_icon_1, 0);
            if(page <= 0){
                btBw.setEnabled(false);
                btBw.setTextColor(Color.parseColor("#88000000"));
                btBw.setBackgroundResource(R.drawable.button1);//button1_grey
                btBw.setCompoundDrawablesWithIntrinsicBounds( R.drawable.button1, 0,0,0);//back_icon_1_grey,
            }
            GridViewFilter(v, homeList);
        }
    }

    public void GridViewFilter(final View v, final List<Product> products){
        GridviewAdapter adapter = new GridviewAdapter(v.getContext(), products);
        gvFilter.setAdapter(adapter);
        gvFilter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                Intent in = new Intent(v.getContext(), ToDetail.class);
                bundle.putInt("idProduct", products.get(i).getIdProduct());
                in.putExtras(bundle);
                startActivity(in);
            }
        });
    }
}
