package com.cyrilsabbagh.noteapp.databaseControllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cyrilsabbagh.noteapp.dataModel.Course;
import com.cyrilsabbagh.noteapp.dataModel.Note;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class CreateCourse extends AsyncTask<String, String, String>{

	private static String url_create_course = "http://cyrilsabbagh.com/NoteApp/create_course.php";
	
	private Course newCourse;
	private Context context;
    private AsyncTaskCompleteListener<String> listener;
    private String result;
    JSONParser jsonParser = new JSONParser();
	
    public CreateCourse(Context ctx, AsyncTaskCompleteListener<String> listener,String _owner,String _name,String _school,String _year)
    {
        this.context = ctx;
        this.listener = listener;
        newCourse = new Course();
        newCourse.setName(_name);
        newCourse.setOwner(_owner);
        newCourse.setYear(_year);
        newCourse.setSchool(_school);
        
    }
    
    protected String doInBackground(String... args) {
        

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", newCourse.getName()));
        params.add(new BasicNameValuePair("school", newCourse.getSchool()));
        params.add(new BasicNameValuePair("user_imei", newCourse.getOwner()));
        params.add(new BasicNameValuePair("year", newCourse.getYear()));
        

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jsonParser.makeHttpRequest(url_create_course,"GET", params);

        // check log cat fro response
        Log.d("Create Response", json.toString());

        // check for success tag
        if(json!=null){
        try {
            int success = json.getInt("success");

            if (success == 1) {
                result = "success";
            } else {
                // failed to create product
            	result = "failure";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
        return result;
    }

    /**
     * After completing background task Dismiss the progress dialog
     * **/
    protected void onPostExecute(String result) {
    	super.onPostExecute(result);
        listener.onTaskComplete(result);
    }


}
