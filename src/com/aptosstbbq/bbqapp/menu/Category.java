package com.aptosstbbq.bbqapp.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category {

	private String name = "Unnamed Category";
	private Category parent = null;
	private List<String> menuItems = new ArrayList<>();
	private List<String> subCategories = new ArrayList<>();
	
	public Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public List<String> getMenuItems() {
		return Collections.unmodifiableList(menuItems);
	}

	public Category addMenuItem(String... items) {
		for (String item : items) {
			if (!menuItems.contains(item)) {
				menuItems.add(item);
			}
		}
		return this;
	}

	public Category removeMenuItem(String... items) {
		for (String item : items) {
			menuItems.remove(item);
		}
		return this;
	}

	public List<String> getSubCategories() {
		return Collections.unmodifiableList(subCategories);
	}

	public Category addSubCategory(String... subCats) {
		for (String subcat : subCats) {
			if (!subCategories.contains(subcat)) {
				subCategories.add(subcat);
			}
		}
		return this;
	}

	public Category removeSubCategory(String... subCats) {
		for (String subcat : subCats) {
			menuItems.remove(subcat);
		}
		return this;
	}
}
