package com.aptosstbbq.bbqapp.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InterchangableIngredient {
	
	List<String> interchangables = new ArrayList<>();
	
	public InterchangableIngredient(String... vars) {
		for (String ing : vars) {
			interchangables.add(ing);
		}
	}

	public List<String> getIngredients() {
		return Collections.unmodifiableList(interchangables);
	}
}
