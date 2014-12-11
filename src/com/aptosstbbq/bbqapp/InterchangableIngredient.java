package com.aptosstbbq.bbqapp;

import java.util.ArrayList;

public class InterchangableIngredient {
	
	ArrayList<Ingredient> interchangables = new ArrayList<>();
	
	public InterchangableIngredient(Ingredient... vars) {
		for(Ingredient ing : vars){
			interchangables.add(ing);
		}
	}
	
	public boolean isSoldOut() {
		for (Ingredient ing : interchangables) {
			if (!ing.isSoldOut()) return false;
		}
		return true;
	}
}
