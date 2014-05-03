/** 
 * 
 * Use This class to get all the courses and note found after a search and store them in a SearchResult object
 * This class returns a SearchResult Object
 * This object contains an arrayList of courses and an arrayList of Notes
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

import com.cyrilsabbagh.noteapp.dataModel.Course;
import com.cyrilsabbagh.noteapp.dataModel.Note;
import com.cyrilsabbagh.noteapp.dataModel.SearchResult;

public class SearchDB extends AsyncTask<String, String, SearchResult>{
	private static String search_db_url = "http://cyrilsabbagh.com/NoteApp/search_db.php";
	private String keywords;
	private SearchResult result;
	JSONArray courses = null;
	JSONArray notes = null;
	private boolean requestError;

	/**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
	
	
    private Context context;
    private AsyncTaskCompleteListener<SearchResult> listener;
 
    public SearchDB(Context ctx, AsyncTaskCompleteListener<SearchResult> listener,String _keywords)
    {
        this.context = ctx;
        this.listener = listener;
        this.keywords = _keywords;
        
    }
    
    
	
	@Override
	protected SearchResult doInBackground(String... arg0) {

		// Building Parameters
		JSONParser jParser = new JSONParser();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("keywords", keywords));
        // getting JSON string from URL
        JSONObject json = jParser.makeHttpRequest(search_db_url, "GET", params);
        result=new SearchResult();
       
        if(json!=null){
        try {
            // Checking for SUCCESS TAG
            int success = json.getInt("success");

            if (success == 1) {
                // products found
                // Getting Array of Products
                courses = json.getJSONArray("courses");
                
                // looping through All Products
                for (int i = 0; i < courses.length(); i++) {
                    JSONObject c = courses.getJSONObject(i);
                    Course tmp_course = new Course();
                    // Storing each json item in variable
                    tmp_course.setId(c.getInt("id"));
                    tmp_course.setName(c.getString("name"));
                    tmp_course.setSchool(c.getString("school"));
                    tmp_course.setYear(c.getString("year"));
                    
                    // adding course to ArrayList
                    result.getCoursesResults().add(tmp_course);
                    requestError=false;
                    
                }
                
                notes = json.getJSONArray("notes");
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
                    result.getNotesResults().add(tmp_note);
                    requestError=false;
                }
            } else {
               
            	Log.e("SearchDB: ", "No result found");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
		return result;
	}
	
	 @Override
	    protected void onPostExecute(SearchResult result)
	    {
	        super.onPostExecute(result);
	        listener.onTaskComplete(result);
	    }
}
