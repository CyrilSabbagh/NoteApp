package com.cyrilsabbagh.noteapp.dataModel;

import java.util.HashMap;
import java.util.Map;

public class Dictionary{
	private Map<String,String> definition; // This the map that contains all the dictionary definitions
	
	public Dictionary(){
		definition = new HashMap<String,String>(); // Create the HashMap in the Constructor
	}

	public Map<String,String> getDefinition() {
		return definition;
	}

	public void setDefinition(Map<String,String> definition) {
		this.definition = definition;
	}
}
