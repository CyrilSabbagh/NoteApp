package com.cyrilsabbagh.noteapp;

import org.xmlpull.v1.XmlPullParser;




//import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuItem;
//import com.touchmenotapps.widget.radialmenu.menu.v1.RadialMenuWidget;
//import com.touchmenotapps.widget.radialmenu.semicircularmenu.SemiCircularRadialMenu;
//import com.touchmenotapps.widget.radialmenu.semicircularmenu.SemiCircularRadialMenuItem;
import com.radialmenu.*;
import com.radialmenu.RadialMenuItem.RadialMenuItemClickListener;
import com.radialmenu.SemiCircularRadialMenuItem.OnSemiCircularRadialMenuPressed;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.os.Build;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;

public class HotCornersActivity extends Activity {
	SemiCircularRadialMenu pieMenuFile,pieMenuEdit,pieMenuOptions,pieMenuMedia,pieMenuStyle; 
	SemiCircularRadialMenuItem ItemNew,ItemOpen,ItemSave, ItemExit;
	SemiCircularRadialMenuItem ItemMultiauthor,ItemShare,ItemDictionary;
	SemiCircularRadialMenuItem ItemDimension,ItemColor,ItemBold, ItemItalic,ItemUnderlined;
	SemiCircularRadialMenuItem ItemRecorder,ItemSnapshot,ItemAttach, ItemLink;
	SemiCircularRadialMenuItem ItemCopy,ItemCut,ItemPaste;
	
	
	com.radialmenu.RadialMenuWidget RD1;
	com.radialmenu.RadialMenuItem rdw, rdw2, rdw3,rdw4;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hot_corners);

		//POUR CHIARA!!***********a supprimer après*****************
		 RD1 = new com.radialmenu.RadialMenuWidget(this);
		 rdw = new com.radialmenu.RadialMenuItem("Test1","Test1");
		 rdw2 = new com.radialmenu.RadialMenuItem("Test2","Test2");
		 rdw3 = new com.radialmenu.RadialMenuItem("Test3","Test3");
		 rdw4 = new com.radialmenu.RadialMenuItem("Test4","Test4");
			
		 RD1.addMenuEntry(rdw);
		 RD1.addMenuEntry(rdw2);
		 RD1.addMenuEntry(rdw3);
		 RD1.addMenuEntry(rdw4);
		 
		 rdw.setOnMenuItemPressed(new RadialMenuItemClickListener() {	
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),rdw.getName(), Toast.LENGTH_SHORT).show();	
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
					Toast.makeText(getApplicationContext(),rdw4.getName(), Toast.LENGTH_SHORT).show();	
				}
		 });
		 
		 Button testButton = (Button) this.findViewById(R.id.btnTest);
			testButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					RD1.show(v);
				}
			});
		//*************************************************************
			
			
		/*if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
		
		pieMenuFile = (SemiCircularRadialMenu)findViewById(R.id.fileMenu); 
		pieMenuFile.setOpenMenuText("File");
		pieMenuFile.setCloseMenuText("File");
		pieMenuFile.setOrientation(SemiCircularRadialMenu.HORIZONTAL_BOTTOM);
		ItemNew = new SemiCircularRadialMenuItem("New",getResources().getDrawable(R.drawable.newfile),"New file");
		pieMenuFile.addMenuItem(ItemNew.getMenuID(),ItemNew);
		ItemOpen = new SemiCircularRadialMenuItem("Open",getResources().getDrawable(R.drawable.openfile),"Open file");
		pieMenuFile.addMenuItem(ItemOpen.getMenuID(),ItemOpen);
		ItemSave = new SemiCircularRadialMenuItem("Save",getResources().getDrawable(R.drawable.save),"Save");
		pieMenuFile.addMenuItem(ItemSave.getMenuID(), ItemSave);
		ItemExit = new SemiCircularRadialMenuItem("Exit",getResources().getDrawable(R.drawable.exit),"Exit");
		pieMenuFile.addMenuItem(ItemExit.getMenuID(), ItemExit);
		
		pieMenuOptions = (SemiCircularRadialMenu)findViewById(R.id.optionsMenu); 
		pieMenuOptions.setOpenMenuText("Options");
		pieMenuOptions.setCloseMenuText("Options");
		pieMenuOptions.setOrientation(SemiCircularRadialMenu.HORIZONTAL_BOTTOM);
		ItemMultiauthor = new SemiCircularRadialMenuItem("Multiauthor",getResources().getDrawable(R.drawable.multi),"Multiauthor");
		pieMenuOptions.addMenuItem(ItemMultiauthor.getMenuID(),ItemMultiauthor);
		ItemShare = new SemiCircularRadialMenuItem("Share",getResources().getDrawable(R.drawable.share),"Share");
		pieMenuOptions.addMenuItem(ItemShare.getMenuID(),ItemShare);
		ItemDictionary = new SemiCircularRadialMenuItem("Dictionary",getResources().getDrawable(R.drawable.dictionary),"Add to dictionary");
		pieMenuOptions.addMenuItem(ItemDictionary.getMenuID(), ItemDictionary);

		pieMenuStyle = (SemiCircularRadialMenu)findViewById(R.id.styleMenu); 
		pieMenuStyle.setOpenMenuText("Style");
		pieMenuStyle.setCloseMenuText("Style");
		pieMenuStyle.setOrientation(SemiCircularRadialMenu.HORIZONTAL_BOTTOM);
		ItemDimension = new SemiCircularRadialMenuItem("Dimension",getResources().getDrawable(R.drawable.dimension),"Dimension");
		pieMenuStyle.addMenuItem(ItemDimension.getMenuID(),ItemDimension);
		ItemColor = new SemiCircularRadialMenuItem("Color",getResources().getDrawable(R.drawable.color),"Color");
		pieMenuStyle.addMenuItem(ItemColor.getMenuID(),ItemColor);
		ItemBold = new SemiCircularRadialMenuItem("Bold",getResources().getDrawable(R.drawable.bold),"Bold");
		pieMenuStyle.addMenuItem(ItemBold.getMenuID(), ItemBold);
		ItemItalic = new SemiCircularRadialMenuItem("Italic",getResources().getDrawable(R.drawable.italic),"Italic");
		pieMenuStyle.addMenuItem(ItemItalic.getMenuID(), ItemItalic);
		ItemUnderlined = new SemiCircularRadialMenuItem("Underlined",getResources().getDrawable(R.drawable.underlined),"Underlined");
		pieMenuStyle.addMenuItem(ItemUnderlined.getMenuID(), ItemUnderlined);
		
		pieMenuMedia = (SemiCircularRadialMenu)findViewById(R.id.mediaMenu); 
		pieMenuMedia.setOpenMenuText("Media");
		pieMenuMedia.setCloseMenuText("Media");
		pieMenuMedia.setOrientation(SemiCircularRadialMenu.HORIZONTAL_BOTTOM);
		ItemRecorder = new SemiCircularRadialMenuItem("Recorder",getResources().getDrawable(R.drawable.recorder),"Recorder");
		pieMenuMedia.addMenuItem(ItemRecorder.getMenuID(),ItemRecorder);
		ItemSnapshot = new SemiCircularRadialMenuItem("Snapshot",getResources().getDrawable(R.drawable.snapshot),"Snapshot");
		pieMenuMedia.addMenuItem(ItemSnapshot.getMenuID(),ItemSnapshot);
		ItemAttach = new SemiCircularRadialMenuItem("Attach",getResources().getDrawable(R.drawable.attachment),"Attach file");
		pieMenuMedia.addMenuItem(ItemAttach.getMenuID(), ItemAttach);
		ItemLink = new SemiCircularRadialMenuItem("Link",getResources().getDrawable(R.drawable.link),"Link");
		pieMenuMedia.addMenuItem(ItemLink.getMenuID(), ItemLink);
		
		pieMenuEdit = (SemiCircularRadialMenu)findViewById(R.id.editMenu); 
		pieMenuEdit.setOpenMenuText("Edit");
		pieMenuEdit.setCloseMenuText("Edit");
		pieMenuEdit.setOrientation(SemiCircularRadialMenu.HORIZONTAL_BOTTOM);
		ItemCopy = new SemiCircularRadialMenuItem("Copy",getResources().getDrawable(R.drawable.copy),"Copy");
		pieMenuEdit.addMenuItem(ItemCopy.getMenuID(),ItemCopy);
		ItemCut = new SemiCircularRadialMenuItem("Cut",getResources().getDrawable(R.drawable.cut),"Cut");
		pieMenuEdit.addMenuItem(ItemCut.getMenuID(),ItemCut);
		ItemPaste = new SemiCircularRadialMenuItem("Paste",getResources().getDrawable(R.drawable.paste),"Paste");
		pieMenuEdit.addMenuItem(ItemPaste.getMenuID(), ItemPaste);
		

		ItemNew.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"New", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemNew.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"New", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemOpen.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Open", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemSave.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Save", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemExit.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Exit", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemMultiauthor.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Multiauthor", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemShare.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Share", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemDictionary.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Dictionary", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemDimension.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Dimension", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemColor.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Color", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemBold.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Bold", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemItalic.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Italic", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemUnderlined.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Underlined", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemRecorder.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Recorder", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemSnapshot.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Snapshot", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemAttach.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Attach", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemLink.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Link", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemCopy.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Copy", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemCut.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Cut", Toast.LENGTH_SHORT).show();		
			}
		});
		
		ItemPaste.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Paste", Toast.LENGTH_SHORT).show();		
			}
		});
		/*pieMenuOptions.setOnTouchListener(new OnTouchListener() {		
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = MotionEventCompat.getActionMasked(event);
		        
			    switch(action) {
			        case (MotionEvent.ACTION_DOWN) :
			            Log.i("DEBUG","Action was DOWN");
			            return true;
			        case (MotionEvent.ACTION_MOVE) :
			            Log.i("DEBUG","Action was MOVE");
			            return true;
			        case (MotionEvent.ACTION_UP) :
			            Log.i("DEBUG","Action was UP");
			            return true;
			        case (MotionEvent.ACTION_CANCEL) :
			            Log.i("DEBUG","Action was CANCEL");
			            return true;
			        case (MotionEvent.ACTION_OUTSIDE) :
			            Log.i("DEBUG","Movement occurred outside bounds " +
			                    "of current screen element");
			            return true;    
			        
			        default : 
			        	return v.onTouchEvent(event);
			    }      
				// 	return false;
			}
		});*/

		/*Button btnFile = (Button)findViewById(R.id.fileMenu);
		btnFile.setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				//EditText editText = (EditText) findViewById(R.id.editText1);
				//editText.setText("File menu");
				
				CircularView cv=(CircularView)findViewById(R.id.circularview);
				RelativeLayout menu=(RelativeLayout)findViewById(R.id.layout_filemenu);				
				View view=findViewById(R.id.center);
				RelativeLayout.LayoutParams layoutParams=(RelativeLayout.LayoutParams)view.getLayoutParams();
				layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
				view.setLayoutParams(layoutParams);
				//Log.i("DEBUG", "center: "+view.getLeft() + " " +view.getTop() + " "+view.getRight()+" "+view.getBottom());				
				//Log.i("DEBUG", "circularview "+cv.getX() + " " +cv.getY());
				//Log.i("DEBUG", "filemenu "+filemenu.getX() + " " +filemenu.getY());
				//Log.i("DEBUG", "optionsmenu "+optionsmenu.getX() + " " +optionsmenu.getY());
				
				//LayoutParams layoutParams=new LayoutParams(1, 1);
			    //layoutParams.setMargins(10,10,11,11);
			    //view.setLayoutParams(layoutParams);
				//view.invalidate();
				
				Log.i("DEBUG", "center: "+view.getLeft() + " " +view.getTop() + " "+view.getWidth()+" "+view.getHeight());
				Log.i("DEBUG", "cv: "+cv.getLeft() + " " +cv.getTop() + " "+cv.getWidth()+" "+cv.getHeight());
				Log.i("DEBUG", "layout: "+menu.getLeft() + " " +menu.getTop() + " "+menu.getWidth()+" "+menu.getHeight());
				RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout_main);
				Log.i("DEBUG", "main: "+rl.getLeft() + " " +rl.getTop() + " "+rl.getWidth()+" "+rl.getHeight());
						
				cv.invalidate();//redraw the view
				cv.setVisibility(View.VISIBLE);
				
				RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout_main);
		        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
		                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);	        
		        RelativeLayout r1 = new RelativeLayout(getApplicationContext());
	            //Create four buttons 
		        for(int j=0;j<4;j++) 
		        {   
		            // Create Button
		            Button btn = new Button(getApplicationContext());
		                // Give button an ID
		                btn.setId(j+1);
		                btn.setText("ciao");
		                // set the layoutParams on the button
		                btn.setLayoutParams(params);
		                 
		                final int index = j;
		                // Set click listener for button
		                btn.setOnClickListener(new OnClickListener() {
		                    public void onClick(View v) {
		                        Log.i("TAG", "index :" + index);
		                        Toast.makeText(getApplicationContext(),"Clicked Button Index :" + index, Toast.LENGTH_LONG).show();	                         
		                    }
		                }); 
		               r1.addView(btn);  
		        }
		        rl.addView(r1);
				
				
				
				return false;
			}
		});*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hot_corners, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_hot_corners,
					container, false);
			return rootView;
		}
	}*/
	
	public void ShowFileMenu(View view){
		//EditText editText = (EditText) findViewById(R.id.editText1);
		//editText.setText("File menu");
	}
	
	public void ShowOptionsMenu(View view){
		//EditText editText = (EditText) findViewById(R.id.editText1);
		//editText.setText("Options menu");
		
	}
	
	public void ShowEditMenu(View view){
		//EditText editText = (EditText) findViewById(R.id.editText1);
		//editText.setText("Edit menu");
		
	}
	
	public void ShowMediaMenu(View view){
		//EditText editText = (EditText) findViewById(R.id.editText1);
		//editText.setText("Media menu");
		
	}
	
	public void ShowStyleMenu(View view){
		//EditText editText = (EditText) findViewById(R.id.editText1);
		//editText.setText("Style menu");
		
	}
	
	/*@Override
	public boolean onTouchEvent(MotionEvent event){ 
	    this.mDetector.onTouchEvent(event);
	    return super.onTouchEvent(event);
	}
	
	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures"; 
        
        @Override
        public boolean onDown(MotionEvent event) { 
            Log.d(DEBUG_TAG,"onDown: " + event.toString()); 
            return true;
        }
        
        
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, 
                float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
            return true;
        }
    }
	*/
}
