package com.threetaps.client;

/**
 * Wrapper class holding instances of all APIs.
 * 
 * @author devin
 *
 */
public class ThreetapsClient {
  
	private static ThreetapsClient instance;
	
	public final static String AUTH_TOKEN_KEY = "authToken";
	private String authToken;
	
	private ReferenceClient referenceClient;
	private SearchClient searchClient;

	private ThreetapsClient() {
		referenceClient = ReferenceClient.getInstance();
		searchClient = SearchClient.getInstance();
	}
	
	public synchronized static ThreetapsClient getInstance() {
		if (instance == null) instance = new ThreetapsClient();
		return instance;
	}
	
	public String getAuthToken() {
		return authToken;
	}
	
	public ThreetapsClient setAuthToken(String authToken) {
		this.authToken = authToken;
		return this;
	}	
	
	public ReferenceClient getReferenceClient() {
		return referenceClient;
	}
	
	public SearchClient getSearchClient() {
		return searchClient;
	}
}
