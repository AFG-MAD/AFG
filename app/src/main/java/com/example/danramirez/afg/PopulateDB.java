package com.example.danramirez.afg;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;

/**
 * Created by danramirez on 3/28/18.
 */

public class PopulateDB {

    public static void main(String[] args)
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("JobListing").setValue("Hello, World!");
    }



}
