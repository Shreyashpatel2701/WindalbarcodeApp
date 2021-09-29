package com.example.admin.barcodescanneractivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class vehicleinformation extends AppCompatActivity {
    public String selectedPartName;
    Button Continue;
    Spinner parts_info;
    String parts_selected;
    EditText et_date;
    EditText vehicle_number;
    EditText invoice_number;
    EditText part_quantity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_vechile);



        init();
        spinner_parts();
        Continue.setOnClickListener(new btncontinue());


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("chakan")
                .document("supervisor")
                .collection("all supervisors")
                .document("LX3LG0SYveLt8WsXjlgk")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Toast.makeText(vehicleinformation.this, documentSnapshot.getData().toString(), Toast.LENGTH_SHORT).show();
                    }
                });



        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add calendar code here
                //calendar();

            }
        });


    }

    private void calendar() {

    }

    void init(){
       Continue = findViewById(R.id.btn_continue);
       parts_info = findViewById(R.id.parts_spinner);
       et_date = findViewById(R.id.et_date);
       vehicle_number = findViewById(R.id.Vehicle_number);
       invoice_number = findViewById(R.id.invoice_number);
       part_quantity = findViewById(R.id.et_part_quantity);

    }



    private class btncontinue implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            parts_selected = parts_info.getSelectedItem().toString();
            if(et_date.getText().toString()==""||vehicle_number.getText().toString()==""||invoice_number.getText().toString()==""||part_quantity.getText().toString()==""){
                Toast.makeText(vehicleinformation.this, "Please enter all values", Toast.LENGTH_SHORT).show();
            }

            if(parts_selected.matches("OLD WAGON")){
                selectedPartName = "SJ10081";
            }
            if(parts_selected.matches("NEW WAGON")){
                selectedPartName = "SJ25659P21";
            }
            if(parts_selected.matches("SSO OLD")){
                selectedPartName = "SJ30237";
            }
            if(parts_selected.matches("SSO NEW")){
                selectedPartName = "SJ30238";
            }
            if(parts_selected.matches("28 HP FRAME")){
                selectedPartName = "SJ32344P21";
            }
            if(parts_selected.matches("36 HP FRAME")){
                selectedPartName = "SJ26990P21";
            }
            if(parts_selected.matches("3-D FRAME")){
                selectedPartName = "SJ26439P21";
            }
            if(parts_selected.matches("UTUL FRAME")){
                selectedPartName = "SJ30398P21";
            }


            if (parts_selected.matches("--Select parts--")){
                Toast.makeText(vehicleinformation.this,"Please select parts",Toast.LENGTH_SHORT).show();
            }else {

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                myEdit.putInt("start_count",0 );
                myEdit.putInt("correct",0);
                myEdit.putInt("wrong",0);
                myEdit.commit();


                Toast.makeText(vehicleinformation.this,parts_selected,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(vehicleinformation.this, ScanCodeActivity.class);
                intent.putExtra("selectedPartName", selectedPartName);
                intent.putExtra("quantity", part_quantity.getText().toString());
                intent.putExtra("vehicle_number", vehicle_number.getText().toString());
                intent.putExtra("invoice_number", invoice_number.getText().toString());
                intent.putExtra("date", et_date.getText().toString());


                startActivity(intent);
            }




        }
    }

    void spinner_parts(){
        String[] parts = {"--Select parts--","OLD WAGON","NEW WAGON","SSO OLD","SSO NEW","28 HP FRAME","36 HP FRAME","3-D FRAME","UTUL FRAME"};
        ArrayAdapter adapter_parts = new ArrayAdapter(this,android.R.layout.simple_spinner_item,parts);
        adapter_parts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parts_info.setAdapter(adapter_parts);
    }

}
