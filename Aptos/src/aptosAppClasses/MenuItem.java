package aptosAppClasses;
import java.util.ArrayList;
import java.util.List;

public class MenuItem {
	
	private String name = "Unnamed Menu Item";
	private List<Ingredient> ingredients = new ArrayList<>();
	private double cost = -1.0;
	
	public MenuItem(String name, double cost, Ingredient... ings) {
		setName(name);
		setCost(cost);
		for (Ingredient ing : ings) {
			addIngredient(ing);
		}
	}
	
	public boolean itemIsSoldOut() {
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
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
}

