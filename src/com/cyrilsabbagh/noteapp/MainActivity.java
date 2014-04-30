package com.cyrilsabbagh.noteapp;

import com.radialmenu.RadialMenuItem.RadialMenuItemClickListener;
import com.radialmenu.RadialMenuWidget;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	com.radialmenu.RadialMenuWidget RD1;
	com.radialmenu.RadialMenuItem rdw, rdw2, rdw3,rdw4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		RD1 = new com.radialmenu.RadialMenuWidget(this);
		 rdw = new com.radialmenu.RadialMenuItem("New Note","New Note");
		 rdw.setDisplayIcon(R.drawable.newnote);
		 rdw2 = new com.radialmenu.RadialMenuItem("Search","Search");
		 rdw2.setDisplayIcon(R.drawable.search);
		 rdw3 = new com.radialmenu.RadialMenuItem("Latest Note","Latest Note");
		 rdw3.setDisplayIcon(R.drawable.recent);
		 rdw4 = new com.radialmenu.RadialMenuItem("My Notes","My Notes");
		 rdw4.setDisplayIcon(R.drawable.mynotes);
			
		 RD1.addMenuEntry(rdw);
		 RD1.addMenuEntry(rdw2);
		 RD1.addMenuEntry(rdw3);
		 RD1.addMenuEntry(rdw4);
		 
		 rdw.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(),rdw.getName(), Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this, HotCornersActivity.class);
				startActivity(intent);
			}
		 });
		 
		 rdw2.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),rdw2.getName(), Toast.LENGTH_SHORT).show();	
				}
		 });
		 rdw3.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),rdw3.getName(), Toast.LENGTH_SHORT).show();	
				}
		 });
		 rdw4.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					//Toast.makeText(getApplicationContext(),rdw4.getName(), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(MainActivity.this, MyNotesActivity.class);
					startActivity(intent);
				}
		 });
		
		 
		 Button testButton = (Button) this.findViewById(R.id.btnTest);
			testButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					RD1.show(v);
				}
			}); 
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/*
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
	*/	

}
