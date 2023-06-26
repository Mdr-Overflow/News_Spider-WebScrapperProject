package Proj;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.nio.file.Paths;
import java.security.KeyStore.Entry;


import com.microsoft.playwright.*;

import DB.DB_RESULT;

public class NewGenerator extends Tls implements Callable<Integer> {
	 	
	
	Tls tls = new Tls();
	int k;
	String[] Url_list; 
	String Params ;
	String KeyWord ;
	ArrayList<Map<String,ArrayList<String>>> toWrite; 
	ArrayList<HashMap<String, String>> agents;
	DB_RESULT db_res;
	String[] options;
	//Lock lock;
	//Playwright plw;
	//Browser brws;
	//BrowserContext brwsC;
	//String user_agent;

		
public NewGenerator(int k, String[] url_list, String params, String keyWord,
			ArrayList<Map<String, ArrayList<String>>> toWrite, ArrayList<HashMap<String, String>> agents, DB_RESULT db_res, String[] options )
{
		super();
		//this.tls = tls;
		this.k = k;
		Url_list = url_list;
		Params = params;
		KeyWord = keyWord;
		this.toWrite = toWrite;
		this.agents = agents;
		this.db_res = db_res;
		this.options = options;
		//this.user_agent = user_agent;
		//this.lock = lock;
	}




public int run(int k, String[] Url_list , String Params , String KeyWord , ArrayList<Map<String,ArrayList<String>>> toWrite,
		ArrayList<HashMap<String, String>> agents, DB_RESULT db_res, String[] options
		  )  {
    //Unpack
    //#########################
     	Logger l = new Logger(true);
     	
     	String headless = options[0];
     	String safe = options[1];
     	String screen = options[2];
     	
     	boolean headl = false;
     	if(headless == "yes") headl = true; 
     	
    //#########################
     	String kstr = Integer.toString(k);
        l.log("THREAD: " + kstr + " - ENTER");
        
        tls.index = k;
        l.log("Created plw");
	       Playwright playw = Playwright.create();
	        
	    tls.playwright = playw;
	        
	        
	        tls.browser = tls.playwright.firefox().launch(new BrowserType.LaunchOptions()
	        		   .setIgnoreDefaultArgs(Arrays.asList("--mute-audio")).setHeadless(headl)
	        		   );
	      
	     
	    
	       // Headers h = new Headers();
	        
	        Random r = new Random();
			int low = 0;
			int high = agents.size();
			int result = r.nextInt(high-low) + low;
	        
	        
	       
	        //String user_agent = h.UserAgent_List.get(result);
	        
	        Map<String,String> extra = new HashMap<String,String>(agents.get(result));
	        //extra.put("sec-ch-ua", Headers.Sec_check(user_agent));
	        
	        String user_agent = agents.get(result).get("user-agent");
	        
	        
	        
	        tls.browsercontext = tls.browser.newContext(new Browser.NewContextOptions()
	    	        .setUserAgent(user_agent)
	    	        .setExtraHTTPHeaders(extra)
	    	        .setBypassCSP(true)
	    		   );
	       
	       
	      
	       	l.log("Context Created");
        
        
        
        
        
        // Separate browser and context creation from threads so we don't open nr.threads browsers
      
     
        l.log(Logger.GeneratorStartedLOG + Url_list[k]);
        if(safe == "yes" || Utils.isRobotsParsable(Url_list[k], user_agent)) {
            l.log(Url_list[k] + "Entered in can_fetch");
            //l.log(Url_list[k] + "Entered in can_fetch");
            tls.page = tls.browsercontext.newPage();
            tls.page.navigate(Url_list[k]);
            tls.page.setDefaultTimeout(5000);
            if(Cookies.ACCEPT_COOKIE_POPUP_NOASYNC(tls.page, Url_list[k])) {
            	l.log("Went past cookie popup for " + kstr);
            
            	
            	
            	if(screen == "yes") {
                	//System.out.println(Url_list[k].lastIndexOf("www."));
                	if(Url_list[k].contains("www.")){
                tls.page.screenshot(new Page.ScreenshotOptions()
                		  .setPath(Paths.get("Screencaps/"+Url_list[k].substring(Url_list[k].lastIndexOf("www.")+4)+".png"))
                		  .setFullPage(false)); }
                	
                
                else {
                	System.out.println(Url_list[k].substring(Url_list[k].lastIndexOf("//")+2) + "<-----------------------------");
                	tls.page.screenshot(new Page.ScreenshotOptions()
              		  .setPath(Paths.get("Screencaps/"+Url_list[k].substring(Url_list[k].lastIndexOf("//")+2)+".png"))
              		  .setFullPage(false)); }
            }   
            	
            	
            	
            	
            	
            	
                try {
                    l.log(Logger.GeneratorEnterDiscLOG);
                    Object isDiscardedNULL = new Object();
                   try {
                    ArrayList<String> isDiscarded = new ArrayList<String>(Objects.requireNonNull(Parsers.ParserNoAsync(tls.page, Params, KeyWord, Url_list[k])));
                    Map<String,ArrayList<String>> Entry = new HashMap<String,ArrayList<String>>();
                	
                    
                    // DUMP TO DATABASE
                    
                    if (isDiscarded.size() != 0)
                    db_res.add_to_RESULTS(Url_list[k], isDiscarded, user_agent);
                    else {
                    	ArrayList<String> empty = new ArrayList<String>();
                    	empty.add("Nothing found");
                    	db_res.add_to_RESULTS(Url_list[k], empty, user_agent);
                    	
                    }
                    // 
                    
                    Entry.put(Url_list[k], isDiscarded);
                    
                	toWrite.add(Entry);
                   }
                   catch(Exception e) {
                	 isDiscardedNULL = null;
                	 e.printStackTrace();
                   }
                  
                    l.log(Logger.GeneratorExitDiscLOG);
                
                    if (isDiscardedNULL == null)
                        l.log(Logger.GeneratorIsDiscLOG + Url_list[k]);

                    //with lock: ??????????????
                    	
                }
                        catch (Exception e) {
                    e.printStackTrace();	
                    l.log(Logger.GeneratorIllegalLOG);}
                finally {
                    l.log("Done" + Url_list[k]);
                  
            }
                	//tls.page.close();
            }
        }
        
        
                    
                    // self.tls.page.screenshot(path="C:/Users/ADMIN/PycharmProjects/pythonProject/Threading" + str(k) + ".png", full_page=False)
        else {
            l.log(Logger.GeneratorNotAllowedOnPageLOG + Url_list[k]);}
      
        //tls.browsercontext.close();
            
            
        
        tls.page.close();
        tls.browsercontext.close();
	    tls.browser.close();
	    tls.playwright.close();
        
        
        //Start working on second page
        
       
        l.log("THREAD: " + kstr + " -EXIT");
        return k;
            
            //self.tls.page.screenshot(path="C:/Users/ADMIN/PycharmProjects/pythonProject/Threading" + str(k+1) + ".png", full_page=False)

        
        
}



@Override
public Integer call() throws Exception {
	try {
	return run(k, Url_list , Params , KeyWord ,toWrite,
			agents,db_res,options);
	}
	catch(Exception e) {
		//l.log(e.toString());
		String kstr = Integer.toString(k);
		System.out.println(Logger.GeneratorCallableThreadUknownErrorLOG + kstr);
		
		e.printStackTrace();
		 tls.page.close();
	        tls.browsercontext.close();
		    tls.browser.close();
		    tls.playwright.close();
	        
	}
	return -1;
	//lock);
}





}
        