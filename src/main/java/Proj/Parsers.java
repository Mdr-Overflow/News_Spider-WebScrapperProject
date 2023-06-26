package Proj;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.playwright.Page;

public class Parsers {
	
	static final String Param_List = "Title,MetaData,All,-FileAsName,--Show";
	
	public static ArrayList<String> AllParse_NoAsync(Page page, String KeyWord, String url) {
	    
		// DOES NOT GET ELEMENTS LIKE THIS : </IMG ... SRC = ..KEYWORD.. > 
		
		Logger l = new Logger(true);
		l.log(Logger.AllParse_NoAsyncLOG);
		String text = page.textContent("*");
	   // System.out.println(text);
		//l.log(text);
	    String Regex = KeyWord;
	    Pattern pattern = Pattern.compile(Regex, Pattern.CASE_INSENSITIVE);
	    
	    Matcher RegexResult = pattern.matcher(text);
	    l.log(RegexResult.toString() + Logger.AllParseResultLOG + url);
	    if (RegexResult.find()) {
	     String  RegexResultString = RegexResult.group();
	
	    
	    //l.log("@@@@@@" + RegexResultString);
	    l.log(Logger.AllParseFounAllContextLOG);
	    if (RegexResultString != null) {
	    	
	       return Utils.DumpToDB(page,KeyWord);
	    
	    }
	    
	   
	        l.log(Logger.AllParseFailedLOG);
	         // DISCARD
	        return null;
	
	    }	
	    return null;
	}
	
	
	public static ArrayList<String> ParserNoAsync(Page page ,String Param ,String KeyWord , String url) throws Exception {
	    Logger l = new Logger(true);
		l.log(Logger.ParserStartedLOG + url);
		l.log(url + Logger.ParserNoAsyncLOG);
	   String[] Params = Param.split(",");
	   
	    for ( int i = 0 ; i<= Params.length ; i++  ) {
	        l.log(Params[i]);
	        
	        if (!Param_List.contains(Params[i]) )
	        	
	        	throw new Exception("Params invalid");
	       
	        else {
	            if (Params[i] == "Title") {
	               
	            	if (page.title().contains(KeyWord) ) {
	                   // Dump_to_DB(page,KeyWord);
	            	}
	            	else {
	                     l.log("Discarded " + url);
	                     return null;
	                //DISCARD     
	            	}}
	            if (Params[i] == "All") {
	              try {
	               ArrayList<String> rez= new ArrayList<String>(Objects.requireNonNull(AllParse_NoAsync(page, KeyWord, url)));
	               return rez;
	              }
	              catch (Exception e){
	              l.log(url + "Failed  Parsing ****");
	              return null;  
	              }
	           
	            }}
	    }
		return null;
	}
	
	
	
}
