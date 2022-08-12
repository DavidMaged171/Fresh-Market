package com.example.freshmarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBLite extends SQLiteOpenHelper {

    public DBLite(@Nullable Context context) {
        super(context, "FreshMarketDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("create table orders (orderno int primary key,prono text, proname text,price text, qty text,total text,image text)");
    }
    public void AddToCart(String no,String name,String price,String qty,String total,String image)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put("prono",no);
        data.put("proname",name);
        data.put("price",price);
        data.put("qty",qty);
        data.put("total",total);
        data.put("image",image);

        db.insert("orders",null,data);
    }
    public float getTotal()
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery("select sum(total) from orders",null);
        if(cursor.moveToFirst())
            return cursor.getFloat(0);
        else
        {
            return 0;
        }
    }
    public int getCount()
    {
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery("select count(*) from orders",null);
        if(cursor.moveToFirst())
            return cursor.getInt(0);
        else
        {
            return 0;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public List<Cart> GetCart()
    {
        ArrayList<Cart>data=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor ccart=db.rawQuery("select * from orders",null);
        while (ccart.moveToFirst())
        {
            Cart cart=new Cart();
            cart.setItemNo(ccart.getString(0));
            cart.setItemName(ccart.getString(1));
            cart.setPrice(ccart.getString(2));
            cart.setQty(ccart.getString(3));
            cart.setTotal(ccart.getString(4));
            cart.setImg(ccart.getString(5));
            data.add(cart);
        }
        return data;
    }
    public void DeleteCart()
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from orders ");
    }
}
