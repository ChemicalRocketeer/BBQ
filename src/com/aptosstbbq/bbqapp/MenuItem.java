package com.aptosstbbq.bbqapp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
	public class MenuItem {
	private String name = "Unnamed Menu Item";
	private List<Ingredient> ingredients = new ArrayList<>();
	private List<InterchangableIngredient> interchangableIngredients = new ArrayList<>();
	private double cost = -1.0;
	private BigDecimal price = new BigDecimal("-1");
	
	public MenuItem(String name, double cost, Ingredient... ings) {
	public MenuItem(String name, BigDecimal price, Ingredient... ings) {
		setName(name);
		setCost(cost);
		setCost(price);
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

		}
	public double getCost() {
		return cost;
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	public void setCost(BigDecimal cost) {
		this.price = cost;
	}
}