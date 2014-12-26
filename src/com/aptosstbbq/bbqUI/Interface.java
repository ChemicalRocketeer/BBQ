package com.aptosstbbq.bbqUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import NotifyUser.Event;

import com.aptosstbbq.bbqapp.Utils;
import com.aptosstbbq.bbqapp.menu.Menu;
import com.aptosstbbq.bbqapp.web.WebIn;
import com.aptosstbbq.bbqapp.web.WebOut;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JToolBar;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;

public class Interface extends JFrame {

	private JPanel contentPane;
	static Menu bleh = new Menu();
	Scanner in = new Scanner(System.in);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		bleh = Menu.fromJSON(new WebIn().read());
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
		setBackground(new Color(204, 153, 51));
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
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblAposStUser = new JLabel("Aptos St Bbq User InterFace");
		lblAposStUser.setBackground(new Color(153, 0, 153));
		lblAposStUser.setForeground(new Color(0, 0, 0));
		lblAposStUser.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		contentPane.add(lblAposStUser, "2, 2");
		
		JButton btnNewButton = new JButton("Set Sold Out");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SOFRAME sO = new SOFRAME();
				sO.setVisible(true);
			}
		});
		contentPane.add(btnNewButton, "2, 6");
		
		JButton btnNewButton_1 = new JButton("Upload Menu");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		btnNewButton_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bleh.saveMenu();
			}
		});
		contentPane.add(btnNewButton_1, "2, 8");
		final JTextArea notification = new JTextArea();
		notification.setText("Write here");
		contentPane.add(notification, "2, 18, fill, fill");
		
		JButton btnNewButton_2 = new JButton("Add Push Notification");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		btnNewButton_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new WebOut(notification.getText());
				notification.setText("Write here");
			}
		});
		contentPane.add(btnNewButton_2, "2, 10");
		
		JButton btnNewButton_3 = new JButton("bleh");
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		
		contentPane.add(btnNewButton_3, "2, 12");
		
		JButton btnNewButton_4 = new JButton("Add Bbq Event");
		btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		btnNewButton_4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s = "Last modified on " + Utils.time() + "/n" +
						notification.getText();
				new WebOut(s);
				final JFrame pop = new JFrame();
				pop.setLayout(new GridLayout(2,1));
				JTextField notify = new JTextField();
				notify.setText("Your event has been uploaded");
				notify.setFont(new Font("Times New Roman", Font.BOLD,29));
				JButton ok = new JButton("Ok");
				ok.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						pop.dispose();
					}
				});
				pop.add(notify);
				pop.add(ok);
				pop.pack();
				pop.setVisible(true);
				notification.setText("Write here");
			}
		});
		
		contentPane.add(btnNewButton_4, "2, 14");
		
		JLabel lblEnterNotificationBelow = new JLabel("Enter notification below");
		lblEnterNotificationBelow.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		
		contentPane.add(lblEnterNotificationBelow, "2, 16");
	}

}