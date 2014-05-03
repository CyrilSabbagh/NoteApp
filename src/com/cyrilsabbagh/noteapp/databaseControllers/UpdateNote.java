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

public class UpdateNote extends AsyncTask<String, String, String>{
	
	private static String url_update_note = "http://cyrilsabbagh.com/NoteApp/update_note.php";
	private Context context;
    private AsyncTaskCompleteListener<String> listener;
    private String result;
    private String name;
    private String content;
    private int id;
    private boolean share;
    JSONParser jsonParser = new JSONParser();
	
    public UpdateNote(Context ctx, AsyncTaskCompleteListener<String> listener,String _name,String _content,int _id,boolean _share)
    {
        this.context = ctx;
        this.listener = listener;
        name = _name;
        content = _content;
        id = _id;
        share = _share;
        
    }
    
    protected String doInBackground(String... args) {
        

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", Integer.toString(id)));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("content", content));
        params.add(new BasicNameValuePair("share", Boolean.toString(share)));
        

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jsonParser.makeHttpRequest(url_update_note,"GET", params);

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
