package com.example.danramirez.afg;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by danramirez on 3/28/18.
 */

public class Job {
    private String jobTitle;
    private String company;
    private String jobDescription;
    private String address;
    private String jobID;
    private int localID;


    public Job(){
        jobTitle = "Job Title";
        company = "Company";
        jobDescription = "Job Description";
        address = "Address";
        jobID = "Job ID";
        localID = -1;
    }

    public Job(String title, String comp, String desc, String addr, String id, int local){
        jobTitle = title;
        company = comp;
        jobDescription = desc;
        address = addr;
        jobID = id;
        localID = local;
    }

    public String getTitle(){
        return jobTitle;
    }

    public String getDescription(){
        return jobDescription;
    }

    public String getAddress(){
        return address;
    }

    public String getCompany() { return company; }

    public String getID() { return jobID; }

    public int getlocalID() { return localID; }

    public void setLocalID(int id) { localID = id; }

    public void setID(String id) { jobID = id; }

    public String toString() {
        return "Job ID: " + jobID + "\nLocal ID: " + localID + "\nJob Title: " + jobTitle + "\nCompany: " + company + "\nDescription: " + jobDescription + "\nAddress: " + address + "\n";
    }

}
