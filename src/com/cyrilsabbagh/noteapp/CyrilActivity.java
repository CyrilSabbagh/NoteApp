package com.cyrilsabbagh.noteapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cyrilsabbagh.noteapp.dataModel.Course;
import com.cyrilsabbagh.noteapp.databaseControllers.AsyncTaskCompleteListener;
import com.cyrilsabbagh.noteapp.databaseControllers.GetUserCourses;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class CyrilActivity extends ListActivity implements AsyncTaskCompleteListener<List<Course>>{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cyril);
		
		// This is the code to get the user imei number !! uncomment after testing
		
//		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
//		String user_imei = telephonyManager.getDeviceId();
		
		String user_imei = "354436053502520"; //Delete this line after testing
		new GetUserCourses(this, this,user_imei).execute();
		
	}
	
	
	 @Override
     public void onTaskComplete(List<Course> result)
     {
         // do something with the result
		 
		 ArrayList<HashMap<String, String>> courseList = new ArrayList<HashMap<String, String>>();
			
			for(int i=0;i<result.size();i++){
				
				// creating new HashMap
	            HashMap<String, String> map = new HashMap<String, String>();

	            // adding each child node to HashMap key => value
	            map.put("id", Integer.toString(result.get(i).getId()));
	            map.put("name", result.get(i).getName());

	            // adding HashList to ArrayList
	            courseList.add(map);
				
			}
			
			
			ListAdapter adapter = new SimpleAdapter(
	                CyrilActivity.this, courseList,
	                R.layout.list_item, new String[] { "id",
	                        "name"},
	                new int[] { R.id.pid, R.id.name });
	        // updating listview
	        setListAdapter(adapter);
		 
		 
     }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cyril, menu);
		return true;
	}

}
