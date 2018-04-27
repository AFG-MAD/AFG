package com.example.danramirez.afg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/*
 * Created by Marissa Langille on 3/27/18
 */

public class FavoritesPage extends AppCompatActivity
{
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        //get the intent that started this activity and extract the data
        Bundle bundle = getIntent().getExtras();

        //Place favorites in the list
        String zipStr = bundle.getString("favorites");

        TextView textResultsHeading = findViewById(R.id.favoritesText);
        textResultsHeading.setText("Favorites");
    }


    public void returnToHome(View v){
        finish();
    }




}
