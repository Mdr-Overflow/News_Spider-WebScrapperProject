package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;



public class DB_CONNECT {

	public static final String USER = "PROJDB";
	public static final String PASS = "AAA";

	
	public SQLServerDataSource sqlDs = new SQLServerDataSource();
	protected   Connection dbc = null;
	public   CallableStatement cstmt = null;
	protected   ResultSet rss = null;
	protected boolean _is = false;
	public String ResultsQ = null;
	  public DB_CONNECT(String resQ) {
	            
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
	this.ResultsQ = resQ;
	// Get connection
	try {
		dbc = sqlDs.getConnection();
		//dbConn.setAutoCommit(false);
		System.out.println("CONNECTED");
		_is = true;
		
		//return true;
	}
	   catch (SQLServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return false;
		}

	
	}
	  
	  public Connection getCon() {
			
			return dbc;
		}
		
		public String getResultQ() {
			return ResultsQ;
		}
		
	  
	  
}	
