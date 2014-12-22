package com.aptosstbbq.bbqUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JLabel;

import com.aptosstbbq.bbqapp.Menu;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

public class Interface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	static Menu bleh = new Menu();
	Scanner in = new Scanner(System.in);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		bleh = Menu.getMenuFromFile();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
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
	public Interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblAposStUser = new JLabel("APTOS STREET BARBEQUE USER INTERFACE");
		contentPane.add(lblAposStUser, "2, 2");
		
		JButton btnNewButton = new JButton("SET SOLD OUT");
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SOFRAME sO = new SOFRAME();
				sO.setVisible(true);
			}
		});
		contentPane.add(btnNewButton, "2, 6");
		
		JButton btnNewButton_1 = new JButton("UPLOAD MENU");
		btnNewButton_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bleh.saveMenu();
			}
		});
		contentPane.add(btnNewButton_1, "2, 8");
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		contentPane.add(btnNewButton_2, "2, 10");
		
		JButton btnNewButton_3 = new JButton("New button");
		contentPane.add(btnNewButton_3, "2, 12");
		
		JButton btnNewButton_4 = new JButton("New button");
		contentPane.add(btnNewButton_4, "2, 14");
		
		textField = new JTextField();
		contentPane.add(textField, "2, 20, fill, default");
		textField.setColumns(10);
	
	}

}
