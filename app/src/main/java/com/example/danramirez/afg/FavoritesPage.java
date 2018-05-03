package com.example.danramirez.afg;

import android.content.Intent;
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
        TextView favoritesText = findViewById(R.id.favoritesText);
        favoritesText.setText("Favorites");
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



}
