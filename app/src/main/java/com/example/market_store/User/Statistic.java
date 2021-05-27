package com.example.market_store.User;

import android.os.Bundle;
import android.widget.TabHost;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.market_store.OBJController.CTDHCtrl;
import com.example.market_store.OBJController.DonHangCtrl;
import com.example.market_store.OBJController.ProductCtrl;
import com.example.market_store.OBJController.UserCtrl;
import com.example.market_store.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;

import java.util.List;

//import com.github.mikephil.charting.formatter.IAxisValueFormatter;
//import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

public class Statistic extends AppCompatActivity {
    TabHost tabHost;
    Toolbar toolbar;
    HorizontalBarChart chart1, chart2, chart3;
    CTDHCtrl ctdhCtrl;
    ProductCtrl productCtrl;
    UserCtrl userCtrl;
    DonHangCtrl donHangCtrl;
    List<String> productName;
    List<Integer> productCount;
    int[] countChart2;
    int[] countChart3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistic);
//        Init();
//        Event();
    }
//
//    public void Init() {
//        tabHost = findViewById(R.id.tabHost);
//        tabHost.setup();
//
//        TabHost.TabSpec ts;
//        ts = tabHost.newTabSpec("t1");
//        ts.setContent(R.id.tab1);
//        ts.setIndicator("Sản phẩm hot");
//        tabHost.addTab(ts);
//
//        ts = tabHost.newTabSpec("t2");
//        ts.setContent(R.id.tab2);
//        ts.setIndicator("Tài khoản mới");
//        tabHost.addTab(ts);
//
//        ts = tabHost.newTabSpec("t1");
//        ts.setContent(R.id.tab3);
//        ts.setIndicator("Tiền bán hàng");
//        tabHost.addTab(ts);
//
//        tabHost.setCurrentTab(0);
//
//        toolbar = findViewById(R.id.stat_toolbar);
//        chart1 = findViewById(R.id.chart1);
//        chart2 = findViewById(R.id.chart2);
//        chart3 = findViewById(R.id.chart3);
//        productName = new ArrayList<>();
//        productCount = new ArrayList<>();
//        ctdhCtrl = new CTDHCtrl();
//        productCtrl = new ProductCtrl();
//        userCtrl = new UserCtrl();
//        donHangCtrl = new DonHangCtrl();
//    }
//
//    public void Event() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon_1);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        DataChart1();
//        SetupChart1();
//        DataChart2();
//        SetupChart2();
//        DataChart3();
//        SetupChart3();
//    }
//
//    public void SetupChart1() {
//        BarData data = new BarData(getDataSet1());
//        chart1.setData(data);
//        chart1.animateXY(0, 0);
//        chart1.invalidate();
//
//        chart1.getAxisRight().setEnabled(false);
//        chart1.getXAxis().setDrawGridLines(false);
//        chart1.getAxisRight().setDrawGridLines(false);
//        chart1.getAxisLeft().setDrawGridLines(false);
//        chart1.getLegend().setEnabled(false);
//        chart1.getXAxis().setTextSize(10f);
//
//        final ArrayList<String> labels = new ArrayList();
//        for (int i = 9; i >= 0; i--) {
//            labels.add(productName.get(i));
//        }
//        XAxis xAxis = chart1.getXAxis();
//        xAxis.setGranularity(1f);
//        xAxis.setValueFormatter(new IndexAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                return labels.get((int) value);
//            }
//        });
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        chart1.getDescription().setText("Lượt mua");
//        chart1.setVisibleXRangeMaximum(8);
//        chart1.moveViewTo(0f, data.getEntryCount(), YAxis.AxisDependency.LEFT);
//    }
//
//    public void DataChart1() {
//        try {
//            int[] count = new int[ProductCtrl.productList.size()];
//            int[] index = new int[count.length];
//            for (int i = 0; i < count.length; i++) {
//                count[i] = 0;
//                index[i] = i + 1;
//            }
//            for (int i = 0; i < count.length; i++) {
//                count[i] = ctdhCtrl.getCTDHlistwithProduct(index[i]).size();
//            }
//            for (int i = 0; i < count.length - 1; i++) {
//                for (int j = count.length - 1; j > i; j--) {
//                    if (count[j - 1] < count[j]) {
//                        int temp = count[j - 1];
//                        count[j - 1] = count[j];
//                        count[j] = temp;
//                        temp = index[j - 1];
//                        index[j - 1] = index[j];
//                        index[j] = temp;
//                    }
//                }
//            }
//
//            for (int i = 0; i < 10; i++) {
//                productName.add(productCtrl.getProduct(index[i]).getTensp());
//                productCount.add(count[i]);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private ArrayList<IBarDataSet> getDataSet1() {
//        ArrayList<IBarDataSet> dataSets = null;
//        ArrayList<BarEntry> entries1 = new ArrayList();
//        entries1.add(new BarEntry(9, productCount.get(0)));
//        ArrayList<BarEntry> entries2 = new ArrayList();
//        entries2.add(new BarEntry(8, productCount.get(1)));
//        ArrayList<BarEntry> entries3 = new ArrayList();
//        entries3.add(new BarEntry(7, productCount.get(2)));
//        ArrayList<BarEntry> entries4 = new ArrayList();
//        entries4.add(new BarEntry(6, productCount.get(3)));
//        ArrayList<BarEntry> entries5 = new ArrayList();
//        entries5.add(new BarEntry(5, productCount.get(4)));
//        ArrayList<BarEntry> entries6 = new ArrayList();
//        entries6.add(new BarEntry(4, productCount.get(5)));
//        ArrayList<BarEntry> entries7 = new ArrayList();
//        entries7.add(new BarEntry(3, productCount.get(6)));
//        ArrayList<BarEntry> entries8 = new ArrayList();
//        entries8.add(new BarEntry(2, productCount.get(7)));
//        ArrayList<BarEntry> entries9 = new ArrayList();
//        entries9.add(new BarEntry(1, productCount.get(8)));
//        ArrayList<BarEntry> entries10 = new ArrayList();
//        entries10.add(new BarEntry(0, productCount.get(9)));
//
//        dataSets = new ArrayList<>();
//
//        BarDataSet dataset1 = new BarDataSet(entries1, null);
//        dataset1.setColor(Color.parseColor("#0E9D90"));
//        dataSets.add(dataset1);
//        BarDataSet dataset2 = new BarDataSet(entries2, null);
//        dataset2.setColor(Color.parseColor("#0e9d58"));
//        dataSets.add(dataset2);
//        BarDataSet dataset3 = new BarDataSet(entries3, null);
//        dataset3.setColor(Color.parseColor("#bfd047"));
//        dataSets.add(dataset3);
//        BarDataSet dataset4 = new BarDataSet(entries4, null);
//        dataset4.setColor(Color.parseColor("#ffc105"));
//        dataSets.add(dataset4);
//        BarDataSet dataset5 = new BarDataSet(entries5, null);
//        dataset5.setColor(Color.parseColor("#ef7e14"));
//        dataSets.add(dataset5);
//        BarDataSet dataset6 = new BarDataSet(entries6, null);
//        dataset6.setColor(Color.parseColor("#d36259"));
//        dataSets.add(dataset6);
//        BarDataSet dataset7 = new BarDataSet(entries7, null);
//        dataset7.setColor(Color.parseColor("#058EB6"));
//        dataSets.add(dataset7);
//        BarDataSet dataset8 = new BarDataSet(entries8, null);
//        dataset8.setColor(Color.parseColor("#0558B6"));
//        dataSets.add(dataset8);
//        BarDataSet dataset9 = new BarDataSet(entries9, null);
//        dataset9.setColor(Color.parseColor("#0512B6"));
//        dataSets.add(dataset9);
//        BarDataSet dataset10 = new BarDataSet(entries10, null);
//        dataset10.setColor(Color.parseColor("#66159E"));
//        dataSets.add(dataset10);
//
//        return dataSets;
//    }
//
//    public void SetupChart2() {
//        BarData data = new BarData(getDataSet2());
//        chart2.setData(data);
//        chart2.animateXY(0, 0);
//        chart2.invalidate();
//
//        chart2.getAxisRight().setEnabled(false);
//        chart2.getXAxis().setDrawGridLines(false);
//        chart2.getAxisRight().setDrawGridLines(false);
//        chart2.getAxisLeft().setDrawGridLines(false);
//        chart2.getLegend().setEnabled(false);
//        chart2.getXAxis().setTextSize(10f);
//
//        final ArrayList<String> labels = new ArrayList();
//        for (int i = 12; i >= 1; i--) {
//            labels.add("Tháng "+ i);
//        }
//        XAxis xAxis = chart2.getXAxis();
//        xAxis.setGranularity(1f);
//        xAxis.setValueFormatter(new IndexAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                return labels.get((int) value);
//            }
//        });
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        chart2.getDescription().setText("Số lượt tạo tài khoản");
//        chart2.setVisibleXRangeMaximum(8);
//        chart2.moveViewTo(0f, data.getEntryCount(), YAxis.AxisDependency.LEFT);
//    }
//    public void DataChart2() {
//        try {
//            countChart2 = new int[12];
//            for (int i = 0; i < countChart2.length; i++) {
//                countChart2[i] = 0;
//            }
//            List<User> users = userCtrl.getUserlist();
//            final Calendar c = Calendar.getInstance();
//            int y = c.get(Calendar.YEAR);
//            for (int i = 0; i < users.size(); i++) {
//                String[] split = users.get(i).getNgaytao().split("/");
//                if (split[2].equals(String.valueOf(y)))
//                    switch (split[1]) {
//                        case "01":
//                            countChart2[0]++;
//                            break;
//                        case "02":
//                            countChart2[1]++;
//                            break;
//                        case "03":
//                            countChart2[2]++;
//                            break;
//                        case "04":
//                            countChart2[3]++;
//                            break;
//                        case "05":
//                            countChart2[4]++;
//                            break;
//                        case "06":
//                            countChart2[5]++;
//                            break;
//                        case "07":
//                            countChart2[6]++;
//                            break;
//                        case "08":
//                            countChart2[7]++;
//                            break;
//                        case "09":
//                            countChart2[8]++;
//                            break;
//                        case "10":
//                            countChart2[9]++;
//                            break;
//                        case "11":
//                            countChart2[10]++;
//                            break;
//                        case "12":
//                            countChart2[11]++;
//                            break;
//                    }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private ArrayList<IBarDataSet> getDataSet2() {
//        ArrayList<IBarDataSet> dataSets = null;
//        ArrayList<BarEntry> entries1 = new ArrayList();
//        entries1.add(new BarEntry(0, countChart2[11]));
//        ArrayList<BarEntry> entries2 = new ArrayList();
//        entries2.add(new BarEntry(1, countChart2[10]));
//        ArrayList<BarEntry> entries3 = new ArrayList();
//        entries3.add(new BarEntry(2, countChart2[9]));
//        ArrayList<BarEntry> entries4 = new ArrayList();
//        entries4.add(new BarEntry(3, countChart2[8]));
//        ArrayList<BarEntry> entries5 = new ArrayList();
//        entries5.add(new BarEntry(4, countChart2[7]));
//        ArrayList<BarEntry> entries6 = new ArrayList();
//        entries6.add(new BarEntry(5, countChart2[6]));
//        ArrayList<BarEntry> entries7 = new ArrayList();
//        entries7.add(new BarEntry(6, countChart2[5]));
//        ArrayList<BarEntry> entries8 = new ArrayList();
//        entries8.add(new BarEntry(7, countChart2[4]));
//        ArrayList<BarEntry> entries9 = new ArrayList();
//        entries9.add(new BarEntry(8, countChart2[3]));
//        ArrayList<BarEntry> entries10 = new ArrayList();
//        entries10.add(new BarEntry(9, countChart2[2]));
//        ArrayList<BarEntry> entries11 = new ArrayList();
//        entries11.add(new BarEntry(10, countChart2[1]));
//        ArrayList<BarEntry> entries12 = new ArrayList();
//        entries12.add(new BarEntry(11, countChart2[0]));
//
//        dataSets = new ArrayList<>();
//
//        BarDataSet dataset1 = new BarDataSet(entries1, null);
//        dataset1.setColor(Color.parseColor("#0E9D90"));
//        dataSets.add(dataset1);
//        BarDataSet dataset2 = new BarDataSet(entries2, null);
//        dataset2.setColor(Color.parseColor("#0e9d58"));
//        dataSets.add(dataset2);
//        BarDataSet dataset3 = new BarDataSet(entries3, null);
//        dataset3.setColor(Color.parseColor("#bfd047"));
//        dataSets.add(dataset3);
//        BarDataSet dataset4 = new BarDataSet(entries4, null);
//        dataset4.setColor(Color.parseColor("#ffc105"));
//        dataSets.add(dataset4);
//        BarDataSet dataset5 = new BarDataSet(entries5, null);
//        dataset5.setColor(Color.parseColor("#ef7e14"));
//        dataSets.add(dataset5);
//        BarDataSet dataset6 = new BarDataSet(entries6, null);
//        dataset6.setColor(Color.parseColor("#d36259"));
//        dataSets.add(dataset6);
//        BarDataSet dataset7 = new BarDataSet(entries7, null);
//        dataset7.setColor(Color.parseColor("#058EB6"));
//        dataSets.add(dataset7);
//        BarDataSet dataset8 = new BarDataSet(entries8, null);
//        dataset8.setColor(Color.parseColor("#0558B6"));
//        dataSets.add(dataset8);
//        BarDataSet dataset9 = new BarDataSet(entries9, null);
//        dataset9.setColor(Color.parseColor("#0512B6"));
//        dataSets.add(dataset9);
//        BarDataSet dataset10 = new BarDataSet(entries10, null);
//        dataset10.setColor(Color.parseColor("#66159E"));
//        dataSets.add(dataset10);
//        BarDataSet dataset11 = new BarDataSet(entries11, null);
//        dataset11.setColor(Color.parseColor("#B11CCC"));
//        dataSets.add(dataset11);
//        BarDataSet dataset12 = new BarDataSet(entries12, null);
//        dataset12.setColor(Color.parseColor("#CC1CAF"));
//        dataSets.add(dataset12);
//
//        return dataSets;
//    }
//
//    public void SetupChart3() {
//        BarData data = new BarData(getsDataSet3());
//        chart3.setData(data);
//        chart3.animateXY(0, 0);
//        chart3.invalidate();
//
//        chart3.getAxisRight().setEnabled(false);
//        chart3.getXAxis().setDrawGridLines(false);
//        chart3.getAxisRight().setDrawGridLines(false);
//        chart3.getAxisLeft().setDrawGridLines(false);
//        chart3.getLegend().setEnabled(false);
//        chart3.getXAxis().setTextSize(10f);
//
//        final ArrayList<String> labels = new ArrayList();
//        for (int i = 12; i >= 1; i--) {
//            labels.add("Tháng "+ i);
//        }
//        XAxis xAxis = chart3.getXAxis();
//        xAxis.setGranularity(1f);
//        xAxis.setValueFormatter(new IndexAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                return labels.get((int) value);
//            }
//        });
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        chart3.getDescription().setText("VNĐ");
//        chart3.setVisibleXRangeMaximum(8);
//        chart3.moveViewTo(0f, data.getEntryCount(), YAxis.AxisDependency.LEFT);
//    }
//    public void DataChart3() {
//        try {
//            List<DonHang> donHangs = donHangCtrl.getDonHanglist();
//            countChart3 = new int[12];
//            for (int i = 0; i < countChart3.length; i++) {
//                countChart3[i] = 0;
//            }
//            final Calendar c = Calendar.getInstance();
//            int y = c.get(Calendar.YEAR);
//            for (int i = 0; i < donHangs.size(); i++) {
//                String[] split = donHangs.get(i).getNgaymua().split("/");
//                if (split[2].equals(String.valueOf(y))&& donHangs.get(i).getStatus() != 3)
//                    switch (split[1]) {
//                        case "01":
//                            countChart3[0]+=Tinhtien(donHangs.get(i).getIdDH());
//                            break;
//                        case "02":
//                            countChart3[1]+=Tinhtien(donHangs.get(i).getIdDH());
//                            break;
//                        case "03":
//                            countChart3[2]+=Tinhtien(donHangs.get(i).getIdDH());
//                            break;
//                        case "04":
//                            countChart3[3]+=Tinhtien(donHangs.get(i).getIdDH());
//                            break;
//                        case "05":
//                            countChart3[4]+=Tinhtien(donHangs.get(i).getIdDH());
//                            break;
//                        case "06":
//                            countChart3[5]+=Tinhtien(donHangs.get(i).getIdDH());
//                            break;
//                        case "07":
//                            countChart3[6]+=Tinhtien(donHangs.get(i).getIdDH());
//                            break;
//                        case "08":
//                            countChart3[7]+=Tinhtien(donHangs.get(i).getIdDH());
//                            break;
//                        case "09":
//                            countChart3[8]+=Tinhtien(donHangs.get(i).getIdDH());
//                            break;
//                        case "10":
//                            countChart3[9]+=Tinhtien(donHangs.get(i).getIdDH());
//                            break;
//                        case "11":
//                            countChart3[10]+=Tinhtien(donHangs.get(i).getIdDH());
//                            break;
//                        case "12":
//                            countChart3[11]+=Tinhtien(donHangs.get(i).getIdDH());
//                            break;
//                    }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public int Tinhtien(int idDH){
//        int d = 0;
//        try {
//            List<CTDH> ctdhs = ctdhCtrl.getCTDHlistwithID(idDH);
//            for(int i = 0; i < ctdhs.size(); i++){
//                d+= ctdhs.get(i).tinhtien();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return d;
//    }
//
//    private ArrayList<IBarDataSet> getDataSet3() {
//        ArrayList<IBarDataSet> dataSets = null;
//        ArrayList<BarEntry> entries1 = new ArrayList();
//        entries1.add(new BarEntry(0, countChart3[11]));
//        ArrayList<BarEntry> entries2 = new ArrayList();
//        entries2.add(new BarEntry(1, countChart3[10]));
//        ArrayList<BarEntry> entries3 = new ArrayList();
//        entries3.add(new BarEntry(2, countChart3[9]));
//        ArrayList<BarEntry> entries4 = new ArrayList();
//        entries4.add(new BarEntry(3, countChart3[8]));
//        ArrayList<BarEntry> entries5 = new ArrayList();
//        entries5.add(new BarEntry(4, countChart3[7]));
//        ArrayList<BarEntry> entries6 = new ArrayList();
//        entries6.add(new BarEntry(5, countChart3[6]));
//        ArrayList<BarEntry> entries7 = new ArrayList();
//        entries7.add(new BarEntry(6, countChart3[5]));
//        ArrayList<BarEntry> entries8 = new ArrayList();
//        entries8.add(new BarEntry(7, countChart3[4]));
//        ArrayList<BarEntry> entries9 = new ArrayList();
//        entries9.add(new BarEntry(8, countChart3[3]));
//        ArrayList<BarEntry> entries10 = new ArrayList();
//        entries10.add(new BarEntry(9, countChart3[2]));
//        ArrayList<BarEntry> entries11 = new ArrayList();
//        entries11.add(new BarEntry(10, countChart3[1]));
//        ArrayList<BarEntry> entries12 = new ArrayList();
//        entries12.add(new BarEntry(11, countChart3[0]));
//
//        dataSets = new ArrayList<>();
//
//        BarDataSet dataset1 = new BarDataSet(entries1, null);
//        dataset1.setColor(Color.parseColor("#0E9D90"));
//        dataSets.add(dataset1);
//        BarDataSet dataset2 = new BarDataSet(entries2, null);
//        dataset2.setColor(Color.parseColor("#0e9d58"));
//        dataSets.add(dataset2);
//        BarDataSet dataset3 = new BarDataSet(entries3, null);
//        dataset3.setColor(Color.parseColor("#bfd047"));
//        dataSets.add(dataset3);
//        BarDataSet dataset4 = new BarDataSet(entries4, null);
//        dataset4.setColor(Color.parseColor("#ffc105"));
//        dataSets.add(dataset4);
//        BarDataSet dataset5 = new BarDataSet(entries5, null);
//        dataset5.setColor(Color.parseColor("#ef7e14"));
//        dataSets.add(dataset5);
//        BarDataSet dataset6 = new BarDataSet(entries6, null);
//        dataset6.setColor(Color.parseColor("#d36259"));
//        dataSets.add(dataset6);
//        BarDataSet dataset7 = new BarDataSet(entries7, null);
//        dataset7.setColor(Color.parseColor("#058EB6"));
//        dataSets.add(dataset7);
//        BarDataSet dataset8 = new BarDataSet(entries8, null);
//        dataset8.setColor(Color.parseColor("#0558B6"));
//        dataSets.add(dataset8);
//        BarDataSet dataset9 = new BarDataSet(entries9, null);
//        dataset9.setColor(Color.parseColor("#0512B6"));
//        dataSets.add(dataset9);
//        BarDataSet dataset10 = new BarDataSet(entries10, null);
//        dataset10.setColor(Color.parseColor("#66159E"));
//        dataSets.add(dataset10);
//        BarDataSet dataset11 = new BarDataSet(entries11, null);
//        dataset11.setColor(Color.parseColor("#B11CCC"));
//        dataSets.add(dataset11);
//        BarDataSet dataset12 = new BarDataSet(entries12, null);
//        dataset12.setColor(Color.parseColor("#CC1CAF"));
//        dataSets.add(dataset12);
//
//        return dataSets;
//    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.extra_common_menu, menu);
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
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
