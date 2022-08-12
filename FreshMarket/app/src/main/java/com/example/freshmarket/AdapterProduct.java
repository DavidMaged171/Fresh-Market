package com.example.freshmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterProduct extends ArrayAdapter<Product> {

    Context c;
    ArrayList<Product> products;

    public AdapterProduct(Context context, ArrayList<Product> cont) {
        super(context, R.layout.productlayout,cont);
        c=context;
        products=cont;
    }

    class Holder
    {
        ImageView imgPro;
        TextView txtname,txtPrice;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        final Product data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        final Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.productlayout, parent, false);

            viewHolder.txtname = (TextView) convertView.findViewById(R.id.txtproductname);
            viewHolder.txtPrice= (TextView) convertView.findViewById(R.id.txtproductprice);

           viewHolder.imgPro = (ImageView) convertView.findViewById(R.id.imgproduct);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }
        PicassoClient.downloadImage(c,data.getProImage(),viewHolder.imgPro);
        viewHolder.txtname.setText(data.getProName());
        viewHolder.txtPrice.setText(data.getProPrice()+" LE");


        return convertView;
    }


}
