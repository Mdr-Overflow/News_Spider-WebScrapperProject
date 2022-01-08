package DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test_RESULTS {

	 public static String ResultsQ = "SELECT id, url, time, result, useragent FROM RESULTS";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
		
		DB_RESULT res = new DB_RESULT();
		String URL = "Example.com";
		String URL2 = "Exampler.com";
	    ArrayList<String> Entry = new ArrayList<String>();
	    Entry.add("wwwww");
	    Entry.add("qweqweq");
	    Entry.add("Nothing found");
	    Entry.add("Nothing found");
	    
	    for (String e : Entry)
	    	System.out.println(e);
	    
	    
	    String UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36";
	    
		try {
			boolean rez =  res.add_to_RESULTS(URL, Entry , UserAgent);
			System.out.println(rez);
			boolean rez2 =  res.add_to_RESULTS(URL2, Entry , UserAgent);
			System.out.println(rez2);
			// try catch for end connection
			res.END_CONNECTION();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		 
		 
		         // AFTER ADD
		     /*    
		         String input= "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/111.36";
		         input = ua;
		         String input2 = input;
		         
		         Pattern uaPat = Pattern.compile("Mozilla|AppleWebKit|Chrome|Safari|Opera|Edge*.[0-9]{2,}");
		         Matcher matcher = uaPat.matcher(input);
		         boolean matchFound = matcher.find();
		         if(matchFound) {
		        	 System.out.println(matchFound);
		        	 try {
		         last_index++;
		         PreparedStatement stmtSEC = dbConn.prepareStatement("INSERT INTO USERAGENTS (id,useragent) VALUES (?, ?)");
		         stmtSEC.setInt(1,last_index);
		         stmtSEC.setString(2, input);
		         stmtSEC.execute();
		         stmtSEC.close();
		         return true;
		         //dbConn.setAutoCommit(false);
		         
		         }
		         catch(Exception e) {
		        	 dbConn.close();
			         stmt.close();
			         //stmtSEC.close();
		        	 return false;
		         }
		         }
				return false;						}
		
		
		
	}*/

}
}
