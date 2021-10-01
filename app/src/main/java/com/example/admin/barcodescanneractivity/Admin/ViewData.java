package com.example.admin.barcodescanneractivity.Admin;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.barcodescanneractivity.R;

public class ViewData extends AppCompatActivity {
    ImageButton datebutton;
    TextView Date;
    RecyclerView VIEWUSER_RECYCLERVIEW;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);
        init();
        datebutton.setOnClickListener( new BtnDatePickerDialogClickListener());
    }

    void init(){
        datebutton= findViewById(R.id.datepickerdialog);
        Date = findViewById(R.id.selected_date);
    }

    private class BtnDatePickerDialogClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            DatePickerDialog dobPickerDialog =
                    new DatePickerDialog(
                            ViewData.this,
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
            Date.setText( dayOfMonth + "-" + (month+1) + "-"+year);
        }
    }
}
