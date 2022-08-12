package com.example.freshmarket;

import com.example.freshmarket.ui.home.HomeFragment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetProduct {
    public ArrayList<Product> GetData()
    {
        Database db=new Database();
        db.ConnectDB();
        ArrayList<Product>data=new ArrayList<>();
        ResultSet rs=db.RunSearch("select * from products where sectionno = '"+ HomeFragment.sectionNo +"'");
        while (true){
            try {
                while (rs.next())
                {
                    Product product=new Product();
                    product.setProNo(rs.getString(1));
                    product.setProName(rs.getString(2));
                    product.setProDetails(rs.getString(3));
                    product.setProPrice(rs.getFloat(4));
                    product.setProDiscount(rs.getFloat(5));
                    product.setProImage(rs.getString(6));
                    product.setSectionNo(rs.getString(7));
                    data.add(product);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return data;
        }
    }

}
