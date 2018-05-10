package com.example.danramirez.afg;

import java.util.ArrayList;

public class JobArray {

    private ArrayList<NewJob> joblist;

    public JobArray() {
        joblist = new ArrayList<NewJob>();
    }

    public JobArray(ArrayList<NewJob> list)
    {
        joblist = list;
    }


    public ArrayList<NewJob> getJobList()
    {
        return joblist;
    }

    public void setJobList(ArrayList<NewJob> list)
    {
        joblist = list;
    }

}


