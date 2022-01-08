package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DB_AGENTS extends DB_CONNECT{

	
	Connection dbConn = null;
	 ResultSet rs = null;
	 Statement stmt = null;
	 int last_index = 0;
	 
	 public static String ResultsQ = "SELECT id, header, useragent FROM AGENTS";
	 
	 public DB_AGENTS() {
		 
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
	            System.out.println("Header : " + rs.getString("header"));
	            System.out.println("UserAgent : " + rs.getString("UserAgent"));
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
	 
	 
	 
	 
	 
	 
	 
	 public static final String Gen = "INSERT INTO dbo.AGENTS (header, useragent)\r\n"
	 		+ "SELECT hd.header, ua.useragent\r\n"
	 		+ "FROM dbo.HEADERS hd\r\n"
	 		+ "CROSS JOIN dbo.USERAGENTS ua\r\n"
	 		+ "WHERE ua.id <= ?\r\n";
		
		 public boolean Create_AGENTS(int UP_TO) {
			 try {
				 del_AGENTS();
				 last_index =0 ;
				 PreparedStatement stmtSEC = dbConn.prepareStatement(Gen);
		         stmtSEC.setInt(1, UP_TO);
		         stmtSEC.execute();
		         stmtSEC.close();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
			 
			 
			 
			 
			 
			 
		 }
	 
	 
	 

public boolean del_from_AGENTS(int id) throws SQLException {
        //DELETE FROM USERAGENT WHERE useragent= ?

    
  /*  Pattern uaPat = Pattern.compile("Mozilla|AppleWebKit|Chrome|Safari|Opera|Edge*.[0-9]{2,}");
    Matcher matcher = uaPat.matcher(input2);
    boolean matchFound = matcher.find();
	 */
	 	
	 		try {
        PreparedStatement stmtSEC = dbConn.prepareStatement("DELETE FROM AGENTS WHERE id= ?");
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


public void END_CONNECTION() throws SQLException {
	 dbConn.close();
    stmt.close();
	 
}





public static final String Del = "DELETE FROM AGENTS";
public static final String GetMaxId = "SELECT MAX(id) max FROM AGENTS";
public static final String RESEED = "DBCC CHECKIDENT(AGENTS, RESEED, ?)";


public boolean del_AGENTS() {
	 try {
		 last_index =0 ;
		 int max_index = 0;
		Statement stmt = dbConn.createStatement();
		
		ResultSet mi = stmt.executeQuery(GetMaxId);
		if(mi.next()) max_index = mi.getInt("max");
		
		
		stmt.execute(Del);
		
		stmt.close();
		
		
		
		PreparedStatement stmtSEC = dbConn.prepareStatement(RESEED);
        stmtSEC.setInt(1, max_index);
        stmtSEC.execute();
        
        stmtSEC.close();
		
		
		return true;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	 
}

private static final String GET = "SELECT header, useragent FROM AGENTS";

public Map<String,String> GET_AGENTS() {
	
	Map<String,String> AG = new HashMap<String,String>();
	String h;
	String ua;
	 try {
		 last_index =0 ;
		
		Statement stmt = dbConn.createStatement();
		
		ResultSet mi = stmt.executeQuery(GET);
		if(mi.next()) { 
			
			h = mi.getString("header");
			System.out.println("HEADERRRRRR --->" + h);
			ua = mi.getString("useragent");
			AG.put(h, ua);
		}
		
		
		
		stmt.close();
		
		
		
		
		return  AG;
		

		
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

	 

	
	
	
	
}






	
	
}
