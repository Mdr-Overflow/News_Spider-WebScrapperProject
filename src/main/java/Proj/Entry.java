package Proj;

import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonValue;

public class Entry {
	Map<String,ArrayList<String>> entry ;
	
	
	public Entry(Map<String, ArrayList<String>> entry) {
		super();
		this.entry = entry;
	}

	@JsonAnyGetter	
	public Map<String, ArrayList<String>> getEntry() {
		return entry;
	}

	public void setEntry(Map<String, ArrayList<String>> entry) {
		this.entry = entry;
	}

	@Override
	@JsonValue
	public String toString() {
		return "Entry [entry=" + entry + "]";
	}
	
	
	
}
