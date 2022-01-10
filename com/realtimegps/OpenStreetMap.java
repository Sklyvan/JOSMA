package com.realtimegps;

import de.westnordost.osmapi.OsmConnection;
import de.westnordost.osmapi.user.*;
import oauth.signpost.*;
import oauth.signpost.basic.*;
import oauth.signpost.exception.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenStreetMap
{
    private final OsmConnection osmConnection;

    public static final String REQUEST_TOKEN_URL = "https://www.openstreetmap.org/oauth/request_token";
    public static final String ACCESS_TOKEN_URL = "https://www.openstreetmap.org/oauth/access_token";
    public static final String AUTHORIZE_URL = "https://www.openstreetmap.org/oauth/authorize";

    public OpenStreetMap(String apiURL, String userAgent, String CONSUMER_KEY, String CONSUMER_SECRET) throws IOException, URISyntaxException,
            OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthNotAuthorizedException
    {
        OAuthConsumer authConsumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        OAuthProvider authProvider = new DefaultOAuthProvider(REQUEST_TOKEN_URL, ACCESS_TOKEN_URL, AUTHORIZE_URL);

        String authURL = authProvider.retrieveRequestToken(authConsumer, OAuth.OUT_OF_BAND);
        authURL = OAuth.addQueryParameters(authURL, OAuth.OAUTH_CONSUMER_KEY, CONSUMER_KEY);

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
        {
            Desktop.getDesktop().browse(new URI(authURL));
        }
        else
        {
            System.out.println(authURL);
        }

        System.out.println("Enter the PIN Code and click ENTER:");

        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        String userKey = bufferReader.readLine();

        authProvider.retrieveAccessToken(authConsumer, userKey);
        this.osmConnection = new OsmConnection(apiURL, userAgent, authConsumer);
    }

    public UserInfo getUserInformation() { return new UserApi(this.osmConnection).getMine(); }
}
