package com.example.danramirez.afg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayPage extends AppCompatActivity
{
    @Override
            protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        //Get the intent that started this display and extract the data
        Bundle bundle = getIntent().getExtras();

       /* String subject = bundle.getSubject("subject");
        int radius = bundle.getRadius("radius");
        int zipcode = bundle.getZip("zipcode");

        TextView textResults = findViewById(R.id.textResultsHeading);
        textResults.setText("Results for " + subject + " within " + radius + " of " + zipcode);*/
    }


}
