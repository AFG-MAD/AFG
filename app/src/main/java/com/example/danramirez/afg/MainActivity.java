package com.example.danramirez.afg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Constructing the Spinner/Dropdown
       Spinner spinner = (Spinner) findViewById(R.id.spinner);
       spinner.setOnItemSelectedListener(this);

       //Array Adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);

        //Specify Layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Apply Adapter to Spinner
        spinner.setAdapter(adapter);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){

        Object category = parent.getItemAtPosition(pos);
        String selectedCategory = category.toString();
        Log.d("MainActivity", selectedCategory);
    }

    public void onNothingSelected(AdapterView<?> parent){


    }



}
