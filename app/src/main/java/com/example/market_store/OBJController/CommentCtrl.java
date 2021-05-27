package com.example.market_store.OBJController;

import com.example.market_store.JDBC.JDBCController;
import com.example.market_store.Object.Comment;
import com.example.market_store.Object.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentCtrl {

    private JDBCController jdbcController = new JDBCController();
    private Connection connection;
    public CommentCtrl() {
        connection = jdbcController.ConnnectionData();
        try {
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Comment> getCommentlist(int id) throws SQLException {
        List<Comment> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from Comment where idProduct = "+id;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Comment(rs.getInt("idProduct"), rs.getString("account"), rs.getString("comment"), rs.getInt("rating"), rs.getString("time")));
        }
        rs.close();
        return list;
    }

    public boolean Insert(Comment objComment) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "insert into Comment values("+objComment.toString()+")";
        if (statement.executeUpdate(sql) > 0) {
            statement.close();
            return true;
        } else {
            statement.close();
            return false;
        }
    }
}
