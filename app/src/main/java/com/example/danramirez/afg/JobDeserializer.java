package com.example.danramirez.afg;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JobDeserializer implements JsonDeserializer<JobArray> {


    public JobArray deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException
    {
        final JsonObject jo = json.getAsJsonObject();
        final JsonArray ja = jo.get("root").getAsJsonObject().get("page").getAsJsonArray();
        ArrayList<NewJob> list = new ArrayList<NewJob>();

        for(JsonElement je : ja)
        {
            JsonElement id = je.getAsJsonObject().get("uniq_id");
            JsonElement crawlStamp = je.getAsJsonObject().get("crawl_timestamp");
            JsonElement url = je.getAsJsonObject().get("url");
            JsonElement company = je.getAsJsonObject().get("company_name");
            JsonElement country = je.getAsJsonObject().get("geo");
            JsonElement title = je.getAsJsonObject().get("JobTitle");
            JsonElement location = je.getAsJsonObject().get("JobLocation");
            JsonElement text = je.getAsJsonObject().get("JobText");
            JsonElement uploadStamp = je.getAsJsonObject().get("upload_timestamp");

            if(id != null && crawlStamp != null && url != null && company != null && country != null && title != null && location != null && text != null && uploadStamp != null)
            {
                NewJob job = new NewJob(id.getAsString(), crawlStamp.getAsString(), url.getAsString(), company.getAsString(), country.getAsString(), title.getAsString(), location.getAsString(), text.getAsString(), uploadStamp.getAsString());
                list.add(job);
            }

        }

        JobArray joblist = new JobArray(list);
        return joblist;


    }
}
