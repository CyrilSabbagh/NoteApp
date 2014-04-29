package com.cyrilsabbagh.noteapp.dataModel;

import java.util.ArrayList;
import java.util.List;

public class Note {
	
	private int id;
	private String owner; // This is the owner of this note
	private String name; // This is the name of the note
	private String content; // This is temporary we will replace it when we know how we want to store the content
	private String date; // This is the date when the note was taken (automatically set when creating a note)
	private float lat; // This is the geolocation where the note was taken (automatically set from GPS)
	private float lng;
	private boolean share;
	private int course_id;
	private List<String> images;
	private List<String> sounds;
	
	public Note(){
		images = new ArrayList<String>();
		sounds = new ArrayList<String>();
	}
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	public boolean isShare() {
		return share;
	}
	public void setShare(boolean share) {
		this.share = share;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public List<String> getSounds() {
		return sounds;
	}
	public void setSounds(List<String> sounds) {
		this.sounds = sounds;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
}
