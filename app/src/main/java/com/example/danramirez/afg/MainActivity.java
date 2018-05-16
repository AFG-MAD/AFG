package com.example.danramirez.afg;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
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

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {


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

       // favoriteToggle = (ToggleButton) findViewById(R.id.addToFavorites);

        //JobAdapter jobAdapter = new JobAdapter(this, R.layout.job, )
        final ListView discoveryList = (ListView) findViewById(R.id.discoveryListView);
        //discoveryList.setOnItemClickListener(this);



        System.out.println("Reference: " + mJobReference.toString());

        setUpFirebaseAdapter(discoveryList);

        final Controller aController = (Controller)getApplicationContext();
        discoveryList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView a, View v, int position, long id){
                if(position>=0){
                    System.out.println("ADD TO FAVORITES");

                    NewJob object = (NewJob) discoveryList.getItemAtPosition(position);
                    aController.getFavorites().add(object);
                    if(object==null){
                        System.out.println("OBJECT NULL");

                    }
                    System.out.println("Object: " + object);
                }

               // Toast.makeText(this, "Added", Toast.LENGTH_LONG ).show();
            }
        });

        discoveryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {


                System.out.println("LONG CLICK ACTIVATED");
                NewJob object = (NewJob) discoveryList.getItemAtPosition(position);
                String url = object.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

                return false;
            }
        });




    }



    private void setUpFirebaseAdapter(final ListView listView) {


        mFirebaseAdapter = new FirebaseListAdapter<NewJob>(this, NewJob.class, R.layout.job, mJobReference) {

            @Override
            protected void populateView(View v, final NewJob model, int position) {

                ((TextView)v.findViewById(R.id.companyTextView)).setText(model.getCompanyName());
                ((TextView)v.findViewById(R.id.descriptionTextView)).setText(model.getJobText());
                ((TextView)v.findViewById(R.id.titleTextView)).setText(model.getJobTitle());
                ((TextView)v.findViewById(R.id.addressTextView)).setText(model.getState());
                System.out.println("LIST VIEW IS POPULATED");




            }
        };

        listView.setAdapter(mFirebaseAdapter);

    }





    /**
     * @param parent
     * @param view
     * @param position
     * @param id
     * Creates the dropdown for the category and the radius.
     */
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
                Log.e("MainActivity", "State Selected: " + selectedRadius);
                Toast.makeText(this, "State Selected", Toast.LENGTH_LONG ).show();
        }
    }


    public void onNothingSelected(AdapterView<?> parent){
            Toast.makeText(this, "Please select a state and select a category.", Toast.LENGTH_LONG).show();


        }


    /**
     * DisplayUserInfoSearch connects the discovery page to the display page.
     * Takes the state [radius] and category and parses through the data to display pertinent job listings.
     * @param v uses a label to connect them
     */
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

    /**
     * displayUserfaves attaches the discovery page to the favorites page.
     * This makes sure the button works when clicked.
     * @param v uses a label to connect them
     */
    public void displayUserfaves(View v)
    {
        TextView favesText = findViewById(R.id.favoritesLabel);
        favesText.setText("Favorites");

        Intent intent = new Intent(this, FavoritesPage.class);
        startActivity(intent);
    }

    /**
     * returnToHome connects the discovery page with the discovery page.
     * This makes sure the button works when clicked.
     * @param v uses a label to connect them
     */
    public void returnToHome(View v)
    {
        TextView favesText = findViewById(R.id.homeLabel);
        favesText.setText("Home");

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }







}

