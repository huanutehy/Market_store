package com.example.market_store.OBJController;

import com.example.market_store.JDBC.JDBCController;
import com.example.market_store.Object.CTDH;
import com.example.market_store.Object.DonHang;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CTDHCtrl {
    private JDBCController jdbcController = new JDBCController();
    private Connection connection;

    public CTDHCtrl() {
        connection = jdbcController.ConnnectionData();
    }

    public List<CTDH> getCTDHlistwithID(int idDH) throws SQLException {
        List<CTDH> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from ChiTietDonHang where idDH = " + idDH;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new CTDH(rs.getInt("idDH"), rs.getInt("idProduct"), rs.getInt("soluong"), rs.getInt("gia")));
        }
        rs.close();
        return list;
    }

    public List<CTDH> getCTDHlistwithProduct(int idProduct) throws SQLException {
        List<CTDH> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from ChiTietDonHang where idProduct = " + idProduct;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new CTDH(rs.getInt("idDH"), rs.getInt("idProduct"), rs.getInt("soluong"), rs.getInt("gia")));
        }
        rs.close();
        return list;
    }

    public boolean Insert(CTDH objCtdh) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into ChiTietDonHang values(" + objCtdh.toString() + ")";
        if (statement.executeUpdate(sql) > 0) {
            statement.close();
            return true;
        } else {
            statement.close();
            return false;
        }
    }
}
