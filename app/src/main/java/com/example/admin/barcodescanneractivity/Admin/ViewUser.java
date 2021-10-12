package com.example.admin.barcodescanneractivity.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.barcodescanneractivity.Admin.Adapter.view_user_adapter;
import com.example.admin.barcodescanneractivity.Admin.Datamodel.view_data_datamodel;
import com.example.admin.barcodescanneractivity.Admin.Datamodel.view_user_datamodel;
import com.example.admin.barcodescanneractivity.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewUser extends AppCompatActivity {

    RecyclerView user_recyclerview;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<view_user_datamodel> view_user_datamodelArrayList = new ArrayList<view_user_datamodel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        user_recyclerview = findViewById(R.id.user_recyclerview);
        user_recyclerview.setHasFixedSize(true);
        user_recyclerview.setLayoutManager(new LinearLayoutManager(getParent()));

        db.collection("chakan")
                .document("users")
                .collection("all users")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot documentSnapshot: queryDocumentSnapshots.getDocuments()){
                            view_user_datamodel child = documentSnapshot.toObject(view_user_datamodel.class);
                            Log.e("child", child.getEmail());
                            view_user_datamodelArrayList.add(child);
                            view_user_adapter adapter = new view_user_adapter(view_user_datamodelArrayList);
                            user_recyclerview.setAdapter(adapter);

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ViewUser.this, "Cannot fetch users", Toast.LENGTH_SHORT).show();
            }
        });




    }
}