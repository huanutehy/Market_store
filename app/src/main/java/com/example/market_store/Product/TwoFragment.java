package com.example.market_store.Product;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.market_store.Adapter.SearchLVAdapter;
import com.example.market_store.OBJController.ProductCtrl;
import com.example.market_store.Object.Product;
import com.example.market_store.R;

import java.util.ArrayList;
import java.util.List;

public class TwoFragment extends Fragment {

    public TwoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Spinner sp;
    EditText etSearch, etPriceFrom, etPriceTo;
    AutoCompleteTextView tvSearch;
    TableRow tr;
    ListView lv;
    Button bt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_layout, container, false);
        Init(view);
        Event(view);
        return view;
    }

    public void Init(View v) {
        sp = v.findViewById(R.id.sp_search);
//        etSearch = v.findViewById(R.id.etSearch);
        tvSearch = v.findViewById(R.id.actvSearch);
        etPriceFrom = v.findViewById(R.id.etFromPrice);
        etPriceTo = v.findViewById(R.id.etToPrice);
        tr = v.findViewById(R.id.priceRow);
        lv = v.findViewById(R.id.lvSearch);
        bt = v.findViewById(R.id.btSearch);
    }

    public void Event(final View v) {
        Spinner(v);
        SetupACTV(v);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                if (sp.getSelectedItem().equals("Tên SP/NSX")) SearchwithName(v);
                else {
                    SearchwithPrice(v);
                }
            }
        });
    }

    public void Spinner(View v) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Tên SP/NSX");
        arr.add("Khoảng giá");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(v.getContext(), R.layout.customspinner, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        tvSearch.setVisibility(View.VISIBLE);
                        tr.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        tvSearch.setVisibility(View.INVISIBLE);
                        tr.setVisibility(View.VISIBLE);
                        break;
                }
                tvSearch.setText("");
                etPriceFrom.setText("");
                etPriceTo.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public void SetupACTV(View v) {
        final List<String> NSXList = new ArrayList<>();
        for (int i = 0; i < ProductCtrl.productList.size(); i++) {
            if (NSXList.size() == 0) NSXList.add(ProductCtrl.productList.get(i).getNsx());
            else {
                boolean d = true;
                for (int j = 0; j < NSXList.size(); j++) {
                    if (NSXList.get(j).equals(ProductCtrl.productList.get(i).getNsx())) {
                        d = false;
                    }
                }
                if (d) NSXList.add(ProductCtrl.productList.get(i).getNsx());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(v.getContext(), android.R.layout.simple_list_item_1, NSXList);
        tvSearch.setAdapter(adapter);
        tvSearch.setThreshold(1);
        tvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tvSearch.setText(NSXList.get(i));
            }
        });
        tvSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tvSearch.showDropDown();
                return false;
            }
        });
    }

    public void SearchwithName(View v) {
        List<Product> searchList = new ArrayList<>();
        String name = tvSearch.getText().toString().trim().toLowerCase();
        if (name.equals(""))
            Toast.makeText(getContext(), "Mời nhập thông tin tìm kiếm!!", Toast.LENGTH_SHORT).show();
        else {
            for (int i = 0; i < ProductCtrl.productList.size(); i++) {
                if (ProductCtrl.productList.get(i).getTensp().toLowerCase().contains(name) || ProductCtrl.productList.get(i).getNsx().toLowerCase().contains(name)) {
                    searchList.add(ProductCtrl.productList.get(i));
                }
            }
            if (searchList.size() == 0)
                Toast.makeText(getContext(), "Không tìm thấy sản phẩm nào!", Toast.LENGTH_SHORT).show();
            else toListView(v, searchList);
        }
    }

    public void SearchwithPrice(View v) {
        int minPrice = Integer.MIN_VALUE;
        int maxPrice = Integer.MIN_VALUE;
        List<Product> searchList = new ArrayList<>();
        if (etPriceFrom.getText().toString().trim().equals("") && etPriceTo.getText().toString().trim().equals("")) {
            Toast.makeText(getContext(), "Mời nhập mức giá nhỏ nhất hoặc lớn nhất!!", Toast.LENGTH_SHORT).show();
        } else {
            if (!etPriceFrom.getText().toString().trim().isEmpty())
                minPrice = Integer.parseInt(etPriceFrom.getText().toString().trim());
            if (!etPriceTo.getText().toString().trim().isEmpty())
                maxPrice = Integer.parseInt(etPriceTo.getText().toString().trim());
            if ((minPrice < 0 && minPrice > Integer.MIN_VALUE) || (maxPrice < 0 && maxPrice > Integer.MIN_VALUE)) {
                Toast.makeText(getContext(), "Hãy nhập mức giá tìm kiếm hợp lệ!!!", Toast.LENGTH_SHORT).show();
            } else {
                if (minPrice == Integer.MIN_VALUE || maxPrice == Integer.MIN_VALUE) {
                    for (int i = 0; i < ProductCtrl.productList.size(); i++) {
                        if (maxPrice == Integer.MIN_VALUE) {
                            if (ProductCtrl.productList.get(i).getGia() >= minPrice)
                                searchList.add(ProductCtrl.productList.get(i));
                        } else if (minPrice == Integer.MIN_VALUE) {
                            if (ProductCtrl.productList.get(i).getGia() <= maxPrice)
                                searchList.add(ProductCtrl.productList.get(i));
                        }
                    }
                } else {
                    for (int i = 0; i < ProductCtrl.productList.size(); i++) {
                        if (ProductCtrl.productList.get(i).getGia() >= minPrice && ProductCtrl.productList.get(i).getGia() <= maxPrice) {
                            searchList.add(ProductCtrl.productList.get(i));
                        }
                    }
                }
                if (searchList.size() == 0)
                    Toast.makeText(getContext(), "Không tìm thấy sản phẩm nào!", Toast.LENGTH_SHORT).show();
                else toListView(v, searchList);
            }
        }
    }

    public void toListView(final View v, final List<Product> products) {
        SearchLVAdapter adapter = new SearchLVAdapter(getContext(), R.layout.custom_sp_lv_search, products);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
