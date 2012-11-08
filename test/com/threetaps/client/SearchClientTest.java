package com.threetaps.client;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import com.threetaps.dto.search.BestMatchResponse;
import com.threetaps.dto.search.RangeRequest;
import com.threetaps.dto.search.RangeResponse;
import com.threetaps.dto.search.SearchRequest;
import com.threetaps.dto.search.SearchResponse;
import com.threetaps.dto.search.SummaryRequest;
import com.threetaps.dto.search.SummaryResponse;


public class SearchClientTest extends TestCase {
	
	protected SearchClient searchClient;
	
	protected void setUp() {
		searchClient = ThreetapsClient.getInstance().setAuthToken("75c8e3851e874fe692a8199022a06645").getSearchClient();
	}
	
	@Test
	public void testSearch() throws IOException {
		
		final SearchRequest searchRequest = new SearchRequest();
	
		searchRequest.setText("Nintendo");
		searchRequest.setSource("CRAIG");

		SearchResponse searchResponse = searchClient.search(searchRequest);
		assert searchResponse.getSuccess().booleanValue() == true;
		System.out.println(searchResponse.getNumResults().intValue());
	}
	
	@Test
	public void testCount() throws IOException {
		
		final SearchRequest searchRequest = new SearchRequest();
	
		searchRequest.setText("Nintendo");
		searchRequest.setSource("CRAIG");

		int count = searchClient.count(searchRequest);
		assert count != 0;
	}
	
	@Test
	public void testSummary() throws IOException {
		
		final SearchRequest searchRequest = new SearchRequest();	
		searchRequest.setText("Nintendo");
		
		final SummaryRequest summaryRequest = new SummaryRequest();
		summaryRequest.setSearchRequest(searchRequest);
		summaryRequest.setDimension("source");
		
		final SummaryResponse summaryResponse = searchClient.summary(summaryRequest);
		assert summaryResponse.getExecTimeMs().intValue() > 0;
		assert summaryResponse.getTotals().get("CRAIG") > 0;
	}
}
