package com.cyrilsabbagh.noteapp;

import java.util.List;

import com.cyrilsabbagh.noteapp.dataModel.Course;
import com.cyrilsabbagh.noteapp.databaseControllers.AddDefinition;
import com.cyrilsabbagh.noteapp.databaseControllers.AsyncTaskCompleteListener;
import com.cyrilsabbagh.noteapp.databaseControllers.GetCourseNotes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDictionaryActivity extends Activity{
	EditText textDef;
	EditText textName;
	String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_dictionary);
		Intent intent = getIntent();
	    
		id = intent.getStringExtra("id");
	    String def = intent.getStringExtra("def");
	    textName = (EditText) findViewById(R.id.editDicName);
	    textDef = (EditText) findViewById(R.id.editDicDef);
	    textDef.setText(def);
	    
	    Button button= (Button) findViewById(R.id.AddDicbutton);
	    button.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	AsyncTaskCompleteListener<String> dicLis = new AsyncTaskCompleteListener<String>() {

	        		@Override
	        		public void onTaskComplete(String result) {
	        			// TODO Auto-generated method stub
	        			if(result=="success"){
	        				Toast.makeText(getApplicationContext(),"Added to definition", Toast.LENGTH_SHORT).show();
	        				finish();}
	        			else Toast.makeText(getApplicationContext(),"couldn't add definition", Toast.LENGTH_SHORT).show();
	        		}
				};
	        	new AddDefinition(getApplicationContext(), dicLis,id,textName.getText().toString(),textDef.getText().toString()).execute();
	        }
	    });
		
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_dictionary, menu);
		return true;
	}

	

}
