package com.example.danramirez.afg;

/**
 * Created by danramirez on 3/28/18.
 */

public class Job {
    private String jobTitle;
    private String company;
    private String jobDescription;
    private String address;


    public Job(){
        jobTitle = "Job Title";
        company = "Company";
        jobDescription = "Job Description";
        address = "Address";
    }

    public Job(String title, String comp, String desc, String addr){
        jobTitle = title;
        company = comp;
        jobDescription = desc;
        address = addr;
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
}
