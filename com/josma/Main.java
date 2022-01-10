package com.josma;

import oauth.signpost.exception.*;
import java.io.IOException;
import java.io.File;
import org.ini4j.*;
import java.net.URISyntaxException;

public class Main
{
    public static final String CSV_FILEPATH = "TestData.csv";
    public static final String KEYS_FILEPATH = "Keys.ini";
    public static final String API_URL = "https://api.openstreetmap.org/api/0.6/";
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
    public static String CONSUMER_KEY;
    public static String CONSUMER_SECRET;

    public static void main(String[] args) throws IOException, CsvException, URISyntaxException,
            OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, OAuthNotAuthorizedException
    {
        if (new File(KEYS_FILEPATH).exists())
        {
            Wini userKeys = new Wini(new File(KEYS_FILEPATH));
            CONSUMER_KEY = userKeys.get("AuthenticationKeys", "ConsumerKey", String.class);
            CONSUMER_SECRET = userKeys.get("AuthenticationKeys", "ConsumerSecret", String.class);
        }
        else
        {
            System.out.println("File " + KEYS_FILEPATH + " not found. This file should contain the Authentication Keys.");
            System.exit(1);
        }

        OpenStreetMap MyMap = new OpenStreetMap(API_URL, USER_AGENT, CONSUMER_KEY, CONSUMER_SECRET);
        System.out.println("Connection to OpenStreetMap API established.");
    }
}
