package com.example.danramirez.afg;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private ArrayList<Job> jobs;
    final DatabaseReference database = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Constructing the Spinner/Dropdown
       Spinner catSpinner = (Spinner) findViewById(R.id.catSpinner);
       catSpinner.setOnItemSelectedListener(this);

       //Array Adapter
        ArrayAdapter<CharSequence> catAdapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_dropdown_item);

        //Specify Layout
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Apply Adapter to Spinner
        catSpinner.setAdapter(catAdapter);



        //Constructing the radius Spinner/Dropdown
        Spinner radSpinner = (Spinner) findViewById(R.id.radSpinner);
        radSpinner.setOnItemSelectedListener(this);

        //Array Adapter
        ArrayAdapter<CharSequence> radAdapter = ArrayAdapter.createFromResource(this, R.array.radius_array, android.R.layout.simple_spinner_item);

        //Specify Layout
        radAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Apply Adapter to radius Spinner
        radSpinner.setAdapter(radAdapter);


        catSpinner.setOnItemSelectedListener(this);
        radSpinner.setOnItemSelectedListener(this);







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

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

        switch(parent.getId()){
            case R.id.catSpinner:
                Object category = parent.getItemAtPosition(position);
                String selectedCategory = category.toString();
                Log.e("MainActivity", "Category Selected: " + selectedCategory);
                Toast.makeText(this, "Category Selected", Toast.LENGTH_LONG ).show();

            case R.id.radSpinner:

                Object radius = parent.getItemAtPosition(position);
                String selectedRadius = radius.toString();
                Log.e("MainActivity", "Radius Selected: " + selectedRadius);
                Toast.makeText(this, "Radius Selected", Toast.LENGTH_LONG ).show();

        }





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



    //Sets dropdown category selected to the var 'selectedCategory' and dropdown radius to the var 'selectedRadius'





    public void onNothingSelected(AdapterView<?> parent){
        Toast.makeText(this, "Please select a radius, type in your zip code, and select a category.", Toast.LENGTH_LONG ).show();



    }



    public void displayUserInfo(View v){
        Spinner catSpinner = (Spinner) findViewById(R.id.catSpinner);
        String category = catSpinner.getSelectedItem().toString();

        System.out.println(category);

        Spinner radSpinner = (Spinner) findViewById(R.id.radSpinner);
        String radius = radSpinner.getSelectedItem().toString();

        System.out.println(radius);

        EditText zipText = findViewById(R.id.zipCodeEditText);
        String zipStr = zipText.getText().toString();

        System.out.println(zipStr);

        TextView resultsHeading = findViewById(R.id.textResultsHeading);
        resultsHeading.setText("Results for " + category + " jobs within " + radius + " miles of " + zipStr);


        Intent intent =new Intent(this, DisplayPage.class);
        intent.putExtra("category", category);
        intent.putExtra("radius", radius);
        intent.putExtra("zip", zipStr);
        startActivity(intent);


    }

}

