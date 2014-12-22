package com.aptosstbbq.bbqUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.aptosstbbq.bbqapp.Menu;
import com.aptosstbbq.bbqapp.MenuItem;

public class SoldOutFrame {
	Menu bleh = new Menu();
	JFrame frame;
	public SoldOutFrame() {
		final Menu item = new Menu();
		final int WIDTH = 1000;
		final int HEIGHT = 1000;
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("Set Sold Out");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLayout(new GridLayout(2, 4));
		JButton b1 = new JButton();
		b1.setBackground(Color.green);
		b1.setContentAreaFilled(false);
		b1.setOpaque(true);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		frame.add(b1);
		JButton b2 = new JButton();
		b2.setBackground(Color.green);
		b2.setContentAreaFilled(false);
		b2.setOpaque(true);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		frame.add(b2);
		JButton b3 = new JButton();
		b3.setBackground(Color.green);
		b3.setContentAreaFilled(false);
		b3.setOpaque(true);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		frame.add(b3);
		JButton b4 = new JButton();
		b4.setBackground(Color.green);
		b4.setContentAreaFilled(false);
		b4.setOpaque(true);
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		frame.add(b4);
		JButton b5 = new JButton();
		b5.setBackground(Color.green);
		b5.setContentAreaFilled(false);
		b5.setOpaque(true);
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		frame.add(b5);
		JButton b6 = new JButton();
		b6.setBackground(Color.green);
		b6.setContentAreaFilled(false);
		b6.setOpaque(true);
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		frame.add(b6);
		JButton b7 = new JButton();
		b7.setBackground(Color.green);
		b7.setContentAreaFilled(false);
		b7.setOpaque(true);
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		frame.add(b7);
		frame.setVisible(false);
	}

	private void setColor(JButton b) {
		
	}

	public void setVisible() {
		frame.setVisible(true);
		
	}
}
