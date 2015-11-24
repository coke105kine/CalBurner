//package calburner.myapplication;

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.Token;

/**
 * Class necessary to get Scribe working with Fitbit's API.
 * Used from the following website: https://senecahealth.wordpress.com/2013/09/25/accessing-fitbits-web-api/
 *
 * @Author Anthony Chang
 */
public class FitbitApi extends DefaultApi10a {
	private static String apiKey = "7f0f8201723b1e7960667563280e3a96";
	private static String apiSecret = "820ee35bd16d38d44b76651559a1753c";
	
	public static String getApiKey(){
		return apiKey;
	}
	
	public static String getApiSecret(){
		return apiSecret;
	}
	
	public static final String BASE_URL = "http://api.fitbit.com/";

    /* Private string variable to hold the authorization URL using OAuth 2*/
    private static final String AUTHORIZE_URL = "https://api.fitbit.com/oauth/authorize?oauth_token=%s";

    /*
    * Public method to get the endpoint of access token.
    */
    public String getAccessTokenEndpoint(){
        String accessTokenEndpoint = "https://api.fitbit.com/oauth/access_token";

        return accessTokenEndpoint;
    }

    /*
    * Public method to get the endpoint of request token.
    */
    public String getRequestTokenEndpoint(){
        String requestTokenEndpoint = "https://api.fitbit.com/oauth/request_token";

        return requestTokenEndpoint;
    }

    /*
    * Public method to get authorization URL.
    */
    public String getAuthorizationUrl(Token requestToken){
        String authorizationURL = String.format(AUTHORIZE_URL, requestToken.getToken());

        return authorizationURL;
    }
}
