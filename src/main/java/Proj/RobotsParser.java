package Proj;
import com.fasterxml.jackson.core.*;

import java.util.ArrayList;
import java.util.regex.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import crawlercommons.robots.*;
//import crawlercommons.robots.SimpleRobotRules.RobotRule;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;





	public class RobotsParser {
		
	

		private static final String DISALLOW = "Disallow";
		
		
		
		
		public static int robotSafe(URL url, String User_Agent) 
		{
		    String strHost = url.getHost();

			System.out.println("@@@@@@@@ HOST IS  " + strHost + "           @@@@@@@@@@@@@@@@@@");
			System.out.println("@@@@@@@@ HOST IS  " + strHost + "           @@@@@@@@@@@@@@@@@@");
			System.out.println("@@@@@@@@ HOST IS  " + strHost + "           @@@@@@@@@@@@@@@@@@");

		    String strRobot = "https://" + strHost + "/robots.txt";

			System.out.println("@@@@@@@@ robots.txt is  " + strRobot+ "           @@@@@@@@@@@@@@@@@@");
		    URL urlRobot;
		    try { urlRobot = new URL(strRobot);
		    } catch (MalformedURLException e) {
		   
		        return -1; // MalformedURLException
		    }

		    String strCommands;
		    
		    try 
		    {
		        InputStream urlRobotStream = urlRobot.openStream();
		        byte b[] = new byte[1000];
		        int numRead = urlRobotStream.read(b);
		        strCommands = new String(b, 0, numRead);
		        while (numRead != -1) {
		            numRead = urlRobotStream.read(b);
		            if (numRead != -1) 
		            {
		                    String newCommands = new String(b, 0, numRead);
		                    strCommands += newCommands;
		            }
		        }
		       urlRobotStream.close();
		    } 
		    catch (IOException e) 
		    {
		        return 0; // if there is no robots.txt file, it is OK to search
		    }

		    if (strCommands.contains(DISALLOW)) // if there are no "disallow" values, then they are not blocking anything.
		    {
		        String[] split = strCommands.split("\n");
		        ArrayList<RobotRule> robotRules = new ArrayList<>();
		        String mostRecentUserAgent = null;
		        for (int i = 0; i < split.length; i++) 
		        {
		            String line = split[i].trim();
		            if (line.toLowerCase().startsWith("user-agent")) 
		            {
		            	
		            	// if (line.toLowerCase().startsWith("user-agent: *")) 
		            		 
		                int start = line.indexOf(":") + 1;
		                int end   = line.length();
		                mostRecentUserAgent = line.substring(start, end).trim();
		            }
		            else if (line.startsWith(DISALLOW)) {
		                if (mostRecentUserAgent != null) {
		                    RobotRule r = new RobotRule();
		                    r.userAgent = mostRecentUserAgent;
		                    int start = line.indexOf(":") + 1;
		                    int end   = line.length();
		                    r.rule = line.substring(start, end).trim();
		                    robotRules.add(r);
		                }
		            }
		        }

		        for (RobotRule robotRule : robotRules)
		        {

//					System.out.println("@@@@@@@@@@ RULE " + robotRule + " @@@@@@@@@@@@@@@@@");
		            String path = url.getPath();
		            if (robotRule.rule.length() == 0) return 0; // allows everything if BLANK
		            if (robotRule.rule.equals("/ ")) return -2;       // allows nothing if /


		        }
		    }
		    return 0;
		}

		
	}	
	
	
