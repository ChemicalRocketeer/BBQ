package com.aptosstbbq.bbqapp.menu;

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
	
	/** A status that an Ingredient can have. The lower the number, the better the availability. */
	public static final int AVAILABLE = 0, RUNNING_LOW = 1, SOLD_OUT = 2;

	/**
	 * An array of string names for statuses. Used to get standardized string names without verbose, error-prone if statements.
	 * To use, just access the array using the status as a parameter. For example: STATUS_STRINGS[AVAILABLE] will give you "avaiable"
	 */
	public static final String[] STATUS_STRINGS = { "available", "running low", "sold out" };

	/**
	 * An array of color hex values for statuses. Used to get standardized colors without verbose, error-prone if statements.
	 * To use, just access the array using the status as a parameter. For example: STATUS_COLORS[AVAILABLE] will give you the hex of Color.GREEN
	 * The colors are hex integers instead of using a color library so that they can be cross-platform.
	 */
	public static final int[] STATUS_COLORS = { 0xFF00FF00, 0xFFFFFF00, 0xFFFF0000 };

	private transient List<Listener> listeners = new LinkedList<>();

	private String name = "";
	private int defaultStatus = 0;
	protected int status = 0; // protected so that BBQMenu can edit without triggering logging
	
	public Ingredient(String name) {
		this(name, AVAILABLE);
	}
	
	public Ingredient(String name, int status) {
		this.name = name;
		setStatus(status);
	}

	public Ingredient(String name, int status, int defaultStatus) {
		this.name = name;
		setStatus(status);
		setDefaultStatus(defaultStatus);
	}

	public Ingredient addListener(Listener l) {
		listeners.add(l);
		return this;
	}
	
	protected Ingredient setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	/**
	 * Returns one of the constants AVAILABLE, RUNNING_LOW, or SOLD_OUT.
	 */
	public int getStatus() {
		return status;
	}
	
	public String getStatusString() {
		return STATUS_STRINGS[status];
	}

	public int getStatusColor() {
		return STATUS_COLORS[status];
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
