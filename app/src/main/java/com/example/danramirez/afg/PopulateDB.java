package com.example.danramirez.afg;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
