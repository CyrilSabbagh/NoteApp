/** 
 * 
 * Use This class to get all the info about a Note
 * This class will return a Note object from a given id
 * 
 * Use this class if you want to get the images : result.getImages()
 * Use this class if you want to get the sounds : result.getSounds()
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

public class GetNote extends AsyncTask<String, String, Note>{
	
	private static String url_get_Note_detail = "http://cyrilsabbagh.com/NoteApp/get_note_details.php";
	private Context context;
    private AsyncTaskCompleteListener<Note> listener;
    int id;
    JSONArray notes = null;
    JSONArray pics = null;
    JSONArray sounds = null;
    
	public GetNote(Context ctx, AsyncTaskCompleteListener<Note> listener,int _id)
    {
        this.context = ctx;
        this.listener = listener;
        this.id = _id;
        
    }
	
	@Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
	
	@Override
	protected Note doInBackground(String... params) {
		// Building Parameters
				JSONParser jParser = new JSONParser();
		        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		        params1.add(new BasicNameValuePair("id", Integer.toString(id)));
		        // getting JSON string from URL
		        JSONObject json = jParser.makeHttpRequest(url_get_Note_detail, "GET", params1);
		        Note result = new Note();
		        // Check your log cat for JSON reponse
		        //Log.d("user Courses: ", json.toString());
		        
		        try {
		            // Checking for SUCCESS TAG
		            int success = json.getInt("success");

		            if (success == 1) {
		                // products found
		                // Getting the Note
		                notes = json.getJSONArray("note");
		                JSONObject c = notes.getJSONObject(0);
		                result.setId(c.getInt("id"));
		                result.setName(c.getString("name"));
	                    result.setOwner(c.getString("user_imei"));
	                    result.setContent(c.getString("content"));
	                    result.setDate(c.getString("date"));
	                    if(c.getInt("share") == 1)
	                    	result.setShare(true);
	                    else result.setShare(false);
		                
		                
		                pics = json.getJSONArray("images");
		                // looping through All Images
		                for (int i = 0; i < pics.length(); i++) {
		                    JSONObject d = pics.getJSONObject(i);
		       
		                    // Storing each json item in variable
		                    result.getImages().add(d.getString("path"));
		                    
		                    
		                }
		                sounds = json.getJSONArray("sounds");
		                for (int i = 0; i < sounds.length(); i++) {
		                    JSONObject d = sounds.getJSONObject(i);
		       
		                    // Storing each json item in variable
		                    result.getSounds().add(d.getString("path"));
		                  
		                }
		            } else {
		               
		            	Log.e("user Courses: ", "Can't get course details");
		            }
		        } catch (JSONException e) {
		            e.printStackTrace();
		        }
		        
				return result;
			}
			
			 @Override
			    protected void onPostExecute(Note result)
			    {
			        super.onPostExecute(result);
			        listener.onTaskComplete(result);
			    }

}
