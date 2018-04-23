package com.example.danramirez.afg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anna Hazelwood on 4/18/2018.
 */

public class JobAdapter extends ArrayAdapter<Job> {
    public JobAdapter(Context context, ArrayList<Job> jobs){
        super(context, 0, jobs);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Get the data item for this position
        Job job = getItem(position);
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

        //Return the completed view to render on screen

        return convertView;


    }




}