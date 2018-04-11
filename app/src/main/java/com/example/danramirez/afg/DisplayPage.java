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

        //Create heading title saying Results for "engineering" within 30 miles of 20305

        String category = bundle.getString("category");
        String radius = bundle.getString("radius");
        String zipStr = bundle.getString("zip");

        TextView textResultsHeading = findViewById(R.id.textResultsHeading);
        textResultsHeading.setText("Results for " + category + " jobs within " + radius + " miles of " + zipStr);



    }


}
