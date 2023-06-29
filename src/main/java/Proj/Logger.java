package Proj;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



public class Logger {

	boolean _log = true;

	public final static String isRobotParsableLOG  ="     <<<<<<<<<<<<<< isRobotParsable LOGGS >>>>>>>>>>>>>> ";
	public final static String AllParse_NoAsyncLOG  ="      <<<<<<<<<<<<<< AllParse_NoAsync LOGGS >>>>>>>>>>>>>>";
	public final static String AllParseResultLOG  ="UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU RESULT UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU FOR ";
	public final static String AllParseFounAllContextLOG = "$$$$$$ FOUND ALL THE CONTEXT OF THE PAGE  $$$$$$";
	public final static String AllParseFailedLOG = "Failed AllParse @@@@@@@@";
	public final static String ParserStartedLOG = "--- Started Parser --- FOR ";
	public final static String ParserNoAsyncLOG ="      <<<<<<<<<<<<<< ParserNoAsync LOGGS >>>>>>>>>>>>>>";
	public final static String ThreadsDeadLOG ="NO THREAD SHOULD BE ALIVE AFTER THIS ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;";
	public final static String ThreadsKilledLOG ="############Exited all threads##############";
	public final static String GeneratorStartedLOG = "############# Started ";
	public final static String GeneratorEnterDiscLOG = "entered isDiscarded";
	public final static String GeneratorExitDiscLOG ="WENT PAST isDiscarded";
	public final static String GeneratorIsDiscLOG ="@@@@@@DISCARDED@@@@@@ ";
	public final static String GeneratorIllegalLOG = "$$$$$$$$$$$ILLEGAL PARAMS$$$$$$$$$$$$$";
	public final static String GeneratorNotAllowedOnPageLOG = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!NOT ALLOWED ON ";
	public final static String GeneratorCallableThreadUknownErrorLOG = 
			"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!UNKNOWN THREAD EXCEPTION!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!      ";
	
	
	
	public Logger(boolean _log) {
		super();
		this._log = _log;
	}
	
	
	
	public void Init_logs()
	{
		if (_log ) {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();
			//System.out.println(dtf.format(now));  
			
			if ( Proj.Utils.CreateFile( "log.txt" ) != -1 )
			{
				
				Proj.Utils.WriteToFile("log.txt", (dateFormat.format(now)).toString() );
				
			}
			
		}
	
		
	}
	
	

	public void log(String to_log)
	{
		
		if( _log ) {
			
			System.out.println(to_log);
			Proj.Utils.WriteToFile("log.txt", to_log );
			
		}
		
		
	}
	
	
	public void logArray(ArrayList<String> to_log)
	{
		
		if( _log ) {
			
			System.out.println(to_log);
			Proj.Utils.WriteToFileArray("log.txt", to_log );
			
		}
		
		
	}



	
	
	
	
	
}
