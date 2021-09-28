package com.example.admin.barcodescanneractivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class vehicleinformation extends AppCompatActivity {
    Button Continue;
    Spinner parts_info;
    String parts_selected;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_vechile);



        init();
        spinner_parts();
        Continue.setOnClickListener(new btncontinue());


//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("chakan")
//                .document("supervisor")
//                .collection("all supervisors")
//                .document("LX3LG0SYveLt8WsXjlgk")
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        Toast.makeText(vehicleinformation.this, documentSnapshot.getData().toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });


    }

    void init(){
       Continue = findViewById(R.id.btn_continue);
       parts_info = findViewById(R.id.parts_spinner);
    }


    private class btncontinue implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            parts_selected = parts_info.getSelectedItem().toString();
            if (parts_selected.matches("--Select parts--")){
                Toast.makeText(vehicleinformation.this,"select parts",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(vehicleinformation.this,parts_selected,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(vehicleinformation.this, compnentsinformation.class);
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
