import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;


public class main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		// Create the OAuthService object.

	    OAuthService service = new ServiceBuilder()
	            .provider(FitbitApi.class)
	            .apiKey("7f0f8201723b1e7960667563280e3a96") // These keys can be found in our chain email.
	            .apiSecret("820ee35bd16d38d44b76651559a1753c")
	            //.callback("http://calburner.weebly.com")        // Add a callback URL here if needed
	            //.scope("activity")
	            .debug()
	            .build();

	    // Get the request token.

	    Token requestToken = service.getRequestToken();
	    System.out.println("***test1***");
	    // Make user validate request token.

	   String authURL = service.getAuthorizationUrl(requestToken);
	   System.out.println("***test2***");
	    // Load Fitbit sign in page here
	    
	    System.out.println("Now go and authorize Scribe here:");
	    System.out.println(authURL);
	    System.out.println("And paste the verifier here");
	    System.out.println("It's at the end of the url in your broswer.");
	    System.out.println(">>");
	   
	    
	    // Get the access token.
	    
	    Verifier v = new Verifier(in.nextLine());
	    Token accessToken = service.getAccessToken(requestToken, v); // Request token from above
	    System.out.println("***test3***");
	    
	    
	    // Sign the request. Apparently this is where the first API call happens? o.o
		
		
	    	System.out.println("test");
	        OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.fitbit.com/1/user/-/profile.json"); // Fill in blank with some URL used to get user profile data
	        service.signRequest(accessToken, request);
	        Response response = request.send();
	        System.out.println(response.getBody());
	        String profile = response.getBody();
	        
	     //Parses through the response and grabs user ID.
	        int indexID = profile.indexOf("encodedId");
	        String profileSub = profile.substring(indexID+12);
	        int endIndex = profileSub.indexOf("\"");
	        String userID = profileSub.substring(0,endIndex);
	        System.out.println("The user ID is " + userID);
	        
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        String date = dateFormat.format(cal.getTime());
	        
	     //Must grab user id...3NLTRX is Chanel's user id & must grab today's date.
	        OAuthRequest request1 = new OAuthRequest(Verb.GET, "https://api.fitbit.com/1/user/" + userID + "/activities/date/" + date + ".json");
	        service.signRequest(accessToken, request1);
	        Response response1 = request1.send();
	        System.out.println(response1.getBody());
	        
	}


}