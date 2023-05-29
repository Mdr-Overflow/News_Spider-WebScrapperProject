package Proj;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.*;
import java.util.regex.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import crawlercommons.robots.*;
//import jdk.internal.org.xml.sax.Locator;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
import com.microsoft.playwright.*;

import com.microsoft.playwright.impl.*;


// datetime , os
//urlib


public class Utils {





	public static final String DEFAULTmaxthreads = "12";
	public static final String DEFAULTrequestrate = "200";



	public static class JsonToHashtableUtility {

		public static Hashtable<String, String[]> convertJsonToHashtable(String json) {
			Hashtable<String, String[]> hashtable = new Hashtable<>();

			try {
				JSONObject jsonObject = new JSONObject(json);
				JSONArray tasksArray = jsonObject.getJSONArray("Tasks");

				for (int i = 0; i < tasksArray.length(); i++) {
					JSONObject taskObject = tasksArray.getJSONObject(i);

					String name = taskObject.getString("Name");
					String page = taskObject.getString("Page");
					String domain = taskObject.getString("Domain");
					String number = taskObject.getString("Number");
					String account = taskObject.getString("Account");
					String rating = taskObject.getString("Rating");
					JSONArray urlsArray = taskObject.getJSONArray("Urls");

					String[] urls = new String[urlsArray.length()];
					for (int j = 0; j < urlsArray.length(); j++) {
						urls[j] = urlsArray.getString(j);
					}

					hashtable.put(name, new String[]{page, domain, number, account, rating});
					hashtable.put(name + "_Urls", urls);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return hashtable;
		}

		public static Hashtable<String, String[]> ReadTasks() {
			String filePath = "Tasks.json";
			Hashtable<String, String[]> hashtable = new Hashtable<String,String[]>();
			String[] strings = {"None"};
			hashtable.put("None",strings);


			try {
				String json = new String(Files.readAllBytes(Paths.get(filePath)));

				 hashtable = convertJsonToHashtable(json);

				// Print the hashtable content
				for (String key : hashtable.keySet()) {
					System.out.println(key + ": " + String.join(", ", hashtable.get(key)));
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Failed to read the JSON file: " + filePath);
			}
			return hashtable;
		}
	}


	public static int CreateFile(String FileName) {
		    try {
		      File myObj = new File(FileName);
		      if (myObj.createNewFile()) {

		        System.out.println("File created: " + myObj.getName());
		        return 0;
		      } else {
		        System.out.println("File already exists.");
		        return 1;
		      }
		    } catch (IOException e) {
		      System.out.println("File open error.");
		      e.printStackTrace();
		      return -1;
		    }
		  }

	public static void  WriteToFileNOAPPEND(String FileName, String Content) {

	    try {


	    	if(new File(FileName).isFile()) {
	    		if ("IGNORE" == Content) {
	    			System.out.println("IGNORED 222222222222222");
	      FileWriter myWriter = new FileWriter(FileName,false);
	    		}
	    		else {

	       FileWriter myWriter = new FileWriter(FileName,false);
	      BufferedWriter bw = new BufferedWriter(myWriter);
	      System.out.println(Content + "THIS HAS BEEN WRITTEN TO FILE");
	      bw.write(Content);
	      bw.newLine();
	      bw.close();
	    		}}
	    	else {
	    		if(CreateFile(FileName) == 0 )
	    		{
	    			FileWriter myWriter = new FileWriter(FileName,false);
	  		      BufferedWriter bw = new BufferedWriter(myWriter);
	  		      bw.write(Content);
	  		      bw.newLine();
	  		      bw.close();
	    		}

	    	}

	    } catch (IOException e) {
	      System.out.println("File write error.");
	      e.printStackTrace();

	    }
	  }






	public static void  WriteToFile(String FileName, String Content) {

		    try {
		    	if(new File(FileName).isFile()) {
		      FileWriter myWriter = new FileWriter(FileName,false);
		      BufferedWriter bw = new BufferedWriter(myWriter);
		      bw.write(Content);
		      bw.newLine();
		      bw.close();
		    	}
		    	else {
		    		if(CreateFile(FileName) == 0 )
		    		{
		    			FileWriter myWriter = new FileWriter(FileName,true);
		  		      BufferedWriter bw = new BufferedWriter(myWriter);
		  		      bw.write(Content);
		  		      bw.newLine();
		  		      bw.close();
		    		}

		    	}

		    } catch (IOException e) {
		      System.out.println("File write error.");
		      e.printStackTrace();

		    }
		  }


	public static void  WriteToFileArray(String FileName, ArrayList<String> Content) {

		try {
	    	if(new File(FileName).isFile()) {
	      FileWriter myWriter = new FileWriter(FileName,true);
	      BufferedWriter bw = new BufferedWriter(myWriter);
	      for (String content : Content) {
	      bw.write(content);
	      bw.newLine();
	      }
	      bw.close();
	    	}
	    	else {
	    		if(CreateFile(FileName) == 0 )
	    		{
	    			FileWriter myWriter = new FileWriter(FileName,true);
	  		      BufferedWriter bw = new BufferedWriter(myWriter);

	  		    for (String content : Content) {
	  		      bw.write(content);
	  		      bw.newLine();
	  		      }
	  		      bw.close();
	    		}

	    	}

	    } catch (IOException e) {
	      System.out.println("File write error.");
	      e.printStackTrace();

	    }
	  }







public static ArrayList<String> DumpToDB( Page page,String KeyWord) {

		Logger l = new Logger(true);

		String to_log = "Dumped " + KeyWord;

		l.log(to_log);

	    String to_search = "text=" + KeyWord;

	    Locator loc = page.locator(to_search);

	     ArrayList<String> contents = new ArrayList<String>();

	    for( String content : loc.allTextContents()) {

	    	Pattern pattern = Pattern.compile("\\s{2}|\\n", Pattern.CASE_INSENSITIVE);

	        pattern.matcher(content).replaceAll("");

	        contents.add(content);

	        l.log(content);
	    }

	    l.logArray(contents);
	    return contents;
	}





public static void WriteToJson(String KeyWord, ArrayList<Map<String,ArrayList<String>>> Url_Results ) {


	Logger l = new Logger(true);

	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

	String KeyWordAndDate = KeyWord + " " + (dateFormat.format(now));

	//ArrayList<String> Lines = new ArrayList<String>();

	l.log(Url_Results.toString());
	if (Url_Results != null) {

	Result res = new Result(KeyWordAndDate, Url_Results);
	 Path path = Paths.get("Results.json");


	l.log(res.toString());
	ObjectMapper mapper = new ObjectMapper();
	 String json;
	try {
		json = mapper.writeValueAsString(res);
		try {
			mapper.writeValue(Paths.get("Results.json").toFile(), json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (JsonProcessingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();}
	}}








public static boolean isRobotsParsable(String url , String User_Agent) {

	Logger l = new Logger(true);

	l.log(url + Logger.isRobotParsableLOG);
	// https://
	try {
	URL urrl = new URL(url);
	l.log(urrl.toString());
	if( RobotsParser.robotSafe(urrl,User_Agent) == 0 )
		return true;

	else if ( RobotsParser.robotSafe(urrl,User_Agent) == -1 ) {
		l.log(url+ " MalformedURL");
	return false;}

	else if ( RobotsParser.robotSafe(urrl,User_Agent) == -2 ) {
		l.log(" NOT ALLOWED ON " + url);
	return false;}

	}
	catch (MalformedURLException e) {
		l.log("URL IS GIVEN WRONG " + url);

	}

	l.log("UNKNOWN ERROR ON " + url);
	return false;

}


public static void Delete_Temp_File(String Filename) {

	 	File myObj = new File(Filename);
	    if (myObj.delete()) {
	      System.out.println("Deleted the file: " + myObj.getName());
	    } else {
	      System.out.println("Failed to delete the file.");
	    }

}




public static ArrayList <String> ReadFromURLs() {

	ArrayList <String> urls = new ArrayList<String>();
	BufferedReader reader;
	try {
		reader = new BufferedReader(new FileReader(
				"Urls.txt"));
		String line = reader.readLine();
		if (line != null) urls.add(line);
		//String line_b = null;
		while (line != null) {
			System.out.println(line);
			line = reader.readLine();
			if (line != null) urls.add(line);
		}

		System.out.println("DONE READING Urls");
		reader.close();
	return urls;
	} catch (IOException e) {
		e.printStackTrace();
		return null ;
	}



}


public static String ReadFromOptions() {

	BufferedReader reader;
	try {
		reader = new BufferedReader(new FileReader(
				"Options.txt"));
		String line = reader.readLine();
		String line_b = null;
//		while (line != null) {
//			System.out.println(line);
//			line = reader.readLine();
//			if (line != null) line_b = line;
//		}
//		if (line_b != null) line = line_b;
		System.out.println("DONE READING OPTIONS");
		reader.close();
	return line;
	} catch (IOException e) {
		e.printStackTrace();
		return "error";
	}
	//return "error";

}

public static Object[] ParseOptions() {
	String line = ReadFromOptions();
	String[] options;
	Object[] result = new Object[11]; //11 ELEMS
	for ( int i =0 ; i<= 7 ;i++)
	result[i] = 0;

	result[8] = "a";
	result[9] = "a";
	result[10] = 0;

	//object [] result = {0,0,0,0,0,0,0,0,0}; // 9 ELEMS
	Object[] error = {-1};

	int i = 0;
	System.out.println(line);
	if (!Objects.equals(line, "error")) {

	System.out.println(line + "    <--------------------------- HERE");
	options = line.split(",");

	for (String option : options) {

	System.out.println(option);
	if(option.charAt(option.length()-1) == 'O' && i < 6) {
	result[i] = 0;}
	else if (i < 6)
	{
	result[i] = 1;
	}


	if (i>=6 && i <= 7)
	{
		System.out.println(option);
		result[i] = Integer.parseInt(option);
		//i++;
	}
	// new stuff
	if (i == 8 || i == 9 ){

		result[i] = option;
 	}
	if (i == 10){

		result[i] = Integer.parseInt(option);
	}




	if (i < 9)
	System.out.println(result[i] + "  " + Integer.toString(i));

	i++;
	}

		return result;
	}

	else return error;   //Options: COOKIE JAR: NO,SAFE URLS: NO,SCREENCAP: NO,DEL URL FILE: YES,DEL LOG FILE: YES,DEL RESULTS FILE: NO,12,200,800x600,Dark,1
						  //			0                  1           2           3                4                   5                6  7    8      9   10

}



public static String ReadFromResults() {

	BufferedReader reader;
	try {
		reader = new BufferedReader(new FileReader(
				"Results.json"));
		String line = reader.readLine();
		if(line != null)
			return line;
	} catch (IOException e) {
		e.printStackTrace();
		return "error";
	}
	//return "error";
	return "error";

}




public static List<String> parseResults(String Url)
{

	String line = ReadFromResults();
	System.out.println(line);
	if (line != "error") {
		 if(line.contains("Fail:null")) {
		 List<String> allMatches = new ArrayList<String>();
		 Matcher url = Pattern.compile("(" + Url + "(.*))")
		     .matcher(line);
		 String after_url = null;
		 if(url.find()) {
			 after_url = url.group(2);


		 }
		 System.out.println(after_url);

		 String NORES = "There are no results for url, We are not allowed on url, couldn't get past gdpr for url or it doesnt't exist";
		 List<String> Wrong = new ArrayList<String>();
		 Wrong.add(NORES);
		 if(after_url == null) return Wrong;


		 after_url=after_url.replaceAll("\\s\\s|\\\\n|]|}|=|\"\"", "");
		 after_url=after_url.replaceAll("\n", "");
		 after_url=after_url.replaceAll("’","");
		 after_url=after_url.replaceAll("\\[","");
		 after_url=after_url.replaceAll("\\\\","");
		 after_url=after_url.replaceAll(",\\s", "\n");
		 after_url=after_url.replaceAll("Fail:null","");
		 System.out.println(after_url);
		 allMatches.add(after_url);
		 Matcher date = Pattern.compile("20[0-9][0-9].*(?=, U)")
			     .matcher(line);

		 while (date.find()) {
			   allMatches.add("\n AT "+date.group());
			   System.out.println(date.group());
			 }

		return allMatches;  //https:\/\/theguardian.com.*, //20[0-9][0-9].*(?=, U)
	}

		 else {

			 List<String> allMatches = new ArrayList<String>();
			 Matcher date = Pattern.compile("20[0-9][0-9].*(?=, U)")
				     .matcher(line);

			 allMatches.add("Nothing was found");
			 while (date.find()) {
				   allMatches.add("\n AT "+date.group());
				   System.out.println(date.group());
				 }

			 return allMatches;
		 }

	}

return null;
}







}
