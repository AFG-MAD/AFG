package com.example.danramirez.afg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class FavoritesPage extends AppCompatActivity
{
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        //get the intent that started this activity and extract the data
        Bundle bundle = getIntent().getExtras();

        //Place favorites in the list
    }


}
