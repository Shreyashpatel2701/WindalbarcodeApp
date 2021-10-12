package com.example.admin.barcodescanneractivity.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.admin.barcodescanneractivity.Loginactivity;
import com.example.admin.barcodescanneractivity.R;
import com.example.admin.barcodescanneractivity.vehicleinformation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class AdminLoginScreen extends AppCompatActivity {
    EditText EMAIL,PASSWORD;
    Button Login;
    Spinner plant_selection;
    ProgressDialog progressDialog;
    SharedPreferences sh;
    SharedPreferences.Editor myEdit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin);
        init();
        spinner_plants();
        Login.setOnClickListener(new btnloginonclicklisteneradmin());
        sh = getSharedPreferences("LoginSharedPref", MODE_PRIVATE);
        myEdit = sh.edit();
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
            if (EMAIL.getText().toString().isEmpty()){
                EMAIL.setError("Invalid email");
                EMAIL.setFocusable(true);
            }else if (PASSWORD.getText().toString().length() < 6 ){
                PASSWORD.setError("enter correct password");
                PASSWORD.setFocusable(true);
            }else if (plant_selection.getSelectedItem().toString().matches("--Select plants--")){

                Toast.makeText(AdminLoginScreen.this,"PLEASE SELECT PALNT",Toast.LENGTH_LONG).show();
                plant_selection.setFocusable(true);
            }else {
                SharedPreferences sharedPreferences = getSharedPreferences("PLANTADMIN",MODE_PRIVATE);
                SharedPreferences.Editor ADMINDATA = sharedPreferences.edit();
                ADMINDATA.putString("plant",plant_selection.getSelectedItem().toString());
                ADMINDATA.commit();
                progressDialog = new ProgressDialog(AdminLoginScreen.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Logging In");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                Admin_Login_auth(EMAIL.getText().toString(),PASSWORD.getText().toString());
            }
            }



        }

        void spinner_plants(){
        String[] parts = {"--Select plants--","chakan","bhpoal"};
        ArrayAdapter adapter_plants = new ArrayAdapter(this,android.R.layout.simple_spinner_item,parts);
        adapter_plants.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plant_selection.setAdapter(adapter_plants);

    }

    void Admin_Login_auth(String email,String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(@NonNull AuthResult authResult) {

                if (authResult.getAdditionalUserInfo().isNewUser()){
                    Toast.makeText(AdminLoginScreen.this,"Register first",Toast.LENGTH_LONG).show();
                }

                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore.collection(plant_selection.getSelectedItem().toString()).document("admin").collection("all admin")
                        .whereEqualTo("email", email).get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.getDocuments().isEmpty()) {

                                    progressDialog.dismiss();
                                    myEdit.putString("userType", "admin");
                                    myEdit.commit();
                                    Intent intent = new Intent(AdminLoginScreen.this, Adminbottomnavigation.class);
                                    startActivity(intent);
                                    Toast.makeText(AdminLoginScreen.this, "Registered User", Toast.LENGTH_LONG).show();
                                }else {
                                    progressDialog.dismiss();
                                    Toast.makeText(AdminLoginScreen.this, "Not Registered User", Toast.LENGTH_LONG).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast .makeText(AdminLoginScreen.this,"error",Toast.LENGTH_LONG).show();
                    }
                });


            }
        });


    }

}
