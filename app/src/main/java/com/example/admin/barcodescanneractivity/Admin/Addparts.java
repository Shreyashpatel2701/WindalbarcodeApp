package com.example.admin.barcodescanneractivity.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.barcodescanneractivity.DynamicParts;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import com.example.admin.barcodescanneractivity.MainActivity;
import com.example.admin.barcodescanneractivity.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Addparts extends AppCompatActivity {
    private ListView mListViewCities;
    private ArrayList<String> mParts;
    EditText addpartsname_alertdialog,addpartsbarcode_alertdialog,DELETEPARTNAME;
    Button ADDPART,DELETEPART;
    String[] stringArray={};
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    ProgressDialog dialog;

//    private ArrayList<String> mListCities;

    private ArrayAdapter<String> mAdapterCities;
    private Button mBtnAdd, mBtnDelete;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addparts);

        init();
         dialog = ProgressDialog.show(Addparts.this, "",
                "Loading. Please wait...", true);
        dialog.show();
        fetchparts();



        mBtnAdd.setOnClickListener(new BtnAddClickListener());
        mBtnDelete.setOnClickListener(new BtnDeleteClickListener());
    }

    private class BtnAddClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(Addparts.this);
                    ViewGroup viewGroup = findViewById(android.R.id.content);
                    final View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.add_parts_alert_dialog, viewGroup, false);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    addpartsname_alertdialog=dialogView.findViewById(R.id.add_part_name);
                    addpartsbarcode_alertdialog=dialogView.findViewById(R.id.add_part_barcode);
                    ADDPART=dialogView.findViewById(R.id.btbaddpartsalertdialog);

                    String name = addpartsname_alertdialog.getText().toString();
                    String barcode = addpartsbarcode_alertdialog.getText().toString();



                    ADDPART.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            addparts(barcode,name);

                        }
                    });
                    alertDialog.setCanceledOnTouchOutside(true);
                    alertDialog.show();

                }


        }


    private class BtnDeleteClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Addparts.this);
            ViewGroup viewGroup = findViewById(android.R.id.content);
            final View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.delete_parts, viewGroup, false);
            builder.setView(dialogView);
            final AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                   DELETEPARTNAME=dialogView.findViewById(R.id.deletepart);
                   DELETEPART=dialogView.findViewById(R.id.deletepartbutton);


            DELETEPART.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }
    }


    private void fetchparts(){
        firebaseFirestore.collection("chakan")
                .document("parts")
                .collection("all parts")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot document: queryDocumentSnapshots.getDocuments()){
                            mParts.add(String.valueOf(document.getData().get("name")));
                        }
                        mAdapterCities = new ArrayAdapter<String>(
                               Addparts.this,
                                android.R.layout.simple_list_item_1,
                                mParts
                        );
                        mListViewCities.setAdapter(mAdapterCities);
                        dialog.dismiss();


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                 Toast.makeText(Addparts.this,e.toString(),Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });




    }

    private  void addparts( String barcode,String name){
        Map<String, Object> add_data = new HashMap<>();
        add_data.put("code",barcode);
        add_data.put("name",name);
        firebaseFirestore.collection("chakan")
                .document("parts")
                .collection("all parts").add(add_data).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(@NonNull DocumentReference documentReference) {
                        Toast.makeText(Addparts.this, "part added", Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Addparts.this, "part error", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void deletepart(){

    }



    private void init() {
        mParts = new ArrayList<>();
       // mEdtCity = findViewById(R.id.edtCity);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnDelete = findViewById(R.id.btnDelete);
        mListViewCities = findViewById(R.id.listViewCities);
    }



}