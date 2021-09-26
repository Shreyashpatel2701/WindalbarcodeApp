package com.example.admin.barcodescanneractivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class vehicleinformation extends AppCompatActivity {
    Button Continue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_vechile);



        init();
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


    }

    void init(){
       Continue = findViewById(R.id.btn_continue);
    }


    private class btncontinue implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(vehicleinformation.this,compnentsinformation.class);
            startActivity(intent);
        }
    }
}
