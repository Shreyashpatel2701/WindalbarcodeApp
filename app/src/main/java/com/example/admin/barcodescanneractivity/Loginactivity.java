package com.example.admin.barcodescanneractivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Loginactivity extends AppCompatActivity {
    Button Login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.login_activity);
         init();
         Login.setOnClickListener(new btnloginonclicklistener());
    }

    void init(){
        Login = findViewById(R.id.login_button);
    }

    class btnloginonclicklistener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Loginactivity.this,vehicleinformation.class);
            startActivity(intent);
        }
    }

}
