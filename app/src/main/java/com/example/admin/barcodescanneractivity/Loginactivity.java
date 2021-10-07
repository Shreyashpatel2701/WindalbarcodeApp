package com.example.admin.barcodescanneractivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Loginactivity extends AppCompatActivity {
      EditText EMAIL,PASSWORD;
    Button Login;
    Spinner plant_selection;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.login_activity);
         init();
         spinner_plants();
         Login.setOnClickListener(new btnloginonclicklistener());
    }

    void init(){
        Login = findViewById(R.id.login_button);
        plant_selection = findViewById(R.id.spinner_plant);
        EMAIL = findViewById(R.id.login_email);
        PASSWORD = findViewById(R.id.login_password);
    }

    public class btnloginonclicklistener implements View.OnClickListener{
        @Override
        public void onClick(View view) {

              Login_auth(EMAIL.getText().toString(),PASSWORD.getText().toString());
//            Intent intent = new Intent(Loginactivity.this,vehicleinformation.class);
//            startActivity(intent);
        }
    }

    void spinner_plants(){
        String[] parts = {"--Select plants--","Chakan","Bhpoal"};
        ArrayAdapter adapter_plants = new ArrayAdapter(this,android.R.layout.simple_spinner_item,parts);
        adapter_plants.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plant_selection.setAdapter(adapter_plants);
    }

    void  Login_auth(String email,String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(@NonNull AuthResult authResult) {

              if (authResult.getAdditionalUserInfo().isNewUser()){

                  Toast.makeText(Loginactivity.this,"Register first",Toast.LENGTH_LONG).show();
              }

                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                 firebaseFirestore.collection("chakan").
                      document("users").
                      collection("all users")
                      .whereEqualTo("email", email).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                     @Override
                     public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                         if (!queryDocumentSnapshots.getDocuments().isEmpty()) {
                               Log.e("sucess","shreyash");
                             Intent intent = new Intent(Loginactivity.this, vehicleinformation.class);
                             startActivity(intent);
                             Toast.makeText(Loginactivity.this, "Registered User", Toast.LENGTH_LONG).show();
                         }else {
                             Log.e("hello","error");
                         }
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                     Toast .makeText(Loginactivity.this,"error",Toast.LENGTH_LONG).show();
                     Log.e("failure","failure");
                     }
                 });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }

}
