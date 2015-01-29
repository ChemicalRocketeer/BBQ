package com.aptosstbbq.bbqapp.menu;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import com.aptosstbbq.bbqapp.util.Logger;

public class Ingredient {

	public interface Listener {
		public void IngredientEvent(Ingredient ing, Event event);
	}

	public enum Event {
		SELLOUT;
	}

	private static long nextID = 0;
	public final long id = nextID++;
	
	/** A status that an Ingredient can have. The lower the number, the better the availability. */
	public static final int AVAILABLE = 0, RUNNING_LOW = 1, SOLD_OUT = 2;

	/**
	 * An array of string names for statuses. Used to get standardized string names without verbose, error-prone if statements.
	 * To use, just access the array using the status as a parameter. For example: STATUS_STRINGS[AVAILABLE] will give you "avaiable"
	 */
	public static final String[] STATUS_STRINGS = { "available", "running low", "sold out" };

	/**
	 * An array of colors for statuses. Used to get standardized colors without verbose, error-prone if statements.
	 * To use, just access the array using the status as a parameter. For example: STATUS_COLORS[AVAILABLE] will give you Color.GREEN
	 */
	public static final Color[] STATUS_COLORS = { Color.GREEN, Color.YELLOW, Color.RED };

	private List<Listener> listeners = new LinkedList<>();

	private String name = "Unnamed Ingredient";
	private int defaultStatus = 0;
	protected int status = 0; // protected so that BBQMenu can edit without triggering logging
	
	public Ingredient(String name) {
		this(name, AVAILABLE);
	}
	
	public Ingredient(String name, int status) {
		setName(name);
		setStatus(status);
	}

	public Ingredient(String name, int status, int defaultStatus) {
		setName(name);
		setStatus(status);
		setDefaultStatus(defaultStatus);
	}

	public Ingredient addListener(Listener l) {
		listeners.add(l);
		return this;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns one of the constants AVAILABLE, RUNNING_LOW, or SOLD_OUT.
	 */
	public int getStatus() {
		return status;
	}
	
	/** Cycles through the availability states, from AVAILABLE to RUNNING_LOW to SOLD_OUT and then back to AVAILABLE. Sends a message to Logger.SELL_OUT */
	public void toggleStatus() {
		status = (status + 1) % 3;
		String message = name + '\t';
		message += STATUS_STRINGS[status];
		Logger.SELL_OUT.log(message);
	}
	
	/** Sets the availability of this Ingredient. If you are toggling, use toggleAvailability() instead. */
	public void setStatus(int av) {
		status = av % 3;
		String message = name + '\t';
		message += STATUS_STRINGS[status];
		Logger.SELL_OUT.log(message);
	}

	public int getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(int defaultStatus) {
		this.defaultStatus = defaultStatus;
	}
	
	public boolean isSoldOut() {
		return status == SOLD_OUT;
	}

	public boolean equals(Ingredient other) {
		return name.equals(other.name);
	}
}
