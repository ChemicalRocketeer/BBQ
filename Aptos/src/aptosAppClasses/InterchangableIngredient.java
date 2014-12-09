package aptosAppClasses;

import java.util.ArrayList;

public class InterchangableIngredient{
	ArrayList<Ingredient> interchangables = new ArrayList<>();
	public InterchangableIngredient(Ingredient... vars){
		for(Ingredient ing : vars){
			interchangables.add(ing);
		}
	}
	public boolean isSoldOut(){
		//
		return true;
	}
}
