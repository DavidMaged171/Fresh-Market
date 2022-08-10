package com.example.freshmarket;

import androidx.fragment.app.FragmentActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(30.1434877, 31.3336087);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        Database db=new Database();
        db.ConnectDB();
        ResultSet rs= db.RunSearch("Select * from branch");
        try {
            while (rs.next()){
                LatLng bransh = new LatLng(rs.getFloat(3), rs.getFloat(4));
                mMap.addMarker(new MarkerOptions().position(bransh).title(rs.getString(2)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bransh,10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Database db=new Database();
                db.ConnectDB();
                ResultSet rs= db.RunSearch("Select * from branch where branchid = '"+marker.getTitle()+"'");
                try {
                    if (rs.next()){
                        AlertDialog.Builder builder=new AlertDialog.Builder(MapsActivity.this);
                        builder.setTitle("Branch details.....");
                        builder.setMessage("Name: "+rs.getString(2)+"\n"+"Phone: "+rs.getString(5) );
                        builder.setPositiveButton("Show Products", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.setNegativeButton("Thanks",null);
                        builder.create().show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                return false;
            }
        });
       /* mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                LatLng bransh = new LatLng(latLng.latitude, latLng.longitude);
                mMap.addMarker(new MarkerOptions().position(bransh).title("Set Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bransh,10));
            }
        });*/
    }
}