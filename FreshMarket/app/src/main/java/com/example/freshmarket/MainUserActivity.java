package com.example.freshmarket;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freshmarket.ui.gallery.GalleryFragment;
import com.example.freshmarket.ui.home.HomeFragment;
import com.example.freshmarket.ui.slideshow.SlideshowFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainUserActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headerView=navigationView.getHeaderView(0);
        ImageView imgUser=headerView.findViewById(R.id.imguser);
        TextView txtUser=headerView.findViewById(R.id.txtuser);
        txtUser.setText(LoginActivity.name);
        PicassoClient.downloadImage(this,LoginActivity.image,imgUser);


        Menu menu=navigationView.getMenu();
        MenuItem cart=menu.findItem(R.id.nav_cart);
        DBLite dbLite=new DBLite(MainUserActivity.this);
        int count =dbLite.getCount();
        if(count==0)
        {
            cart.setVisible(false);
        }
        else
        {
            cart.setVisible(true);
            cart.setTitle("Shopping Cart ( "+count+" )");
        }
        //loadFragment(new HomeFragment());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if(id==R.id.nav_home)
                {
                    GalleryFragment f1=new GalleryFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.frame,f1).commit();
                    //loadFragment(new HomeFragment());
                }
                else if(id==R.id.nav_gallery)
                {
                    SlideshowFragment f1=new SlideshowFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.frame,f1).commit();
                    //loadFragment(new GalleryFragment());
                }
                else if(id==R.id.nav_slideshow)
                {
                    HomeFragment f1=new HomeFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.frame,f1).commit();
                }
                    //loadFragment(new SlideshowFragment());
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_user, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            // do something on back.
            finishAffinity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.action_logout)
        {
            getSharedPreferences("Fresh",MODE_PRIVATE).edit().clear().commit();
            startActivity(new Intent(MainUserActivity.this,LoginActivity.class));
        }
        else if(id==R.id.action_delete)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(MainUserActivity.this)
                    .setTitle("Delete Account...")
                    .setMessage("Are you sure delete your account?")
                    .setPositiveButton("No",null)
                    .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Database db=new Database();
                            db.ConnectDB();
                            db.RunDML("delete from users where UserID = '"+LoginActivity.id+"'");
                            getSharedPreferences("Fresh",MODE_PRIVATE).edit().clear().commit();
                            startActivity(new Intent(MainUserActivity.this,LoginActivity.class));
                        }
                    });
            builder.create().show();

        }
        else if(id==R.id.action_profile)
        {
            startActivity(new Intent(MainUserActivity.this,MapsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }


}