package com.example.danramirez.afg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    private ArrayList<Job> jobs;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Job placeholder;
    private int idPlaceholder;
    private DatabaseReference mJobReference;




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

        System.out.println("Reference: "+database.getReference().child("JobListings"));


        readJobData();


        JobAdapter adapter = new JobAdapter(this, jobs);
        ListView discoveryList = (ListView) findViewById(R.id.discoveryListView);
        discoveryList.setAdapter(adapter);

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
        jobs = new ArrayList<Job>();
        InputStream is = getResources().openRawResource(R.raw.joblist);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";
        try {
            while((line = reader.readLine()) != null) {
                // Split by '''
                String[] fields = line.split("'''");
                Job s = new Job(fields[0], fields[1], fields[2], fields[3], "idPlaceholder", Integer.parseInt(fields[4]));
                jobs.add(s);

            }
        } catch(IOException e) {
            Log.e("MainActivity", "Error reading data on line " + line);
        }

        // Check if each job exists - if not, add to DB; if yes, print that it already exists
        for(Job job: jobs)
        {
            System.out.println("Checking for job " + job.getlocalID());
            idPlaceholder = job.getlocalID();
            findJobByApiID();
            /*if(placeholder.equals(job))
            {
                System.out.println("Job found in DB. Not adding.");
            }
            else{
                System.out.println("Job not found - adding it to database now");
                DatabaseReference pushedJobListing = database.getReference().child("JobListings").child(Integer.toString(job.getlocalID()));
                pushedJobListing.setValue(job);
            }*/
        }
    }


    public void findJobByApiID()
    {
        DatabaseReference resultList = database.getReferenceFromUrl("https://afg-db.firebaseio.com/JobListings");
        System.out.println("Got into findJobByApiID method");
        resultList.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            //FIREBASERECYCLERADAPTER
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("Made it in for:each");
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    System.out.println("Inside for loop");
                    Job job = ds.getValue(Job.class);
                    if(job.getlocalID() == idPlaceholder)
                    {
                        System.out.println("Found job in database -  do not add it!");
                        System.out.println("Setting placeholder to job object");
                        placeholder = job;

                    }
                    else{
                        System.out.println("Else");

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }







    public void onNothingSelected(AdapterView<?> parent){
            Toast.makeText(this, "Please select a radius, type in your zip code, and select a category.", Toast.LENGTH_LONG).show();


        }



    public void displayUserInfoSearch(View v){
        Spinner catSpinner = (Spinner) findViewById(R.id.catSpinner);
        String category = catSpinner.getSelectedItem().toString();

        System.out.println(category);

        Spinner radSpinner = (Spinner) findViewById(R.id.radSpinner);
        String radius = radSpinner.getSelectedItem().toString();

        System.out.println(radius);

        EditText zipText = findViewById(R.id.zipCodeEditText);
        String zipStr = zipText.getText().toString();


        System.out.println(zipStr);

        TextView resultsHeading = findViewById(R.id.resultsLabel);
        resultsHeading.setText("Results for " + category + " jobs within " + radius + " of " + zipStr);


        Intent intent =new Intent(this, DisplayPage.class);
        intent.putExtra("category", category);
        intent.putExtra("radius", radius);
        intent.putExtra("zip", zipStr);
        intent.putExtra("jobs", jobs);
        startActivity(intent);



    }


    public void displayUserfaves(View v)
    {
        Spinner catSpinner = (Spinner) findViewById(R.id.catSpinner);
        String category = catSpinner.getSelectedItem().toString();

        System.out.println(category);

        TextView resultsHeading = findViewById(R.id.favoritesLabel);
        resultsHeading.setText("Favorites");


        Intent intent =new Intent(this, FavoritesPage.class);
        intent.putExtra("favorites",category);
        startActivity(intent);


    }




}

