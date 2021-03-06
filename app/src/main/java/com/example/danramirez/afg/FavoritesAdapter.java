package com.example.danramirez.afg;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by Anna Hazelwood on 5/10/2018.
 */

public class FavoritesAdapter extends ArrayAdapter<NewJob> {

    public FavoritesAdapter(Context context, ArrayList<NewJob> favorites){
        super(context, 0, favorites);



    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //Get the data item for this position
        final NewJob job = getItem(position);
        //Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.favlist, parent, false);

        }
        //Lookup view for data population


        TextView titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
        TextView companyTextView = (TextView) convertView.findViewById(R.id.companyTextView);
        TextView descriptionTextView = (TextView) convertView.findViewById(R.id.descriptionTextView);
        TextView addressTextView = (TextView) convertView.findViewById(R.id.addressTextView);

        //Populate the data into the template view using the data object
        titleTextView.setText(job.getJobTitle());
        companyTextView.setText(job.getCompanyName());
        descriptionTextView.setText(job.getJobText());
        addressTextView.setText(job.getJobLocation());





        final Controller aController = (Controller)getContext().getApplicationContext();
        if(aController==null){
            System.out.println("Object Null");
        }


        // Code adapted from https://stackoverflow.com/questions/34980309/favourite-button-android

        final Button removeButton = (Button) convertView.findViewById(R.id.removeButton);
        removeButton.setTag(position);
        removeButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View convertView){

                    System.out.println("FAVORITES TOGGLE CHECKED");
                   NewJob job = getItem(position);


                    aController.getFavorites().remove(job);
                    notifyDataSetChanged();
                    System.out.println(aController.getFavorites().toString());





            }





        });



        return convertView;
    }
}
