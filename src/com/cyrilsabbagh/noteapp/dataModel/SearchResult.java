package com.cyrilsabbagh.noteapp.dataModel;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
	private List<Course> coursesResults;
	private List<Note> notesResults;
	
	public SearchResult(){
		coursesResults = new ArrayList<Course>();
		notesResults = new ArrayList<Note>();
		
	}

	public List<Course> getCoursesResults() {
		return coursesResults;
	}

	public void setCoursesResults(List<Course> coursesResults) {
		this.coursesResults = coursesResults;
	}

	public List<Note> getNotesResults() {
		return notesResults;
	}

	public void setNotesResults(List<Note> notesResults) {
		this.notesResults = notesResults;
	}
	
	
	
}
