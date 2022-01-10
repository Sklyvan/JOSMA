package com.josma;

import de.westnordost.osmapi.OsmConnection;
import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenStreetMap
{
    public final OsmConnection osmConnection;

    private static final String REQUEST_TOKEN_URL = "https://www.openstreetmap.org/oauth/request_token";
    private static final String ACCESS_TOKEN_URL = "https://www.openstreetmap.org/oauth/access_token";
    private static final String AUTHORIZE_URL = "https://www.openstreetmap.org/oauth/authorize";

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

}
