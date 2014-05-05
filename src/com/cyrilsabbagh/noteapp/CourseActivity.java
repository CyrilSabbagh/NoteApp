package com.cyrilsabbagh.noteapp;

import java.util.List;
import java.lang.*;
import android.os.Bundle;

import android.view.*;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.app.Activity;
import android.content.*;

import com.cyrilsabbagh.noteapp.dataModel.*;
import com.cyrilsabbagh.noteapp.databaseControllers.AsyncTaskCompleteListener;
import com.cyrilsabbagh.noteapp.databaseControllers.GetCourseNotes;
import com.cyrilsabbagh.noteapp.databaseControllers.GetUserCourses;
import com.radialmenu.*;
import com.radialmenu.RadialMenuItem.RadialMenuItemClickListener;


public class CourseActivity extends Activity {

	/*IN ORDER TO PASS DATA TO THE SECOND ACTIVITY
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";*/
	
	com.radialmenu.RadialMenuWidget RD2;
	com.radialmenu.RadialMenuItem it1, it2, it3,it4;
	int[] location= new int[]{0,0};
	String[] courselist; 
	String[] namecourses; 
	String nameC=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course);
		
		String courseID=null;
		
		int cid;
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		   courseID = extras.getString("CurrCourse");
		   nameC = extras.getString("NameCourse");
		   //cid = extras.getInt("CurrCourse");
		}
		cid = Integer.parseInt(courseID);
		
		TextView name = (TextView)findViewById(R.id.nameCourse);
		name.setText(nameC);
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
							//codcourse.setText(c.getId());
							courselist[i-1]=""+c.getId();
							namecourses[i-1]=c.getName();
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
				
				new GetCourseNotes(getApplicationContext(),myListenerNotes,cid).execute();
				
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
	
	
	public void circmenu(View sender) {
	//Toast.makeText(MyNotesActivity.this, "CircMenu", Toast.LENGTH_SHORT).show();
	sender.getLocationOnScreen(location);
	RD2.setCenterLocation(location[0]+65, location[1]+65);
	RD2.show(sender);	
	}
	
	public void opencourse(View sender) {
		Toast.makeText(CourseActivity.this, "openAct2.5", Toast.LENGTH_SHORT).show();
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
	
	public void Quiz (View sender) {
		//Toast.makeText(CourseActivity.this, "openQuizActivity", Toast.LENGTH_SHORT).show();
	    Intent intent = new Intent(this, QuizActivity.class);
	    startActivity(intent);
		}
	
	public void Dictionary (View sender) {
		Toast.makeText(CourseActivity.this, "openDictionaryActivity", Toast.LENGTH_SHORT).show();
	   // Intent intent = new Intent(CourseActivity.this, DictionaryActivity.class);
	   //startActivity(intent);
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_notes, menu);
		return true;
	}

}
