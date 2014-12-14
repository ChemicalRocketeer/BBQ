package com.aptosstbbq.bbqapp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Menu {

	private List<Ingredient> ingredients = new ArrayList<>();
	private List<MenuItem> menuItems = new ArrayList<>();

	public static final Ingredient NULL_INGREDIENT = new Ingredient("NULL",
			true);

	public void addIngredient(Ingredient ing) {
		if (!ingredients.contains(ing)) {
			ingredients.add(ing);
		}
	}

	public Ingredient getIngredient(String name) {
		for (Ingredient ing : ingredients) {
			if (ing.getName() == name) {
				return ing;
			}
		}
		return NULL_INGREDIENT;
	}

	public void addMenuItem(MenuItem mi) {
		menuItems.add(mi);
	}

	public String toString() {
		StringBuilder steve = new StringBuilder();
		steve.append("Ingredients:\n");
		for (Ingredient ing : ingredients) {
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

	public void saveMenu() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter("MENU", false)));
			out.print(json);
			out.close();
		} catch (Exception e) {
		}
	}

	public static Menu getMenuFromFile() {
		StringBuilder string = new StringBuilder();
		try {
			File file = new File("MENU");
			Scanner in = new Scanner(file);
			while(in.hasNextLine()){
				string.append(in.nextLine());
			}
		} catch (Exception e) {
		}
		Gson obj = new Gson();
		return obj.fromJson(string.toString(), Menu.class);
	}
}
