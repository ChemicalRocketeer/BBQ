package com.aptosstbbq.bbqapp.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.aptosstbbq.bbqapp.menu.Ingredient;

public class SQLOut extends Thread {

	private String server = "www.digitalrocketry.com";
	private String username = "u857457562_larry";
	private String password = "LarryIsRad";
	private String cmd;

	private SQLOut(String cmd) {
		this.cmd = cmd;
	}

	public boolean addIngredient(Ingredient ing) {
		return false;
	}

	public boolean updateIngredient(Ingredient ing) {
		new SQLOut("");
		return false;
	}

	public boolean removeIngredient(Ingredient ing) {
		return false;
	}

	public void run() {
		Connection conn = null;
		Statement statement = null;
		try {
			// new Driver();

			// connect to database
			conn = DriverManager.getConnection(server, username, password);
			// create statement, statements allow to issue SQL queries to the database
			statement = conn.createStatement();
			// get the results
			ResultSet rs = statement.executeQuery(cmd);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
