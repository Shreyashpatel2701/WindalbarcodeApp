package com.example.admin.barcodescanneractivity.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.barcodescanneractivity.MainActivity;
import com.example.admin.barcodescanneractivity.R;

import java.util.ArrayList;

public class Addparts extends AppCompatActivity {
    private ListView mListViewCities;

    private String[] mArrCities = {
            "Pune0", "Mumbai0", "Chennai0", "Benguluru0", "jaipur0",
            "Pune1", "Mumbai1", "Chennai1", "Benguluru1", "jaipur1",
            "Pune2", "Mumbai2", "Chennai2", "Benguluru2", "jaipur2",
            "Pune3", "Mumbai3", "Chennai3", "Benguluru3", "jaipur3",
            "Pune4", "Mumbai4", "Chennai4", "Benguluru4", "jaipur4",
            "Pune5", "Mumbai5", "Chennai5", "Benguluru5", "jaipur5"
    };

//    private ArrayList<String> mListCities;

    private ArrayAdapter<String> mAdapterCities;

    private EditText mEdtCity;
    private Button mBtnAdd, mBtnDelete;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addparts);

        init();


        mAdapterCities = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                mArrCities
        );
        mListViewCities.setAdapter(mAdapterCities);
        mListViewCities.setOnItemClickListener(new MyOnItemClickListener());

        mBtnAdd.setOnClickListener(new BtnAddClickListener());
        mBtnDelete.setOnClickListener(new BtnDeleteClickListener());
    }

    private class BtnAddClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
//            mArrCities.add(mEdtCity.getText().toString());
            mAdapterCities.notifyDataSetChanged();
        }
    }

    private class BtnDeleteClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
//            mListCities.remove(mEdtCity.getText().toString());
            mAdapterCities.notifyDataSetChanged();
        }
    }

    private void init() {
        mEdtCity = findViewById(R.id.edtCity);
        mBtnAdd = findViewById(R.id.btnAdd);
        mBtnDelete = findViewById(R.id.btnDelete);
        mListViewCities = findViewById(R.id.listViewCities);

    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            //String selectedCity = mArrCities[position];
//            String selectedCity = mListCities.get(position);
//            Toast.makeText(Addparts.this, selectedCity + " -- " + position, Toast.LENGTH_LONG).show();
        }
    }
}