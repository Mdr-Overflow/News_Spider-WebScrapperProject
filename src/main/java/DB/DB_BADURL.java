package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DB_BADURL extends DB_CONNECT{

/*
 static final String sql_T3 = "CREATE TABLE BADURL " +
            "(id INTEGER not NULL, " +
            " url VARCHAR(255), " + 
            " time TIMESTAMP, " +
            " status VARCHAR(255), " +
            " PRIMARY KEY ( id ))";
 */
	
	 Connection dbConn = null;
	 ResultSet rs = null;
	 Statement stmt = null;
	 int last_index = 0;
	 public static String ResultsQ = "SELECT id, url, time, status FROM BADURL";
	 
	 public DB_BADURL() {
		 
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
	            System.out.print("ID: " + rs.getInt("id"));
	            i = rs.getInt("id");   //"SELECT id, url, time, result, useragent FROM RESULTS";
	            System.out.println("URL : " + rs.getString("url"));
	            System.out.println("TIME : " + rs.getTime("time"));
	            System.out.println("STATUS : " + rs.getString("status"));
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
	 
	 
	 public static final String Gen = "INSERT INTO BADURL (id, url, time, status)\r\n"
	 		+ "SELECT id, url ,time, 'BAD'\r\n"
	 		+ "FROM RESULTS\r\n"
	 		+ "WHERE result = 'Nothing found' \r\n";
	
	 public boolean Create_BADURL() {
		 try {
			Statement stmt = dbConn.createStatement();
			rs = stmt.executeQuery(Gen);
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		 
		 
		 
		 
		 
		 
	 }
	 
	 
	 public static final String Get = "SELECT url FROM BADURL WHERE status = 'BAD'";
	 public ArrayList<String> Get_BadUrls(){
		
		 
		 try {
				Statement stmt = dbConn.createStatement();
				rs = stmt.executeQuery(Get);
				ArrayList<String> Got = new ArrayList<String>();
				
				 while(rs.next()) {
					 Got.add(rs.getString("url")); 
				 }
				return Got;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 return null;
	 }
	 
	 
	 
	 
	 public static final String Del = "DELETE FROM BADURL";
	 
	 public boolean del_BADURL() {
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
	 
	 public void END_CONNECTION() throws SQLException {
		 dbConn.close();
         stmt.close();
		 
	 }
	 
	 
	 /*
	 public boolean add_to_BADURL(String URL , ArrayList<String> Entry,  String UserAgent) throws SQLException {
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
    	  System.out.println(last_index);
    	  System.out.println(URL);
    	  System.out.println(time);
    	  System.out.println(entry);
    	  System.out.println(UserAgent);
         
         PreparedStatement stmtSEC = dbConn.prepareStatement("INSERT INTO RESULTS (id, url, result, time, useragent) VALUES (?, ?, ?, ?, ?)");
         stmtSEC.setInt(1,last_index);
         stmtSEC.setString(2, URL);
         stmtSEC.setString(3, entry);
         stmtSEC.setTimestamp(4,time);
         stmtSEC.setString(5,UserAgent);
         stmtSEC.execute();
         stmtSEC.close();
         last_index++;
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
	 
	 */
	 
	 
			
	
	
}
