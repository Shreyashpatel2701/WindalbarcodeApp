package com.example.admin.barcodescanneractivity.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.admin.barcodescanneractivity.LoginoptionsActivity;
import com.example.admin.barcodescanneractivity.Masteradminlogin;
import com.example.admin.barcodescanneractivity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminHomeScreen extends Fragment {
      Button VIEWDATA,USERDATA,EDITUSERDATA,ADDPLANT,ADDPARTS;

      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.adminhomefragment, container, false);
        VIEWDATA =view.findViewById(R.id.btn_viewdata);
        USERDATA=view.findViewById(R.id.btn_viewuser);
        EDITUSERDATA=view.findViewById(R.id.btn_edituserdata);
        ADDPLANT=view.findViewById(R.id.btn_addpartdata);
        ADDPARTS=view.findViewById(R.id.btn_addplantdata);
        VIEWDATA.setOnClickListener(new btnviewdata());
        ADDPARTS.setOnClickListener(new btnaddplant());
        ADDPLANT.setOnClickListener(new btnaddparts());

        return view;
      }

      private class btnviewdata implements View.OnClickListener{
     @Override
     public void onClick(View view) {
         Intent intent = new Intent(getActivity(),ViewData.class);
         startActivity(intent);
       }
     }
     private class btnaddplant implements View.OnClickListener{
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(getActivity(), Masteradminlogin.class);
             startActivity(intent);
         }

     }

    private class btnaddparts implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(),Addparts.class);
            startActivity(intent);
        }
    }
}
