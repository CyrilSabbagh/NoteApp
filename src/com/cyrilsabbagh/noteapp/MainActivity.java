package com.cyrilsabbagh.noteapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void StartHotCorners(View view){
		Intent intent = new Intent(this, HotCornersActivity.class);
		startActivity(intent);
	}
	
	public void StartMyNotes(View view){
		Intent intent = new Intent(this, MyNotesActivity.class);
		startActivity(intent);
	}
	
	public void Search(View view){
		Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
		//Intent intent = new Intent(this, SearchActivity.class);
		//startActivity(intent);
	}
	
	public void LatestNote(View view){
		Toast.makeText(MainActivity.this, "LatestNote", Toast.LENGTH_SHORT).show();
		//Intent intent = new Intent(this, LatestNoteActivity.class);
		//startActivity(intent);
	}

}
