package com.example.danramirez.afg;

import java.io.Serializable;

public class NewJob implements Serializable {

    private String uniqID;
    private String crawlTimestamp;
    private String url;
    private String companyName;
    private String geo;
    private String jobTitle;
    private String jobLocation;
    private String jobText;
    private String uploadTimestamp;
    private String state;
    private boolean loc;
    private String[] states = {", AK", ", AL", ", AR", ", AZ", ", CA", ", CO", ", CT", ", DC", ", DE", ", FL", ", GA", ", HI", ", IA", ", ID", ", IL", ", IN", ", KS", ", KY", ", LA", ", MA", ", MD", ", ME", ", MI", ", MN", ", MO", ", MS", ", MT", ", NC", ", ND", ", NE", ", NH", ", NJ", ", NM", ", NV", ", NY", ", OH", ", OK", ", OR", ", PA", ", RI", ", SC", ", SD", ", TN", ", TX", ", UT", ", VA", ", VT", ", WA", ", WI", ", WV", ", WY"};
    private String[] entryLevelStrings = {"part time", "years of relevant work experience: 0", "years of relevant work experience: 1"};


    public NewJob () {
        uniqID = "ID";
        crawlTimestamp = "Crawl timestamp";
        url = "url";
        companyName = "Company";
        geo = "Country";
        jobTitle = "Job Title";
        jobLocation = "Job Location";
        jobText = "Description";
        uploadTimestamp = "Upload timestamp";
        state = "State";

    }

    public NewJob(String id, String crawlStamp, String link, String company, String country, String title, String location, String text, String uploadStamp) {
        uniqID = id;
        crawlTimestamp = crawlStamp;
        url = link;
        companyName = company;
        geo = country;
        jobTitle = title;
        jobLocation = location;
        jobText = text;
        uploadTimestamp = uploadStamp;
        for(String s : states)
        {
            if(location.contains(s))
            {
                state = s;
            }
        }

    }


    public void setUniqID(String id)
    {
        uniqID = id;
    }

    public void setCrawlTimestamp(String stamp)
    {
        crawlTimestamp =  stamp;
    }

    public void setUrl(String link)
    {
        url = link;
    }

    public void setCompanyName(String name)
    {
        companyName = name;
    }

    public void setGeo(String g)
    {
        geo = g;
    }

    public void setJobTitle(String title)
    {
        jobTitle = title;
    }

    public void setJobLocation(String loc)
    {
        jobLocation = loc;
    }

    public void setJobText(String text)
    {
        jobText = text;
    }

    public void setUploadTimestamp(String stamp)
    {
        uploadTimestamp = stamp;
    }

    public void setState(String st)
    {
        state = st;
    }


    public String getCompanyName() {
        return companyName;
    }

    public String getCrawlTimestamp() {
        return crawlTimestamp;
    }

    public String getGeo() {
        return geo;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public String getJobText() {
        return jobText;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getUniqID() {
        return uniqID;
    }

    public String getUploadTimestamp() {
        return uploadTimestamp;
    }

    public String getUrl() {
        return url;
    }

    public String toString(){
        return "Unique id: " + uniqID + "\nURL: " + url + "\nCompany Name: " + companyName + "\nCountry: " + geo + "\nJob Title: " + jobTitle + "\nLocation: " + jobLocation +"\nJob Description: " + jobText;
    }

    public String getState() {
        return state;
    }


    public boolean isEntryLevel() {
        String desc = jobText.toLowerCase();
        if (desc.contains(entryLevelStrings[0]) || desc.contains(entryLevelStrings[1]) || desc.contains(entryLevelStrings[2]))
        {
            return true;
        }
        else return false;
    }
}

