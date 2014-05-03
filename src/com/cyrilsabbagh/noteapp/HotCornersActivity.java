package com.cyrilsabbagh.noteapp;

import java.io.File;
import java.lang.reflect.Method;

import org.xmlpull.v1.XmlPullParser;

import com.cyrilsabbagh.noteapp.controllers.HtmlFormat;
import com.radialmenu.*;
import com.radialmenu.RadialMenuItem.RadialMenuItemClickListener;
import com.radialmenu.SemiCircularRadialMenuItem.OnSemiCircularRadialMenuPressed;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.text.InputFilter;
import android.text.Spanned;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.KeyEvent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;

public class HotCornersActivity extends Activity {
	
	static final int SAVE_NOTE = 1;
	static final int TAKE_PICTURE = 2;
	
	private boolean bold=false, italic=false, underlined=false;
	
	private String mSelectedImagePath;
	private StringBuffer webViewContent=new StringBuffer("");
	
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
		
		//get current screen orientation
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
	        int orientation = display.getRotation(); 
		switch(orientation){
		case Surface.ROTATION_0:	//portrait
			getWindow().setSoftInputMode(
	     		       WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
			break;
		case Surface.ROTATION_90:	//landscape
			getWindow().setSoftInputMode(
	     		       WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
			break;
		case Surface.ROTATION_180:	//portrait
			getWindow().setSoftInputMode(
	     		       WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
			break;	
		case Surface.ROTATION_270:	//landscape
			getWindow().setSoftInputMode(
	     		       WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
			break;
		}
		String idNote="", activity_type;
		Intent myIntent = getIntent(); 
		try{
			activity_type= myIntent.getStringExtra("activity_type");
			if (activity_type.compareTo("modify")==0){					
				idNote = myIntent.getStringExtra("id_note");
				//GetNote(idNote);
			}
			else if (activity_type.compareTo("view")==0){
				idNote = myIntent.getStringExtra("id_note");
				//GetNote(idNote);
			}
			else if (activity_type.compareTo("new")==0){
				idNote = "unknown";
			}
			//else
				//Toast.makeText(getApplicationContext(),"Unknown activity type", Toast.LENGTH_SHORT).show();	
			//Toast.makeText(getApplicationContext(),activity_type + " " + idNote, Toast.LENGTH_SHORT).show();		
		}catch(Exception e){}
		
		//check when keyboard pops up
		/*final View activityRootView = findViewById(R.id.layout_main);  
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {  
            @Override  
            public void onGlobalLayout() {  
                int i = activityRootView.getRootView().getHeight();  
                int j = activityRootView.getHeight();  
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();  
                if (heightDiff > 200) { // if more than 100 pixels, its probably a keyboard...  
                	//Toast.makeText(getApplicationContext(), "keyboard pop up", Toast.LENGTH_SHORT).show();
                	pieMenuMedia = (SemiCircularRadialMenu)findViewById(R.id.mediaMenu);
                	pieMenuMedia.setTranslationY(120);
                	pieMenuMedia.setTranslationX(-150);
                	pieMenuEdit = (SemiCircularRadialMenu)findViewById(R.id.editMenu);
                	pieMenuEdit.setTranslationY(-110);
                	pieMenuEdit.setTranslationX(140);
                	
                	                	
                } else{  
                	//Toast.makeText(getApplicationContext(), "no pop up ", Toast.LENGTH_SHORT).show(); 
                	
                }  
             }  
        });  
		*/
        //detect when pressed ENTER on the soft keyboard inside the editing note
        
		final EditText myNote = (EditText) findViewById(R.id.editNote);
        myNote.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {                	
                	EditText edtContent=(EditText)findViewById(R.id.allContent);
                	webViewContent.append(formatText(v.getText().toString())+"<br>");
                	//wv.setText(Html.fromHtml(webViewContent.toString()));
                	//edtContent.loadData(webViewContent.toString(), "text/html", "utf-8");
                	edtContent.setText(Html.fromHtml(webViewContent.toString()));              	
                	v.setText("");                	
                	Toast.makeText(getApplicationContext(),webViewContent.toString(), Toast.LENGTH_LONG).show();		
                }
                return true;
            }
        });
        
        final EditText edtContent = (EditText) findViewById(R.id.allContent);
        edtContent.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				//when it has focus, the content edit view is not editable
				//code source from: http://thedevelopersinfo.com/2009/11/15/making-not-editable-edittext-in-android/
				if (hasFocus){					
					edtContent.setFilters(new InputFilter[]{ new InputFilter(){
						@Override
						public CharSequence filter(CharSequence source, int start,int end, Spanned dest, int dstart, int dend) {
								return source.length() < 1 ? dest.subSequence(dstart, dend): "";
						}
					}});
				}else{
					edtContent.setFilters(new InputFilter[]{ new InputFilter(){
						@Override
						public CharSequence filter(CharSequence source, int start,int end, Spanned dest, int dstart, int dend) {
								return null;
						}
					}});
				}
			}
		});
        //detect when text changed inside the content
        
        edtContent.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				//webViewContent.insert(start, s);
				//Log.d("TextEditor","Edit:"+s.toString());
				//Log.d("TextEditor","Changed:"+start+" "+count+" "+s.toString().substring(start,start+count));
				//Log.d("TextEditor","Web:"+webViewContent.toString());				
				/*String changedText=s.toString().substring(start,start+count);
				if (changedText.indexOf('\n')==-1)
					webViewContent.replace(start, start+before, changedText);
				*/
				//Log.d("TextEditor","Web after:"+webViewContent.toString());
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
      
        
      //detect when pressed ENTER on the soft keyboard inside the content note
       /* 
        edtContent.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                	//Toast.makeText(getApplicationContext(),"webview "+webViewContent.toString(), Toast.LENGTH_SHORT).show();		
                	//Toast.makeText(getApplicationContext(),"edt "+edtContent.getText().toString(), Toast.LENGTH_SHORT).show();		
                	int cursorPosition=edtContent.getSelectionStart();
                	String search=edtContent.getText().toString().substring(cursorPosition);
                	Log.d("TextEditor","cursorpos:"+cursorPosition);
                	Log.d("TextEditor","search:"+search);
                	Log.d("TextEditor","indexof:"+webViewContent.indexOf(search) +" length "+search.length());
    				
                	webViewContent.replace(webViewContent.indexOf(search), webViewContent.indexOf(search), "<br>");
                	edtContent.setText(Html.fromHtml(webViewContent.toString()));              	
                	
                	Log.d("TextEditor","webview after:"+webViewContent.toString());
                	
               }
                return true;
            }
        });*/
			
		/*if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
		
		pieMenuFile = (SemiCircularRadialMenu)findViewById(R.id.fileMenu); 
		pieMenuFile.setOpenMenuText("File");
		pieMenuFile.setCloseMenuText("File");
		//pieMenuFile.setOrientation(SemiCircularRadialMenu.HORIZONTAL_BOTTOM);
		ItemNew = new SemiCircularRadialMenuItem("New",getResources().getDrawable(R.drawable.newfile),"New file");
		pieMenuFile.addMenuItem(ItemNew.getMenuID(),ItemNew);
		ItemOpen = new SemiCircularRadialMenuItem("Open",getResources().getDrawable(R.drawable.openfile),"Open file");
		pieMenuFile.addMenuItem(ItemOpen.getMenuID(),ItemOpen);
		ItemSave = new SemiCircularRadialMenuItem("Save",getResources().getDrawable(R.drawable.save),"Save");
		pieMenuFile.addMenuItem(ItemSave.getMenuID(), ItemSave);
		ItemExit = new SemiCircularRadialMenuItem("Exit",getResources().getDrawable(R.drawable.exit),"Exit");
		pieMenuFile.addMenuItem(ItemExit.getMenuID(), ItemExit);
		pieMenuFile.setAngles(180, 90);
		//pieMenuFile.setLeft(-pieMenuFile.getWidth()/2);
		
		pieMenuOptions = (SemiCircularRadialMenu)findViewById(R.id.optionsMenu); 
		pieMenuOptions.setOpenMenuText("Options");
		pieMenuOptions.setCloseMenuText("Options");
		//pieMenuOptions.setOrientation(SemiCircularRadialMenu.HORIZONTAL_BOTTOM);
		ItemMultiauthor = new SemiCircularRadialMenuItem("Multiauthor",getResources().getDrawable(R.drawable.multi),"Multiauthor");
		pieMenuOptions.addMenuItem(ItemMultiauthor.getMenuID(),ItemMultiauthor);
		ItemShare = new SemiCircularRadialMenuItem("Share",getResources().getDrawable(R.drawable.share),"Share");
		pieMenuOptions.addMenuItem(ItemShare.getMenuID(),ItemShare);
		ItemDictionary = new SemiCircularRadialMenuItem("Dictionary",getResources().getDrawable(R.drawable.dictionary),"Add to dictionary");
		pieMenuOptions.addMenuItem(ItemDictionary.getMenuID(), ItemDictionary);
		pieMenuOptions.setAngles(180, 180);
		
		pieMenuStyle = (SemiCircularRadialMenu)findViewById(R.id.styleMenu); 
		pieMenuStyle.setOpenMenuText("Style");
		pieMenuStyle.setCloseMenuText("Style");
		//pieMenuStyle.setOrientation(SemiCircularRadialMenu.HORIZONTAL_BOTTOM);
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
		pieMenuStyle.setAngles(0, 90);
		
		pieMenuMedia = (SemiCircularRadialMenu)findViewById(R.id.mediaMenu); 
		pieMenuMedia.setOpenMenuText("Media");
		pieMenuMedia.setCloseMenuText("Media");
		//pieMenuMedia.setOrientation(SemiCircularRadialMenu.HORIZONTAL_BOTTOM);
		ItemRecorder = new SemiCircularRadialMenuItem("Recorder",getResources().getDrawable(R.drawable.recorder),"Recorder");
		pieMenuMedia.addMenuItem(ItemRecorder.getMenuID(),ItemRecorder);
		ItemSnapshot = new SemiCircularRadialMenuItem("Snapshot",getResources().getDrawable(R.drawable.snapshot),"Snapshot");
		pieMenuMedia.addMenuItem(ItemSnapshot.getMenuID(),ItemSnapshot);
		ItemAttach = new SemiCircularRadialMenuItem("Attach",getResources().getDrawable(R.drawable.attachment),"Attach file");
		pieMenuMedia.addMenuItem(ItemAttach.getMenuID(), ItemAttach);
		ItemLink = new SemiCircularRadialMenuItem("Link",getResources().getDrawable(R.drawable.link),"Link");
		pieMenuMedia.addMenuItem(ItemLink.getMenuID(), ItemLink);
		pieMenuMedia.setAngles(90, 90);
		
		pieMenuEdit = (SemiCircularRadialMenu)findViewById(R.id.editMenu); 
		pieMenuEdit.setOpenMenuText("Edit");
		pieMenuEdit.setCloseMenuText("Edit");
		//pieMenuEdit.setOrientation(SemiCircularRadialMenu.HORIZONTAL_BOTTOM);
		ItemCopy = new SemiCircularRadialMenuItem("Copy",getResources().getDrawable(R.drawable.copy),"Copy");
		pieMenuEdit.addMenuItem(ItemCopy.getMenuID(),ItemCopy);
		ItemCut = new SemiCircularRadialMenuItem("Cut",getResources().getDrawable(R.drawable.cut),"Cut");
		pieMenuEdit.addMenuItem(ItemCut.getMenuID(),ItemCut);
		ItemPaste = new SemiCircularRadialMenuItem("Paste",getResources().getDrawable(R.drawable.paste),"Paste");
		pieMenuEdit.addMenuItem(ItemPaste.getMenuID(), ItemPaste);	
		pieMenuEdit.setAngles(270, 90);
		
		ItemAttach.setIconDimen(60);
		ItemNew.setIconDimen(60);
		ItemBold.setIconDimen(60);
		ItemColor.setIconDimen(60);
		ItemCut.setIconDimen(60);
		ItemCopy.setIconDimen(60);
		ItemDictionary.setIconDimen(60);
		ItemDimension.setIconDimen(60);
		ItemExit.setIconDimen(60);
		ItemItalic.setIconDimen(60);
		ItemLink.setIconDimen(60);
		ItemMultiauthor.setIconDimen(60);	
		ItemOpen.setIconDimen(60);
		ItemPaste.setIconDimen(60);
		ItemRecorder.setIconDimen(60);
		ItemSave.setIconDimen(60);
		ItemShare.setIconDimen(60);
		ItemSnapshot.setIconDimen(60);
		ItemUnderlined.setIconDimen(60);
		
		
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
				//Toast.makeText(getApplicationContext(),"Save", Toast.LENGTH_SHORT).show();	
				Intent intent = new Intent(HotCornersActivity.this, SaveActivity.class);
				intent.putExtra("note_text",webViewContent.toString());
				startActivityForResult(intent, SAVE_NOTE);
			}
		});
		
		ItemExit.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(),"Exit", Toast.LENGTH_SHORT).show();		
				setResult(RESULT_OK);
				finish();
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
				bold=true;
				ApplyFormatingToSelection();
			}
		});
		
		ItemItalic.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Italic", Toast.LENGTH_SHORT).show();	
				italic=true;
				ApplyFormatingToSelection();
			}
		});
		
		ItemUnderlined.setOnSemiCircularRadialMenuPressed(new OnSemiCircularRadialMenuPressed() {			
			@Override
			public void onMenuItemPressed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"Underlined", Toast.LENGTH_SHORT).show();
				underlined=true;
				ApplyFormatingToSelection();
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
				//Toast.makeText(getApplicationContext(),"Snapshot", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
			    File mImageFile = new File(Environment.getExternalStorageDirectory()+File.separator+"DCIM"+File.separator+"Camera",  
			            "PIC"+System.currentTimeMillis()+".jpg");
			    mSelectedImagePath = mImageFile.getAbsolutePath();
			    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mImageFile));
			    startActivityForResult(intent, TAKE_PICTURE);
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
			        
			        de-fault : 
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
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // Check which request we're responding to
	    switch(requestCode){
	    case SAVE_NOTE: 
	    	// Make sure the request was successful
	        if (resultCode == RESULT_OK) {
	            // The user saved the note.
	        	Toast.makeText(this, "Save successful", Toast.LENGTH_SHORT).show();
	        }else{
	        	//cancel of the save
	        	Toast.makeText(this, "Save aborted", Toast.LENGTH_SHORT).show();
	        }
	    break;
	    case TAKE_PICTURE:
	    	if (resultCode == RESULT_OK) {
	    		//save picture mSelectedImagePath on the web server
	    		Toast.makeText(this, "Photo " +mSelectedImagePath +" saved", Toast.LENGTH_LONG).show();
	    	}
	    }
	}
	
	private String formatText(String textToFormat){
		StringBuffer htmlText=new StringBuffer(textToFormat);
		if (underlined){
			htmlText=new StringBuffer(HtmlFormat.Underline(htmlText.toString()));
			underlined=false;
		}
		if (bold){
			htmlText=new StringBuffer(HtmlFormat.Bold(htmlText.toString()));
			bold=false;
		}
		if (italic){
			htmlText=new StringBuffer(HtmlFormat.Italic(htmlText.toString()));
			italic=false;
		}
		return htmlText.toString();
	}
	
	private void ApplyFormatingToSelection(){
		EditText edtContent=(EditText)findViewById(R.id.allContent);
		int startSelection=-1, endSelection=-1;
		startSelection=edtContent.getSelectionStart();
		endSelection=edtContent.getSelectionEnd();
		if (startSelection>0 && endSelection>0 && startSelection<endSelection){
			String selectedText = edtContent.getText().toString().substring(startSelection, endSelection);
			String replacement=formatText(selectedText);
			//Toast.makeText(getApplicationContext(),"Selected text: "+selectedText+" replaced by " + replacement, Toast.LENGTH_SHORT).show();		
			//do the same for the content
			StringBuffer newText=new StringBuffer();
			//Toast.makeText(getApplicationContext(),webViewContent.toString() + ":"+webViewContent.toString().replace(selectedText, replacement), Toast.LENGTH_SHORT).show();				
			newText.append(webViewContent.toString().replace(selectedText, replacement));
			edtContent.setText(Html.fromHtml(newText.toString()));
			webViewContent=newText;
			
		}
	}
	
	/*public boolean onOrientationChanges() {
		  if(orientation == landscape)
		    if(settings.get("lock_orientation"))
		      return false;   // Retain portrait mode
		    else
		      return true; // change to landscape mode

		  return true; 
		}
	
	*/
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Toast.makeText(this, "Orientation: "+newConfig.orientation, Toast.LENGTH_LONG).show();
        // Checks the orientation of the screen for landscape and portrait and set portrait mode always
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        	getWindow().setSoftInputMode(
     		       WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
        	getWindow().setSoftInputMode(
      		       WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        	
        }
    }
	
}