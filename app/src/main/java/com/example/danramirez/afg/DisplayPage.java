package com.example.danramirez.afg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/*
 * Created by Marissa Langille on 3/27/18
 * Intended to display the job lists taken from the data base. These jobs are selected based on the state and category selected.
 */

public class DisplayPage extends AppCompatActivity
{
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseListAdapter<NewJob> mFirebaseAdapter;
    private DatabaseReference mJobReference = database.getReference().child("JobListings");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        //Get the intent that started this display and extract the data
        Bundle bundle = getIntent().getExtras();



        ArrayList<Job> jobs = (ArrayList<Job>) getIntent().getSerializableExtra("jobs");

        ListView displayList = (ListView) findViewById(R.id.displayListView);


        //Create heading title saying Results for "engineering" within 30 miles of 20305
        //Need to make a getSubject from the drop down and a getRadius and a getZip, this will come from the mainactivity


        String category = bundle.getString("category");
        String radius = bundle.getString("radius");
        System.out.println(category);
        System.out.println(radius);

        TextView textResultsHeading = findViewById(R.id.textResultsHeading);
        textResultsHeading.setText("Results for " + category + " related jobs in " + radius);

        setUpFirebaseAdapter(displayList);
    }

    /**
     *
     * @param listView this displays the jobs
     */
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

    /**
     * displayUserfaves attaches the display page to the favorites page.
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
     * returnToHome connects the display page with the discovery page.
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
