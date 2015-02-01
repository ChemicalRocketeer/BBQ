package com.aptosstbbq.bbqapp.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BBQCategory {

	private String name;
	private String parent = null;
	private List<String> menuItems = new ArrayList<>();
	private List<BBQCategory> subCategories = new ArrayList<>();
	
	public BBQCategory(String name) {
		this.name = name;
	}

	protected BBQCategory setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	public String getParent() {
		return parent;
	}

	public List<String> getMenuItems() {
		return Collections.unmodifiableList(menuItems);
	}

	public List<BBQCategory> getSubCategories() {
		return Collections.unmodifiableList(subCategories);
	}

	public void setParent(BBQCategory parent) {
		this.parent = parent.getName();
	}

	public BBQCategory addMenuItem(String... items) {
		for (String item : items) {
			if (!menuItems.contains(item)) {
				menuItems.add(item);
			}
		}
		return this;
	}

	public BBQCategory addSubCategory(BBQCategory... subCats) {
		for (BBQCategory subcat : subCats) {
			if (!subCategories.contains(subcat)) {
				subCategories.add(subcat);
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

	public BBQCategory removeSubCategory(String... subCats) {
		for (String name : subCats) {
			for (BBQCategory subcat : subCategories) {
				if (name.equals(subcat.getName())) {
					menuItems.remove(subcat);
				}
			}
		}
		return this;
	}
}
