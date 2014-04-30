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

public class AddImage extends AsyncTask<String, String, String>{
private static String url_add_image = "http://cyrilsabbagh.com/NoteApp/add_image.php";
	
	private Context context;
    private AsyncTaskCompleteListener<String> listener;
    private String result;
    JSONParser jsonParser = new JSONParser();
    private String path;
    private String note_id;
	
    public AddImage(Context ctx, AsyncTaskCompleteListener<String> listener,String _path,String _noteId)
    {
        this.context = ctx;
        this.listener = listener;
        path=_path;
        note_id=_noteId;
        
    }
    
    protected String doInBackground(String... args) {
        

        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("path", path));
        params.add(new BasicNameValuePair("note_id", note_id));
        

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jsonParser.makeHttpRequest(url_add_image,
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
