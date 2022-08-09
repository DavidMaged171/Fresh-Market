package com.example.freshmarket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;

public class RegisterActivity extends AppCompatActivity {

    EditText txtname,txtphone,txtaddress,txtpassword,txtemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtname=findViewById(R.id.txtname);
        txtaddress=findViewById(R.id.txtaddress);
        txtemail=findViewById(R.id.txtemail);
        txtpassword=findViewById(R.id.txtpassword);
        txtphone=findViewById(R.id.txtphone);
    }

    public void register(View view) {
        if(txtphone.getText().toString().isEmpty())
        {
            txtphone.setError("Enter your phone");
            txtphone.requestFocus();
        }

        if(txtpassword.getText().toString().isEmpty())
        {
            txtpassword.setError("Enter your password");
            txtpassword.requestFocus();
        }
        if(txtname.getText().toString().isEmpty())
        {
            txtname.setError("Enter your name");
            txtname.requestFocus();
        }
        String reg="^\\w+@[a-zA-z_]+?\\.[a-zA-Z]{2,3}$";
        if(txtemail.getText().toString().matches(reg))
        {
            Database db=new Database();
            Connection conn=db.ConnectDB();
            if(conn==null)
            {
                Toast.makeText(this,"NO Internet Connection",Toast.LENGTH_SHORT).show();
            }
            else{
                String msg=db.RunDML("insert into [users] values(N'"+txtname.getText()+"','"+txtphone.getText()+"','"+txtemail.getText()+"','"+txtaddress.getText()+"','"+txtpassword.getText()+"','')");
                if(msg.equals("Ok"))
                {
                    AlertDialog.Builder AD=new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("Register....")
                            .setMessage("User Created Sucessfully")
                            .setIcon(R.drawable.logo)
                            .setPositiveButton("Thanks",null)
                            .setNegativeButton("Login", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                }
                            });
                    AD.create().show();
                }
                else if(msg.contains("UQPhone"))
                {
                    txtphone.setError("This Phone is already exists.");
                    txtphone.requestFocus();
                }
                else if(msg.contains("UQEmail"))
                {
                    txtemail.setError("This Email is already exists.");
                    txtemail.requestFocus();
                }
                else
                {
                    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
                }
            }
        }
        else{
            Toast.makeText(this,"Invalid Email address",Toast.LENGTH_SHORT).show();

        }
    }
}