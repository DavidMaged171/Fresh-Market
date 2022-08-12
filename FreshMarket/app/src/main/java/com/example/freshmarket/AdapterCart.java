package com.example.freshmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterCart extends ArrayAdapter<Cart> {

    Context c;
    ArrayList<Cart> products;

    public AdapterCart(Context context, ArrayList<Cart> cont) {
        super(context, R.layout.cartdetails,cont);
        c=context;
        products=cont;
    }

    class Holder
    {
        ImageView imgCart,imgDelete;
        TextView txtname,txtPrice,txttotal,txtqty;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        final Cart data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        final Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cartdetails, parent, false);

            viewHolder.txtname = (TextView) convertView.findViewById(R.id.txtnamecart);
            viewHolder.txtPrice= (TextView) convertView.findViewById(R.id.txtpricecart);
            viewHolder.txttotal= (TextView) convertView.findViewById(R.id.txtcarttotal);
            viewHolder.txtqty= (TextView) convertView.findViewById(R.id.txtcartqty);

            viewHolder.imgCart = (ImageView) convertView.findViewById(R.id.imgdcart);
            viewHolder.imgDelete = (ImageView) convertView.findViewById(R.id.imgdelete);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }
        PicassoClient.downloadImage(c,data.getImg(),viewHolder.imgCart);
        viewHolder.txtname.setText(data.getItemName());
        viewHolder.txtPrice.setText(data.getPrice()+" LE");
        viewHolder.txtqty.setText(data.getQty());
        viewHolder.txttotal.setText(data.getTotal());
        viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return convertView;
    }


}
