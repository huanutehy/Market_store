package com.example.market_store.JDBC;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCModel {
    public Connection getConnectionOf() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection objConn = null;
        String sConnURL = null;
        JDBCObject objEntity = new JDBCObject("192.168.43.203", "vdbinh", "1", "newMobileStore", "1433");
        try {
            Class.forName(objEntity.getsClass());
            sConnURL = "jdbc:jtds:sqlserver://"
                    + objEntity.getsServerName() + ":" + objEntity.getsPort() + ";"
                    + "databaseName=" + objEntity.getsDatabase()
                    + ";user=" + objEntity.getsUserId()
                    + ";password=" + objEntity.getsPwd() + ";";
            objConn = DriverManager.getConnection(sConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return objConn;
    }
}
