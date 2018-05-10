package com.example.danramirez.afg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseListAdapter;
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
import java.io.StringReader;
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


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Query mJobReference = database.getReference().child("JobListings").limitToFirst(10);
    private FirebaseListAdapter<NewJob> mFirebaseAdapter;




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



        //JobAdapter adapter = new JobAdapter(this, jobs);
        ListView discoveryList = (ListView) findViewById(R.id.discoveryListView);
        //discoveryList.setAdapter(adapter);
        System.out.println("Reference: " + mJobReference.toString());

        setUpFirebaseAdapter(discoveryList);

    }

    private void setUpFirebaseAdapter(ListView listView) {
        mFirebaseAdapter = new FirebaseListAdapter<NewJob>(this, NewJob.class, R.layout.job, mJobReference) {
            @Override
            protected void populateView(View v, NewJob model, int position) {
                ((TextView)v.findViewById(R.id.companyTextView)).setText(model.getCompanyName());
                ((TextView)v.findViewById(R.id.descriptionTextView)).setText(model.getJobText());
                ((TextView)v.findViewById(R.id.titleTextView)).setText(model.getJobTitle());
                ((TextView)v.findViewById(R.id.addressTextView)).setText(model.getJobLocation());
            }
        };
        listView.setAdapter(mFirebaseAdapter);
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


    public void onNothingSelected(AdapterView<?> parent){
            Toast.makeText(this, "Please select a radius, type in your zip code, and select a category.", Toast.LENGTH_LONG).show();


        }


    public void displayUserInfoSearch(View v)
    {
        Spinner catSpinner = (Spinner) findViewById(R.id.catSpinner);
        String category = catSpinner.getSelectedItem().toString();

        System.out.println(category);

        Spinner radSpinner = (Spinner) findViewById(R.id.radSpinner);
        String radius = radSpinner.getSelectedItem().toString();

        System.out.println(radius);


        TextView resultsHeading = findViewById(R.id.resultsLabel);
        resultsHeading.setText("Results for " + category + " jobs in " + radius);


        Intent intent =new Intent(this, DisplayPage.class);
        intent.putExtra("category", category);
        intent.putExtra("radius", radius);
        //intent.putExtra("jobs", jobs);
        startActivity(intent);

    }


    public void displayUserfaves(View v)
    {
        TextView favesText = findViewById(R.id.favoritesLabel);
        favesText.setText("Favorites");

        Intent intent = new Intent(this, FavoritesPage.class);
        startActivity(intent);
    }


    public void returnToHome(View v)
    {
        TextView favesText = findViewById(R.id.homeLabel);
        favesText.setText("Home");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    public void addToFavorites(){



    }







}

