package com.example.freshmarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freshmarket.Section;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterSection extends ArrayAdapter<Section> {

    Context c;
    ArrayList<Section> ass;

    public AdapterSection(Context context, ArrayList<Section> cont) {
        super(context, R.layout.sectionlayout,cont);
        c=context;
        ass=cont;
    }

    class Holder
    {
        ImageView imgcategory;
        TextView txtname;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        final Section data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        final Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.sectionlayout, parent, false);

            viewHolder.txtname = (TextView) convertView.findViewById(R.id.txtsection);

           viewHolder.imgcategory = (ImageView) convertView.findViewById(R.id.imgsection);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }
        PicassoClient.downloadImage(c,data.getSecImage(),viewHolder.imgcategory);
        viewHolder.txtname.setText(data.getSecName());


        return convertView;
    }


}
