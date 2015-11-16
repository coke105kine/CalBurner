import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import java.net.URISyntaxException;
import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import java.awt.Desktop; 	//used to open web browser
import java.net.URI; 		//used to open web browser

public class main {

	public static void main(String[] args) throws IOException, URISyntaxException {
		Scanner in = new Scanner(System.in);
		
		// Create the OAuthService object.

		OAuthService service = new ServiceBuilder()
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
	    System.out.println("And paste the verifier here");
	    System.out.println("It's at the end of the url in your broswer.");
	    System.out.println(">>");
	    	   
	    
	    // Get the access token.    
	    Verifier v = new Verifier(in.nextLine());
	    Token accessToken = service.getAccessToken(requestToken, v); // Request token from above	    
	    

	    // Sign the request. Apparently this is where the first API call happens? o.o	
    	System.out.println("test");
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.fitbit.com/1/user/-/profile.json"); 
        service.signRequest(accessToken, request);
        Response response = request.send();
        System.out.println(response.getBody());
        String profile = response.getBody();
        
        
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
