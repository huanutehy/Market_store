package com.example.market_store.OBJController;

import android.widget.Toast;

import com.example.market_store.JDBC.JDBCController;
import com.example.market_store.Object.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductCtrl {

    private JDBCController jdbcController = new JDBCController();
    private Connection connection;
    public static List<Product> productList = new ArrayList<>();
    public ProductCtrl() {
        connection = jdbcController.ConnnectionData();
        try {
            productList = getProductlist();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Product> getProductlist() throws SQLException {
        List<Product> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from Product";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Product(rs.getInt("idProduct"), rs.getString("tensp"),  rs.getString("hinhanh"), rs.getString("nsx"), rs.getString("chitiet"), rs.getString("khuyenmai"), rs.getInt("baohanh"), rs.getInt("gia"), rs.getString("review"), rs.getString("ngaynhap")));
        }
        rs.close();
        return list;
    }

    public Product getProduct(int idProduct) throws SQLException {
        Product product = null;
        Statement statement = connection.createStatement();
        String sql = "select * from Product where idProduct = "+ idProduct;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            product = new Product(rs.getInt("idProduct"), rs.getString("tensp"),  rs.getString("hinhanh"), rs.getString("nsx"), rs.getString("chitiet"), rs.getString("khuyenmai"), rs.getInt("baohanh"), rs.getInt("gia"), rs.getString("review"), rs.getString("ngaynhap"));
        }
        rs.close();
        return product;
    }
}
