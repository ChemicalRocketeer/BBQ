package aptosAppClasses;

import java.util.Calendar;
public class Ingredient {
	
	private String name = "Unnamed Ingredient";
	private boolean soldOut = false;
	private String soldOutAt;
	public Ingredient(){
		//System.out.println("hi");
	}
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
	//	if(so !=soldOut)
		//	soldOutAt = getTime();
		soldOut = so;
	}
	public boolean equals(Ingredient other) {
		return equals(other.name);
	}
}