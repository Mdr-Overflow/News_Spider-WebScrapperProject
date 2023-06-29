package DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DB_RESULT extends DB_CONNECT{

	
	Connection dbConn = null;
	 ResultSet rs = null;
	 Statement stmt = null;
	 int last_index = 0;
	 public static String ResultsQ = "SELECT id, url, time, result, useragent FROM RESULTS";
	 
	 public DB_RESULT() {
		 
			super(ResultsQ);
			if(_is == true) {
				
				
			
			dbConn = dbc;
			rs = rss;
			last_index = 0;
				
			// Create Statement
			try {
			  stmt = dbConn.createStatement();
			
			
				try {

	         System.out.println("Database accessed successfully...");  
	         
	         
	         rs = stmt.executeQuery(ResultsQ);
	         int i = 0;
	         while(rs.next()){
	            //Display values
	           // System.out.print("ID: " + rs.getInt("id"));
	            i = rs.getInt("id");   //"SELECT id, url, time, result, useragent FROM RESULTS";
	          //  System.out.println("URL : " + rs.getString("url"));
	           // System.out.println("TIME : " + rs.getTime("time"));
	           // System.out.println("RESULT : " + rs.getString("result"));
	           // System.out.println("User-Agent : " + rs.getString("useragent"));
	         }
				last_index = i;
				}
				
				catch(Exception e) {e.printStackTrace();
				stmt.close();
		         //rs.close();
				
				}
			
				//stmt.close();
				System.out.println("all good for RESULTS");
			
			}catch(Exception e) {e.printStackTrace();}}
			
			
			
			}
			
	 
	 //DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	//	LocalDateTime now = LocalDateTime.now();
	 
	 public boolean add_to_RESULTS(String URL , ArrayList<String> Entry,  String UserAgent) throws SQLException {
	         // AFTER ADD
	         
	        
		 for (String e : Entry)
		    	System.out.println(e);
		 
		 //Date date = new Date(System.currentTimeMillis());
		// LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		 //Date date = (Date) Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		 Timestamp time = new Timestamp(System.currentTimeMillis());
		 //java.sql.date
	        	// System.out.println(matchFound);
	        	 try {
	        		 dbConn.setAutoCommit(true);
	      for (String entry : Entry) {
	    	  last_index++;
	    	  //System.out.println(last_index);
	    	 // System.out.println(URL);
	    	  //System.out.println(time);
	    	 // System.out.println(entry);
	    	 // System.out.println(UserAgent);
	         
	         PreparedStatement stmtSEC = dbConn.prepareStatement("INSERT INTO RESULTS (id, url, result, time, useragent) VALUES (?, ?, ?, ?, ?)");
	         stmtSEC.setInt(1,last_index);
	         stmtSEC.setString(2, URL);
	         stmtSEC.setString(3, entry);
	         stmtSEC.setTimestamp(4,time);
	         stmtSEC.setString(5,UserAgent);
	         stmtSEC.execute();
	         stmtSEC.close();
	      }
	         
	         
	         return true;
	         //dbConn.setAutoCommit(false);
	         
	         }
	         catch(Exception e) {
	        	 e.printStackTrace();
	        	 dbConn.close();
		         stmt.close();
		         //stmtSEC.close();
	        	 return false;
	         }						}
	 
	
	 public void END_CONNECTION() throws SQLException {
		 dbConn.close();
         stmt.close();
		 
	 }
	 public boolean del_from_RESULT(int id) throws SQLException {
         //DELETE FROM USERAGENT WHERE useragent= ?
	
	 	
	 		
	 		try {
         PreparedStatement stmtSEC = dbConn.prepareStatement("DELETE FROM RESULTS WHERE id = ?");
         stmtSEC.setInt(1, id);
         stmtSEC.execute();
         stmtSEC.close();
         return true;
         //dbConn.setAutoCommit(false);
         
         }
	 		
	 	catch(Exception e) {
	 		 dbConn.close();
	         stmt.close();
	        // stmtSEC.close();
	 		return false;
	 	}
 													}
	 
	 
	 
	 //public void GetOrderedView  //DONE
	
	 
	 //public void Search
		 
	 
	 
	 
	public int GetNrSiteRes(String URL) throws SQLException{
		try {
	         PreparedStatement stmtSEC = dbConn.prepareStatement("SELECT COUNT(?) max FROM RESULT");
	         stmtSEC.setString(1, URL);
	         ResultSet remax = stmtSEC.executeQuery();
	        
	          int max_=0;
			
				if(remax.next()) max_ = remax.getInt("max");
				
				
	         
	         stmtSEC.close();
	         return max_;
	         //dbConn.setAutoCommit(false);
	         
	         }
		 		
		 	catch(Exception e) {
		 		 dbConn.close();
		         stmt.close();
		         return 0;
		        // stmtSEC.close();
		 	
		 	}
		
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
 public static final String Del = "DELETE FROM RESULT";
	 
	 public boolean del_RESULT() {
		 try {
			 last_index=0;
			 Statement stmt = dbConn.createStatement();
			stmt.execute(Del);
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		 
	 
	 }



	
	
}
