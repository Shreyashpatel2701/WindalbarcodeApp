package com.example.admin.barcodescanneractivity.Admin.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.barcodescanneractivity.Admin.Datamodel.view_data_datamodel;
import com.example.admin.barcodescanneractivity.R;

import java.util.ArrayList;

public class view_data_adpater extends RecyclerView.Adapter {


    ArrayList<view_data_datamodel> mArraylist;

    view_data_adpater(ArrayList<view_data_datamodel> arrayList){
         mArraylist = arrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_data, parent,false);
        return new viewdataviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
              viewdataviewholder mviewdataviewholder = (viewdataviewholder) holder;
    }

    public class viewdataviewholder extends RecyclerView.ViewHolder{
            TextView correct_data,date,wrong_data,vehicle_number,invoice_number,part_name;

            public viewdataviewholder(@NonNull View itemView) {
            super(itemView);
            correct_data = itemView.findViewById(R.id.viewdata_correctpart);
            wrong_data = itemView.findViewById(R.id.viewdata_wrongpart);
            vehicle_number = itemView.findViewById(R.id.viewdata_vehcile_no);
            invoice_number = itemView.findViewById(R.id.viewdata_invoicenumber);
            date = itemView.findViewById(R.id.viewdata_date);
            part_name = itemView.findViewById(R.id.viewdata_parts_name);


        }

    }


    @Override
    public int getItemCount() {
        if (mArraylist.size() == 0){
            return  0;
        }
        return mArraylist.size();
    }
}
