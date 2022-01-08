package Proj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import DB.DB_RESULT;

public class GeneratorENDCASE extends Tls implements Callable<Integer> {
	 	
	
	Tls tls = new Tls();
	int k;
	String[] Url_list; 
	String Params ;
	String KeyWord ;
	ArrayList<Map<String,ArrayList<String>>> toWrite; 
	ArrayList<HashMap<String, String>> agents;
	DB_RESULT db_res;
	//Lock lock;
	
	

		
public GeneratorENDCASE(int k, String[] url_list, String params, String keyWord,
			ArrayList<Map<String, ArrayList<String>>> toWrite, ArrayList<HashMap<String, String>> agents, DB_RESULT db_res) {
		super();
		//this.tls = tls;
		this.k = k;
		Url_list = url_list;
		Params = params;
		KeyWord = keyWord;
		this.toWrite = toWrite;
		this.agents = agents;
		this.db_res = db_res;
		//this.lock = lock;
	}




public int run(int k, String[] Url_list , String Params , String KeyWord , ArrayList<Map<String,ArrayList<String>>> toWrite ) {
	//Unpack
    //#########################
     	Logger l = new Logger(true);
    //#########################
     	String kstr = Integer.toString(k);
        l.log("THREAD: " + kstr + " - ENTER");
        
        // Separate browser and context creation from threads so we don't open nr.threads browsers
        
        tls.index = k;
        l.log(kstr + "Before test");
 
       l.log(kstr +  "Created plw");
       Playwright playw = Playwright.create();
        
        tls.playwright = playw;
        
        
        tls.browser = tls.playwright.firefox().launch(new BrowserType.LaunchOptions()
        		   .setIgnoreDefaultArgs(Arrays.asList("--mute-audio")).setHeadless(false)
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
       
       
       
       	l.log("Context Created for " + kstr);
     
        l.log(Logger.GeneratorStartedLOG + Url_list[k]);
        if(Utils.isRobotsParsable(Url_list[k], user_agent)) {
            l.log(Url_list[k] + "Entered in can_fetch");
            //l.log(Url_list[k] + "Entered in can_fetch");
            tls.page = tls.browsercontext.newPage();
            tls.page.navigate(Url_list[k]);
            tls.page.setDefaultTimeout(5000);
            if(Cookies.ACCEPT_COOKIE_POPUP_NOASYNC(tls.page, Url_list[k])) {
            	l.log("Went past cookie popup for " + kstr);
            
            	
            	
                try {
                    l.log(Logger.GeneratorEnterDiscLOG);
                    Object isDiscardedNULL = new Object();
                   try {
                    ArrayList<String> isDiscarded = new ArrayList<String>(Parsers.ParserNoAsync(tls.page, Params, KeyWord, Url_list[k]));
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
                    l.log("Done" + Url_list[k]);}
            }
                	tls.page.close();
            }
            
        
        
                    
                    // self.tls.page.screenshot(path="C:/Users/ADMIN/PycharmProjects/pythonProject/Threading" + str(k) + ".png", full_page=False)
        else {
            l.log(Logger.GeneratorNotAllowedOnPageLOG + Url_list[k]);}
      
        tls.browsercontext.close();
        tls.browser.close();
        tls.playwright.close();
        l.log("THREAD: " + kstr + " -EXIT");
        return k;
}

@Override
public Integer call() throws Exception {
	try {
	return run(k, Url_list , Params , KeyWord ,toWrite);
	}
	catch(Exception e) {
		//l.log(e.toString());
		String kstr = Integer.toString(k);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!UNKNOWN THREAD EXCEPTION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!      " + kstr);
		e.printStackTrace();
	}
	return -1;
	//lock);
}





}