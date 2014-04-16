package com.cyrilsabbagh.noteapp.dataModel;

public class Note {
	
	private String owner; // This is the owner of this note
	private String name; // This is the name of the note
	private String content; // This is temporary we will replace it when we know how we want to store the content
	private String date; // This is the date when the note was taken (automatically set when creating a note)
	private String location; // This is the geolocation where the note was taken (automatically set from GPS)
	
	
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
