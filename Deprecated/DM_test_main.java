package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import Proj.Headers;

public class DM_test_main {

	
	//1 U-A
	
	static final String sql_T = "CREATE TABLE USERAGENTS " +
            "(id INTEGER not NULL, " +
            " useragent VARCHAR(255), " + 
            " PRIMARY KEY ( id ))"; 

  
	//  static final String DB_URL = "jdbc:mysql://localhost/TUTORIALSPOINT";
	   static final String USER = "PROJDB";
	   static final String PASS = "asd";
	   static final String sql = "CREATE DATABASE EXAMPLE";
	    
	  // static final String sql_I = "INSERT INTO USERAGENTS VALUES(";
	   static final String QUERY = "SELECT id, useragent FROM USERAGENTS";
	   
	   
		   public static void main(String[] args) throws SQLException  {
			   SQLServerDataSource sqlDs = new SQLServerDataSource();
               
			  
			   
			   Connection dbConn = null;
			    CallableStatement cstmt = null;
			    ResultSet rs = null;
			    
			    //String sql_I = "INSERT INTO Registration VALUES" + (100, 'Zara', 'Ali', 18)";
			// Use window integrate authentication.
			//sqlDs.setIntegratedSecurity(true);
			            
			/* Use sql server account authentication.*/
			sqlDs.setIntegratedSecurity(false);
			sqlDs.setUser(USER);
			sqlDs.setPassword(PASS);
			                    
			// Set ds server name or ip.
			sqlDs.setServerName("DESKTOP-BES316T\\PROJDB");
			// Set sql server listening port number.
			sqlDs.setPortNumber(8888); 
			// Set the database name.
			//sqlDs.setDatabaseName("TestDB");
			            
			// Get connection
			try {
				dbConn = sqlDs.getConnection();
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
				
				
				
				// try creating database + table 
					// db
					try {
		         //stmt.executeUpdate(sql);
		         System.out.println("Database created successfully...");  
		         	
		         //stmt.executeUpdate(sql_T);
		         //System.out.println("Created table in given database...");
		         
		             ///     EXECUTE BATCH

		         // Create an int[] to hold returned values
		         int[] count = stmt.executeBatch();

		         //Explicitly commit statements to apply changes
		         dbConn.commit();
		         
		         
		        // stmt.executeUpdate(sql_I);
		         System.out.println("Created entry in given database...");
		         rs = stmt.executeQuery(QUERY);
		         while(rs.next()){
		            //Display values
		            System.out.print("ID: " + rs.getInt("id"));
		            System.out.println("User-Agent : " + rs.getString("useragent"));
		         }
		         stmt.close();
		         rs.close();
		         			
		         	
				
					}catch(Exception e) {e.printStackTrace();
					stmt.close();
			         //rs.close();
					
					}
				
					
				}
				catch(Exception e) {e.printStackTrace();}
						
			} catch (SQLServerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
}
}

