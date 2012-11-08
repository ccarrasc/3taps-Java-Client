package com.threetaps.client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.google.gson.reflect.TypeToken;
import com.threetaps.model.Category;
import com.threetaps.model.Location;

public class ReferenceClient extends Client {
	
	private static ReferenceClient instance;
	
	private ReferenceClient() {};
	
	public synchronized static ReferenceClient getInstance() {
		if (instance == null) instance = new ReferenceClient();
		return instance;
	}

	public List<Category> getCategories() throws IOException {

		final HttpResponse response = this.executeGet("/reference/categories");
		final String responseString = EntityUtils.toString(response.getEntity());

		final Type type = new TypeToken<Collection<Category>>() {}.getType();
		
		@SuppressWarnings("unchecked")
		final List<Category> categories = (List<Category>)gson.fromJson(responseString, type);
		return categories;
	}
}
