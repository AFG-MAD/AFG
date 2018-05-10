package com.example.danramirez.afg;

/**
 * Created by Anna Hazelwood on 5/9/2018.
 */

import android.app.Application;

import java.util.ArrayList;

public class Controller extends Application {

    private ArrayList<Job> favorites = new ArrayList<Job>();

    public ArrayList<Job> getFavorites(){
        return favorites;
    }

}
