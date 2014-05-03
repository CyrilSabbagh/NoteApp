/** 
 * 
 * Use This class to get all the courses for the user
 * This class returns an arrayList of Courses
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

import com.cyrilsabbagh.noteapp.dataModel.Course;

import android.content.Context;
//import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class GetUserCourses extends AsyncTask<String, String, List<Course>>{
	//ProgressDialog pDialog;
	private static String url_all_products = "http://cyrilsabbagh.com/NoteApp/get_user_courses.php";
	private String user_imei;
	private List<Course> result;
	JSONArray courses = null;
	private boolean requestError;

	/**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        pDialog = new ProgressDialog(AllProductsActivity.this);
//        pDialog.setMessage("Loading products. Please wait...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(false);
//        pDialog.show();
    }
	
	
    private Context context;
    private AsyncTaskCompleteListener<List<Course>> listener;
 
    public GetUserCourses(Context ctx, AsyncTaskCompleteListener<List<Course>> listener,String _userimei)
    {
        this.context = ctx;
        this.listener = listener;
        this.user_imei = _userimei;
        
    }
    
    
	
	@Override
	protected List<Course> doInBackground(String... arg0) {

		// Building Parameters
		JSONParser jParser = new JSONParser();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("user_imei", user_imei));
        // getting JSON string from URL
        JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
        result=new ArrayList<Course>();
        // Check your log cat for JSON reponse
        //Log.d("user Courses: ", json.toString());
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
                    
//                    String id = c.getString(TAG_PID);
//                    String name = c.getString(TAG_NAME);

                    // adding course to ArrayList
                    result.add(tmp_course);
                    requestError=false;
                }
            } else {
                // no products found
                // Launch Add New product Activity
//                Intent i = new Intent(getApplicationContext(),
//                        NewProductActivity.class);
                // Closing all previous activities
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
            	Log.e("user Courses: ", "Can't get user courses");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
		return result;
	}
	
	 @Override
	    protected void onPostExecute(List<Course> result)
	    {
	        super.onPostExecute(result);
	        listener.onTaskComplete(result);
	    }

	public List<Course> getResult() {
		return result;
	}

	public void setResult(List<Course> result) {
		this.result = result;
	}

	public boolean isRequestError() {
		return requestError;
	}

	public void setRequestError(boolean requestError) {
		this.requestError = requestError;
	}

}
