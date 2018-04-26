package com.example.danramirez.afg;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

/**
 * Created by danramirez on 3/28/18.
 */

public class Job implements Serializable{
    private String title;
    private String company;
    private String description;
    private String address;
    private String jobID;
    private int localID;


    public Job(){
        title = "Job Title";
        company = "Company";
        description = "Job Description";
        address = "Address";
        jobID = "Job ID";
        localID = -1;
    }

    public Job(String title, String comp, String desc, String addr, String id, int local){
        title = title;
        company = comp;
        description = desc;
        address = addr;
        jobID = id;
        localID = local;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String jobTitle) { title = jobTitle; }

    public String getDescription(){
        return description;
    }

    public void setDescription(String desc) { description = desc; }

    public String getAddress(){
        return address;
    }

    public void setAddress(String addr) { address = addr; }

    public String getCompany() { return company; }

    public void setCompany(String comp) { company = comp; }

    public String getID() { return jobID; }

    public void setID(String id) { jobID = id; }

    public int getlocalID() { return localID; }

    public void setLocalID(int id) { localID = id; }



    public String toString() {
        return "Job ID: " + jobID + "\nLocal ID: " + localID + "\nJob Title: " + title + "\nCompany: " + company + "\nDescription: " + description + "\nAddress: " + address + "\n";
    }

}
