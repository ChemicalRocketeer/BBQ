package com.aptosstbbq.bbqapp.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.aptosstbbq.bbqapp.util.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BBQMenu {

	private HashMap<String, Ingredient> ingredients = new HashMap<>();
	private HashMap<String, BBQMenuItem> menuItems = new HashMap<>();
	private HashMap<String, BBQCategory> categories = new HashMap<>();

	public static final Ingredient NULL_INGREDIENT = new Ingredient("NULL", true);

	public boolean isSoldOut(BBQCategory cat) {
		for (String item : cat.getMenuItems()) {
			if (!isSoldOut(getMenuItem(item))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isSoldOut(BBQMenuItem mi) {
		for (String ing : mi.getIngredients()) {
			if (getIngredient(ing).isSoldOut()) return true;
		}
		for (InterchangableIngredient inter : mi.getInterchangableIngredients()) {
			if (isSoldOut(inter))
				return true;
		}
		return false;
	}

	public boolean isSoldOut(InterchangableIngredient inter) {
		for (String ing : inter.getIngredients()) {
			// if any ingredient is available, the InterchangableIngredient is available.
			if (!getIngredient(ing).isSoldOut())
				return false;
		}
		return true;
	}

	public void toggleSoldOut(String ingredient) {
		Ingredient ing = ingredients.get(ingredient);
		if (ing != null) {
			ing.setSoldOut(!ing.isSoldOut());
			String message = ing.isSoldOut() ? "Sold Out:\t" : "Available:\t";
			message += ingredient + '\t';
			Logger.SELL_OUT.log(message);
		}
	}

	public List<Ingredient> getIngredients() {
		return Collections.unmodifiableList(new ArrayList<Ingredient>(ingredients.values()));
	}

	public List<BBQMenuItem> getBBQMenuItems() {
		return Collections.unmodifiableList(new ArrayList<BBQMenuItem>(menuItems.values()));
	}

	public List<BBQCategory> getBBQCategories() {
		return Collections.unmodifiableList(new ArrayList<BBQCategory>(categories.values()));
	}

	public void addIngredient(Ingredient mi) {
		ingredients.put(mi.getName(), mi);
	}

	public void addBBQMenuItem(BBQMenuItem mi) {
		menuItems.put(mi.getName(), mi);
	}

	public void addBBQCategory(BBQCategory mi) {
		categories.put(mi.getName(), mi);
	}

	public Ingredient getIngredient(String name) {
		return ingredients.get(name);
	}

	public BBQMenuItem getMenuItem(String name) {
		return menuItems.get(name);
	}

	public BBQCategory getCategory(String name) {
		return categories.get(name);
	}

	public static BBQMenu fromJSON(String json) {
		Gson obj = new Gson();
		BBQMenu bBQMenu = obj.fromJson(json, BBQMenu.class);
		return bBQMenu != null ? bBQMenu : new BBQMenu();
	}

	public String toJSON() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public String toString() {
		StringBuilder steve = new StringBuilder();
		steve.append("Ingredients:\n");
		for (Ingredient ing : ingredients.values()) {
			steve.append(ing.getName());
			steve.append(": ");
			steve.append(ing.isSoldOut() ? "Sold Out\n" : "In Stock\n");
		}
		steve.append("BBQMenu Items:\n");
		for (BBQMenuItem mi : getBBQMenuItems()) {
			steve.append(mi.getName());
			steve.append(": ");
			if (isSoldOut(mi)) {
				steve.append("Sold Out");
			} else {
				steve.append(mi.getPrice());
			}
			steve.append("\n");
		}
		return steve.toString();
	}

	public void reset() {
		for (Ingredient ing : getIngredients()) {
			ing.setSoldOut(false);
		}
	}
}
