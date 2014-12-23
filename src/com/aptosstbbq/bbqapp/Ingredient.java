package com.aptosstbbq.bbqapp;

public class Ingredient {
	
	private String name = "Unnamed Ingredient";
	private boolean soldOut = false;
	private boolean isCrucial = false;
	
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
	public void setIsCrucial(boolean b){
		isCrucial = b;
	}
	public boolean getisCrucial(){
		return isCrucial;
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
