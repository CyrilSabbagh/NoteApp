/** 
 * 
 * Use This class to create a new Note
 * This class will return a String : "success" or "failure"
 * 
 * Pass all the needed arguments to the constructor!!
 * 
 * 
 * **/



package com.cyrilsabbagh.noteapp.databaseControllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.cyrilsabbagh.noteapp.dataModel.Note;

public class CreateNote extends AsyncTask<String, String, String>{
	
	private static String url_create_note = "http://cyrilsabbagh.com/NoteApp/create_note.php";
	
	private Note newNote;
	private Context context;
    private AsyncTaskCompleteListener<String> listener;
    private String result;
    JSONParser jsonParser = new JSONParser();
	
    public CreateNote(Context ctx, AsyncTaskCompleteListener<String> listener,String _owner,String _name,String _content,String date,float lat,float lng,int _courseId,boolean share)
    {
        this.context = ctx;
        this.listener = listener;
        newNote = new Note();
        newNote.setName(_name);
        newNote.setContent(_content);
        newNote.setOwner(_owner);
        newNote.setDate(date);
        newNote.setLat(lat);
        newNote.setLng(lng);
        newNote.setCourse_id(_courseId);
        newNote.setShare(share);
        
    }
    
    protected String doInBackground(String... args) {
        

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", newNote.getName()));
        params.add(new BasicNameValuePair("content", newNote.getContent()));
        params.add(new BasicNameValuePair("user_imei", newNote.getOwner()));
        params.add(new BasicNameValuePair("date", newNote.getDate()));
        params.add(new BasicNameValuePair("lat", Float.toString(newNote.getLat())));
        params.add(new BasicNameValuePair("lng", Float.toString(newNote.getLng())));
        params.add(new BasicNameValuePair("course_id", Integer.toString(newNote.getCourse_id())));
        params.add(new BasicNameValuePair("share", Boolean.toString(newNote.isShare())));
        

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jsonParser.makeHttpRequest(url_create_note,
                "POST", params);

        // check log cat fro response
        Log.d("Create Response", json.toString());

        // check for success tag
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
