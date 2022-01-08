package Proj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import DB.DB_RESULT;

public class driver2 {

	
	public static boolean Is_there_at_least_one_or_more_closed_threads( ThreadPoolExecutor tpe,int i, ArrayList<Future<Integer>> futures, int  RateLimiter ,String Params
			, String KeyWord, ArrayList<Map<String,ArrayList<String>>>  to_write, 
			String[] Url_list, ArrayList<HashMap<String, String>> agents, DB_RESULT db_res, String[] options) throws InterruptedException {
		int j = 0;
		int closed = 0;
		Logger l = new Logger(true);
		while(closed == 0)
    	{
			l.log("Waiting for thread to close");
	        for (Future<Integer> t : futures) {
	        	
	        		
	            if( t.isDone() ) {
	                // log(futures)
	            	
	                try {
						j = t.get();
					} catch (InterruptedException e) {
						l.log(e.toString());
						e.printStackTrace();
					} catch (ExecutionException e) {
						l.log(e.toString());
						e.printStackTrace();
					}
	                l.log("   is   " + j);
	                futures.remove(t);
	                futures.add(tpe.submit( (Callable<Integer>) new NewGenerator(j,Url_list,Params,KeyWord,to_write,agents,db_res,options)));
	                TimeUnit.SECONDS.sleep(RateLimiter);  // Modf. based on ratelimit
	                closed = 1;
	                return true;
	        }
	            else {
	            	
	            	l.log(i + " IS NOT DONE YET");
	            	TimeUnit.SECONDS.sleep(RateLimiter);
	            }
	        }}
		return false;

	}	    
	
	
	
	               		
  		 
	                		
	                		
	                		
	                		

	public static  Runnable Run(String Params,String KeyWord, String[] Url_list, 
			int rate, int max_threads, ArrayList<HashMap<String, String>> agents, String[] options) throws InterruptedException {
	   // ''' rate is in milliseconds '''
	  
		Logger l = new Logger(true);
	    ArrayList<Map<String,ArrayList<String>>>  to_write = new ArrayList<Map<String,ArrayList<String>>>();
	    
	    ArrayList<Future<Integer>> futures =  new ArrayList<Future<Integer>>();
	    
	    ThreadPoolExecutor tpe = 
	    		  (ThreadPoolExecutor) Executors.newFixedThreadPool(2*max_threads);
	    int i = 0;

	  // MAKE CONN TO RESULTS DB
	    
	    DB_RESULT db_res = new DB_RESULT();
	    
	    ///
	    
	    
	   
	    
	    
	    
	    int RateLimiter = (rate/1000);
	    
	   // start_time = time()
	    while (i < Url_list.length) {
	        String istr = Integer.toString(i);
	        		
	    	l.log(istr);
	        if (i <= (max_threads*2)) {
	            futures.add(tpe.submit( (Callable<Integer>) new NewGenerator(i,Url_list,Params,KeyWord,to_write,agents,db_res,options )  ));
	           // sleep(RateLimiter)  // Modf. based on ratelimit
	            i ++;}
	        else {
	            l.log("Entered else branch");
	          try {
				boolean done =  Is_there_at_least_one_or_more_closed_threads(tpe, i, futures, RateLimiter, Params,
				                                          KeyWord,to_write,Url_list,agents,db_res, options) ;
			} catch (InterruptedException e) {
				l.log(e.toString());
				e.printStackTrace();
			}  
	          i ++;
	        }// Needed function to break out of for and while loop(since else it would loop indefinitely always adding same thread)
	            
	    }
	     l.log("Exited while");
	     
	    // Wait 3 sec for all to end
	    int is_alive = 1;
	    int threads = 0;
	    while(is_alive == 1)
    	{
	    	
	        for (Future<Integer> t : futures) {
	        	
	        	if (t.isDone() )
	        	{
	        		threads++;
	        		
	        	}
	        	else {
	        		l.log("ALIVE");
	        		l.log(t.toString());
	        		TimeUnit.SECONDS.sleep(5);
	        	}
	        if (threads == (2*max_threads) ) {
	        	is_alive = 0;
	        	//l.log("qWEARSTDGFHYJUFYTRESARDFTGHYFYFTGRESAWRSDFGTHJYTREWAARSTDFYGHTREWQERSTDFGYHY   ZAAAAAAAAAAAAAAAAAAAAA");
	        }
	        }

    	}
	    l.log(Logger.ThreadsDeadLOG);
	    tpe.shutdown();
	    futures.clear();
	    //tpe.shutdown();
	    //futures.clear();
	    l.log(Logger.ThreadsKilledLOG);
	    
	    
	    
	    Utils.WriteToJson(KeyWord,to_write);
		//return null;
		return null;
	    
	    //print("took ~" + str(time() - start_time) + " s ")

	   // return "Results.json";
	}
	
	
	
	public static Runnable RunInGui(String params, String keyword,  String[] Url_list, 
			int Rate_limiter, int max_threads, ArrayList<HashMap<String, String>> agents, String options[]) {
	    try {
			return Run(params, keyword, Url_list,Rate_limiter,max_threads,agents,options);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	
		
}