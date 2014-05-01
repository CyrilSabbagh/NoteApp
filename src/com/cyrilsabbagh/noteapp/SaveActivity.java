package com.cyrilsabbagh.noteapp;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.radialmenu.SemiCircularRadialMenu;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;

import com.cyrilsabbagh.noteapp.databaseControllers.*;

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
		EditText edtName = (EditText)findViewById(R.id.edtName);
		EditText edtCourse = (EditText)findViewById(R.id.edtCourse);
		
		if (edtName.getText().toString().compareTo("")!=0){
			
			AsyncTaskCompleteListener<String> myListener=new AsyncTaskCompleteListener<String>() {
				
				@Override
				public void onTaskComplete(String result) {
					// TODO Auto-generated method stub
					
				}
			};
			TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			Calendar c = Calendar.getInstance(); 
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String currentDate = sdf.format(new Date(c.get(Calendar.SECOND)));
			if (!getCurrentLocation()){
				lat=-1;
				lng=-1;
			}
			CheckBox ck=(CheckBox)findViewById(R.id.checkShare);
			//courseId=getCourseId(edtCourse.getText.toString());
			new CreateNote(getApplicationContext(), myListener,telephonyManager.getDeviceId(), edtName.getText().toString(), 
					myNote, currentDate, (float)lat, (float)lng, 0, ck.isChecked());
			setResult(RESULT_OK);
			finish();
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
