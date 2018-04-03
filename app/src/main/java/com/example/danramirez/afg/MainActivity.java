package com.example.danramirez.afg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private ArrayList<Job> jobs;


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

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        jobs = new ArrayList<Job>();
        readSongData();

        for(Job job: jobs) {
            Log.d("MainActivity", job.getTitle() + " " + job.getCompany() + " " + job.getDescription() + " " + job.getAddress());
            database.child("JobListing").setValue(job);
        }



    }



    private void readSongData() {
        // Read data from file
        InputStream is = getResources().openRawResource(R.raw.joblist);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = "";
        try {
            while((line = reader.readLine()) != null) {
                // Split by ','
                String[] fields = line.split("'''");
                Job s = new Job(fields[0], fields[1], fields[2], fields[3]);
                jobs.add(s);
            }
        } catch(IOException e) {
            Log.e("MainActivity", "Error reading data on line " + line);
        }

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){

        Object category = parent.getItemAtPosition(pos);
        String selectedCategory = category.toString();
        Log.d("MainActivity", selectedCategory);
    }

    public void onNothingSelected(AdapterView<?> parent){


    }



}
