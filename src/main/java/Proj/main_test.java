package Proj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import DB.DB_UA;

public class main_test {

	public static void main(String[] args) throws InterruptedException {
		
		//
		
		
		DB_UA ua_db = new DB_UA();
		DB.DB_HEADERS hd_db = new DB.DB_HEADERS();
		
		ua_db.Populate_UA_DEFAULT();
		hd_db.Populate_HEADERS_DEFAULT();
	
		
		 
		
		hd_db.Populate_HEADERS_DEFAULT();
		// CREATE AGENTS DB
		
		try {
			ua_db.END_CONNECTION();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			hd_db.END_CONNECTION();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Headers h = new Headers();
		
		DB.DB_AGENTS ag_db = new DB.DB_AGENTS();
		ag_db.del_AGENTS();
		ag_db.Create_AGENTS(10);
		ArrayList<HashMap<String,String>> Agents = new ArrayList<HashMap<String,String>>(h.Generate_Agents(ag_db));
		
		//
		
		
		
		
		Logger l = new Logger(true);
		String[] Url_list_Test = {"https://www.theverge.com", "https://www.libertatea.ro/", "https://www.pcgarage.ro/",
		                     "https://www.theguardian.com/", "https://www.emag.ro/", "https://www.theguardian.com/",
		                     "https://www.dailymail.co.uk/", "https://www.reuters.com/","https://time.com/",
		                     "https://www.bloomberg.com/europe","https://www.digi24.ro/","https://www.heraldscotland.com/", "https://example.com"};
		    
		String[] Url_list_Test_2 =  {
			"https://theguardian.com",
			"https://libertatea.ro"

				
		};
		
		String[] Url_list_Test_3 =  {
				"https://theguardian.com",
				"https://libertatea.ro",
				"https://www.theverge.com"
					
			};
		
			//Utils.Delete_Temp_File("Results.json");
		
		   String params = "All";
		   String keyword = "biden";
		  
		   String options[] = {"yes","no","yes"};
		   //Headless , safe , screencap
		   //Driver.Run(params,keyword,Url_list_Test_2,200,12, Agents);
		    driver2.Run(params,keyword,Url_list_Test_3,200,12, Agents,options);
		
		return;
			
	}

}
