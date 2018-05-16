package com.example.danramirez.afg;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class DBPopulator extends AppCompatActivity {

    GsonBuilder gb = new GsonBuilder().registerTypeAdapter(JobArray.class, new JobDeserializer());
    Gson g = gb.create();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbpopulator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        readJobData();

    }

    private void readJobData() {
        // Read data from file
        ArrayList<Job> jobs = new ArrayList<Job>();
        InputStream is = getResources().openRawResource(R.raw.jobdoc);
        Reader reader = new InputStreamReader(is);

        JobArray joblist = g.fromJson(reader, JobArray.class);

        //System.out.println("Job ArrayList size: " + joblist.getJobList().size());

        for(NewJob j : joblist.getJobList())
        {
            if(j.isEntryLevel())
            {
                DatabaseReference pushedJobListing = database.getReference().child("JobListings").child(j.getUniqID());
                pushedJobListing.setValue(j);
            }
        }
    }

}
