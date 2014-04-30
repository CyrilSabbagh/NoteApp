package com.cyrilsabbagh.noteapp.dataModel;

import java.util.ArrayList;
import java.util.List;

public class Course {
	
	private int id;
	private String name; // This is the name of the course
	private String school; // This is the school where this course was taken
	private List<Note> notes; // This is the list of notes taken during this course
	private Dictionary dictionary; // This is the dictionary of all notes in this course
	private String year; // This is the year this course was taken
	private String owner;
	
	public Course(){
		notes = new ArrayList<Note>(); // Initialize the ArrayList of notes in the constructor
		dictionary = new Dictionary(); // Initialize the dictionary for this Course
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
