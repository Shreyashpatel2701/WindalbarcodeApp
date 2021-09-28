package com.example.admin.barcodescanneractivity.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.admin.barcodescanneractivity.LoginoptionsActivity;
import com.example.admin.barcodescanneractivity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Adminprofilescreen extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.adminprofilefragment, container, false);

        FirebaseAuth firebaseAuth;
        FirebaseUser currentuser;
        TextView Logout = view.findViewById(R.id.logoutadmin);
        TextView editprofile = view.findViewById(R.id.Editprofileadmin);
        TextView exporttoexcel = view.findViewById(R.id.export_to_excel_admin);
        firebaseAuth = FirebaseAuth.getInstance();
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getActivity(), LoginoptionsActivity.class));
                getActivity().finish();
            }
        });
//        editprofile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), EditProfile.class);
//                startActivity(intent);
//            }
//        });
        exporttoexcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


        return view;
    }


}
