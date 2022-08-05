package com.example.freshmarket;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    Connection conn=null;

    //to connect with db
    public Connection ConnectDB()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:jtds:sqlserver://sql8004.site4now.net/db_a8b358_viadb","db_a8b358_viadb_admin","ABC@123456");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return conn;

    }


    //to add or delete or update
    public String RunDML(String st)
    {
        try {
            Statement statement=conn.createStatement();
            statement.executeUpdate(st);
            return "Ok";

        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }


    //to search
    public ResultSet RunSearch(String st)
    {
        try {
            Statement statement=conn.createStatement();
            ResultSet rs=statement.executeQuery(st);
            return  rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
