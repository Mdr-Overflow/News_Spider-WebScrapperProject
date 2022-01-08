package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.table.DefaultTableModel;

public class DB_UTILS {

	
	//static String query = "SELECT * FROM table";
   // static String count = "SELECT COUNT(id) AS total FROM table";   // SETTING THESE TO STATIC EFF. GIVES THEM IMMUTABILITY IN LONG STATIC CHAIN
	
	public static String convertWithStream(Map<String, ?> map) {
	    String mapAsString = map.keySet().stream()
	      .map(key -> key + "=" + map.get(key))
	      .collect(Collectors.joining(",, ", "{", "}"));
	    return mapAsString;
	}


	public static Map<String, String> convertWithStream(String mapAsString) {
	    Map<String, String> map = Arrays.stream(mapAsString.split(",,"))
	      .map(entry -> entry.split("="))
	      .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
	    return map;
	}
	

	public static DefaultTableModel GetTableModel(Connection dbConn, String[] Table_name) throws SQLException {
		
		int Nr = 0;
		String query = "SELECT * FROM table";
		String count = "SELECT COUNT(id) AS total FROM table";
		System.out.println(Table_name[0] + "<---------");
		
		Statement stmQ = dbConn.createStatement();

		ArrayList <String> cols = new ArrayList<String>();
		
		System.out.println(Table_name[0] + "<---------");
		String pattern = new String(Table_name[0]) ;           // JAVA REGEX INTERNAL POINTER BUG
		System.out.println(pattern + "<---------");
		pattern=pattern.trim();
		
			query = query.replaceAll("table",pattern);
			count = count.replaceAll("table",pattern);
			
		
		
		
		System.out.println(query);
		System.out.println(count);
		
		System.out.println(pattern+ " AFTER <---------");
		ResultSet rs =
				stmQ.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int columnCount = rsmd.getColumnCount();
		System.out.println(columnCount);
		
		// The column count starts from 1
		for (int i = 1; i <= columnCount; i++ ) {
		  cols.add(rsmd.getColumnLabel(i));
		  // Do stuff with name
		}
		
		
		
		 String columns[] = new String[columnCount];
		 
			for (int i = 0; i <= columnCount-1; i++ ) {
				columns[i] = cols.get(i);
				System.out.println(columns[i]);
			}
		 
			System.out.println("AFTER");
			Statement stm = dbConn.createStatement();
			 ResultSet nr = stm.executeQuery(count);
			 //System.out.println(nr.next());
			 while(nr.next()){
				 Nr = nr.getInt("total");
				}
			 //System.out.println(Nr);
			
			
			
			
	      String data[][] = new String[Nr][columnCount];
		 DefaultTableModel model = new DefaultTableModel(data, columns);
		
				
				// Nr= Integer.parseInt(nr.getString("total"));
				 	
			      int i = 0;
			      int j = 0;
			      while (rs.next()) {
			    	  	
			    	  //System.out.println(i);
			    	  for (j = 0 ; j<=columnCount-1; j++) {
			    		  //System.out.println(columns[j]);
			        String entry = rs.getObject(columns[j]).toString();
			        //System.out.println(entry);
			        data[i][j] = entry;
			    	}
			        
			        i++;
			      }
				
			      if (Nr != 0) model = new DefaultTableModel(data, columns);	  
			
			      
			      
			      stmQ.close();
			      stm.close();
			      
			      
			      
			
		
		
		
		return model;
		
	}
	
	
}
