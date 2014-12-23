package com.aptosstbbq.bbqapp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Menu {

	public HashMap<String, Ingredient> ingredients = new HashMap<>();
	public List<MenuItem> menuItems = new ArrayList<>();

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
		Logger.MENU_CHANGES.log("Ingredient Added\t" + ing.toString() + '\t' + Utils.time());
	}

	public void addMenuItem(MenuItem mi) {
		menuItems.add(mi);
		Logger.MENU_CHANGES.log("Menu Item Added\t" + mi.toString() + '\t' + Utils.time());
	}

	public Ingredient getIngredient(String name) {
		Ingredient ing = ingredients.get(name);
		return ing == null ? NULL_INGREDIENT : ing;
	}

	public void saveMenu() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		ThreadedWriter.write("MENU", json);
	}

	public static Menu getMenuFromFile() {
		StringBuilder string = new StringBuilder();
		try (Scanner in = new Scanner(new File("MENU"))) {
			while (in.hasNextLine()) {
				string.append(in.nextLine());
			}
		} catch (Exception e) {
		}
		Gson obj = new Gson();
		return obj.fromJson(string.toString(), Menu.class);
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
