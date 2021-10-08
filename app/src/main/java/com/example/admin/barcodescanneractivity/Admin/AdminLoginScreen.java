package com.example.admin.barcodescanneractivity.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.admin.barcodescanneractivity.Loginactivity;
import com.example.admin.barcodescanneractivity.R;
import com.example.admin.barcodescanneractivity.vehicleinformation;

public class AdminLoginScreen extends AppCompatActivity {
    EditText EMAIL,PASSWORD;
    Button Login;
    Spinner plant_selection;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin);
        init();
        spinner_plants();
        Login.setOnClickListener(new btnloginonclicklisteneradmin());
    }

    void init(){

        EMAIL = findViewById(R.id.login_email_admin);
        PASSWORD = findViewById(R.id.login_password_admin);
        Login = findViewById(R.id.login_button_admin);
        plant_selection = findViewById(R.id.spinner_plant);

    }

    class btnloginonclicklisteneradmin implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (!EMAIL.getText().toString().matches("nilesh.jaiswal@windalsprecision.com")){
                EMAIL.setError("Invalid email");
                EMAIL.setFocusable(true);
            }else if (PASSWORD.getText().toString().length() < 6 ){
                PASSWORD.setError("enter correct password");
                PASSWORD.setFocusable(true);
            }
             //   admin_login_auth();
            else {
                admin_login_auth();
            }



        }
    }

    void spinner_plants(){
        String[] parts = {"--Select plants--","Chakan","Bhpoal"};
        ArrayAdapter adapter_plants = new ArrayAdapter(this,android.R.layout.simple_spinner_item,parts);
        adapter_plants.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plant_selection.setAdapter(adapter_plants);
    }

    void admin_login_auth(){
        Intent intent = new Intent(AdminLoginScreen.this, Adminbottomnavigation.class);
        startActivity(intent);
    }
}
