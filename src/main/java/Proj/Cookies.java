package Proj;

//import java.net.MalformedURLException;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.*;

public class Cookies {

	
	public static boolean ACCEPT_COOKIE_POPUP_NOASYNC(Page page,String url) {
	   // start = time();
	    Logger l = new Logger(true);
	    l.log(url + "     <<<<<<<<<<<<<< ACCEPT_COOKIE_POPUP_NOASYNC LOGGS >>>>>>>>>>>>>>");
	    try {
	        page.waitForLoadState();
	        //l.log(page.mainFrame().content());
	        l.log(url + " Page loaded");
	        
	        for (Frame frame: page.mainFrame().childFrames()) {
	        	
	        	String Regex = "iframe|'about:blank'";
	     	    Pattern pattern = Pattern.compile(Regex, Pattern.CASE_INSENSITIVE);
	            if (pattern.matcher(frame.name()).find()) {
	            	
	               l.log(url + "Got past about:blank");
	              
	               List<String> accept_button = new ArrayList<String>();
	               
	               accept_button = frame.locator("button:has-text(\"Yes\"), button:has-text(\"Accept\"), button:has-text(\"Accepta\") ").allTextContents(); 
	               l.log("THIS ##########" + url);
	               l.log(accept_button.toString());
	               if (accept_button.size() != 0)  {  // all_text_contents of locator class returns [str] so if not it returns [None]

	                l.log(accept_button.toString());
	                frame.click("button:has-text(\" " + accept_button.get(0) + "\")" );
	                return true;
	               }
	              // page.setDefaultTimeout(3000); // ????????????????
	               page.click("button:has-text(\"Accept\"), button:has-text(\"ACCEPT\"), button:has-text(\"accept\"), button:has-text(\"Yes\"), gdpr-button ");
	               
	     
	        return true;
	            }
	            

	        
	        }}
	       catch (Exception e) {
	        l.log(e.toString());
	        l.log("Couldn't get past cookie popup for : " + url);
	       //print(start - time())
	        return false;
	       }
		return true;
	
	
	}
	   
	
	
}
