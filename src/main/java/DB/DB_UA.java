package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Proj.Headers;

public class DB_UA extends DB_CONNECT {
	 public static final String ResultsQ  = "SELECT id, useragent FROM USERAGENTS";
	 
	 Connection dbConn = null;
	 ResultSet rs = null;
	 Statement stmt = null;
	 int last_index = 0;
	 public DB_UA() {
		 
		 	
		 	super(ResultsQ);
			if(_is == true) {
				
				
			
			dbConn = dbc;
			rs = rss;
			
			
			
			// Create Statement
			try {
			 stmt = dbConn.createStatement();
			
			
				try {

	         System.out.println("Database accessed successfully...");  
	         
	         
	         rs = stmt.executeQuery(ResultsQ);
	         int i = 0;
	         while(rs.next()){
	            //Display values
	            System.out.print("ID: " + rs.getInt("id"));
	            i = rs.getInt("id");
	            System.out.println("User-Agent : " + rs.getString("useragent"));
	         }
				last_index = i;
				}
				
				catch(Exception e) {e.printStackTrace();
				stmt.close();
		         //rs.close();
				
				}
			
			
			
			}catch(Exception e) {e.printStackTrace();}}
			
			
			
			
			}
			
	 
	 
	 
	 public boolean add_to_UA(String ua) throws SQLException {
	         // AFTER ADD
	         
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
	 
	 
	 
	 public boolean del_from_UA(String ua) throws SQLException {
	         //DELETE FROM USERAGENT WHERE useragent= ?
		 String input2 = ua;
         
         Pattern uaPat = Pattern.compile("Mozilla|AppleWebKit|Chrome|Safari|Opera|Edge*.[0-9]{2,}");
         Matcher matcher = uaPat.matcher(input2);
         boolean matchFound = matcher.find();
		 
		 	if(matchFound) {
		 		 System.out.println(matchFound);
		 		try {
	         PreparedStatement stmtSEC = dbConn.prepareStatement("DELETE FROM USERAGENTS WHERE useragent= ?");
	         stmtSEC.setString(1, input2);
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
			return false;
	 													}
	 
	 
	 public void END_CONNECTION() throws SQLException {
		 dbConn.close();
         stmt.close();
		 
	 }
	 
	 
	 
 public static final String Del = "DELETE FROM USERAGENTS";
	 
	 public boolean del_UA() {
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
	 
	 public void Populate_UA_DEFAULT() {
		 del_UA();
		 Headers h = new Headers();
			for (String user :h.UserAgent_List)
				try {
					System.out.println(add_to_UA(user));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 }
	 
	 
	 
	 public Connection GetConn() {
		 return dbConn;
	 }
	 
	 
}
