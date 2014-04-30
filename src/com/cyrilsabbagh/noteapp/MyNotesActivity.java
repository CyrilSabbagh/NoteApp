package com.cyrilsabbagh.noteapp;

import android.os.Bundle;

import android.view.*;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.app.Activity;
import com.radialmenu.*;
import com.radialmenu.RadialMenuItem.RadialMenuItemClickListener;


public class MyNotesActivity extends Activity {

	/*IN ORDER TO PASS DATA TO THE SECOND ACTIVITY
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";*/
	
	com.radialmenu.RadialMenuWidget RD2;
	com.radialmenu.RadialMenuItem it1, it2, it3,it4;
	int[] location= new int[]{0,0};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_notes);
		
		RD2 = new com.radialmenu.RadialMenuWidget(this);
		 it1 = new com.radialmenu.RadialMenuItem("ViewNote","ViewNote");
		 it1.setDisplayIcon(R.drawable.viewnote);
		 it2 = new com.radialmenu.RadialMenuItem("DeleteNote","DeleteNote");
		 it2.setDisplayIcon(R.drawable.delnote);
		 it3 = new com.radialmenu.RadialMenuItem("ModifyNote","ModifyNote");
		 it3.setDisplayIcon(R.drawable.modnote);
		 it4 = new com.radialmenu.RadialMenuItem("ShareNote","ShareNote");
		 it4.setDisplayIcon(R.drawable.sharenote);
			
		 RD2.addMenuEntry(it1);
		 RD2.addMenuEntry(it2);
		 RD2.addMenuEntry(it3);
		 RD2.addMenuEntry(it4);
		 
		 /*To hide(INVISIBLE) or remove(GONE) a button if you don't have 5 notes
	     - The same for a text
	     You can set INVISIBLE all the notes-buttons in the xml and then you can show
	     only the notes-button that you have --> do the same for the courses
	     
		 View b = findViewById(R.id.button2);
		 b.setVisibility(View.GONE); 
		 View a= findViewById(R.id.course2);
		 a.setVisibility(View.GONE);
		 */
		 
		 it1.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),it1.getName(), Toast.LENGTH_SHORT).show();
					RD2.dismiss();
				}
			 });
		 
		 it2.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),it2.getName(), Toast.LENGTH_SHORT).show();
					RD2.dismiss();
				}
			 });
		 
		 it3.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),it3.getName(), Toast.LENGTH_SHORT).show();
					RD2.dismiss();
				}
			 });
		 
		 it4.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),it4.getName(), Toast.LENGTH_SHORT).show();
					RD2.dismiss();
				}
			 });
		 
	}  
	
	/*guarda qui: per creare il tuo radial menu
	 
	
	
	 RadialMenuWidget RD1 = new RadialMenuWidget(getApplicationContext());
	 RadialMenuItem rdw = new RadialMenuItem("Share","share");
	 RadialMenuItem rdw2 = new RadialMenuItem("Share","share");
	 
	 RD1.addMenuEntry(rdw);
	 RD1.addMenuEntry(rdw2);
	 
	// RD1.show(qui metti il button); questo lo metti dove vuoi farlo partire tipo nella
	 //funzione onClick del button
	 
//	 rdw.setOnMenuItemPressed(listener) per fargli fare una cosa quando lo premi
//   e ti crei il listener
	 
	 RD1.dismiss(); */////////////////////////////////////////////////////////
	
	
	public void circmenu(View sender) {
	//Toast.makeText(MyNotesActivity.this, "CircMenu", Toast.LENGTH_SHORT).show();
	sender.getLocationOnScreen(location);
	RD2.setCenterLocation(location[0]+65, location[1]+65);
	RD2.show(sender);	
	}
	
	public void opencourse(View sender) {
		Toast.makeText(MyNotesActivity.this, "openAct2.5", Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent(this, CourseActivity.class);
		startActivity(intent);
		
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_notes, menu);
		return true;
	}

}
