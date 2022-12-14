package com.example.freshmarket.ui.home;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.freshmarket.AdapterSection;
import com.example.freshmarket.GetSection;
import com.example.freshmarket.R;
import com.example.freshmarket.Section;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static String sectionNo;
    GridView gv;
    GetSection getSection=new GetSection();
    AdapterSection adapterSection;
    Section model;
    ArrayList<Section>data;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel =
          //      ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        gv=root.findViewById(R.id.gvdepart);
        data=new ArrayList<>(getSection.GetData());
        adapterSection=new AdapterSection(getActivity(),data);
        gv.setAdapter(adapterSection);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                data.get(i);
                sectionNo=model.getSecNo();
                // Go tp fragment;
            }
        });

        SwipeRefreshLayout swp=root.findViewById(R.id.swp);
        swp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data=new ArrayList<>(getSection.GetData());
                adapterSection=new AdapterSection(getActivity(),data);
                gv.setAdapter(adapterSection);
                swp.setRefreshing(false);
            }
        });

        return root;
    }
    public void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }
}