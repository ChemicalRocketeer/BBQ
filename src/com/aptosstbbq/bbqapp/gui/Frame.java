package com.aptosstbbq.bbqapp.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.aptosstbbq.bbqapp.menu.Ingredient;
import com.aptosstbbq.bbqapp.menu.BBQMenu;
import com.aptosstbbq.bbqapp.menu.BBQMenuItem;
import com.aptosstbbq.bbqapp.web.WebOut;

public class Frame extends JFrame implements ActionListener {
	Scanner in = new Scanner(System.in);
	BBQMenu bleh = new BBQMenu();
	
	public Frame() {
		final BBQMenu item = new BBQMenu();
		final int WIDTH = 1000;
		final int HEIGHT = 1000;
		//basic jframe
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Aptos St ADD BBQMenu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLayout(new GridLayout(2, 2));
		JButton upload = new JButton("UPLOAD MENU");
        //Add action listener to button
		//uploads the menu to the file in json
        upload.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
				WebOut.out(item);
            }
        }); 
		frame.add(upload);
		JButton ing = new JButton("ADD INGREDIENT");
		ing.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String set = in.next();
				Ingredient add = new Ingredient(set);
				bleh.addIngredient(add);
			}
		});
		frame.add(ing);
		JButton out = new JButton("SET SOLD OUT");
		ing.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SoldOutFrame sO = new SoldOutFrame();
				sO.setVisible();
			}
		});
		frame.add(out);
		JButton addMenuItem = new JButton("ADD MENU ITEM");
		ing.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String set = in.next();
				String price = in.next();
				BBQMenuItem add = new BBQMenuItem(set,price);
				bleh.addBBQMenuItem(add);
			}
		});
		frame.add(addMenuItem);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

