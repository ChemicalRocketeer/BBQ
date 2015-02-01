package com.aptosstbbq.bbqapp;

import com.aptosstbbq.bbqapp.gui.Interface;
import com.aptosstbbq.bbqapp.menu.BBQMenu;
import com.aptosstbbq.bbqapp.menu.BBQMenuItem;
import com.aptosstbbq.bbqapp.menu.Ingredient;
import com.aptosstbbq.bbqapp.menu.InterchangableIngredient;
import com.aptosstbbq.bbqapp.util.Const;
import com.aptosstbbq.bbqapp.util.ThreadedWriter;
import com.aptosstbbq.bbqapp.web.WebOut;

/**
 * Regenerates the menu, for use in development when the menu is somehow deleted or the json is invalidated.
 */
public class MenuRegenerator {

	public static void main(String[] args) {
		BBQMenu menu = new BBQMenu();
		menu.addIngredient(new Ingredient("Bread"));
		menu.addIngredient(new Ingredient("Tri-Tip"));
		menu.addIngredient(new Ingredient("Cold Beef"));
		menu.addIngredient(new Ingredient("Pulled Chicken"));
		menu.addIngredient(new Ingredient("Pulled Pork"));
		menu.addIngredient(new Ingredient("Ribs"));
		menu.addIngredient(new Ingredient("Salad"));
		menu.addIngredient(new Ingredient("Bleu Cheese"));

		menu.addMenuItem(new BBQMenuItem("BBQ Sandwiches", "Bread", "BBQ Sauce").addInterchangableIngredient(new InterchangableIngredient("Tri-Tip", "Cold Beef", "Pulled Chicken", "Pulled Pork", "Cold Pork")));
		menu.addMenuItem(new BBQMenuItem("Tri-Tip Meal", "Bread", "Tri-Tip"));
		menu.addMenuItem(new BBQMenuItem("Tri-Tip Salad", "Salad", "Tri-Tip"));
		menu.addMenuItem(new BBQMenuItem("Rib Sandwich", "Bread", "Ribs"));
		menu.addMenuItem(new BBQMenuItem("Pulled Pork Sandwich", "Bread", "Pulled Pork"));
		menu.addMenuItem(new BBQMenuItem("Bread", "Bread"));
		menu.addMenuItem(new BBQMenuItem("BBQ Chicken Sandwich", "Bread", "Pulled Chicken"));
		menu.addMenuItem(new BBQMenuItem("Bleu Pig Sandwich", "Bread", "Pulled Pork", "Bleu Cheese"));

		System.out.println("Uploading fresh menu...");
		new WebOut(menu).addListener(new WebOut.Listener() {

			@Override
			public void webOutEvent(WebOut w) {
				System.out.println(w.getStatus());
				Interface.main(new String[0]);
			}
		}).start();
		// WebOut.out(menu);
		ThreadedWriter.write(Const.MENU_FILE_NAME, menu.toJSON());
	}
}
