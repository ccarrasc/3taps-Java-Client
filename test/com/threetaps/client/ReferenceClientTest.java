package com.threetaps.client;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.threetaps.model.Category;

public class ReferenceClientTest extends TestCase {
	
	protected ReferenceClient referenceClient;
	
	protected void setUp() {
		referenceClient = ThreetapsClient.getInstance().setAuthToken("75c8e3851e874fe692a8199022a06645").getReferenceClient();
	}	

	@Test
	public void testGetCategories() throws IOException {
		final List<Category> categories = referenceClient.getCategories();
		assert(categories != null);
		assert(categories.size() > 0);
	}
}
