package com.example.danramirez.afg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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
        TextView favoritesText = findViewById(R.id.favoritesText);
        favoritesText.setText("Favorites");

        final Controller aController = (Controller)getApplicationContext();
        ArrayList<Job> favorites = aController.getFavorites();
        System.out.println(favorites.toString());
        JobAdapter adapter = new JobAdapter(this, favorites);
        ListView favoritesList = (ListView) findViewById(R.id.favoritesList);
        favoritesList.setAdapter(adapter);




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



    public static void addFavorite(CompoundButton buttonView, ArrayList<Job> favorites, Job j){
        favorites.add(j);
    }

    public static void removeFavorite(CompoundButton buttonView, ArrayList<Job> favorites, Job j){
        favorites.remove(j);
    }


}
