package com.example.danramirez.afg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< Updated upstream
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
=======
import android.view.View;
>>>>>>> Stashed changes

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Constructing the Spinner/Dropdown
       Spinner spinner = (Spinner) findViewById(R.id.spinner);

       //Array Adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);

        //Specify Layout
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Apply Adapter to Spinner
        spinner.setAdapter(adapter);

    }

<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
}
