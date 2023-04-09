package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import Proj.Headers;

public class Create_Tables {

	
	
	//public static final String USER = "PROJDB";
	//public static final String PASS = "asd";

	
	//public SQLServerDataSource sqlDs = new SQLServerDataSource();
	public   Connection dbConn = null;
	public   CallableStatement cstmt = null;
	public   ResultSet rs = null;
	
	
	
	static final String sql_T = "CREATE TABLE USERAGENTS " +
            "(id INTEGER not NULL, " +
            " useragent VARCHAR(255), " + 
            " PRIMARY KEY ( id ))";
	
	static final String sql_T2 = "CREATE TABLE RESULTS " +
            "(id INTEGER not NULL, " +
            " url VARCHAR(255), " + 
            " time DATETIME, " +
            " result VARCHAR(500), " +
            " useragent VARCHAR(255), " +
            " PRIMARY KEY ( id ))";
	
	static final String sql_T3 = "CREATE TABLE BADURL " +
            "(id INTEGER not NULL, " +
            " url VARCHAR(255), " + 
            " time DATETIME, " +
            " status VARCHAR(255), " +
            " PRIMARY KEY ( id ))";
	
	
	static final String sql_T4 = "CREATE TABLE HEADERS " +
            "(id INTEGER not NULL, " +
            " header VARCHAR(1000), " + 
            " PRIMARY KEY ( id ))";
	
	
	
	static final String sql_T5= "CREATE TABLE AGENTS " +
            "(id INTEGER not NULL IDENTITY(1,1), " +
            " header VARCHAR(1000), " +  
            " useragent VARCHAR(255), "	+
            " PRIMARY KEY ( id ))";
	
	
	public Create_Tables() throws SQLException {
		
		//1 U-A																		@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		DB_CONNECT con = new DB_CONNECT(null);
		if(con._is) {
		dbConn = con.dbc;
		rs = con.rss;
		Statement create_ua = dbConn.createStatement();
		try {
		create_ua.executeUpdate(sql_T);
        
		System.out.println("Created table USERAGENT in given database...");
		
		dbConn.setAutoCommit(false);
		System.out.println("aa");
		// Create Statement
		try {
		Statement stmt = dbConn.createStatement();
		
		 Headers headers = new Headers();
		   
		   ArrayList<String> UserAgent_List = new  ArrayList<String>(headers.UserAgent_List);
		   int i =0;
		   for (String agent : UserAgent_List ) {
			   
			   String SQL = "INSERT INTO USERAGENTS VALUES(" + i + ", '" + agent + "'" + ")";
			   System.out.println(SQL);
			   stmt.addBatch(SQL);
			   i++;
		   
		   }
		 
		
		
			try {
         //stmt.executeUpdate(sql);
         System.out.println("Database created successfully...");  
         	
         int[] count = stmt.executeBatch();

         //Explicitly commit statements to apply changes
         dbConn.commit();
         
        // create_ua.close(); Already at the end
			}
			// COMMIT EXCEPTION
			catch(Exception e) { System.out.println("USERAGENTS COMMIT EXCEPTION");  stmt.close(); }
		
			stmt.close();
		} // STATEMENT EXCEPTION
		  catch (Exception e) {System.out.println("USERAGENTS STATEMENT EXCEPTION"); create_ua.close(); }
			
		
		create_ua.close();
		}
		// TABLE ALREADY MADE
		catch (Exception e) {
			System.out.println("USERAGENTS WAS ALREADY CREATED");
			create_ua.close();
		}
		
		
		
		
		
		dbConn.setAutoCommit(true);
		
		
		// 2 RESULTS                                                                  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		Statement create_results = dbConn.createStatement();
		try {
			create_results.executeUpdate(sql_T2);
        
		System.out.println("Created table RESULTS in given database...");
		
		//dbConn.setAutoCommit(false);
		System.out.println("aa");
		// Create Statement
		
		create_results.close();
		}
		// TABLE ALREADY MADE
		catch (Exception e) {
			System.out.println("RESULTS WAS ALREADY CREATED");
			create_results.close();
		}
		
		
		
		// 3 BAD-URL
		
		Statement create_BadUrl = dbConn.createStatement();
		try {
			create_BadUrl.executeUpdate(sql_T3);
        
		System.out.println("Created table BADURL in given database...");
		
		//dbConn.setAutoCommit(false);
		System.out.println("aa");
		// Create Statement
		
		create_BadUrl.close();
		}
		// TABLE ALREADY MADE
		catch (Exception e) {
			System.out.println("BADURL WAS ALREADY CREATED");
			create_BadUrl.close();
		}
		
		
		
		
		//4 Headers 
		
		Statement create_Headers = dbConn.createStatement();
		try {
			create_Headers.executeUpdate(sql_T4);
        
		System.out.println("Created table HEADERS in given database...");
		
		//dbConn.setAutoCommit(false);
		System.out.println("aa");
		// Create Statement
		
		create_Headers.close();
		}
		// TABLE ALREADY MADE
		catch (Exception e) {
			System.out.println("HEADERS WAS ALREADY CREATED");
			create_Headers.close();
		}
		
		
		
		// 5 Agents
		
		Statement create_Agents = dbConn.createStatement();
		try {
			create_Agents.executeUpdate(sql_T5);
        
		System.out.println("Created table AGENTS in given database...");
		
		//dbConn.setAutoCommit(false);
		System.out.println("aa");
		// Create Statement
		
		create_Agents.close();
		}
		// TABLE ALREADY MADE
		catch (Exception e) {
			System.out.println("AGENTS WAS ALREADY CREATED");
			create_Agents.close();
		}
		
		
		
		
		
		
		
		
		
		dbConn.close();
		}
		else {System.out.println("Failed to establish connection");
		dbConn.close();
				
		}
		
		
	
 }

	
		
	
	
	
	
	
	
	
	
	
}
