package com.example.danramirez.afg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/*
 * Created by Marissa Langille on 3/27/18
 */

public class DisplayPage extends AppCompatActivity
{
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseListAdapter<Job> mFirebaseAdapter;
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
        String zipStr = bundle.getString("zip");

        System.out.println(category);
        System.out.println(radius);
        System.out.println(zipStr);

        TextView textResultsHeading = findViewById(R.id.textResultsHeading);
        textResultsHeading.setText("Results for " + category + " jobs within " + radius + " of " + zipStr);

        setUpFirebaseAdapter(displayList);
    }

    private void setUpFirebaseAdapter(ListView listView) {
        mFirebaseAdapter = new FirebaseListAdapter<Job>(this, Job.class, R.layout.job, mJobReference) {
            @Override
            protected void populateView(View v, Job model, int position) {
                ((TextView)v.findViewById(R.id.companyTextView)).setText(model.getCompany());
                ((TextView)v.findViewById(R.id.descriptionTextView)).setText(model.getDescription());
                ((TextView)v.findViewById(R.id.titleTextView)).setText(model.getTitle());
                ((TextView)v.findViewById(R.id.addressTextView)).setText(model.getAddress());
            }
        };
        listView.setAdapter(mFirebaseAdapter);
    }


}
