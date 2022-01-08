package Proj;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.javatuples.Pair;

public class Headers {

	public ArrayList<String> UserAgent_List = new  ArrayList<String>();
	public ArrayList<HashMap<String, String>> Headers_List = new  ArrayList<HashMap<String,String>>();;
	
	
	public Headers() {
		super();
		ArrayList<String> UserAgent_List2 = new  ArrayList<String>();
		UserAgent_List2.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36");
		UserAgent_List2.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36 Edg/87.0.664.75");
		UserAgent_List2.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36");
		
		UserAgent_List.addAll(UserAgent_List2);
		ArrayList<HashMap<String, String>> headers_list = new  ArrayList<HashMap<String,String>>();
		
		
		ArrayList<HashMap<String, String>> Headers_List2 = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> val  = new HashMap<String, String>() {{
		    put("authority", "httpbin.org");
		    put("key2", "value2");
		    put("cache-control", "max-age=0");
		    put("sec-ch-ua", "\"Chromium\";v=\"92\", \" Not A;Brand\";v=\"99\", \"Google Chrome\";v=\"92\"");
		    put("sec-ch-ua-mobile", "?0");
		    put("upgrade-insecure-requests", "1");
		    put("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");
		    put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		    put("sec-fetch-site", "none");
		    put("sec-fetch-mode", "navigate");
		    put("sec-fetch-user", "?1");
		    put("sec-fetch-dest", "document");
		    put("accept-language", "en-US,en;q=0.9");
		    
		}};
		
		Headers_List2.add(val);
	
		HashMap<String, String> val2  = new HashMap<String, String>() {{
			 put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
	            put("Accept-Encoding", "gzip, deflate, br");
	            put("Accept-Language", "en-US,en;q=0.5");
	            put("Host", "httpbin.org");
	            put("Sec-Fetch-Dest", "document");
	            put("Sec-Fetch-Mode", "navigate");
	            put("Sec-Fetch-Site", "none");
	            put("Sec-Fetch-User", "?1");
	            put("Upgrade-Insecure-Requests", "1");
	            put("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv,90.0) Gecko/20100101 Firefox/90.0");
	            put("X-Amzn-Trace-Id", "Root=1-60ff12e8-229efca73430280304023fb9");
		    
		}};
		
		Headers_List2.add(val2);
		
		Headers_List.addAll(Headers_List2);
		
	}

	public   ArrayList<HashMap<String,String>>  Generate_Agents(DB.DB_AGENTS  ag ) {
	
		
		String Browser_string = "((Chrome|Firefox)";
	
		//ag.GET_AGENTS();
		
		//convertWithStream
		
		/*
		Random r = new Random();
		int low = 0;
		int high = 1;
		int result = r.nextInt(high-low) + low;
		*/
	  
	    Map<String,String> agents = new HashMap<String,String>(ag.GET_AGENTS());
	   Map<String, String> headers = new HashMap<String,String>();
	   ArrayList<HashMap<String, String>> Agents = new ArrayList<HashMap<String,String>>();
	   
	   // GOOD 
	    for(String header : agents.keySet()) {
	    	for (String useragent : agents.values()) {
	    		System.out.println("HEADDDDDDDEERR ------>" + header);
	    	headers.putAll(DB.DB_UTILS.convertWithStream(header));
	    	
	    	String  toRegex = useragent;
		    
		    
		    String  Regex = Browser_string + "\\\\/([0-9]{2,}))"; // \\ 
		    
		    Regex = "((Chrome|Firefox)/([0-9]{2,}))";
		    System.out.println(toRegex + " <----user  " +  Regex + "   <---- Regex Pattern");
		    Pattern pattern = Pattern.compile(Regex);
		   
		    Matcher ToRegex = pattern.matcher(toRegex);
		    
		  
		   
		    if (ToRegex.find()) {
		    System.out.println(ToRegex.group(3));
		    toRegex = ToRegex.group(3);
		    }
		   
		    headers.put("user-agent",useragent);
		    headers.put("sec-ch-ua", "\"Chromium\";v=" + toRegex + ", \" Not A;Brand\";v=\"99\" , \"Google Chrome\";v='" + toRegex);
	    	}
	    	Agents.add((HashMap<String, String>) headers);
	    }
	    
	   
	    
	    
		return Agents;
	    
	   // return headers.get("sec-ch-ua");
	}
	
}

/*


*/