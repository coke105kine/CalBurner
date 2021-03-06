import java.io.FileWriter;
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

public class main {

	public static void main(String[] args) throws IOException, URISyntaxException {
		Scanner in = new Scanner(System.in);
		
		// Create the OAuthService object.

		final OAuthService service = new ServiceBuilder()
	            .provider(FitbitApi.class)
	            .apiKey(FitbitApi.getApiKey()) // These keys can be found in our chain email.
	            .apiSecret(FitbitApi.getApiSecret())
	            //.scope("activity")
	            .debug()
	            .build();

	    // Get the request token.
	    Token requestToken = service.getRequestToken();
	    
	    // Make user validate request token.
	   String authURL = service.getAuthorizationUrl(requestToken);
	   	   
	    // Load Fitbit sign in page here	
	   if(Desktop.isDesktopSupported())
	   {
	     Desktop.getDesktop().browse(new URI(authURL));
	   }
	   
	   
	   /*
	    System.out.println("Now go and authorize Scribe here:");
	    System.out.println(authURL);*/
	    // Anthony commented out 11/28/15
	    //System.out.println("And paste the verifier here");
	    //System.out.println("It's at the end of the url in your broswer.");
	    //System.out.println(">>");
	    System.out.println("Please enter the verification code by completing the following steps:");
	    System.out.println("1) You will now be asked to log in to Fitbit.com using your account.");
	    System.out.println("   If you do not have a Fitbit account, please create one first at Fitbit.com.");
	    System.out.println("2) After logging in, you will be re-directed to CalBurner's website.");
	    System.out.println("3) Obtain the verification code from our website.");
	    System.out.println("   This code can be entered here in one of two ways:");
	    System.out.println("   a) Highlight the letters and numbers after 'oauth_token =' in the address bar.");
	    System.out.println("   b) Press 'Verifier ID' on the CalBurner webpage, and highlight the letters and numbers in the popup.");
	    System.out.println("4) Copy the verification code by:");
	    System.out.println("   a) Pressing 'Crtl + C' or Right-Clicking and selecting 'Copy'.");
	    System.out.println("5) Return to this application and paste the code by:");
	    System.out.println("   a) Clicking in the code entry area.");
	    System.out.println("   b) Pressing 'Ctrl + V' or Right-Clicking and selecting 'Paste'.");
	    System.out.println("");
	    System.out.println(">>");
	    	   
	    
	    // Get the access token.    
	    Verifier v = new Verifier(in.nextLine());
	    final Token accessToken = service.getAccessToken(requestToken, v); // Request token from above	    
	    

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
        
        
        // This is used to have the app update fitbit data periodically (hourly)
        final Timer timer = new Timer ();
        TimerTask hourlyTask = new TimerTask () {
        	public int countRuns = 0; // countRuns keeps tracks of hourly update loop
        	@Override
            public void run () { // any code in this method will be performed periodically
            	countRuns++;
                if (countRuns >= 6) { // currently, code will update data 6 times
                    timer.cancel();
                    timer.purge();
                    return;
                }
                
                
                gatherData(accessToken, service); // here's the data call to Fitbit
 
                // The try/catch is currently being used as a test for period data updates
                // It will create a text file named "overnightTest.txt"
                // It just appends the "test", every time data updates
                
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
	public static void gatherData(Token accessToken, OAuthService service){
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

        System.out.println("\nYour steps today are: " + stepStat);
        System.out.println("Your steps goal is: " + stepGoal);
        
        double doublePercentage = ((stepStat*1.0)/stepGoal)*100;
        int percentage = (int) doublePercentage;
        if (percentage > 100) percentage = 100;
        System.out.println("You are " + percentage + "% to meeting your goal.");
	}
}
