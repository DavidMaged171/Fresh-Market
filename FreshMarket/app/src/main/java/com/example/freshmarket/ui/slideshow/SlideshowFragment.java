package com.example.freshmarket.ui.slideshow;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.freshmarket.AdapterCart;
import com.example.freshmarket.AdapterProduct;
import com.example.freshmarket.Cart;
import com.example.freshmarket.DBLite;
import com.example.freshmarket.Database;
import com.example.freshmarket.GetProduct;
import com.example.freshmarket.LoginActivity;
import com.example.freshmarket.Product;
import com.example.freshmarket.R;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class SlideshowFragment extends Fragment {
    TextView txtFinal;
    ListView lst;
    Cart cart=new Cart();
    DBLite dbLite=new DBLite(getActivity());
    AdapterCart adapterCart;
    ArrayList<Cart>data;




    Button btnConfirm;

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        txtFinal=root.findViewById(R.id.txtfinal);
        txtFinal.setText("Total: "+dbLite.getTotal());

        btnConfirm=root.findViewById(R.id.btnconfirm);


        lst.findViewById(R.id.lstcart);
        data=new ArrayList<>(dbLite.GetCart());
        adapterCart=new AdapterCart(getActivity(),data);
        lst.setAdapter(adapterCart);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cart model;
                Database db =new Database();
                db.ConnectDB();
                String msOrder=db.RunDML("insert into orders('"+Calendar.getInstance().getTime().toString()+"','"+dbLite.getTotal()+",'Pending','"+LoginActivity.id+"','','','')");
                if(msOrder.equals("ok"))
                {
                    ResultSet rmax=db.RunSearch("select max(orderno) from orders where userid='"+LoginActivity.id+"'");
                    try {
                        if(rmax.next())
                        {
                            for (int i=0;i<data.size();i++)
                            {
                                model=data.get(i);
                                db.RunDML("insert into sales values ('"+rmax.getString(1)+"','"+model.getItemNo()+"','"+model.getQty()+"','"+model.getPrice()+"','"+model.getTotal()+"')");

                            }
                            dbLite.DeleteCart();
                            //Go to home fragment
                            Toast.makeText(getActivity(),"Your Order Has been Sent",Toast.LENGTH_SHORT).show();

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        return root;
    }
}