package com.example.danramirez.afg;

/**
 * Created by danramirez on 3/28/18.
 */

public class Job {
    private String jobTitle;
    private String company;
    private String jobDescription;
    private String address;
    private int jobID;


    public Job(){
        jobTitle = "Job Title";
        company = "Company";
        jobDescription = "Job Description";
        address = "Address";
        jobID = -1;
    }

    public Job(String title, String comp, String desc, String addr, int id){
        jobTitle = title;
        company = comp;
        jobDescription = desc;
        address = addr;
        jobID = id;
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

    public int getID() { return jobID; }
}
