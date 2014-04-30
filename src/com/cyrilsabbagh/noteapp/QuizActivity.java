package com.cyrilsabbagh.noteapp;

import android.os.Bundle;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorEvent;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;
import android.content.Context;
import com.cyrilsabbagh.noteapp.ShakeDetector.*;

public class QuizActivity extends Activity {
	// The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		
		 // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new OnShakeListener(){
 
            public void onShake(int count) {
            	this.handleShakeEvent(count);
            }
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
                 * method you would use to setup whatever you want done once the
                 * device has been shook.
                 */
            	void handleShakeEvent(int count) {
            		// TODO Auto-generated method stub
            		Toast.makeText(QuizActivity.this, "SHAKE", Toast.LENGTH_SHORT).show();
            	}           
            }
            );
        
        
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}
	
	 @Override
	    public void onResume() {
	        super.onResume();
	        // Add the following line to register the Session Manager Listener onResume
	        mSensorManager.registerListener(mShakeDetector, mAccelerometer,    SensorManager.SENSOR_DELAY_UI);
	    }
	 
	    @Override
	    public void onPause() {
	        // Add the following line to unregister the Sensor Manager onPause
	        mSensorManager.unregisterListener(mShakeDetector);
	        super.onPause();
	    }
	
	

}
