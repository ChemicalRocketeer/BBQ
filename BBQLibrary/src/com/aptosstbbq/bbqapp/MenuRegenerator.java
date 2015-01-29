package com.aptosstbbq.bbqapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		menu.addMenuItem(new BBQMenuItem("Tri-Tip Sandwich", "14.99", "Bread", "Tri-Tip").addInterchangableIngredient(new InterchangableIngredient("Tri-Tip", "Cold Beef")));
		menu.addMenuItem(new BBQMenuItem("Tri-Tip Meal", "15.99", "Bread", "Tri-Tip"));
		menu.addMenuItem(new BBQMenuItem("Tri-Tip Salad", "14.99", "Salad", "Tri-Tip"));
		menu.addMenuItem(new BBQMenuItem("Rib Sandwich", "14.99", "Bread", "Ribs"));
		menu.addMenuItem(new BBQMenuItem("Pulled Pork Sandwich", "14.99", "Bread", "Pulled Pork"));
		menu.addMenuItem(new BBQMenuItem("Bread", "14.99", "Bread"));
		menu.addMenuItem(new BBQMenuItem("BBQ Chicken Sandwich", "14.99", "Bread", "Pulled Chicken"));
		menu.addMenuItem(new BBQMenuItem("Bleu Pig Sandwich", "14.99", "Bread", "Pulled Pork", "Bleu Cheese"));

		new WebOut(menu).addListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				System.out.println(((WebOut) ae.getSource()).getStatus());
			}
		}).start();
		// WebOut.out(menu);
		ThreadedWriter.write(Const.MENU_FILE_NAME, menu.toJSON());
	}
}
