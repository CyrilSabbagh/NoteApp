package com.cyrilsabbagh.noteapp;

import com.radialmenu.RadialMenuItem.RadialMenuItemClickListener;
import com.radialmenu.RadialMenuWidget;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	private com.radialmenu.RadialMenuWidget RD1;
	private com.radialmenu.RadialMenuItem rdw, rdw2, rdw3,rdw4, rdw0;
	private boolean appLaunched=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 RD1 = new com.radialmenu.RadialMenuWidget(this);
		 RD1.setInnerRingRadius(100,228);
		 RD1.setIconSize(100, 110);
		 //RD1.setCenterLocation(,);
		 rdw = new com.radialmenu.RadialMenuItem("New Note","New Note");
		 rdw.setDisplayIcon(R.drawable.newnote);
		 rdw2 = new com.radialmenu.RadialMenuItem("Search","Search");
		 rdw2.setDisplayIcon(R.drawable.search);
		 rdw3 = new com.radialmenu.RadialMenuItem("Latest Note","Latest Note");
		 rdw3.setDisplayIcon(R.drawable.recent);
		 rdw4 = new com.radialmenu.RadialMenuItem("My Notes","My Notes");
		 rdw4.setDisplayIcon(R.drawable.mynotes);
		
		 //rdw0 = new com.radialmenu.RadialMenuItem("Start","Start");
		 //rdw0.setDisplayIcon(R.drawable.notes);
		 RD1.addMenuEntry(rdw);
		 RD1.addMenuEntry(rdw2);
		 RD1.addMenuEntry(rdw3);
		 RD1.addMenuEntry(rdw4);
		 RD1.setCenterCircle(rdw0);
		 rdw.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(),rdw.getName(), Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this, HotCornersActivity.class);
				//intent.putExtra("id_note","note1");
				intent.putExtra("activity_type","new");
				startActivity(intent);
			}
		 });
		 
		 rdw2.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
				@Override
				public void execute() {
					// TODO Auto-generated method stub

					Toast.makeText(getApplicationContext(),rdw2.getName(), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(MainActivity.this, SearchActivity.class);
					startActivity(intent);
					//RD1.eventHandled=false;

				}
		 });
		 rdw3.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),rdw3.getName(), Toast.LENGTH_SHORT).show();
					//RD1.eventHandled=false;
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
			 
		 /*Button btn = (Button) findViewById(R.id.btnExit);
		 btn.setOnClickListener(new View.OnClickListener() {
		     public void onClick(View v) {
		        CloseApplication();
		      }
		  });*/
	}
	
	public void DisplayMenu(View v){
		if (!appLaunched){
			appLaunched=true;
			//v.setVisibility(View.GONE);
			LinearLayout ll=(LinearLayout)findViewById(R.id.mainlayout);
			//ll.setBackground(R.drawable.backmain1);
			RD1.show(v);
		}
	}
	
	public void TestCourse(View v){
		Intent intent = new Intent(MainActivity.this, CyrilActivity.class);
		startActivity(intent);
	}
	
//	public void StartCyrilActivity(View view){
//		Intent intent = new Intent(this, CyrilActivity.class);
//		startActivity(intent);
//	}

	/*public void onResume(){
		findViewById(R.id.mainlayout).post(new Runnable() {

			public void run() {
			pieMenu.show(findViewById(R.id.mainlayout));
			}
			});
	}*/
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

	public void StartCyrilActivity(View view){
		Intent intent = new Intent(this, CyrilActivity.class);

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
	public void onResume(){
		super.onResume();
		appLaunched=false;
		if (RD1!=null)
			RD1.eventHandled=false;
	}
	
	public void Exit(View v){
		CloseApplication();
	}
	
	@Override
	public void onBackPressed() {
		CloseApplication();
	}
	
	public void CloseApplication(){
		appLaunched=false;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage("Are you sure you want to exit");
	    builder.setCancelable(false);
	    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
	            //YES
	    	public void onClick(DialogInterface dialog,int id) {
	                finish();
	            }
	        });
	     builder.setNegativeButton("NO",  new DialogInterface.OnClickListener() {
	            //NO
	            public void onClick(DialogInterface dialog,int id) {
	            	
	                dialog.cancel();
	            }
	        });

	        AlertDialog alert = builder.create();
	        alert.show();
	}
}
