package calburner.myapplication;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * Class that handles authentication to the Fitbit API using OAuth 2.0.
 * (Note: OAuth 1.0 is deprecated and will be discontinued in 2016. Safer to use OAuth 2.0.)
 * oauthAuthentication.java uses a Java library called Scribe to simplify OAuth access.
 * Scribe can be found at: https://github.com/fernandezpablo85/scribe-java
 *
 * @Author Anthony Chang
 *
 */
public class oauthAuthentication {

    // Create the OAuthService object.

    OAuthService service = new ServiceBuilder()
                                .provider(FitbitApi.class)
                                .apiKey("7f0f8201723b1e7960667563280e3a96") // These keys can be found in our chain email.
                                .apiSecret("820ee35bd16d38d44b76651559a1753c")
                                // Add a callback URL here if needed
                                .build();

    // Get the request token.

    Token requestToken = service.getRequestToken();

    // Make user validate request token.

    String authURL = service.getAuthorizationUrl(requestToken);
    // Load Fitbit sign in page here

    // Get the access token.

    Verifier v = new Verifier("verifier obtained from user");
    Token accessToken = service.getAccessToken(requestToken, v); // Request token from above

    // Sign the request. Apparently this is where the first API call happens? o.o

    OAuthRequest request = new OAuthRequest(Verb.POST, ); // Fill in blank with some URL used to get user profile data
    service.signRequest(accessToken, request);
    Response response = request.send();
    System.out.println(response.getBody());
}
