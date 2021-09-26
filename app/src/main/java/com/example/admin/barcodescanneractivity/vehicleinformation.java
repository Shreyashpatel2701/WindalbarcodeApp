package com.example.admin.barcodescanneractivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class vehicleinformation extends AppCompatActivity {
    Button Continue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_vechile);
        init();
        Continue.setOnClickListener(new btncontinue());
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
