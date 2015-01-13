package com.aptosstbbq.bbqapp.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BBQCategory {

	private String name = "Unnamed BBQCategory";
	private BBQCategory parent = null;
	private List<String> menuItems = new ArrayList<>();
	private List<String> subCategories = new ArrayList<>();
	
	public BBQCategory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BBQCategory getParent() {
		return parent;
	}

	public void setParent(BBQCategory parent) {
		this.parent = parent;
	}

	public List<String> getMenuItems() {
		return Collections.unmodifiableList(menuItems);
	}

	public BBQCategory addMenuItem(String... items) {
		for (String item : items) {
			if (!menuItems.contains(item)) {
				menuItems.add(item);
			}
		}
		return this;
	}

	public BBQCategory removeMenuItem(String... items) {
		for (String item : items) {
			menuItems.remove(item);
		}
		return this;
	}

	public List<String> getSubCategories() {
		return Collections.unmodifiableList(subCategories);
	}

	public BBQCategory addSubCategory(String... subCats) {
		for (String subcat : subCats) {
			if (!subCategories.contains(subcat)) {
				subCategories.add(subcat);
			}
		}
		return this;
	}

	public BBQCategory removeSubCategory(String... subCats) {
		for (String subcat : subCats) {
			menuItems.remove(subcat);
		}
		return this;
	}
}
