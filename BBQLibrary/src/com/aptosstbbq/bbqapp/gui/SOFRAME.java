package com.aptosstbbq.bbqapp.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aptosstbbq.bbqapp.menu.BBQMenu;
import com.aptosstbbq.bbqapp.menu.Ingredient;
import com.aptosstbbq.bbqapp.web.WebIn;
import com.aptosstbbq.bbqapp.web.WebOut;

public class SOFRAME extends JFrame {

	private JPanel contentPane;
	static BBQMenu bleh = new BBQMenu();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SOFRAME frame = new SOFRAME();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SOFRAME() {
		WebIn win = new WebIn();
		win.run();
		bleh = BBQMenu.fromJSON(win.getResult());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		Ingredient[] ings = new Ingredient[bleh.getIngredients().size()];
		bleh.getIngredients().toArray(ings);
		JButton[] buttons = new JButton[bleh.getIngredients().size()];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(ings[i].getName());
			buttons[i].setBackground(ings[i].isSoldOut() ? Color.RED : Color.GREEN);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setOpaque(true);
			buttons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setSO((JButton) arg0.getSource());
					System.out.println(bleh.toString());
				}
			});
			contentPane.add(buttons[i]);
		}
	}
	private void setSO(JButton b) {
		String check = b.getText();
		Ingredient ing = bleh.getIngredient(check);
		ing.toggleStatus();
		b.setBackground(Ingredient.STATUS_COLORS[ing.getStatus()]);
		b.setContentAreaFilled(false);
		b.setOpaque(true);
		b.setContentAreaFilled(false);
		b.setOpaque(true);
		WebOut.out(bleh);
	}
}
