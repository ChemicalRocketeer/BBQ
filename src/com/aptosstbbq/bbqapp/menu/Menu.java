package com.aptosstbbq.bbqapp.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.aptosstbbq.bbqapp.Logger;
import com.aptosstbbq.bbqapp.ThreadedWriter;
import com.aptosstbbq.bbqapp.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Menu {

	private HashMap<String, Ingredient> ingredients = new HashMap<>();
	private List<MenuItem> menuItems = new ArrayList<>();

	public static final Ingredient NULL_INGREDIENT = new Ingredient("NULL",
			true);

	public void toggleSoldOut(String ingredient) {
		Ingredient ing = ingredients.get(ingredient);
		if (ing != null) {
			ing.setSoldOut(!ing.isSoldOut());
			String message = ing.isSoldOut() ? "Sold Out:\t" : "Available:\t";
			message += ingredient + '\t';
			message += Utils.time();
			Logger.SELL_OUT.log(message);
		}
	}

	public void addIngredient(Ingredient ing) {
		if (!ingredients.containsKey(ing.getName())) {
			ingredients.put(ing.getName(), ing);
		}
		Logger.MENU_CHANGES.log("Ingredient Added\t" + ing.toString());
	}

	public void addMenuItem(MenuItem mi) {
		menuItems.add(mi);
		Logger.MENU_CHANGES.log("Menu Item Added\t" + mi.toString());
	}

	public Ingredient getIngredient(String name) {
		Ingredient ing = ingredients.get(name);
		return ing == null ? NULL_INGREDIENT : ing;
	}

	public void saveMenu() {
		ThreadedWriter.write("MENU", toJSON());
	}
	
	public static Menu fromJSON(String json) {
		Gson obj = new Gson();
		return obj.fromJson(json, Menu.class);
	}

	public static Menu fromFile(String path) {
		return fromJSON(Utils.readFile(path));
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
		steve.append("Menu Items:\n");
		for (MenuItem mi : menuItems) {
			steve.append(mi.getName());
			steve.append(": ");
			if (mi.isSoldOut()) {
				steve.append("Sold Out");
			} else {
				steve.append(mi.getPrice());
			}
			steve.append("\n");
		}
		return steve.toString();
	}
}
