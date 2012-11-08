package com.threetaps.model;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Category {

    private ArrayList<Category> children;
    private boolean isCategoryClass = false; // if true, indicates the Category has children
	private String code;
	private String categoryName;

    public static JsonDeserializer<Category> getGsonDeserializer() {
        return new JsonDeserializer<Category>() {
            @Override
            public Category deserialize(JsonElement jsonElement, Type type,
                    JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject payload = jsonElement.getAsJsonObject();

                Category category = new Category();
                category.isCategoryClass = true;
                category.children = new ArrayList<Category>();
                category.setCode(payload.get("categoryClass").getAsString());
                category.setCategoryName(payload.get("categoryClassName").getAsString());

                Iterator<JsonElement> iterator = payload.getAsJsonArray("categories").iterator();
                while (iterator.hasNext()) {
                    JsonObject child = iterator.next().getAsJsonObject();

                    Category childCategory = new Category();
                    childCategory.setCode(child.get("category").getAsString());
                    childCategory.setCategoryName(child.get("categoryName").getAsString());

                    category.children.add(childCategory);
                }

                return category;
            }
        };
    }

    // hack to build the category class -> categories relationship --KT
    private Category() {

    }

    public boolean isCategoryClass() {
        return isCategoryClass;
    }

    public List<Category> getChildren() {
        if (!isCategoryClass) {
            throw new IllegalStateException("cannot request children of Category that is not itself a category class");
        }

        return children;
    }

	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
