package aptosAppClasses;


import java.util.ArrayList;
import java.util.List;

public class Menu {
	
	private List<Ingredient> ingredients = new ArrayList<>();
	private List<MenuItem> menuItems = new ArrayList<>();
	
	public static final Ingredient NULL_INGREDIENT = new Ingredient("NULL", true);
	
	public void addIngredient(Ingredient ing) {
		if (!ingredients.contains(ing)){
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
				steve.append(mi.getCost());
			}
			steve.append("\n");
		}
		return steve.toString();
	}
}