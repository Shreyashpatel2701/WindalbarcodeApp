package com.example.admin.barcodescanneractivity.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.barcodescanneractivity.MainActivity;
import com.example.admin.barcodescanneractivity.R;

import java.util.ArrayList;
import java.util.Locale;

public class Addparts extends AppCompatActivity {
    private ListView mListViewCities;
    private ArrayList<String> mParts;
    EditText addpartsname_alertdialog,addpartsbarcode_alertdialog,DELETEPARTNAME;
    Button ADDPART,DELETEPART;


//    private ArrayList<String> mListCities;

    private ArrayAdapter<String> mAdapterCities;
    private Button mBtnAdd, mBtnDelete;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addparts);

        init();

        addparts();
        mAdapterCities = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                mParts
        );
        mListViewCities.setAdapter(mAdapterCities);


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

                    ADDPART.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                          if (mParts.contains(addpartsname_alertdialog.getText().toString().toUpperCase(Locale.ROOT))){
                              Toast.makeText(Addparts.this,"THIS PART IS ALREADY ADDED ",Toast.LENGTH_LONG).show();
                              alertDialog.dismiss();
                          }else {
                              Toast.makeText(Addparts.this,addpartsname_alertdialog.getText().toString() + "part added successfully",Toast.LENGTH_LONG).show();
                              mParts.add(addpartsname_alertdialog.getText().toString().toUpperCase(Locale.ROOT));
                              alertDialog.dismiss();
                           }

                        }
                    });
                    alertDialog.setCanceledOnTouchOutside(false);
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
                    if (mParts.contains(DELETEPARTNAME.getText().toString().toUpperCase(Locale.ROOT))){
                        Toast.makeText(Addparts.this,DELETEPARTNAME.getText().toString() + "PART DELETED SUCCESSFULLY",Toast.LENGTH_LONG ).show();

                    }else {
                        mParts.remove(DELETEPARTNAME.getText().toString());
                        Toast.makeText(Addparts.this,DELETEPARTNAME.getText().toString() + "PART DOES NOT EXISTS",Toast.LENGTH_LONG ).show();
                    }


                }
            });
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }
    }

    private  void addparts(){
        mParts.add("OLD WAGON");
        mParts.add("NEW WAGON");
        mParts.add("SSO OLD");
        mParts.add("SSO NEW");
    }
    private void init() {
        mParts = new ArrayList<>();
       // mEdtCity = findViewById(R.id.edtCity);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnDelete = findViewById(R.id.btnDelete);
        mListViewCities = findViewById(R.id.listViewCities);

    }



}