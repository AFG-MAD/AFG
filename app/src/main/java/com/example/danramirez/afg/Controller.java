package com.example.danramirez.afg;

/**
 * Created by Anna Hazelwood on 5/9/2018.
 */

import android.app.Application;

import java.util.ArrayList;

public class Controller extends Application {

    private ArrayList<NewJob> favorites = new ArrayList<NewJob>();

    public ArrayList<NewJob> getFavorites(){
        return favorites;
    }

}
