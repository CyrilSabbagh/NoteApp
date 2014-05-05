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
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.app.Activity;

import com.cyrilsabbagh.noteapp.dataModel.Course;
import com.cyrilsabbagh.noteapp.dataModel.Note;
import com.cyrilsabbagh.noteapp.databaseControllers.AsyncTaskCompleteListener;
import com.cyrilsabbagh.noteapp.databaseControllers.CreateNote;
import com.cyrilsabbagh.noteapp.databaseControllers.GetCourseNotes;
import com.cyrilsabbagh.noteapp.databaseControllers.GetLatestNotes;
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
	String[] courselist;
	String[] namecourses;
	//int[] courselist= new int[]{0,0,0,0};
	
	private RadialMenuRenderer mRenderer;
	private FrameLayout mHolderLayout;
	public RadialMenuItem ViewNoteItem, DeleteNoteItem, ModifyNoteItem,ShareNoteItem;
	private ArrayList<RadialMenuItem> mMenuItems = new ArrayList<RadialMenuItem>(0);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_notes);
		
		//final String deviceId = Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
		String deviceId="354436053502520";
		AsyncTaskCompleteListener<List<Course>> myListenerCourses= new AsyncTaskCompleteListener<List<Course>>(){
			
			@Override
			public void onTaskComplete(List<Course> result) {
				// TODO Auto-generated method stub
				//populate list view
				//Log.i("DATABASE","Create new course: " +edtCourse.getText().toString());
			
			// qui puoi usare la lista di corsi: result.
				int i=1;
				courselist = new String[result.size()];
				namecourses = new String[result.size()];
				for(Course c: result){
					String tmp = new String("title"+(i+5));
					String tmp1 = new String("school"+(i+5));
					String tmp2 = new String("button"+(i+5));
					//String tmp3 = new String("c"+(i));
					TextView title = (TextView)findViewById(getResources().getIdentifier(tmp, "id", getPackageName()));
					TextView school = (TextView)findViewById(getResources().getIdentifier(tmp1, "id", getPackageName()));
					Button mybutton = (Button)findViewById(getResources().getIdentifier(tmp2, "id", getPackageName()));
					//TextView codcourse = (TextView)findViewById(getResources().getIdentifier(tmp3, "id", getPackageName()));
					title.setText(c.getName());
					school.setText(c.getSchool());
					//codcourse.setText("sad");
					courselist[i-1]=""+c.getId();
					namecourses[i-1]=c.getName();
					//courselist[i-1]=c.getId();
					title.setVisibility(View.VISIBLE);
					school.setVisibility(View.VISIBLE);
					mybutton.setVisibility(View.VISIBLE);
					i++;
				}
			}			
		};
		
		new GetUserCourses(getApplicationContext(),myListenerCourses,deviceId).execute();
		
		//same function but for course's notes//////////////////////////
		
		AsyncTaskCompleteListener<List<Note>> myListenerNotes= new AsyncTaskCompleteListener<List<Note>>(){
			
			@Override
			public void onTaskComplete(List<Note> result) {
				// TODO Auto-generated method stub
				//populate list view
				//Log.i("DATABASE","Create new course: " +edtCourse.getText().toString());
			
			// qui puoi usare la lista di corsi: result.
				int i=1;
				for(Note c: result){
					if(i<5){
					String tmp = new String("title"+i);
					String tmp1 = new String("course"+i);
					String tmp2 = new String("button"+i);
					TextView title1 = (TextView)findViewById(getResources().getIdentifier(tmp, "id", getPackageName()));
					TextView course1 = (TextView)findViewById(getResources().getIdentifier(tmp1, "id", getPackageName()));
					Button mybutton1 = (Button)findViewById(getResources().getIdentifier(tmp2, "id", getPackageName()));
					title1.setText(c.getName());
					//course1.setText(CourseActivity.nameC);
					title1.setVisibility(View.VISIBLE);
					//course1.setVisibility(View.VISIBLE);
					mybutton1.setVisibility(View.VISIBLE);
					i++;
				}
				}
			}			
		};
		
		new GetLatestNotes(getApplicationContext(),myListenerNotes,deviceId).execute();
		
//////////////////////////////////////////////////////////////////
		
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
		//TextView cc = new TextView(null);
		String cc=null;
		String name=null;
		//int cc=0;
		switch(sender.getId()) {
        case R.id.button6:
        	//cc = (TextView)findViewById(R.id.c1);
        	cc= courselist[0];
        	name=namecourses[0];
        	break;
        case R.id.button7:
        	//cc = (TextView)findViewById(R.id.c2);
        	cc= courselist[1];
        	name=namecourses[1];
        	break;
        case R.id.button8:
        	//cc = (TextView)findViewById(R.id.c3);
        	cc= courselist[2];
        	name=namecourses[2];
        	break;
        case R.id.button9:
        	//cc = (TextView)findViewById(R.id.c4);
        	cc= courselist[3];
        	name=namecourses[3];
        	break;
        case R.id.button10:
        	//cc = (TextView)findViewById(R.id.c5);
        	cc= courselist[4];
        	name=namecourses[4];
        	break;
		}
		intent.putExtra("CurrCourse", cc);
		intent.putExtra("NameCourse", name);
		startActivity(intent);
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_notes, menu);
		return true;
	}
	

} 


