package com.example.admin.barcodescanneractivity.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.barcodescanneractivity.R;

import java.util.ArrayList;
import java.util.Locale;

public class Addplants extends AppCompatActivity {
    private ListView mListViewCities;
    private ArrayList<String> mParts;
     Button ADDPLANT;

      EditText addplantname;

//    private ArrayList<String> mListCities;

    private ArrayAdapter<String> mAdapterCities;
    private Button mBtnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addplants);

        init();

       addplants();
        mAdapterCities = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                mParts
        );
        mListViewCities.setAdapter(mAdapterCities);


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
                    if (mParts.contains(addplantname.getText().toString().toUpperCase(Locale.ROOT))){
                        Toast.makeText(Addplants.this,"THIS PART IS ALREADY ADDED ",Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                    }else {
                        Toast.makeText(Addplants.this,addplantname.getText().toString() + "part added successfully",Toast.LENGTH_LONG).show();
                        mParts.add(addplantname.getText().toString().toUpperCase(Locale.ROOT));
                        alertDialog.dismiss();
                    }

                }
            });
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();

        }


    }

    private  void addplants(){
        mParts.add("BHOPAL");
        mParts.add("CHAKAN");
        mParts.add("NAGPUR");
        mParts.add("BHOSRI");
    }
    private void init() {
        mParts = new ArrayList<>();
        mBtnAdd = findViewById(R.id.addplants);
        mListViewCities = findViewById(R.id.listViewCities);

    }

}
