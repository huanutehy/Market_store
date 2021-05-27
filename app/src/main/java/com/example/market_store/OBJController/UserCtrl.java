package com.example.market_store.OBJController;

import com.example.market_store.JDBC.JDBCController;
import com.example.market_store.Object.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserCtrl {

    private JDBCController jdbcController = new JDBCController();
    private Connection connection;
    public UserCtrl() {
        connection = jdbcController.ConnnectionData();
    }

    public List<User> getUserlist() throws SQLException {
        List<User> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from Account";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new User(rs.getString("account"), rs.getString("password"),  rs.getString("hoten"), rs.getString("ngaysinh"), rs.getString("email"), rs.getString("diachi"), rs.getLong("CMT"), rs.getInt("SDT"), rs.getString("ngaytao"), rs.getInt("loaitk")));
        }
        rs.close();
        return list;
    }

    public User getUser(String account) throws SQLException{
        Statement statement = connection.createStatement();
        String sql = "select * from Account where account = '" + account + "'";
        ResultSet rs = statement.executeQuery(sql);
        User user = null;
        while (rs.next()) {
            user = new User(rs.getString("account"), rs.getString("password"),  rs.getString("hoten"), rs.getString("ngaysinh"), rs.getString("email"), rs.getString("diachi"), rs.getLong("CMT"), rs.getInt("SDT"), rs.getString("ngaytao"), rs.getInt("loaitk"));
        }
        rs.close();
        return user;
    }
    public boolean Check(String account, String password) throws SQLException{
        Statement statement = connection.createStatement();
        String sql = "select * from Account where account = '" + account +"' and password = '"+ password + "'";
        ResultSet rs = statement.executeQuery(sql);
        if(rs.isBeforeFirst())
        {
            rs.close();
            return true;
        }
        rs.close();
        return false;
    }
    public boolean Insert(User objUser) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into Account values("+objUser.toString()+")";
        if (statement.executeUpdate(sql) > 0) {
            statement.close();
            return true;
        } else {
            statement.close();
            return false;
        }
    }

    public boolean Update(User objUser) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "Update Account set password = '"+objUser.getPassword()+"', hoten = N'"+objUser.getHoten()+"', ngaysinh = '"+objUser.getNgaysinh()+"', email = '"+objUser.getEmail()+"', diachi = N'"+objUser.getDiachi()+"', cmt = "+objUser.getCMT()+", sdt = "+objUser.getSDT()+", ngaytao = '"+objUser.getNgaytao()+"', loaitk = "+objUser.getLoaitk()+" where account ='"+objUser.getAccount()+"'";
        if (statement.executeUpdate(sql) > 0) {
            statement.close();
            return true;
        } else
            statement.close();
        return false;
    }

    public boolean Delete(User objUser) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "delete from Account where ID = ";
        if (statement.executeUpdate(sql) > 0){
            statement.close();
            return true;
        }

        else
            statement.close();
        return false;
    }
}
