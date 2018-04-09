package com.example.danramirez.afg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    final DatabaseReference database = FirebaseDatabase.getInstance().getReference();


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



        jobs = new ArrayList<Job>();
        readJobData();
        for(Job job: jobs)
        {
            DatabaseReference pushedJobListing = database.child("JobListings").push();
            job.setID(pushedJobListing.getKey());
            pushedJobListing.setValue(job);
        }

        database.child("JobListings").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Job job = dataSnapshot.getValue(Job.class);
                System.out.println(job.toString());
                //Instead of printing, this should insert the job listing information into the GUI
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }



    private void readJobData() {
        // Read data from file
        InputStream is = getResources().openRawResource(R.raw.joblist);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";
        try {
            while((line = reader.readLine()) != null) {
                // Split by '''

                String[] fields = line.split("'''");
                Job s = new Job(fields[0], fields[1], fields[2], fields[3], "idPlaceholder");
                jobs.add(s);

            }
        } catch(IOException e) {
            Log.e("MainActivity", "Error reading data on line " + line);
        }

    }

    private void pullJobListings() {
        database.child("JobListings").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Job job = dataSnapshot.getValue(Job.class);
                System.out.println(job);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){

        Object category = parent.getItemAtPosition(pos);
        String selectedCategory = category.toString();

    }

    public void onNothingSelected(AdapterView<?> parent){


    }



}

