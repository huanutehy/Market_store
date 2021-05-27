package com.example.market_store.OBJController;

import com.example.market_store.JDBC.JDBCController;
import com.example.market_store.Object.Product;
import com.example.market_store.Object.Specification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SpecificationCtrl {

    private JDBCController jdbcController = new JDBCController();
    private Connection connection;
    public static List<Specification> specificationList = new ArrayList<>();
    public SpecificationCtrl() {
        connection = jdbcController.ConnnectionData();
        try {
             specificationList = getSpecificationlist();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Specification> getSpecificationlist() throws SQLException {
        List<Specification> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select * from Specification";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Specification(rs.getInt("idProduct"), rs.getString("manhinh"),  rs.getString("hdh"), rs.getString("camera"), rs.getString("CPU"), rs.getInt("RAM"), rs.getInt("ROM"), rs.getString("thenho"), rs.getString("sim"), rs.getString("pin")));
        }
        rs.close();
        return list;
    }

}
