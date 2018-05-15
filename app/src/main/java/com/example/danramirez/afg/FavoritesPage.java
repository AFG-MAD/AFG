package com.example.danramirez.afg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * Created by Marissa Langille on 3/27/18
 * Intended to display the job listings the user "adds" via the Add button
 */

public class FavoritesPage extends AppCompatActivity
{
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        //get the intent that started this activity and extract the data
        Bundle bundle = getIntent().getExtras();
        TextView favoritesText = findViewById(R.id.favoritesText);
        favoritesText.setText("Favorites");

        final Controller aController = (Controller)getApplicationContext();
        ArrayList<NewJob> favorites = aController.getFavorites();
        Log.e("FavoritesPage", "Favorites Array List: " + favorites);
        FavoritesAdapter adapter = new FavoritesAdapter(this, favorites);
        ListView favoritesList = (ListView) findViewById(R.id.favoritesList);
        favoritesList.setAdapter(adapter);




    }

    /**
     * displayUserfaves attaches the favorites page to the favorites page.
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
     * returnToHome connects the favorites page with the discovery page.
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
