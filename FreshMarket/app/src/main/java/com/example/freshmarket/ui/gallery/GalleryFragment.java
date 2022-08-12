package com.example.freshmarket.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.freshmarket.AdapterProduct;
import com.example.freshmarket.DBLite;
import com.example.freshmarket.GetProduct;
import com.example.freshmarket.PicassoClient;
import com.example.freshmarket.Product;
import com.example.freshmarket.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class  GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    ListView lst;
    GetProduct getProduct;
    Product product;
    AdapterProduct adapterProduct;
    ArrayList<Product>data;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        lst=root.findViewById(R.id.lstproduct);

        data=new ArrayList<>(getProduct.GetData());
        adapterProduct=new AdapterProduct(getActivity(),data);
        lst.setAdapter(adapterProduct);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                product=data.get(i);
                LayoutInflater lf=LayoutInflater.from(getActivity());
                View v=lf.inflate(R.layout.productdetails,null);
                TextView txtpro=v.findViewById(R.id.txtnamedet),
                        txtprice=v.findViewById(R.id.txtpricedet),
                        txtdetails=v.findViewById(R.id.txtdetails);
                EditText txtQty=v.findViewById(R.id.txtqty);
                ImageView img=v.findViewById(R.id.imgprodet);
                Button button=v.findViewById(R.id.btncart);

                txtpro.setText(product.getProName());
                txtprice.setText(product.getProPrice()+" LE");
                txtdetails.setText(product.getProDetails());
                PicassoClient.downloadImage(getActivity(),product.getProImage(),img);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // ADD TO CART
                        float total=Float.parseFloat(txtQty.getText().toString())*(product.getProPrice()-product.getProDiscount());
                        DBLite dbLite=new DBLite(getActivity());
                        dbLite.AddToCart(product.getProNo(),product.getProName(),String.valueOf(product.getProPrice()),
                                txtQty.getText().toString(),String.valueOf(total),product.getProImage());

                        Toast.makeText(getActivity(),"Added To Cart",Toast.LENGTH_SHORT).show();
                    }
                });


                BottomSheetDialog sh=new BottomSheetDialog(getActivity());
                sh.setContentView(v);
                sh.show();
            }
        });


        return root;
    }
}