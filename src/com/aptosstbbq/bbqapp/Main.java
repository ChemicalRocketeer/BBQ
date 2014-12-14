package com.aptosstbbq.bbqapp;

public class Main {

	public static void main(String[] args) {
		Menu menu = new Menu();
	/*	
		menu.addIngredient(new Ingredient("Bread"));
		menu.addIngredient(new Ingredient("Tri-Tip"));
		menu.addIngredient(new Ingredient("Pulled Pork"));
		menu.addIngredient(new Ingredient("Cole Slaw"));
		menu.addIngredient(new Ingredient("Bleu Cheese"));
		menu.addMenuItem(new MenuItem("Tri-Tip Sandwich", 14.99, menu.getIngredient("Bread"), menu.getIngredient("Tri-Tip")));
		menu.addMenuItem(new MenuItem("Tri-Tip Meal", 15.99, menu.getIngredient("Bread"), menu.getIngredient("Tri-Tip")));
		menu.addMenuItem(new MenuItem("Pulled Pork Sandwich", 14.99, menu.getIngredient("Bread"), menu.getIngredient("Pulled Pork")));
		menu.getIngredient("Pulled Pork").setSoldOut(true);
		System.out.println(menu.toString());
		menu.saveMenu();
		*/
		System.out.println(Menu.getMenuFromFile());
	}

}