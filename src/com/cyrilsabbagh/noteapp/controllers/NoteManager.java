package com.cyrilsabbagh.noteapp.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import com.cyrilsabbagh.noteapp.dataModel.*;

public class NoteManager {
	
	// Create new course with empty dictionary and empty notes
	public Course newCourse(String _name,String _school,String _year){
		Course course = new Course();
		course.setName(_name);
		course.setSchool(_school);
		course.setYear(_year);
		return course;
	}
	
	// Create new course with existing notes and dictionary
	public Course newCourse(String _name,String _school,String _year,ArrayList<Note> notes,Dictionary dic){
		Course course = new Course();
		course.setName(_name);
		course.setSchool(_school);
		course.setYear(_year);
		course.setNotes(notes);
		course.setDictionary(dic);
		return course;
	}
	
	// Add a new definition to a course dictionary
	public void addToDictionary(Course course,String key,String def){
	
		course.getDictionary().getDefinition().put(key, def);
		
	}
	
	// Remove a definition from a course dictionary
	public void removeFromDictionary(Course course,String key){
		course.getDictionary().getDefinition().remove(key);
	}
	
	// Create a new note and automatically set the datetime (TODO: set location)
	@SuppressLint("SimpleDateFormat")
	public Note newNote(String _owner,String _name,String _content){
		Note note = new Note();
		note.setOwner(_owner);
		note.setName(_name);
		note.setContent(_content);
		
		Locale locale = new Locale("en_US"); // Using en_US makes format easy to sort
		Locale.setDefault(locale);
		
		String pattern = "yyyy-MM-dd HH:mm:ss Z"; // Define the pattern with Z to show the gmt offset
		SimpleDateFormat  formatter = new SimpleDateFormat(pattern);
		note.setDate(formatter.format(new Date()));
		
		return note;
	}
	
	// Add a new note to a course
	public void addNote(Course course,Note note){
		course.getNotes().add(note);
	}
	
	// Remove a note from a course
	public void removeNote(Course course,Note note){
		course.getNotes().remove(note);
	}
}
