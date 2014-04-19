package com.cyrilsabbagh.noteapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author Mike Weng mike@mikeweng.com
 * http://blog.mikeweng.com/2013/06/on-android-circular-style-layout.html
 * */

public class CircularView extends RelativeLayout {
	CircularView parent;
	Context context;
	View v[];
	int radius; // Radius 
	int startDeg,endDeg;
	int child_num; // Number of Children View
	
	public CircularView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		// Need to set this for calling onDraw();
		// https://groups.google.com/forum/?
		//     fromgroups#!topic/android-developers/oLccWfszuUo
/*		this.setWillNotDraw(false); 
		TypedArray a = context.getTheme()
				.obtainStyledAttributes(attrs, R.styleable.CircularView, 0, 0);
		try {	
			startDeg = a.getInteger(
					R.styleable.CircularView_startDeg, 0);
			endDeg = a.getInteger(
					R.styleable.CircularView_endDeg, 360);

		} finally {
			a.recycle();
		}
		parent = (CircularView) findViewById(R.id.circularview);
		radius = 30;*/
	}
	/*
	@Override
	protected void onDraw(Canvas c) {
		super.onDraw(c);	
		child_num = parent.getChildCount();
		if (child_num == 1) {
			// Only 1 children. i.e. No actual child view. 
			// (It must be View ID="center")
			return;
		}
		float degree_between_views;
		if (child_num == 2) { // Only 1 actual child. 
			degree_between_views = 0;
		} else {
			degree_between_views = 
					(float) (endDeg/(child_num - 2));
		}
		for (int i = 1; i < child_num; i++) {
			if (parent.getChildAt(i).getId() != R.id.center) {
				parent.getChildAt(i).setLayoutParams(modifyLayoutParams
					((RelativeLayout.LayoutParams)
					parent.getChildAt(i).getLayoutParams(), 
					(int)(startDeg + degree_between_views * (i-1))));
			}
		}
	}
	
	*/ 
	private RelativeLayout.LayoutParams modifyLayoutParams(
			RelativeLayout.LayoutParams lp, int degree) {
		/**
		 * Determine in Quadrant or on Axis
		 * Using Android convention. Right X-axis is degree 0, growing clockwise.
		 * */
	/*	degree = degree % 360;
		if (degree < 0) { // Make it positive
			degree += 360;
		}
		Log.i("DEBUG", "degree "+degree);
		if (degree == 0) { // right x-axis. Right
			lp.addRule(RelativeLayout.RIGHT_OF, R.id.center);
			//*****************modified***********************
			lp.addRule(RelativeLayout.CENTER_VERTICAL);
			//lp.addRule(RelativeLayout.ALIGN_BASELINE,R.id.center);
			//************************************************
			lp.setMargins(radius, 0, 0, 0);
		} else if (degree > 0 && degree < 90) { // Quadrant IV. Lower Right
			lp.addRule(RelativeLayout.BELOW, R.id.center);
			lp.addRule(RelativeLayout.RIGHT_OF, R.id.center);
			// Determine margin. 
			lp.setMargins(getMarginX(degree), getMarginY(degree), 0, 0); 
		} else if (degree == 90) { // Bottom y-axis. Bottom
			lp.addRule(RelativeLayout.BELOW, R.id.center); // Above Center.
			lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
			lp.setMargins(0, radius, 0, 0);
		} else if (degree > 90 && degree < 180) { // Quadrant III. Lower Left
			lp.addRule(RelativeLayout.BELOW, R.id.center);
			lp.addRule(RelativeLayout.LEFT_OF, R.id.center);
			// Determine margin. 
			lp.setMargins(0, getMarginX(degree - 90), getMarginY(degree - 90), 0);
		} else if (degree == 180) { // Right x-axis. Left
			lp.addRule(RelativeLayout.LEFT_OF, R.id.center);
			lp.addRule(RelativeLayout.CENTER_VERTICAL);
			lp.setMargins(0, 0, radius, 0);
		} else if (degree > 180 && degree < 270) { // Quadrant II. Upper Left
			lp.addRule(RelativeLayout.ABOVE, R.id.center);
			lp.addRule(RelativeLayout.LEFT_OF, R.id.center);
			// Determine margin. 
			lp.setMargins(0, 0, getMarginX(degree - 180), getMarginY(degree - 180));
		} else if (degree == 270) { // Top y-axis. Top
			lp.addRule(RelativeLayout.ABOVE, R.id.center); // Above Center.
			lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
			lp.setMargins(0, 0, 0, radius);
		} else if (degree > 270 && degree < 360) { // Quadrant I. Upper Right
			lp.addRule(RelativeLayout.ABOVE, R.id.center);
			lp.addRule(RelativeLayout.RIGHT_OF, R.id.center);
			// Determine margin. 
			lp.setMargins(getMarginX(360 - degree), 0, 0, getMarginY(360 - degree));
		}*/
		return lp;
	}
	/** X offset i.e. adjacent length */
	/*private int getMarginX(int degree) {
		return Math.abs((int)(Math.cos(Math.toRadians(degree)) * radius));
	}*/
	/** Y offset i.e. opposite length */
	/*private int getMarginY(int degree) {
		return Math.abs((int)(Math.sin(Math.toRadians(degree)) * radius));
	}*/
}