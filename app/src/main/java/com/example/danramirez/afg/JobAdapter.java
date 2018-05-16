package com.example.danramirez.afg;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;



/**
 * Created by Anna Hazelwood on 4/18/2018.
 */

public class JobAdapter extends ArrayAdapter<NewJob> {
    private Context context;
    private int resource;
    private ArrayList arrayList;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private Query mJobReference = database.getReference().child("JobListings").limitToFirst(10);



    public JobAdapter(Context context, ArrayList<NewJob> jobs){
        super(context, 0, jobs);

    }


    //public JobAdapter(Context context, int resource, ArrayList arrayList){

        /*
        super(context, resource, arrayList);
        this.context = context;
        this.resource = resource;
        this.arrayList = arrayList;
    }

    private static class ViewHolder{
        TextView titleTextView;
        TextView companyTextView;
        TextView descriptionTextView;
        TextView addressTextView;
        Button addToFavorites;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = ((MainActivity) context).getLayoutInflater();
            view = inflater.inflate(resource, parent, false);
            //viewHolder.addToFavorites = (Button) view.findViewById(R.id.addToFavorites);
            view.setTag(viewHolder);

        }
        else{
            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final NewJob job = (NewJob) arrayList.get(position);

        viewHolder.titleTextView.setText(job.getJobTitle());
        viewHolder.companyTextView.setText(job.getCompanyName());
        viewHolder.descriptionTextView.setText(job.getJobText());
        viewHolder.addressTextView.setText(job.getJobLocation());

        viewHolder.addToFavorites.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.e("JobAdapter", "BUTTON CLICKED");

            }

        });



        return view;

        */



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
        Log.e("JobAdapter", "List View Populated" );
       // System.out.println("LIST VIEW POPULATED");





        final Controller aController = (Controller)getContext().getApplicationContext();


        if(aController==null){
            System.out.println("CONTROLLER OBJECT NULL");
        }

        // Code adapted from https://stackoverflow.com/questions/34980309/favourite-button-android

            /*
        final ToggleButton favoriteToggle = (ToggleButton) convertView.findViewById(R.id.addToFavorites);
        favoriteToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){


                if(isChecked) {

                    System.out.println("JOB TOGGLE CHECKED");
                    System.out.println("ADD JOB: " + njob.toString());
                    Log.e("JobAdapter", "Job Toggle Checked" + "\nAdd Job: " + njob.toString());
                   aController.getFavorites().add(njob);
                   System.out.println("Favorited: " + njob.getUniqID() + njob.getJobTitle());

                   System.out.println(aController.getFavorites().toString());
                    //favoriteToggle.setChecked(true);


                }
                else{

                    System.out.println("JOB TOGGLE UNCHECKED");
                    Log.e("JobAdapter", "Job Toggle Unchecked");
                    System.out.println(aController.getFavorites().toString());
                    //aController.getFavorites().remove(njob);

                }


            }





        });
        */

        //Return the completed view to render on screen

        return convertView;










    }



}
