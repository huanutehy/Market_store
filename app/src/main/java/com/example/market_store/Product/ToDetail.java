package com.example.market_store.Product;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.market_store.Cart.Cart;
import com.example.market_store.Login.Login;
import com.example.market_store.Login.Register;
import com.example.market_store.OBJController.CommentCtrl;
import com.example.market_store.OBJController.DonHangCtrl;
import com.example.market_store.OBJController.ProductCtrl;
import com.example.market_store.OBJController.SpecificationCtrl;
import com.example.market_store.OBJController.UserCtrl;
import com.example.market_store.Object.Comment;
import com.example.market_store.Object.DonHang;
import com.example.market_store.Object.Product;
import com.example.market_store.Object.Specification;
import com.example.market_store.Object.User;
import com.example.market_store.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import com.taufiqrahman.reviewratings.BarLabels;
//import com.taufiqrahman.reviewratings.RatingReviews;

public class ToDetail extends AppCompatActivity {
    TextView tvTensp, tvGia, tvNSX, tvCT, tvKM, tvBH, tvReview, tvRating;
    ImageView imageView;
    Toolbar toolbar;
    Button specification;
    TabHost tabHost;
    WebView webView;
    FloatingActionButton fab;
    int id;
    //RatingReviews ratingReviews;
    CommentCtrl commentCtrl;
    UserCtrl userCtrl;
    DonHangCtrl donHangCtrl;
    List<Comment> commentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsp);
        Init();
        Event();
    }

    public void Init() {
        tvTensp = findViewById(R.id.tvProdName);
        tvGia = findViewById(R.id.tvPrice_ctsp);
        tvNSX = findViewById(R.id.tvNSX_ctsp);
        tvCT = findViewById(R.id.tvChitiet_ctsp);
        tvKM = findViewById(R.id.tvKM_ctsp);
        tvBH = findViewById(R.id.tvBH_ctsp);
        tvReview = findViewById(R.id.tvReview);
        tvRating = findViewById(R.id.tvRating);
    //    ratingReviews = findViewById(R.id.ratingReview);
        imageView = findViewById(R.id.img_ctsp);
        toolbar = findViewById(R.id.ctsp_toolbar);
        specification = findViewById(R.id.btSpecification);
        tabHost = findViewById(R.id.tabHost_detail);
        tabHost.setup();
        TabHost.TabSpec ts;
        ts = tabHost.newTabSpec("t1");
        ts.setContent(R.id.tab1);
        ts.setIndicator("Chi tiết");
        tabHost.addTab(ts);

        ts = tabHost.newTabSpec("t2");
        ts.setContent(R.id.tab2);
        ts.setIndicator("Video đánh giá");
        tabHost.addTab(ts);
        tabHost.setCurrentTab(0);

        webView = findViewById(R.id.webView);
        fab = findViewById(R.id.fab_comment);
        commentCtrl = new CommentCtrl();
        userCtrl = new UserCtrl();
        donHangCtrl = new DonHangCtrl();
        commentList = new ArrayList<>();
    }

    Product product;
    String videoID;
    public void Event() {
        Intent in = getIntent();
        Bundle bundle = in.getExtras();
        id = bundle.getInt("idProduct");
        product = new Product();
        for (int i = 0; i < ProductCtrl.productList.size(); i++) {
            if (id == ProductCtrl.productList.get(i).getIdProduct()) {
                product = ProductCtrl.productList.get(i);
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTensp.setText(product.getTensp());
        tvGia.setText(decimalFormat.format(product.getGia()) + "đ");
        tvNSX.setText(product.getNsx());
        tvCT.setText(product.getChitiet());
        tvKM.setText(product.getKhuyenmai());
        tvBH.setText(product.getBaohanh() + " tháng");
        String[] split = product.getReview().split(";");
        String review = "";
        for (int i = 1; i < split.length - 1; i++) {
            review += "- " + split[i] + "\n";
        }
        videoID = split[0];
        review += "- " + split[split.length - 1];
        tvReview.setText(review);
    //    Picasso.get().load(product.getHinhanh()).into(imageView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_1);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        specification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomSpecification();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Login.loginStatus == 0) LoginDialog();
           //     if (Login.loginStatus == 1) CustomComment();
            }
        });
  //      Rating();
        Video();
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if(tabHost.getCurrentTab() == 0) Video();
            }
        });
    }

    public void Video() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.loadUrl("http://www.youtube.com/embed/" + videoID + "?autoplay=1&vq=small");
       // webView.setWebViewClient(new MyWebViewClient());
    }

//    public void Rating() {
//        int[] ratings = new int[5];
//
//        try {
//            commentList = commentCtrl.getCommentlist(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        for (int cs = 0; cs < 5; cs++) {
//            int d = 0;
//            for (int i = 0; i < commentList.size(); i++) {
//                if (commentList.get(i).getRating() == (5 - cs)) d++;
//            }
//            ratings[cs] = d;
//        }
//
//        tvRating.setText(String.valueOf(Math.round(((5 * ratings[0] + 4 * ratings[1] + 3 * ratings[2] + 2 * ratings[3] + 1 * ratings[4]) * 1.0 / (ratings[0] + ratings[1] + ratings[2] + ratings[3] + ratings[4])) * 10) / 10.0));
//        tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.full_star, 0);
//
//        final int colors[] = new int[]{
//                Color.parseColor("#0e9d58"),
//                Color.parseColor("#bfd047"),
//                Color.parseColor("#ffc105"),
//                Color.parseColor("#ef7e14"),
//                Color.parseColor("#d36259")};
//
//        ratingReviews.createRatingBars(100, BarLabels.STYPE1, colors, ratings);
//
//    }

    public void LoginDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View loginDialogView = layoutInflater.inflate(R.layout.login, null);
        final AlertDialog loginDialog = new AlertDialog.Builder(this).create();

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
                        SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("account", username);
                        editor.putString("password", password);
                        editor.apply();
                        Login.account = username;
                        Login.password = password;
                        Login.loginStatus = 1;
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
                        if (d < 10) Cart.donHang.setNgaymua("0" + d + "/" + m + "/" + y);
                        if (m < 10 && d < 10) Cart.donHang.setNgaymua("0" + d + "/0" + m + "/" + y);
                        loginDialog.cancel();
                        Toast.makeText(ToDetail.this, "Đăng nhập thành công!!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ToDetail.this, "Đăng nhập thất bại! Mời bạn đăng nhập lại!", Toast.LENGTH_SHORT).show();
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

    public void CustomSpecification() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View alertLayout = layoutInflater.inflate(R.layout.specification, null);
        TextView tvScreen = alertLayout.findViewById(R.id.tvScreen);
        TextView tvHDH = alertLayout.findViewById(R.id.tvOS);
        TextView tvCamera = alertLayout.findViewById(R.id.tvCamera);
        TextView tvCPU = alertLayout.findViewById(R.id.tvCPU);
        TextView tvRAM = alertLayout.findViewById(R.id.tvRAM);
        TextView tvROM = alertLayout.findViewById(R.id.tvROM);
        TextView tvThenho = alertLayout.findViewById(R.id.tvExternalStorage);
        TextView tvSIM = alertLayout.findViewById(R.id.tvSIM);
        TextView tvPin = alertLayout.findViewById(R.id.tvPIN);
        Button btCancel = alertLayout.findViewById(R.id.btBack_spec);

        Specification specification = new Specification();
        for (int i = 0; i < SpecificationCtrl.specificationList.size(); i++) {
            if (id == SpecificationCtrl.specificationList.get(i).getIdProduct()) {
                specification = SpecificationCtrl.specificationList.get(i);
                break;
            }
        }
        tvScreen.setText(specification.getManhinh().trim());
        tvHDH.setText(specification.getHdh().trim());
        tvCamera.setText(specification.getCamera().trim());
        tvCPU.setText(specification.getCPU().trim());
        tvRAM.setText(String.valueOf(specification.getRAM()) + "GB");
        tvROM.setText(String.valueOf(specification.getROM()) + "GB");
        tvThenho.setText(specification.getThenho().trim());
        tvSIM.setText(specification.getSim().trim());
        tvPin.setText(specification.getPin().trim());

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        alert.setCancelable(false);

        final AlertDialog dialog = alert.create();
        dialog.show();
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

//    public void CustomComment() {
//        LayoutInflater layoutInflater = getLayoutInflater();
//        View alertLayout = layoutInflater.inflate(R.layout.custom_comment, null);
//        final ListView lv = alertLayout.findViewById(R.id.lvComment);
//        final EditText et = alertLayout.findViewById(R.id.etComment);
//        Button btSend = alertLayout.findViewById(R.id.btComment);
//        Button btBack = alertLayout.findViewById(R.id.btBack_cust_cmt);
//        final RatingBar ratingBar = alertLayout.findViewById(R.id.ratingBar);
//        final RatingReviews ratingReviewsex = alertLayout.findViewById(R.id.ratingReview);
//        final TextView tvRatingex = alertLayout.findViewById(R.id.tvRating);
//
//        int[] ratings = new int[5];
//
//        try {
//            commentList = commentCtrl.getCommentlist(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        for (int cs = 0; cs < 5; cs++) {
//            int d = 0;
//            for (int i = 0; i < commentList.size(); i++) {
//                if (commentList.get(i).getRating() == (5 - cs)) d++;
//            }
//            ratings[cs] = d;
//        }
//
//        tvRatingex.setText(String.valueOf(Math.round(((5 * ratings[0] + 4 * ratings[1] + 3 * ratings[2] + 2 * ratings[3] + 1 * ratings[4]) * 1.0 / (ratings[0] + ratings[1] + ratings[2] + ratings[3] + ratings[4])) * 10) / 10.0));
//        tvRatingex.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.full_star, 0);
//
//        final int colors[] = new int[]{
//                Color.parseColor("#0e9d58"),
//                Color.parseColor("#bfd047"),
//                Color.parseColor("#ffc105"),
//                Color.parseColor("#ef7e14"),
//                Color.parseColor("#d36259")};
//
//        ratingReviewsex.createRatingBars(100, BarLabels.STYPE1, colors, ratings);
//
//        commentList.sort(new Comparator<Comment>() {
//            @Override
//            public int compare(Comment comment, Comment t1) {
//                if (comment.getTime().compareTo(t1.getTime()) == -1) return -1;
//                if (comment.getTime().compareTo(t1.getTime()) == 1) return 1;
//                return 0;
//            }
//        });
//        final CommentLVAdapter commentLVAdapter = new CommentLVAdapter(this, R.layout.custom_cmt, commentList);
//        lv.setAdapter(commentLVAdapter);
//
//        btSend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//                String cmt = et.getText().toString().trim();
//                float rating = ratingBar.getRating();
//                if (rating == 0 || cmt.isEmpty())
//                    Toast.makeText(ToDetail.this, "Xin hãy đưa ra đánh giá và bình luận của bạn về sản phẩm!", Toast.LENGTH_SHORT).show();
//                else {
//                    final Calendar c = Calendar.getInstance();
//                    int y = c.get(Calendar.YEAR);
//                    int m = c.get(Calendar.MONTH) + 1;
//                    int dd = c.get(Calendar.DAY_OF_MONTH);
//                    int hh = c.get(Calendar.HOUR);
//                    int MM = c.get(Calendar.MINUTE);
//                    int ss = c.get(Calendar.SECOND);
//                    String time = "";
//                    String month = String.valueOf(m);
//                    String day = String.valueOf(dd);
//                    String hour = String.valueOf(hh);
//                    String minute = String.valueOf(MM);
//                    String second = String.valueOf(ss);
//                    if (m < 10) month = "0" + m;
//                    if (dd < 10) day = "0" + dd;
//                    if (hh < 10) hour = "0" + hh;
//                    if (MM < 10) minute = "0" + MM;
//                    if (ss < 10) second = "0" + ss;
//                    time = hour + ":" + minute + ":" + second + " " + day + "/" + month + "/" + y;
//                    Comment comment = new Comment(id, Login.account, cmt, (int) rating, time);
//                    try {
//                        if (commentCtrl.Insert(comment)) {
//                            et.setText("");
//                            ratingBar.setRating(0);
//                            commentList = commentCtrl.getCommentlist(id);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    int[] ratings = new int[5];
//                    for (int cs = 0; cs < 5; cs++) {
//                        int d = 0;
//                        for (int i = 0; i < commentList.size(); i++) {
//                            if (commentList.get(i).getRating() == (5 - cs)) d++;
//                        }
//                        ratings[cs] = d;
//                    }
//                    commentList.sort(new Comparator<Comment>() {
//                        @Override
//                        public int compare(Comment comment, Comment t1) {
//                            if (comment.getTime().compareTo(t1.getTime()) == -1) return -1;
//                            if (comment.getTime().compareTo(t1.getTime()) == 1) return 1;
//                            return 0;
//                        }
//                    });
//                    tvRatingex.setText(String.valueOf(Math.round(((5 * ratings[0] + 4 * ratings[1] + 3 * ratings[2] + 2 * ratings[3] + 1 * ratings[4]) * 1.0 / (ratings[0] + ratings[1] + ratings[2] + ratings[3] + ratings[4])) * 10) / 10.0));
//                    tvRating.setText(String.valueOf(Math.round(((5 * ratings[0] + 4 * ratings[1] + 3 * ratings[2] + 2 * ratings[3] + 1 * ratings[4]) * 1.0 / (ratings[0] + ratings[1] + ratings[2] + ratings[3] + ratings[4])) * 10) / 10.0));
//                    commentLVAdapter.clear();
//                    commentLVAdapter.addAll(commentList);
//                    commentLVAdapter.notifyDataSetChanged();
//                    lv.invalidateViews();
//                    lv.refreshDrawableState();
//                    ratingReviews.createRatingBars(100, BarLabels.STYPE1, colors, ratings);
//                    ratingReviewsex.createRatingBars(100, BarLabels.STYPE1, colors, ratings);
//                }
//            }
//        });
//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setView(alertLayout);
//        alert.setCancelable(false);
//        final AlertDialog dialog = alert.create();
//        dialog.show();
//        btBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.cancel();
//            }
//        });
//    }
//
//    private class MyWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            webView.loadUrl(url);
//            return true;
//        }
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//            super.onPageStarted(view, url, favicon);
//        }
//
//        @Override
//        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//            super.onReceivedError(view, request, error);
//        }
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.common_menu, menu);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                break;
//            case R.id.addtocart:
//                if (Login.loginStatus == 0) LoginDialog();
//                if (Login.loginStatus == 1) AddtoCart_dialog();
//                break;
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void AddtoCart_dialog() {
//        LayoutInflater layoutInflater = LayoutInflater.from(this);
//        final View view = layoutInflater.inflate(R.layout.addtocart_dialog, null);
//        final AlertDialog Dialog = new AlertDialog.Builder(this).create();
//        ImageView img = view.findViewById(R.id.img_addtocart);
//        TextView tvprodName = view.findViewById(R.id.tvProdName_addtocart);
//        TextView tvprodPrice = view.findViewById(R.id.tvPrice_addtocart);
//        final EditText etQuantity = view.findViewById(R.id.etQuantity_addtocart);
//        Button btConfirm = view.findViewById(R.id.btConfirm_addtocart);
//
//        Glide.with(getApplicationContext()).load(product.getHinhanh()).into(img);
//        tvprodName.setText(product.getTensp());
//        tvprodPrice.setText("Giá: " + product.getGia() + "đ");
//
//        Dialog.setView(view);
//        Dialog.show();
//        btConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int sl = Integer.valueOf(etQuantity.getText().toString().trim());
//                if (sl < 1 || sl > 10)
//                    Toast.makeText(ToDetail.this, "Đặt hàng tối đa không quá 10 chiếc! Liên hệ 0987654321 để biết thêm thông tin chi tiết!", Toast.LENGTH_SHORT).show();
//                else {
//                    boolean kt = true;
//                    for (int i = 0; i < Cart.ctdhList.size(); i++) {
//                        if (Cart.ctdhList.get(i).getIdProduct() == id) {
//                            if (Cart.ctdhList.get(i).getSoluong() + sl <= 10) {
//                                Cart.ctdhList.get(i).setSoluong(Cart.ctdhList.get(i).getSoluong() + sl);
//                                Toast.makeText(ToDetail.this, "Thêm sản phẩm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
//                                kt = false;
//                            } else {
//                                Toast.makeText(ToDetail.this, "Đặt hàng tối đa không quá 10 chiếc! Liên hệ 0987654321 để biết thêm thông tin chi tiết!", Toast.LENGTH_SHORT).show();
//                                kt = false;
//                            }
//                        }
//                    }
//                    if (kt) {
//                        Cart.ctdhList.add(new CTDH(Cart.donHang.getIdDH(), id, sl, product.getGia()));
//                        Toast.makeText(ToDetail.this, "Thêm sản phẩm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
//                    }
//                    Dialog.cancel();
//                }
//            }
//        });
//    }
}

