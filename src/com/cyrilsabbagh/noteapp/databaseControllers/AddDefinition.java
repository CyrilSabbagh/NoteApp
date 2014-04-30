package com.cyrilsabbagh.noteapp.databaseControllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class AddDefinition extends AsyncTask<String, String, String>{
private static String url_add_dictionary = "http://cyrilsabbagh.com/NoteApp/add_dictionary.php";
	
	private Context context;
    private AsyncTaskCompleteListener<String> listener;
    private String result;
    JSONParser jsonParser = new JSONParser();
    private String name;
    private String def;
    private String course_id;
	
    public AddDefinition(Context ctx, AsyncTaskCompleteListener<String> listener,String _courseId,String _name,String _def)
    {
        this.context = ctx;
        this.listener = listener;
        name=_name;
        def=_def;
        course_id=_courseId;
        
    }
    
    protected String doInBackground(String... args) {
        

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("definition", def));
        params.add(new BasicNameValuePair("course_id", course_id));
        

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jsonParser.makeHttpRequest(url_add_dictionary,
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
