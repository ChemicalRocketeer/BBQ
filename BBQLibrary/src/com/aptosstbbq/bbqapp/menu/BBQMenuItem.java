package com.aptosstbbq.bbqapp.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BBQMenuItem {

	private static long nextID = 0;
	public final long id = nextID++;

	private String name;
	private String description = null;
	private String category = null;
	private String price = null;
	private List<String> ingredients = new ArrayList<String>();
	private List<InterchangableIngredient> interchangableIngredients = new ArrayList<InterchangableIngredient>();

	public BBQMenuItem(String name, String... ings) {
		this.name = name;
		for (String ing : ings) {
			addIngredient(ing);
		}
	}

	public BBQMenuItem(String name, String description, String category, String price, List<String> ingredients, List<InterchangableIngredient> interchangables) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.ingredients = ingredients;
		this.interchangableIngredients = interchangables;
	}

	protected BBQMenuItem setName(String name) {
		this.name = name;
		return this;
	}

	public BBQMenuItem setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public BBQMenuItem setCategory(String cat) {
		this.category = cat;
		return this;
	}
	
	public BBQMenuItem setCategory(BBQCategory cat) {
		return setCategory(cat.getName());
	}

	public BBQMenuItem setPrice(String price) {
		this.price = price;
		return this;
	}
	
	public BBQMenuItem addIngredient(Ingredient... ings) {
		for (Ingredient ing : ings) {
			addIngredient(ing.getName());
		}
		return this;
	}

	public BBQMenuItem addIngredient(String... ings) {
		for (String ing : ings) {
			if (!ingredients.contains(ing)) {
				ingredients.add(ing);
			}
		}
		return this;
	}

	public BBQMenuItem addInterchangableIngredient(InterchangableIngredient... ings) {
		for (InterchangableIngredient ing : ings) {
			if (!interchangableIngredients.contains(ing)) {
				interchangableIngredients.add(ing);
			}
		}
		return this;
	}

	public List<String> getIngredients() {
		return Collections.unmodifiableList(ingredients);
	}

	public List<InterchangableIngredient> getInterchangableIngredients() {
		return Collections.unmodifiableList(interchangableIngredients);
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}
	
	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}
}
