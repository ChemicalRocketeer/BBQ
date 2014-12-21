package com.aptosstbbq.bbqapp.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MenuItem {
	
	private String name = "Unnamed Menu Item";
	private List<Ingredient> ingredients = new ArrayList<>();
	private List<InterchangableIngredient> interchangableIngredients = new ArrayList<>();
	private BigDecimal price = new BigDecimal("-1");
	
	public MenuItem(String name, BigDecimal price, Ingredient... ings) {
		setName(name);
		setPrice(price);
		for (Ingredient ing : ings) {
			addIngredient(ing);
		}
	}
	
	public MenuItem(String name, String price, Ingredient... args) {
		this (name, new BigDecimal(price), args);
	}
	
	public boolean isSoldOut() {
		if (ingredients.size() < 1) return true;
		for (Ingredient ing : ingredients) {
			if (ing.isSoldOut()) return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addIngredient(Ingredient ing) {
		if (!ingredients.contains(ing)){
			ingredients.add(ing);
		}
	}
	
	public void addInterchangableIngredient(InterchangableIngredient ing) {
		if (!interchangableIngredients.contains(ing)){
			interchangableIngredients.add(ing);
		}
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public void setCost(String price) {
		this.price = new BigDecimal(price);
	}
}
