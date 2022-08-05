package com.example.freshmarket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {
    EditText txtuser,txtpassword;
    CheckBox chkremeber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtpassword=findViewById(R.id.txtpass);
        txtuser=findViewById(R.id.txtuser);
        chkremeber=findViewById(R.id.chkremeber);

        SharedPreferences sh=getSharedPreferences("Fresh",MODE_PRIVATE);
        String name=sh.getString("name",null);
        if (name!=null)
            startActivity(new Intent(LoginActivity.this,MainUserActivity.class));

    }

    public void goToRegister(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }

    public void Login(View view) {


        if(txtuser.getText().toString().isEmpty())
        {
            txtuser.setError("Enter Email or Phone");
            txtuser.requestFocus();
        }
        else {
            if(txtpassword.getText().toString().isEmpty())
            {
                txtpassword.setError("Enter Password");
                txtpassword.requestFocus();
            }
            else
            {
                Database db=new Database();
                Connection conn=db.ConnectDB();
                if(conn==null)
                {
                    Toast.makeText(LoginActivity.this,"Check Internet Connection",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    ResultSet rs=db.RunSearch("select * from users where (phone ='"+txtuser.getText()+"'or email='"+txtuser.getText()+"') and password = '"+txtpassword.getText()+"'");
                    try
                    {
                        if(rs.next())
                        {
                            if(chkremeber.isChecked())
                            {
                                getSharedPreferences("Fresh",MODE_PRIVATE)
                                .edit().putString("id",rs.getString(1)).putString("name",rs.getString(2))
                                .commit();
                            }
                            startActivity(new Intent(LoginActivity.this,MainUserActivity.class ));

                        }
                        else
                        {
                            AlertDialog.Builder AD=new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("Login....")
                                    .setMessage("Check email or password")
                                    .setIcon(R.drawable.logo)
                                    .setPositiveButton("Try again",null)
                                    .setNegativeButton("Register", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                                        }
                                    });
                            AD.create().show();
                        }
                    }catch (SQLException throwables){
                        throwables.printStackTrace();
                    }
                }
            }
        }


    }
}