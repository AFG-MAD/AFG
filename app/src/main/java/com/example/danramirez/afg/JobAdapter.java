package com.example.danramirez.afg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.app.Application;

import java.util.ArrayList;

import static com.example.danramirez.afg.FavoritesPage.addFavorite;
import static com.example.danramirez.afg.FavoritesPage.removeFavorite;

/**
 * Created by Anna Hazelwood on 4/18/2018.
 */

//Used tutorial from https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView

public class JobAdapter extends ArrayAdapter<Job> {

    public JobAdapter(Context context, ArrayList<Job> jobs){
        super(context, 0, jobs);
        final ArrayList<Job> favorites = new ArrayList<Job>();

    }

    /*
    public ArrayList<Job> getArrayList (){

        return favorites;
    }
    */

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        //Get the data item for this position
        final Job job = getItem(position);

        //Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.job, parent, false);

        }
        //Lookup view for data population


        TextView titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
        TextView companyTextView = (TextView) convertView.findViewById(R.id.companyTextView);
        TextView descriptionTextView = (TextView) convertView.findViewById(R.id.descriptionTextView);
        TextView addressTextView = (TextView) convertView.findViewById(R.id.addressTextView);

        //Populate the data into the template view using the data object
        titleTextView.setText(job.getTitle());
        companyTextView.setText(job.getCompany());
        descriptionTextView.setText(job.getDescription());
        addressTextView.setText(job.getAddress());



        final Controller aController = (Controller)getContext().getApplicationContext();
        if(aController==null){
            System.out.println("Object Null");
        }

        //final ArrayList<Job> favorites = new ArrayList<Job>();
        // Code adapted from https://stackoverflow.com/questions/34980309/favourite-button-android
        //final Controller aController = new Controller();
        final ToggleButton favoriteToggle = (ToggleButton) convertView.findViewById(R.id.addToFavorites);
        favoriteToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){




                if(isChecked) {
                   /*

                   System.out.println(buttonView);
                    addFavorite(buttonView, favorites, job);
                    System.out.println(favorites.toString());
                    */
                    favoriteToggle.setChecked(isChecked);
                    System.out.println("JOB TOGGLE CHECKED");
                   aController.getFavorites().add(job);

                   System.out.println(aController.getFavorites().toString());

                  /*
                   Intent intent = new Intent(JobAdapter.this, FavoritesPage.class);
                   startActivity(intent);
                   */

                }
                else{

                    System.out.println("JOB TOGGLE UNCHECKED");
                    aController.getFavorites().remove(job);
                    System.out.println(aController.getFavorites().toString());


                    /*

                    removeFavorite(buttonView, favorites, job);
                    */
                }


            }





        });








        //Return the completed view to render on screen

        return convertView;



    }







}
