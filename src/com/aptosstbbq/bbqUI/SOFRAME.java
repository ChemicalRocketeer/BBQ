package com.aptosstbbq.bbqUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JButton;

import com.aptosstbbq.bbqapp.Menu;

import java.awt.Color;

public class SOFRAME extends JFrame {

	private JPanel contentPane;
	Menu bleh = new Menu();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		JButton[] buttons = new JButton[10];
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new JButton(buttons[i].getName());
			buttons[i].setBackground(Color.GREEN);
			buttons[i].setContentAreaFilled(false);
			buttons[i].setOpaque(true);
			contentPane.add(buttons[i]);
		}
		//JButton btnNewButton = new JButton("New button");
		//btnNewButton.setBackground(Color.GREEN);
		//btnNewButton.setContentAreaFilled(false);
		//btnNewButton.setOpaque(true);
		
		//contentPane.add(btnNewButton);
		
	//	JButton btnNewButton_1 = new JButton("New button");
		//btnNewButton_1.setBackground(Color.GREEN);
	//	btnNewButton_1.setContentAreaFilled(false);
	//	btnNewButton_1.setOpaque(true);
	//	contentPane.add(btnNewButton_1);
	}
	private void setSO(JButton b){
		String check = b.getText();
		bleh.getIngredient(check).setSoldOut(!bleh.getIngredient(check).isSoldOut());
		if(bleh.getIngredient(check).isSoldOut()){
			b.setBackground(Color.RED);
			b.setContentAreaFilled(false);
			b.setOpaque(true);
		}
		else{
			b.setBackground(Color.GREEN);
			b.setContentAreaFilled(false);
			b.setOpaque(true);
		}
	}
}
