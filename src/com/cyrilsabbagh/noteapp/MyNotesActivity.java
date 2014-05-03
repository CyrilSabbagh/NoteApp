package com.cyrilsabbagh.noteapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.content.Intent;
import android.app.Activity;

import com.cyrilsabbagh.noteapp.dataModel.Course;
import com.cyrilsabbagh.noteapp.databaseControllers.AsyncTaskCompleteListener;
import com.cyrilsabbagh.noteapp.databaseControllers.CreateNote;
import com.cyrilsabbagh.noteapp.databaseControllers.GetUserCourses;
import com.radialmenu.*;
import com.radialmenu.RadialMenuItem.RadialMenuItemClickListener;
//import com.touchmenotapps.radialdemo.RadialMenuAboutFragment;
//import com.touchmenotapps.radialdemo.RadialMenuContactFragment;
//import com.touchmenotapps.radialdemo.RadialMenuMainFragment;
import com.touchmenotapps.widget.radialmenu.menu.v2.*;
import com.touchmenotapps.widget.radialmenu.menu.v2.RadialMenuItem;
import com.touchmenotapps.widget.radialmenu.menu.v2.RadialMenuRenderer.OnRadailMenuClick;


public class MyNotesActivity extends Activity {

	/*IN ORDER TO PASS DATA TO THE SECOND ACTIVITY
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";*/
	
	
	com.radialmenu.RadialMenuWidget RD2;
	com.radialmenu.RadialMenuItem it1, it2, it3,it4;
	int[] location= new int[]{0,0};
	
	private RadialMenuRenderer mRenderer;
	private FrameLayout mHolderLayout;
	public RadialMenuItem ViewNoteItem, DeleteNoteItem, ModifyNoteItem,ShareNoteItem;
	private ArrayList<RadialMenuItem> mMenuItems = new ArrayList<RadialMenuItem>(0);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_notes);
		
		final String deviceId = Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
		
		AsyncTaskCompleteListener<List<Course>> myListenerCourses= new AsyncTaskCompleteListener<List<Course>>(){
			
			@Override
			public void onTaskComplete(List<Course> result) {
				// TODO Auto-generated method stub
				//populate list view
				//Log.i("DATABASE","Create new course: " +edtCourse.getText().toString());
			
			// qui puoi usare la lista di corsi: result.
				for(int i=1;i<=5;i++){
				}
			
			}			
		};
		
		new GetUserCourses(getApplicationContext(),myListenerCourses,deviceId).execute();
		
		RD2 = new com.radialmenu.RadialMenuWidget(this);
		RD2.setInnerRingRadius(40,120);
		RD2.setIconSize(50, 60);
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
		 
		 //////////////////////////////////////////////////////////////////////////
		//Init the frame layout
		/*	mHolderLayout = (FrameLayout) findViewById(R.id.fragment_container);
			// Init the Radial Menu and menu items
			mRenderer = new RadialMenuRenderer(mHolderLayout, true, 30, 60);		
			ViewNoteItem = new RadialMenuItem("ViewNote","ViewNote");
			DeleteNoteItem = new RadialMenuItem("DeleteNote","DeleteNote");
			ModifyNoteItem = new RadialMenuItem("ModifyNote","ModifyNote");
			ShareNoteItem = new RadialMenuItem("ShareNote","ShareNote");
			//Add the menu Items
			mMenuItems.add(ViewNoteItem);
			mMenuItems.add(DeleteNoteItem);
			mMenuItems.add(ModifyNoteItem);
			mMenuItems.add(ShareNoteItem);
			ViewNoteItem.
			mRenderer.setRadialMenuContent(mMenuItems);
			mHolderLayout.addView(mRenderer.renderView());
			//Handle the menu item interactions
			ViewNoteItem.setOnRadialMenuClickListener(new OnRadailMenuClick() {
				@Override
				public void onRadailMenuClickedListener(String id) {
					//Can edit based on preference. Also can add animations here.
				//	getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				//	getSupportFragmentManager().beginTransaction().replace(mHolderLayout.getId(), new RadialMenuContactFragment()).commit();
				}
			});
			
			DeleteNoteItem.setOnRadialMenuClickListener(new OnRadailMenuClick() {
				@Override
				public void onRadailMenuClickedListener(String id) {
					//Can edit based on preference. Also can add animations here.
				//	getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				//	getSupportFragmentManager().beginTransaction().replace(mHolderLayout.getId(), new RadialMenuMainFragment()).commit();
				}
			});
			
			ModifyNoteItem.setOnRadialMenuClickListener(new OnRadailMenuClick() {
				@Override
				public void onRadailMenuClickedListener(String id) {
					//Can edit based on preference. Also can add animations here.
				//	getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				//	getSupportFragmentManager().beginTransaction().replace(mHolderLayout.getId(), new RadialMenuAboutFragment()).commit();
				}
			});
			
			ShareNoteItem.setOnRadialMenuClickListener(new OnRadailMenuClick() {
				@Override
				public void onRadailMenuClickedListener(String id) {
					//Can edit based on preference. Also can add animations here.
				//	getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
				//	getSupportFragmentManager().beginTransaction().replace(mHolderLayout.getId(), new RadialMenuAboutFragment()).commit();
				}
			});*/
		 
		 //////////////////////////////////////////////////////////////////////////
		 
		 
		 
		 
		 
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
					RD2.eventHandled=false;
				}
			 });
		 
		 it2.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),it2.getName(), Toast.LENGTH_SHORT).show();
					RD2.dismiss();
					RD2.eventHandled=false;
				}
			 });
		 
		 it3.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),it3.getName(), Toast.LENGTH_SHORT).show();
					RD2.dismiss();
					RD2.eventHandled=false;
				}
			 });
		 
		 it4.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),it4.getName(), Toast.LENGTH_SHORT).show();
					RD2.dismiss();
					RD2.eventHandled=false;
				}
			 });
		 /*((Button)findViewById(R.id.button1)).setOnTouchListener( new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch ( event.getAction() ) {
			     case MotionEvent.ACTION_DOWN: 
			    	 circmenu(v);
			    	 break;
			     case MotionEvent.ACTION_UP:
			    	 RD2.dismiss();
			    	 break;
			     }
			     return true;
			}
		});ij\	\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\hb
		 ((Button)findViewById(R.id.button2)).setOnTouchListener( new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					switch ( event.getAction() ) {
				     case MotionEvent.ACTION_DOWN: 
				    	 circmenu(v);
				    	 break;
				     case MotionEvent.ACTION_UP:
				    	 RD2.dismiss();
				    	 break;
				     }
				     return true;
				}
			});
		 ((Button)findViewById(R.id.button3)).setOnTouchListener( new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					switch ( event.getAction() ) {
				     case MotionEvent.ACTION_DOWN: 
				    	 circmenu(v);
				    	 break;
				     case MotionEvent.ACTION_UP:
				    	 RD2.dismiss();
				    	 break;
				     }
				     return true;
				}
			});
		 ((Button)findViewById(R.id.button4)).setOnTouchListener( new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					switch ( event.getAction() ) {
				     case MotionEvent.ACTION_DOWN: 
				    	 circmenu(v);
				    	 break;
				     case MotionEvent.ACTION_UP:
				    	 RD2.dismiss();
				    	 break;
				     }
				     return true;
				}
			});*/
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


