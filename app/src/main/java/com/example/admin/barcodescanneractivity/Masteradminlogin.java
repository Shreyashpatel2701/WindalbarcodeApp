package com.example.admin.barcodescanneractivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.barcodescanneractivity.Admin.Addplants;

import java.util.Locale;

public class Masteradminlogin extends AppCompatActivity {
    EditText masteradminpassword,masteramdinemail;
    Button Admin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.masteradminlogin);
        init();
        if (!masteramdinemail.getText().toString().toLowerCase(Locale.ROOT).matches("nilesh.jaiswal@windalsprecision.com")){
             masteramdinemail.setError("wrong email");
             masteramdinemail.setFocusable(true);
        }else if (!masteradminpassword.getText().toString().toLowerCase(Locale.ROOT).matches("987654321")){
            masteradminpassword.setError("wrong password");
            masteradminpassword.setFocusable(true);
        }else {
              Intent intent = new Intent(this,Addplants.class);
              startActivity(intent);
        }
    }

    void init(){
        masteradminpassword =findViewById(R.id.masteradminlogin_password);
        masteramdinemail = findViewById(R.id.masteradminlogin_email);
        Admin = findViewById(R.id.masterlogin_button);
    }

 private class btnLoginclicklistener implements View.OnClickListener{
     @Override
     public void onClick(View view) {
         Intent intent  = new Intent(Masteradminlogin.this, Addplants.class);
         startActivity(intent);
     }
 }
}
