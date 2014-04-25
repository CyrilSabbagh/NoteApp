package com.cyrilsabbagh.noteapp;

import android.os.Bundle;

import android.view.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.app.Activity;
import com.radialmenu.*;


public class MyNotesActivity extends Activity {

	/*IN ORDER TO PASS DATA TO THE SECOND ACTIVITY
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";*/
	
	//SemiCircularRadialMenu NoteFile;
	
	 com.radialmenu.RadialMenuWidget RD1;
	 com.radialmenu.RadialMenuItem rdw;
	 com.radialmenu.RadialMenuItem rdw2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_notes);
		/*
		NoteFile = (SemiCircularRadialMenu)findViewById(R.id.fileMenu); 
		NoteFile.setOpenMenuText("Share");
		NoteFile.setCloseMenuText("Share");
		*/
		//RD1 = new RadialMenuWidget(getApplicationContext());
	/*
		RD1 = (RadialMenuWidget)findViewById(R.id.NoteMenu); 
		rdw = new RadialMenuItem("Share","share");
		rdw2 = new RadialMenuItem("Edit","edit");
		
		 RD1.addMenuEntry(rdw);
		 RD1.addMenuEntry(rdw2);*/
		
		 
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
	Toast.makeText(MyNotesActivity.this, "CircMenu", Toast.LENGTH_SHORT).show();
	//RD1.show(findViewById(R.id.button));
	}
	
	public void opencourse(View sender) {
		Toast.makeText(MyNotesActivity.this, "openAct2.5", Toast.LENGTH_SHORT).show();
		
		/*WHEN THE ACTIVITY 2.5 WILL BE READY, YOU WILL USE THIS CODE
		Intent intent = new Intent(this, Activity25.class);
	    EditText editText = (EditText) findViewById(R.id.edit_message);
	    String message = editText.getText().toString();
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);  */
		
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_notes, menu);
		return true;
	}

}
