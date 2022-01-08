package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_ViewMaker {

	
	public static  String UA_V = "CREATE OR ALTER VIEW z@VIEW AS\r\n"
			+ "SELECT *\r\n"
			+ "FROM z\r\n";
	
	public static  String UA_VO = "CREATE OR ALTER VIEW ztable@VIEW@zorder AS\r\n"
			+ "SELECT TOP(max) *\r\n"
			+ "FROM ztable\r\n"
			+ "ORDER BY zname zorder";
	
	
	
	public static Connection Table_make_view(Connection dbConn, String Table_name , String[] View_name) {
		System.out.println(Table_name);
		Statement stmt;
		try {
			
			
			UA_V = new String("CREATE OR ALTER VIEW z@VIEW AS\r\n"
					+ "SELECT *\r\n"
					+ "FROM z\r\n");
			
			// IDK why it won't work normally
			UA_V=UA_V.replaceAll("z", Table_name);
			System.out.println(UA_V);
			View_name[0]=UA_V.substring(UA_V.indexOf("VIEW") + 5 , UA_V.indexOf(" AS"));
			//View_name = View_name.replaceAll("\n", "");
			System.out.println(View_name[0]);
			//
			 PreparedStatement stmtSEC = dbConn.prepareStatement(UA_V);
			 
	        // stmtSEC.setString(1, Table_name);
	        // stmtSEC.setString(2, Table_name);
	         
	         stmtSEC.execute();
	         stmtSEC.close();
	         System.out.println("MADE VIEW OF " + Table_name);
	         return dbConn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	public static String GetMaxId = "SELECT MAX(id) max FROM ztable";
	
	public static Connection Table_make_view_ordered(Connection dbConn, String Table_name,String what_to_order_by , String order,  String[] View_name) {
		System.out.println(Table_name);
		Statement stmt;
		try {
			
			// IDK why it won't work normally
			UA_VO = new String("CREATE OR ALTER VIEW ztable@VIEW@zorder AS\r\n"
					+ "SELECT TOP(max) *\r\n"
					+ "FROM ztable\r\n"
					+ "ORDER BY zname zorder");
			
			UA_VO=UA_VO.replaceAll("ztable", Table_name);
			
			
			
			System.out.println(order + " SSSSSSSSSSSSSSS");
			
			
			GetMaxId = GetMaxId.replace("ztable", Table_name);
			
			Statement stmt2 = dbConn.createStatement();
			
			int max_index=0;
			ResultSet mi = stmt2.executeQuery(GetMaxId);
			if(mi.next()) max_index = mi.getInt("max");
			stmt2.close();
			
			UA_VO=UA_VO.replace("zname", what_to_order_by);
			UA_VO=UA_VO.replaceAll("zorder", order);
			
			
			View_name[0]=UA_VO.substring(UA_VO.indexOf("VIEW") + 5 , UA_VO.indexOf(" AS"));
			//View_name = View_name.replaceAll("\n", "");
			System.out.println(View_name[0]);
			
			UA_VO=UA_VO.replace("max", Integer.toString(max_index));
			System.out.println(UA_VO);
			
			//
			 PreparedStatement stmtSEC = dbConn.prepareStatement(UA_VO);
			 
	        // stmtSEC.setString(1, Table_name);
	        // stmtSEC.setString(2, Table_name);
	         
	         stmtSEC.execute();
	         stmtSEC.close();
	         
	         System.out.println("MADE VIEW OF " + Table_name + "ORDERED " + order + "BY " + what_to_order_by);
	         return dbConn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	
	public static String SEARCH_id = "CREATE OR ALTER VIEW z@VIEW@SEARCH AS\r\n"
			+ "SELECT *\r\n"
			+ "FROM z\r\n"
			+ "WHERE column = ?";
	
	public static String SEARCH_string = "CREATE OR ALTER VIEW z@VIEW@SEARCH AS\r\n"
			+ "SELECT *\r\n"
			+ "FROM z\r\n"+
			"  WHERE column COLLATE Latin1_General_BIN LIKE ?";
	
	public static String SEARCH_date = "CREATE OR ALTER VIEW z@VIEW@SEARCH AS\r\n"
			+ "SELECT *\r\n"
			+ "FROM z\r\n"+
			"  WHERE CONVERT(VARCHAR(25), time, 121) LIKE ?";
	
	
	public static Connection Table_make_view_search(Connection dbConn, String Table_name,String to_search, String column, String[] View_name) {
		System.out.println(Table_name);
		Statement stmt;
		try {
			
			
			
			SEARCH_id = new String("CREATE OR ALTER VIEW z@VIEW@SEARCH AS\r\n"
					+ "SELECT *\r\n"
					+ "FROM z\r\n"
					+ "WHERE column = ?");
			SEARCH_string= new String("CREATE OR ALTER VIEW z@VIEW@SEARCH AS\r\n"
					+ "SELECT *\r\n"
					+ "FROM z\r\n"+
					"  WHERE column COLLATE Latin1_General_BIN LIKE ?");
			SEARCH_date = new String("CREATE OR ALTER VIEW z@VIEW@SEARCH AS\r\n"
					+ "SELECT *\r\n"
					+ "FROM z\r\n"+
					"  WHERE CONVERT(VARCHAR(25), time, 121) LIKE ?");
			
			
			
			
			String SEARCH = "?";
			
			// IDK why it won't work normally
			
			System.out.println("COLUMN IS:  " + column); //f  fvsf  f
			if(column.contains("id") ) {
			SEARCH_id=SEARCH_id.replaceAll("z", Table_name);
			
			SEARCH_id=SEARCH_id.replace("column", column);
			System.out.println(SEARCH_id);
			SEARCH = SEARCH_id;
			}
			
			else if(column.contains("time") ) {
				SEARCH_date=SEARCH_date.replaceAll("z", Table_name);
				
				SEARCH_date=SEARCH_date.replace("column", column);
				System.out.println(SEARCH_date);
				SEARCH = SEARCH_date;
				}
			
			else {
				SEARCH_string=SEARCH_string.replaceAll("z", Table_name);
				
				SEARCH_string=SEARCH_string.replace("column", column);
				System.out.println(SEARCH_string);
				SEARCH = SEARCH_string;
				}
			
			
			//
			
			View_name[0]=SEARCH.substring(SEARCH.indexOf("VIEW") + 5 , SEARCH.indexOf(" AS"));
			//View_name = View_name.replaceAll("\n", "");
			System.out.println(View_name[0]);
			
			
			if(!to_search.contains("'") && !to_search.contains("\"")) {
				if(!column.contains("id")) to_search = "'%" + to_search + "%'";
				
			SEARCH = SEARCH.replace("?",to_search);
			System.out.println(SEARCH);
			PreparedStatement stmtSEC = dbConn.prepareStatement(SEARCH);
			//stmtSEC.setString(1, to_search);
			 stmtSEC.execute();
	         stmtSEC.close();
	         
	         System.out.println("MADE VIEW OF " + Table_name + " FINDING : " + column +" : " + to_search);
			return dbConn;
			
			}
			else {
				System.out.println("Sec error");
			}
	         
	         
	         // stmtSEC.setString(1, to_search);
	       
	        // stmtSEC.setString(2, Table_name);
	         
	       //  stmtSEC.execute();
	       //  stmtSEC.close();
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	
	
	
	
}
