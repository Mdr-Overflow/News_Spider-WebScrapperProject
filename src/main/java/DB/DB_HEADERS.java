package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Proj.Headers;

public class DB_HEADERS extends DB_CONNECT{
	Connection dbConn = null;
	 ResultSet rs = null;
	 Statement stmt = null;
	 int last_index = 0;
	 
	 public static String ResultsQ = "SELECT id, header FROM HEADERS";
	 
	 public DB_HEADERS() {
		 
		 super(ResultsQ);
			if(_is) {
				
				
			
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
	            System.out.println("Header : " + rs.getString("header"));
	         }
				last_index = i;
				}
				
				catch(Exception e) {e.printStackTrace();
				stmt.close();
		         //rs.close();
				
				}
			
				//stmt.close();
				System.out.println("all good for HEADERS");
			
			}catch(Exception e) {e.printStackTrace();}}
			
		
			}
	 
	 
	 public boolean add_to_HEADERS(String header) throws SQLException {
         // AFTER ADD
         
        // String input= "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/111.36";
         String input = header;
         //String input2 = input;
         
         
         
        /* Pattern uaPat = Pattern.compile("Mozilla|AppleWebKit|Chrome|Safari|Opera|Edge*.[0-9]{2,}");
         Matcher matcher = uaPat.matcher(input);
         boolean matchFound = matcher.find();*/
       
        	 try {
         last_index++;
         PreparedStatement stmtSEC = dbConn.prepareStatement("INSERT INTO HEADERS (id,header) VALUES (?, ?)");
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
         }						}
 
 
 
 public boolean del_from_HEADERS(String header) throws SQLException {
         //DELETE FROM USERAGENT WHERE useragent= ?
	 String input2 = header;
     
   /*  Pattern uaPat = Pattern.compile("Mozilla|AppleWebKit|Chrome|Safari|Opera|Edge*.[0-9]{2,}");
     Matcher matcher = uaPat.matcher(input2);
     boolean matchFound = matcher.find();
	 */
	 	
	 		try {
         PreparedStatement stmtSEC = dbConn.prepareStatement("DELETE FROM HEADERS WHERE header= ?");
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
 
 
 public void END_CONNECTION() throws SQLException {
	 dbConn.close();
     stmt.close();
	 
 }
 
 
 
public static final String Del = "DELETE FROM HEADERS";
 
 public boolean del_HEADERS() {
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
 
 
 
 public void Populate_HEADERS_DEFAULT(){
	 del_HEADERS();
 String res;
 Headers h = new Headers();
	for (HashMap<String, String> header :h.Headers_List)
	{
		
		res = DB_UTILS.convertWithStream(header);
		System.out.println(res);
		try {
			add_to_HEADERS(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
 
 
 }
 
 public Connection GetConn() {
	 return dbConn;
 }
 
 
 
}
