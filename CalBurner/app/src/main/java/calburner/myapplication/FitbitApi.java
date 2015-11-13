package calburner.myapplication;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * Class necessary to get Scribe working with Fitbit's API.
 * Used from the following website: https://senecahealth.wordpress.com/2013/09/25/accessing-fitbits-web-api/
 *
 * @Author Anthony Chang
 */
public class FitbitApi extends DefaultApi10a {

    /* Private string variable to hold the authorization URL using OAuth 2*/
    private static final String AUTHORIZE_URL = "https://www.fitbit.com/oauth2/authorize?oauth_token=%s";

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
        String authorizationURL = AUTHORIZE_URL;

        return authorizationURL;
    }
}
