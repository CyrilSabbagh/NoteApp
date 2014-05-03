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

import com.cyrilsabbagh.noteapp.dataModel.Course;
import com.cyrilsabbagh.noteapp.dataModel.Note;

public class GetLatestNotes extends AsyncTask<String, String, List<Note>>{
	
	private static String url_course_notes = "http://cyrilsabbagh.com/NoteApp/get_course_notes.php";
	private String course_id;
	private List<Note> result;
	JSONArray notes = null;
	private boolean requestError;
	private Context context;
    private AsyncTaskCompleteListener<List<Note>> listener;

    public GetLatestNotes(Context ctx, AsyncTaskCompleteListener<List<Note>> listener,String _courseId)
    {
        this.context = ctx;
        this.listener = listener;
        this.course_id = _courseId;
        
    }
    
    @Override
	protected List<Note> doInBackground(String... arg0) {

		// Building Parameters
		JSONParser jParser = new JSONParser();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("course_id", course_id));
        // getting JSON string from URL
        JSONObject json = jParser.makeHttpRequest(url_course_notes, "GET", params);
        result=new ArrayList<Note>();
        // Check your log cat for JSON reponse
        //Log.d("user Courses: ", json.toString());
        if(json!=null){
        try {
            // Checking for SUCCESS TAG
            int success = json.getInt("success");

            if (success == 1) {
                // products found
                // Getting Array of Products
                notes = json.getJSONArray("courses");
                
                // looping through All Products
                for (int i = 0; i < notes.length(); i++) {
                    JSONObject c = notes.getJSONObject(i);
                    Note tmp_note = new Note();
                    // Storing each json item in variable
                    tmp_note.setId(c.getInt("id"));
                    tmp_note.setName(c.getString("name"));
                    tmp_note.setOwner(c.getString("user_imei"));
                    tmp_note.setContent(c.getString("content"));
                    tmp_note.setDate(c.getString("date"));
                    if(c.getInt("share") == 1)
                    	tmp_note.setShare(true);
                    else tmp_note.setShare(false);
                  

                    // adding course to ArrayList
                    result.add(tmp_note);
                    requestError=false;
                }
            } else {
                
            	Log.e("user Courses: ", "Can't get latest notes");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
		return result;
	}
	
	 @Override
	    protected void onPostExecute(List<Note> result)
	    {
	        super.onPostExecute(result);
	        listener.onTaskComplete(result);
	    }

}