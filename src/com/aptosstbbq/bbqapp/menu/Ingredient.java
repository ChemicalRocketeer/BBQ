package com.aptosstbbq.bbqapp.menu;

public class Ingredient {
	
	private String name = "Unnamed Ingredient";
	private boolean soldOut = false;
	
	public Ingredient(String name) {
		this(name, false);
	}
	
	public Ingredient(String name, boolean soldOut) {
		setName(name);
		setSoldOut(soldOut);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isSoldOut() {
		return soldOut;
	}
	
	public void setSoldOut(boolean so) {
		soldOut = so;
	}
	
	public boolean equals(Ingredient other) {
		return name.equals(other.name);
	}
}
