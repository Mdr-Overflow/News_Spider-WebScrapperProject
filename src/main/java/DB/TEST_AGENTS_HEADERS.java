package DB;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import Proj.Headers;

public class TEST_AGENTS_HEADERS {
	public static void main(String[] args) throws SQLException {
		DB_UA ua = new DB_UA();
		
		ua.Populate_UA_DEFAULT();
		
		ua.END_CONNECTION();
		
		DB_HEADERS hd = new DB_HEADERS();
		
		hd.Populate_HEADERS_DEFAULT();
		
	hd.END_CONNECTION();
	
	DB_AGENTS ag = new DB_AGENTS();
	ag.del_AGENTS();
	ag.Create_AGENTS(6);
	
	
	
	//ag.
	String[] ViewTest = {" "};
	DB_ViewMaker.Table_make_view( ag.dbConn, ag.ResultsQ.substring(ag.ResultsQ.lastIndexOf("FROM")+5),ViewTest);
	
	DB_ViewMaker.Table_make_view_ordered(ag.dbConn, ag.ResultsQ.substring(ag.ResultsQ.lastIndexOf("FROM")+5), "id", "DESC", ViewTest); //[ ASC | DESC ]   
	
	
	DB_ViewMaker.Table_make_view_search(ag.dbConn, ag.ResultsQ.substring(ag.ResultsQ.lastIndexOf("FROM")+5), "Accept", "header", ViewTest);
	
	ag.END_CONNECTION();
	
	}
	
	public static String convertWithStream(Map<String, ?> map) {
	    String mapAsString = map.keySet().stream()
	      .map(key -> key + "=" + map.get(key))
	      .collect(Collectors.joining(", ", "{", "}"));
	    return mapAsString;
	}


	public static Map<String, String> convertWithStream(String mapAsString) {
	    Map<String, String> map = Arrays.stream(mapAsString.split(","))
	      .map(entry -> entry.split("="))
	      .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
	    return map;
	}
	
}

