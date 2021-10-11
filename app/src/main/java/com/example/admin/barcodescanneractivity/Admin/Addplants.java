package com.example.admin.barcodescanneractivity.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.barcodescanneractivity.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Addplants extends AppCompatActivity {
    private ListView mListViewCities;
    private ArrayList<String> mPlants;
     Button ADDPLANT;
    ProgressDialog dialog;
      EditText addplantname;

//    private ArrayList<String> mListCities;

    private ArrayAdapter<String> mAdapterCities;
    private Button mBtnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addplants);

        init();
        dialog = ProgressDialog.show(Addplants.this, "",
                "Loading. Please wait...", true);
        dialog.show();
         fetchplants();
         mBtnAdd.setOnClickListener(new BtnAddClickListener());
    }

    private class BtnAddClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(Addplants.this);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            final View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.addplant_alertdialog, viewGroup, false);
            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

             addplantname=dialogView.findViewById(R.id.addplant);
            ADDPLANT=dialogView.findViewById(R.id.addplantbutton);


            ADDPLANT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                if (addplantname.getText().toString().isEmpty()){

                    Toast.makeText(Addplants.this,"Please Enter Plant name",Toast.LENGTH_LONG).show();
                }else {
                   addplants(addplantname.getText().toString());



                }
                }
            });
               alertDialog.show();

        }


    }

    private void fetchplants(){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("plants").get().addOnSuccessListener(
                new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot snapshot: queryDocumentSnapshots.getDocuments()){
                            mPlants.add(String.valueOf(snapshot.getData().get("name")));
                        }
                        mAdapterCities = new ArrayAdapter<String>(
                                Addplants.this,
                                android.R.layout.simple_list_item_1,
                                mPlants
                        );
                        mListViewCities.setAdapter(mAdapterCities);
                        dialog.dismiss();
                    }
                }
        ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Addplants.this,"Error please return ",Toast.LENGTH_LONG).show();
            }
        });

    }

    private  void addplants(String Plantname){
        Map<String, Object> add_data = new HashMap<>();
        add_data.put("name",Plantname);
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("plants").add(add_data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(@NonNull DocumentReference documentReference) {
                 Toast.makeText(Addplants.this,"Part Added sucessfully",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Addplants.this,"Error",Toast.LENGTH_LONG).show();
            }
        });



    }
    private void init() {

        mPlants = new ArrayList<>();
        mBtnAdd = findViewById(R.id.addplants);
        mListViewCities = findViewById(R.id.listViewCities);

    }

}
