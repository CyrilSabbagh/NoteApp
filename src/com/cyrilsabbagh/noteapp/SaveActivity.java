package com.cyrilsabbagh.noteapp;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.radialmenu.SemiCircularRadialMenu;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.cyrilsabbagh.noteapp.databaseControllers.*;
import com.cyrilsabbagh.noteapp.dataModel.*;
public class SaveActivity extends Activity {
	private double lat, lng;
	private String myNote="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save);
		Intent myIntent = getIntent(); 
		try{
			myNote= myIntent.getStringExtra("note_text");
		}catch(Exception e){}
		
		EditText edt = (EditText)findViewById(R.id.edtName);
		edt.requestFocus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save, menu);
		return true;
	}
	
	public void SaveNote(View view){
		final EditText edtName = (EditText)findViewById(R.id.edtName);
		final EditText edtCourse = (EditText)findViewById(R.id.edtCourse);
		
		if (edtName.getText().toString().compareTo("")!=0){
			Toast.makeText(getApplicationContext(), "Connecting to the database...", Toast.LENGTH_LONG).show();
			final AsyncTaskCompleteListener<String> myListener=new AsyncTaskCompleteListener<String>() {				
				@Override
				public void onTaskComplete(String result) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "Task completed", Toast.LENGTH_SHORT).show();
					Log.i("DATABASE","Saved " + result +" on the database");
					setResult(RESULT_OK);
					finish();
				}
			};
			//IMEI present only on 3G tablets
			//final TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			Calendar c = Calendar.getInstance();
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			final String currentDate = sdf.format(c.getTime());
			
			if (!getCurrentLocation()){
				lat=-1;
				lng=-1;
			}
			final String deviceId = Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
			Log.i("DATABASE","IMEI: " + deviceId);
			Log.i("DATABASE","Location: latitude " + lat+ " longitude "+lng);
			Log.i("DATABASE","Date " + currentDate);
			
			final CheckBox ck=(CheckBox)findViewById(R.id.checkShare);
			ArrayList<Course> myCourses=new ArrayList<Course>();
			AsyncTaskCompleteListener<List<Course>> myListenerCourses= new AsyncTaskCompleteListener<List<Course>>(){
				
				@Override
				public void onTaskComplete(List<Course> result) {
					// TODO Auto-generated method stub
					//populate list view
					Log.i("DATABASE","Create new course: " +edtCourse.getText().toString());
					Course myCourse = null;
					for (Course c: result){
						if (c.getName().compareTo(edtCourse.getText().toString())==0){
							//the course already exists
							myCourse=c;
							break;
						}
					}
					if (myCourse==null){
						//create a new course
						
						
					}
					Toast.makeText(getApplicationContext(), "Task courses completed", Toast.LENGTH_SHORT).show();
					Log.i("DATABASE","Selected courses from the database: " + result.toString());
					new CreateNote(getApplicationContext(), myListener,deviceId, edtName.getText().toString(), 
							myNote, currentDate, (float)lat, (float)lng, myCourse.getId(), ck.isChecked()).execute();
				}			
			};
				
			//new GetUserCourses(getApplicationContext(),myListenerCourses,deviceId).execute();
			
			new CreateNote(getApplicationContext(), myListener,"354436053502520", edtName.getText().toString(), 
					myNote, currentDate, (float)lat, (float)lng,1, ck.isChecked()).execute();
		}
	}
	
	public void Abort(View view){
		setResult(RESULT_CANCELED);
		finish();
	}
	
	public boolean getCurrentLocation(){
		 LocationManager lm;
		 Location l;
		 String provider;
		 lm=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		   Criteria c=new Criteria();
		   //criteria object will select best service based on
		   //Accuracy, power consumption, response, bearing and monetary cost;
		   provider=lm.getBestProvider(c, false);
		   l=lm.getLastKnownLocation(provider);
		   if(l!=null)
		   {
		     //get latitude and longitude of the location
		     lng=l.getLongitude();
		     lat=l.getLatitude();
		     return true;
		   }
		   return false;
	}

}
