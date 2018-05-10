package com.example.danramirez.afg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.ArrayList;



/**
 * Created by Anna Hazelwood on 4/18/2018.
 */

public class JobAdapter extends ArrayAdapter<NewJob> {
    public JobAdapter(Context context, ArrayList<NewJob> jobs){
        super(context, 0, jobs);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        //Get the data item for this position
        final NewJob njob = getItem(position);
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
        titleTextView.setText(njob.getJobTitle());
        companyTextView.setText(njob.getCompanyName());
        descriptionTextView.setText(njob.getJobText());
        addressTextView.setText(njob.getJobLocation());
        System.out.println("LIST VIEW POPULATED");



        final Controller aController = (Controller)getContext().getApplicationContext();

       /*
        if(aController==null){
            System.out.println("CONTROLLER OBJECT NULL");
        }
        */

        // Code adapted from https://stackoverflow.com/questions/34980309/favourite-button-android

        final ToggleButton favoriteToggle = (ToggleButton) convertView.findViewById(R.id.addToFavorites);
        favoriteToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){


                if(isChecked) {


                    System.out.println("JOB TOGGLE CHECKED");
                   aController.getFavorites().add(njob);

                   System.out.println(aController.getFavorites().toString());
                    //favoriteToggle.setChecked(isChecked);


                }
                else{

                    System.out.println("JOB TOGGLE UNCHECKED");

                    System.out.println(aController.getFavorites().toString());
                    aController.getFavorites().remove(njob);

                }


            }





        });








        //Return the completed view to render on screen

        return convertView;



    }





}
