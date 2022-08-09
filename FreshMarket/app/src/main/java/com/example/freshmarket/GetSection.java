package com.example.freshmarket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetSection {
    public ArrayList<Section>GetData()
    {
        Database db=new Database();
        db.ConnectDB();
        ArrayList<Section>data=new ArrayList<>();
        ResultSet rs=db.RunSearch("select * from section");
        while (true){
            try {
                while (rs.next())
                {
                    Section section=new Section();
                    section.setSecNo(rs.getString(1));
                    section.setSecName(rs.getString(2));
                    section.setSecImage(rs.getString(3));
                    data.add(section);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return data;
        }
    }
}
