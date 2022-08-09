package com.example.freshmarket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class LoginActivity extends AppCompatActivity {
    public static String id=null,name,image;
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
        id=sh.getString("name",null);
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
                            name=rs.getString(2);
                            id=rs.getString(1);
                            image=rs.getString(7);
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

    public void forgetPassword(View view) {
        LayoutInflater inflater=LayoutInflater.from(LoginActivity.this);
        View v=inflater.inflate(R.layout.forgetpassword,null);
        AlertDialog.Builder ad=new AlertDialog.Builder(LoginActivity.this);


        EditText txtfEmail=(EditText) v.findViewById(R.id.txtfmail );
        Toast.makeText(LoginActivity.this,"Forget password clicked",Toast.LENGTH_SHORT).show();

        ad.setView(v);
        ad.setPositiveButton("Send Verification Code", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Database db=new Database();
                db.ConnectDB();
                ResultSet rs=db.RunSearch("select * from users where email = '"+ txtfEmail.getText()+"'");
                try {
                    if(rs.next())
                    {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    final String username = "freshmarket171@gmail.com";
                                    final String password = "ABC@123456";
                                    Properties props = new Properties();
                                    props.put("mail.smtp.auth", "true");
                                    props.put("mail.smtp.starttls.enable", "true");
                                    props.put("mail.smtp.host", "smtp.gmail.com");
                                    props.put("mail.smtp.port", "587");

                                    Session session = Session.getInstance(props,
                                            new javax.mail.Authenticator() {

                                                protected PasswordAuthentication getPasswordAuthentication() {
                                                    return new PasswordAuthentication(username, password);
                                                }
                                            });

                                    try {
                                        Message message = new MimeMessage(session);
                                        message.setFrom(new InternetAddress("yourmobileapp2017@gmail.com"));
                                        message.setRecipients(Message.RecipientType.TO,
                                                InternetAddress.parse(txtfEmail.getText().toString()));

                                        message.setSubject("Forget password code Fresh Market App");

                                        Random rand=new Random();
                                        int newpass=rand.nextInt(99999-11111+1)+11111;

                                        message.setText("Dear : " + rs.getString(2).toString() + "\n" + "Verification Code: " + newpass + "\n" + "Thanks :) ");
                                        Transport.send(message);


                                    } catch (MessagingException e) {
                                        Toast.makeText(getApplication(), e.getMessage() + "", Toast.LENGTH_SHORT).show();
                                        throw new RuntimeException(e);
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }).start();

                        Toast.makeText(LoginActivity.this, "Verification code has been sent", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(LoginActivity.this,"This email not exists",Toast.LENGTH_SHORT).show();
                    }
                }catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }).setNegativeButton("Thanks",null);
        ad.create().show();
    }
}