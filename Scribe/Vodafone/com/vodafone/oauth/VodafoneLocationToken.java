package com.vodafone.oauth;

import java.net.MalformedURLException;
import java.util.Scanner;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

public class VodafoneLocationToken {
  
  private static final String PROTECTED_RESOURCE_URL = "http://api.developer.vodafone.com/v2/location/queries/location?address=tel%3A447999999999&requestedAccuracy=1500";
  
  public static void main(String[] args) throws MalformedURLException
  {

	String redirect_uri = "http://www.example/";
	String scope = "GET-/location/queries/location";
    String apiKey = "xxx";
    String responseType = "token";
    VodafoneAPI api = new VodafoneAPI();
    ServiceBuilder service = new ServiceBuilder()
                                  .apiKey(apiKey)
                                  .callback(redirect_uri)
                                  .scope(scope)
                                  .responseType(responseType);
    
    Scanner in = new Scanner(System.in);
    System.out.println();

//    Obtain the Authorization URL
    System.out.println("Fetching the Authorization URL...");
    System.out.println("Consent Screen URL. Copy and paste the following URL on a browser. Provide consent");
    String authorizationUrl = api.getAuthorizationUrl(service);
    System.out.println(authorizationUrl);
    System.out.println("And paste the auth code here");
    System.out.print(">>");
    String token = in.nextLine();
    System.out.println();
    
    // Now let's go and ask for a protected resource!
    System.out.println("Now we're going to access a protected resource...");
    OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
    request.addHeader("Authorization", "OAuth "+token);
    Response response = request.send();
    System.out.println("Got it! Lets see what we found...");
    System.out.println();
    System.out.println(response.getCode());
    System.out.println(response.getBody());
  }
}