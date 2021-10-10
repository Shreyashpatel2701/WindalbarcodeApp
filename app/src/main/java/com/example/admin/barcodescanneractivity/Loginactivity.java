package com.example.admin.barcodescanneractivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Loginactivity extends AppCompatActivity {
      EditText EMAIL,PASSWORD;
    Button Login;
    Spinner plant_selection;
    ProgressDialog progressDialog;
    Spinner select_month, select_plant;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> array = new ArrayList<String>();
    String[] stringArray={};



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.login_activity);
         init();
        plant_selection.setEnabled(false);
        plant_spinner();
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



            if(EMAIL.getText().toString().isEmpty()){
                EMAIL.setError("Invalid Email");
                PASSWORD.setFocusable(true);
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(EMAIL.getText().toString()).matches()) {
                EMAIL.setError("Invalid Email");
                PASSWORD.setFocusable(true);
            }
//            if (!EMAIL.getText().toString().matches(Patterns.EMAIL_ADDRESS.toString())){
//                EMAIL.setError("Invalid email");
//                EMAIL.setFocusable(true);
//            }
            else if (PASSWORD.length() < 6){
                PASSWORD.setError("PASSWORD SHOULD BE Atleast 6 digits ");
                PASSWORD.setFocusable(true);

            }else if(plant_selection.getSelectedItem().toString().matches("--Select plants--")){
                Toast.makeText(Loginactivity.this, "Please select plant", Toast.LENGTH_SHORT).show();
            }else{
                progressDialog = new ProgressDialog(Loginactivity.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Logging In");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                Login_auth(EMAIL.getText().toString(),PASSWORD.getText().toString());
            }
//            else if (PASSWORD.getText().toString().length() < 6 ){
//                PASSWORD.setError("enter correct password");
//                PASSWORD.setFocusable(true);
//            }



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
                      .whereEqualTo("email", email).get()
                         .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                     @Override
                     public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                         if (!queryDocumentSnapshots.getDocuments().isEmpty()) {

                               progressDialog.dismiss();
                             Intent intent = new Intent(Loginactivity.this, vehicleinformation.class);
                             startActivity(intent);
                             Toast.makeText(Loginactivity.this, "Registered User", Toast.LENGTH_LONG).show();
                         }else {
                             Toast.makeText(Loginactivity.this, "Not Registered User", Toast.LENGTH_LONG).show();
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

    void plant_spinner(){

        array.add("--Select Plant--");

        db.collection("plants")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot document: queryDocumentSnapshots.getDocuments()){
                            //Log.e("type", String.valueOf(document.getData().get("name")));
                            array.add(String.valueOf(document.getData().get("name")));
                        }
                        Log.e("type",array.toString());
//                        parts = array.toArray(array);
                        stringArray = array.toArray(new String[0]);
                        Log.e("converted shit",stringArray.toString());
//                        ArrayAdapter adapter_parts = new ArrayAdapter(ExportToExcel.this,android.R.layout.simple_spinner_item,stringArray);
//                        adapter_parts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        select_plant.setAdapter(adapter_parts);
                        ArrayAdapter adapter_plants = new ArrayAdapter(Loginactivity.this,android.R.layout.simple_spinner_item,stringArray);
                        adapter_plants.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        plant_selection.setAdapter(adapter_plants);

                        plant_selection.setEnabled(true);
                        //progressDialog.dismiss();
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(e!=null){
                            Toast.makeText(Loginactivity.this, "Cant fetch plants", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



//        ArrayAdapter adapter_parts = new ArrayAdapter(ExportToExcel.this,android.R.layout.simple_spinner_item,plantArray);
//        adapter_parts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        select_plant.setAdapter(adapter_parts);
    }

}
