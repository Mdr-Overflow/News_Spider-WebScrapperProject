package Proj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Result {

	String KeyWordAndDate;
	ArrayList<Map<String,ArrayList<String>>> UrlResults;
	String Fail = null;
	
	public Result(String keyWordAndDate, ArrayList<Map<String,ArrayList<String>>> urlResults) {
		super();
		KeyWordAndDate = keyWordAndDate;
		UrlResults = new ArrayList<Map<String,ArrayList<String>>>(urlResults);
		
	}


	public Result(String keyWordAndDate2, String string) {
		Fail = string;
		KeyWordAndDate = keyWordAndDate2;
	}




	public void setUrlResults(ArrayList<Map<String, ArrayList<String>>> urlResults) {
		UrlResults = urlResults;
	}


	@Override
	@JsonValue
	public String toString() {
		return "Result: " + "[KeyWordAndDate:" + KeyWordAndDate + ", UrlResults:" + UrlResults + ", Fail:" + Fail + "]";
	}


	
}
