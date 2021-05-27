package com.example.market_store.OBJController;

import com.example.market_store.JDBC.JDBCController;
import com.example.market_store.Object.DonHang;
import com.example.market_store.Object.Product;
import com.example.market_store.Object.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DonHangCtrl {
    private JDBCController jdbcController = new JDBCController();
    private Connection connection;
    public static List<DonHang> donHangList = new ArrayList<>();

    public DonHangCtrl() {
        connection = jdbcController.ConnnectionData();
        try {
            donHangList = getDonHanglist();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DonHang> getDonHanglist() throws SQLException {
        List<DonHang> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from DonHang";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new DonHang(rs.getInt("idDH"), rs.getString("account"), rs.getString("hoten"), rs.getInt("sdt"), rs.getString("diachi"), rs.getInt("status"), rs.getString("ngaymua")));
        }
        rs.close();
        return list;
    }

    public List<DonHang> getDonHanglistwithAccountandSTT(String account, int status) throws SQLException {
        List<DonHang> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from DonHang where account = '" + account + "' and status = " + status;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new DonHang(rs.getInt("idDH"), rs.getString("account"), rs.getString("hoten"), rs.getInt("sdt"), rs.getString("diachi"), rs.getInt("status"), rs.getString("ngaymua")));
        }
        rs.close();
        return list;
    }

    public List<DonHang> getDonHanglistwithSTT( int status) throws SQLException {
        List<DonHang> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from DonHang where status = " + status;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new DonHang(rs.getInt("idDH"), rs.getString("account"), rs.getString("hoten"), rs.getInt("sdt"), rs.getString("diachi"), rs.getInt("status"), rs.getString("ngaymua")));
        }
        rs.close();
        return list;
    }


    public DonHang getDonHangwithID(int idDH) throws SQLException {
        DonHang donHang = null;
        Statement statement = connection.createStatement();
        String sql = "select * from DonHang where idDH = " + idDH;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            donHang = new DonHang(rs.getInt("idDH"), rs.getString("account"), rs.getString("hoten"), rs.getInt("sdt"), rs.getString("diachi"), rs.getInt("status"), rs.getString("ngaymua"));
        }
        rs.close();
        return donHang;
    }

    public boolean Insert(DonHang objDonHang) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into DonHang values(" + objDonHang.toString() + ")";
        if (statement.executeUpdate(sql) > 0) {
            statement.close();
            return true;
        } else {
            statement.close();
            return false;
        }
    }

    public boolean UpdateStatus(int status, int idDH) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "Update DonHang set status = " + status + "where idDH = " + idDH;
        if (statement.executeUpdate(sql) > 0) {
            statement.close();
            return true;
        } else
            statement.close();
        return false;
    }

    public boolean UpdateDonHang(DonHang donHang) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "Update DonHang set hoten = N'" + donHang.getHoten() + ", sdt = " + donHang.getSdt() + ", diachi = N'" + donHang.getDiachi() + "where idDH = " + donHang.getIdDH();
        if (statement.executeUpdate(sql) > 0) {
            statement.close();
            return true;
        } else
            statement.close();
        return false;
    }

    public boolean Delete(int idDH) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "delete from DonHang where idDH = " + idDH;
        if (statement.executeUpdate(sql) > 0) {
            statement.close();
            return true;
        } else
            statement.close();
        return false;
    }
}
