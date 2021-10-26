package com.example.admin.barcodescanneractivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.example.admin.barcodescanneractivity.Admin.ViewData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class vehicleinformation extends AppCompatActivity {
    public String selectedPartName;
    Button Continue;
    Spinner parts_info;
    String parts_selected;
    EditText et_date;
    EditText vehicle_number;
    EditText invoice_number;
    EditText part_quantity;
    ImageButton datepickerdialog;
    ArrayList<String> supervisor_phno = new ArrayList<String>();

    Spinner selectParts;
    Button codename;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<String> array = new ArrayList<String>();
    //    String parts[]={};
    String[] stringArray={};
    String selected_parts,plants;
    ProgressDialog dialog;
    String part_code,month_name;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    AlertDialog progress;
    SharedPreferences shLogin;
    SharedPreferences.Editor myEditLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_vechile);



        init();
        @SuppressLint("WrongConstant") SharedPreferences sh = getSharedPreferences("PLANTADMIN",MODE_APPEND);
        plants = sh.getString("plant","");
        shLogin = getSharedPreferences("LoginSharedPref", MODE_PRIVATE);
        myEditLogin = shLogin.edit();
//         dialog = ProgressDialog.show(vehicleinformation.this, "",
//                "Loading. Please wait...", true);
//        dialog.show();

//        progressDialog = new ProgressDialog(vehicleinformation.this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();

        AlertDialog.Builder builder = new AlertDialog.Builder(vehicleinformation.this);
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.progressdialog, viewGroup, false);
        builder.setView(dialogView);
        progress = builder.create();
        progress.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        progress.show();

        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        month_name = month_date.format(cal.getTime());
        //Toast.makeText(vehicleinformation.this, month_name.toString(), Toast.LENGTH_SHORT).show();


        spinner_parts();
        datepickerdialog.setOnClickListener(new BtnDatePickerDialogClickListener());
        Continue.setOnClickListener(new btncontinue());

                db.collection(plants)
                .document("supervisor")
                .collection("all supervisors")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot document : queryDocumentSnapshots.getDocuments()){
                            supervisor_phno.add(document.getData().get("phno").toString());
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(vehicleinformation.this,"Can't get supervisor number for sending message", Toast.LENGTH_LONG).show();
            }
        });


        //dialog.dismiss();

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
       //parts_info = findViewById(R.id.parts_spinner);
       et_date = findViewById(R.id.et_date);
       vehicle_number = findViewById(R.id.Vehicle_number);
       invoice_number = findViewById(R.id.invoice_number);
       part_quantity = findViewById(R.id.et_part_quantity);
       datepickerdialog = findViewById(R.id.btn_datepickerdialog);
       selectParts = findViewById(R.id.parts_spinner);

    }



    private class btncontinue implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            //parts_selected = parts_info.getSelectedItem().toString();
            selected_parts = selectParts.getSelectedItem().toString();


            if(et_date.getText().toString().equals("")||vehicle_number.getText().toString().equals("")||part_quantity.getText().toString().equals("")){
                Toast.makeText(vehicleinformation.this, "Please enter all values", Toast.LENGTH_SHORT).show();
            }
            else {
                if (selected_parts.equals("--Select parts--")){
                Toast.makeText(vehicleinformation.this,"Please select parts",Toast.LENGTH_SHORT).show();
                return;
            }
                //Todo: get plant name from SharedPrefs
                db.collection(plants)
                        .document("parts")
                        .collection("all parts")
                        .whereEqualTo("name",selected_parts)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                                for(DocumentSnapshot document: queryDocumentSnapshots.getDocuments()){
                                    Toast.makeText(vehicleinformation.this, document.getData().get("code").toString(), Toast.LENGTH_SHORT).show();
                                    part_code = document.getData().get("code").toString();
                                }
                                selectedPartName = part_code;
//                                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
//
//                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//                                myEdit.putInt("start_count",0 );
//                                myEdit.putInt("correct",0);
//                                myEdit.putInt("wrong",0);
//                                myEdit.commit();
                                new AsyncSharedPref().execute();
                                //Toast.makeText(vehicleinformation.this, sharedPreferences.toString(), Toast.LENGTH_SHORT).show();
                                //Toast.makeText(vehicleinformation.this,parts_selected,Toast.LENGTH_LONG).show();
//                                Intent intent = new Intent(vehicleinformation.this, ScanCodeActivity.class);
//                                intent.putExtra("selectedPartName", selectedPartName);
//                                intent.putExtra("quantity", part_quantity.getText().toString());
//                                intent.putExtra("vehicle_number", vehicle_number.getText().toString());
//                                intent.putExtra("invoice_number", invoice_number.getText().toString());
//                                intent.putExtra("date", et_date.getText().toString());
//                                intent.putExtra("month", month_name);
//                                intent.putExtra("part_name", selected_parts);
//
//
//                                startActivity(intent);
//                                finish();

                            }
                        });


            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_memu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.user_logout:
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signOut();
                myEditLogin.putString("userType", "none");
                myEditLogin.commit();
                Intent intent = new Intent(vehicleinformation.this,LoginoptionsActivity.class);
                startActivity(intent);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class AsyncSharedPref extends AsyncTask<Void, Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
//            sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
//            myEdit = sharedPreferences.edit();

            myEdit.putInt("start_count",0 );
            myEdit.putInt("correct",0);
            myEdit.putInt("wrong",0);
            myEdit.commit();
            Log.e("shredPref: ",sharedPreferences.getAll().toString());
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Intent intent = new Intent(vehicleinformation.this, ScanCodeActivity.class);
            intent.putExtra("selectedPartName", selectedPartName);
            intent.putExtra("quantity", part_quantity.getText().toString());
            intent.putExtra("vehicle_number", vehicle_number.getText().toString());
            intent.putExtra("invoice_number", invoice_number.getText().toString());
            intent.putExtra("date", et_date.getText().toString());
            intent.putExtra("month", month_name);
            intent.putExtra("part_name", selected_parts);
            intent.putExtra("supervisor_phno", supervisor_phno);


            startActivity(intent);
            finish();
        }
    }

    void spinner_parts(){


        array.add("--Select parts--");

        //Todo: get plant name from SharedPrefs
        db.collection(plants)
                .document("parts")
                .collection("all parts")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot document: queryDocumentSnapshots.getDocuments()){
                            Log.e("type", String.valueOf(document.getData().get("name")));
                            array.add(String.valueOf(document.getData().get("name")));
                        }
                        Log.e("type",array.toString());
//                        parts = array.toArray(array);
                        stringArray = array.toArray(new String[0]);
                        Log.e("converted shit",stringArray.toString());
                        ArrayAdapter adapter_parts = new ArrayAdapter(vehicleinformation.this,android.R.layout.simple_spinner_item,stringArray);
                        adapter_parts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        selectParts.setAdapter(adapter_parts);
                        progress.dismiss();
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(e!=null){
                            Toast.makeText(vehicleinformation.this, "Cant fetch parts", Toast.LENGTH_SHORT).show();
                        }
                    }
                });




    }


    private class BtnDatePickerDialogClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            DatePickerDialog dobPickerDialog =
                    new DatePickerDialog(
                            vehicleinformation.this,
                            new DOBPickUpListener(),
                            2021,
                            9,
                            1
                    );
            dobPickerDialog.show();
        }
    }




    private class DOBPickUpListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            et_date.setText( dayOfMonth + "-" + (month+1) + "-"+year);
        }
    }
}
