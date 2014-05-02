package com.cyrilsabbagh.noteapp.controllers;

public class HtmlFormat {
	public HtmlFormat(){}
	public static String Underline(String text){
		return new String("<u>"+text+"</u>");
	}
	
	public static String Bold(String text){
		return new String("<b>"+text+"</b>");
	}
	
	public static String Italic(String text){
		return new String("<i>"+text+"</i>");
	}
	
	public static String getNewline(){
		return new String("<br>");
	}
}
