import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import java.awt.Desktop; 	//used to open web browser

public class DataDriver {
		private static Token requestToken;
		private static Scanner in;
		private static OAuthService service;
		private static Token accessToken;
		public static int p = 100;
	
		
		public static void startGrabData() throws IOException, URISyntaxException {
			in = new Scanner(System.in);
			
			// Create the OAuthService object.
	
			service = new ServiceBuilder()
		            .provider(FitbitApi.class)
		            .apiKey(FitbitApi.getApiKey()) // These keys can be found in our chain email.
		            .apiSecret(FitbitApi.getApiSecret())
		            //.scope("activity")
		            .debug()
		            .build();
	
		    // Get the request token.
		    requestToken = service.getRequestToken();
		    
		    // Make user validate request token.
		   String authURL = service.getAuthorizationUrl(requestToken);
		   	   
		    // Load Fitbit sign in page here	
		   if(Desktop.isDesktopSupported())
		   {
		     Desktop.getDesktop().browse(new URI(authURL));
		   }
	   
		}
		
		public static void sendVerification(String verCode){
		   /*
		    System.out.println("Now go and authorize Scribe here:");
		    System.out.println(authURL);*/
		   /* Replace with interface box
		    System.out.println("And paste the verifier here");
		    System.out.println("It's at the end of the url in your broswer.");
		    System.out.println(">>");
		    	 */  
		    System.out.println("TEST TEST TEST: " + verCode);
		    // Get the access token.    
		    Verifier v = new Verifier(verCode);
		    System.out.println("TEST1");
		    System.out.println(requestToken);
		    System.out.println(v);
		    System.out.println("TEST2");
		    accessToken = service.getAccessToken(requestToken, v); // Request token from above	    
		    System.out.println("AFTER ACCESSTOKEN");
	
		    // Sign the request.	
	    	System.out.println("test");
	        OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.fitbit.com/1/user/-/profile.json"); 
	        service.signRequest(accessToken, request);
	        Response response = request.send();
	        System.out.println(response.getBody());
	        String profile = response.getBody();
	        
	        // Moved requests for data into a separate method so it can be reused for
	        // refreshing data hourly
	        // gatherData method can be found below the main method
	        
	        timer();
	        
	        
		 }
		
		public static void timer(){
			// This is used to have the app update fitbit data periodically (hourly)
	        final Timer timer = new Timer ();
	        TimerTask hourlyTask = new TimerTask () {
	        	public int countRuns = 0; // countRuns keeps tracks of hourly update loop
	        	@Override
	            public void run () { // any code in this method will be performed periodically
	            	countRuns++;
	                if (countRuns >= 1000) { // currently, code will update data 6 times
	                    timer.cancel();
	                    timer.purge();
	                    return;
	                }
	                
	        		CardLayout_Interface.percentage = gatherData(accessToken, service); // here's the data call to Fitbit
	 
	                // The try/catch is currently being used as a test for period data updates
	                // It will create a text file named "overnightTest.txt"
	                // It just appends the "test", every time data updates
	                /*
	                try
	                {
	                    String filename= "overnightTest.txt";
	                    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
	                    fw.write("test \n");//appends the string to the file
	                    fw.close();
	                }
	                catch(IOException ioe)
	                {
	                    System.err.println("IOException: " + ioe.getMessage());
	                }
	                */
	            }   
	        }; //ends TimerTask hourlyTask       
	
	        // schedule the task to run starting now and then every hour
	        // timer.schedule (task, long delay, long period)
	        // delay = delay in milliseconds before task is executed
	        // period = time in milliseconds between successive task executions
	        	timer.schedule (hourlyTask, 0l, 1000*5); //period should be 1000*60*60 to call hourly
	 
		}
	
		// This method uses the accessToken to gather data from Fitbit
		// It also parses through input received and prints
		// the step stats and goals
		// It calculates goal percentage and outputs to screen
		public static String gatherData(Token accessToken, OAuthService service){
			//Grabbing daily goals
	        OAuthRequest request1 = new OAuthRequest(Verb.GET, "https://api.fitbit.com/1/user/-/activities/goals/daily.json");
	        service.signRequest(accessToken, request1);
	        Response response1 = request1.send();
	        String goals = response1.getBody();
	        
	        //Grabbing stats for the day
	        OAuthRequest request2 = new OAuthRequest(Verb.GET, "https://api.fitbit.com/1/user/-/activities/tracker/steps/date/today/1d.json");
	        service.signRequest(accessToken, request2);
	        Response response2 = request2.send();
	        String activity  = response2.getBody();
	        
	        //Finds steps goal within all goal data
	        int index1 = goals.indexOf("steps");
	        String sub1 = goals.substring(index1+7);
	        int index2 = sub1.indexOf("}");
	        String sub2 = sub1.substring(0, index2);
	        int stepGoal = Integer.parseInt(sub2);
	        
	        //Finds steps stats within all stats
	        index1 = activity.indexOf("value");
	        sub1 = activity.substring(index1+8);
	        index2 = sub1.indexOf("\"");
	        sub2 = sub1.substring(0, index2);
	        int stepStat = Integer.parseInt(sub2);
	
	        /* Used for testing
	        System.out.println("\nYour steps today are: " + stepStat);
	        System.out.println("Your steps goal is: " + stepGoal);
	        */
	        
	        double doublePercentage = ((stepStat*1.0)/stepGoal)*100;
	        int percentage = (int) doublePercentage;
	        if (percentage > 100) percentage = 100;
	        // Used for testing
	        //System.out.println("You are " + percentage + "% to meeting your goal.");
	        
	        
	        // this whole part with variable p is just used for testing
	        // comment out from "p-=" to "return Integer.toString(p);" if you want to see real function
	        // since fitbit data does not change very often
	        p = 0; //*** p -= 1; //*** change this back to p -= 1 to decrement again!!! ****

	         
	        CardLayout_Interface.percentage = Integer.toString(p);
	        		//Integer.toString(percentage);
	        System.out.println("P = " + p);
	        
	        return Integer.toString(p); //used for testing
	        
	        
	        // comment out line 191 if you want to test using lines 179-187
	        //return Integer.toString(percentage);
	}
}
