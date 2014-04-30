/** 
 * 
 * Use This class to get all the info about a course and his dictionary
 * This class will return a Course object from a given id
 * 
 * Use this class if you want to get the dictionary : result.getDictionary()
 * 
 * 
 * **/

package com.cyrilsabbagh.noteapp.databaseControllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cyrilsabbagh.noteapp.dataModel.Course;
import com.cyrilsabbagh.noteapp.dataModel.Dictionary;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class GetCourseDetails extends AsyncTask<String, String, Course>{
	
	
	private static String url_get_Course_detail = "http://cyrilsabbagh.com/NoteApp/get_course_details.php";
	private Context context;
    private AsyncTaskCompleteListener<Course> listener;
    int id;
    JSONArray courses = null;
    JSONArray dic = null;
    
	public GetCourseDetails(Context ctx, AsyncTaskCompleteListener<Course> listener,int _id)
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
	protected Course doInBackground(String... params) {
		// Building Parameters
				JSONParser jParser = new JSONParser();
		        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		        params1.add(new BasicNameValuePair("id", Integer.toString(id)));
		        // getting JSON string from URL
		        JSONObject json = jParser.makeHttpRequest(url_get_Course_detail, "GET", params1);
		        Course result = new Course();
		        // Check your log cat for JSON reponse
		        //Log.d("user Courses: ", json.toString());
		        
		        try {
		            // Checking for SUCCESS TAG
		            int success = json.getInt("success");

		            if (success == 1) {
		                // products found
		                // Getting Array of Products
		                courses = json.getJSONArray("courses");
		                JSONObject c = courses.getJSONObject(0);
		                result.setId(c.getInt("id"));
		                result.setName(c.getString("name"));
		                result.setSchool(c.getString("school"));
		                result.setYear(c.getString("year"));
		                
		                Dictionary dictionary = new Dictionary();
		                Map<String,String> def = new HashMap<String,String>();
		                dic = json.getJSONArray("dictionary");
		                
		                // looping through All Products
		                for (int i = 0; i < dic.length(); i++) {
		                    JSONObject d = dic.getJSONObject(i);
		       
		                    // Storing each json item in variable
		                    def.put(d.getString("name"), c.getString("definition"));
		                    
		                    
		                }
		                dictionary.setDefinition(def);
		                result.setDictionary(dictionary);
		            } else {
		                // no products found
		                // Launch Add New product Activity
//		                Intent i = new Intent(getApplicationContext(),
//		                        NewProductActivity.class);
		                // Closing all previous activities
//		                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		                startActivity(i);
		            	Log.e("user Courses: ", "Can't get course details");
		            }
		        } catch (JSONException e) {
		            e.printStackTrace();
		        }
		        
				return result;
			}
			
			 @Override
			    protected void onPostExecute(Course result)
			    {
			        super.onPostExecute(result);
			        listener.onTaskComplete(result);
			    }

}
