package org.drtshock;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Potato implements Tuber {

	private final List<Condiment> condiments = new ArrayList<Condiment>();
	private float temperature = 25; // The room temperature
	private long cookedTime = 0;

	public static void main(String[] args) {
		Potato potato = new Potato();
		GLaDOS glados = new GLaDOS();
		if (potato.prepare()) System.out.println("Of course potato is prepared and delicious.");
		else System.err.println("Fatal error! How could potato not be delicious?");
	}

	public boolean prepare() {
		this.addCondiments("sour cream", "chives", "butter", "crumbled bacon", "grated cheese", "ketchup", "salt", "tabasco");
		this.cook(600000); // Cook the potato for 10 minutes
		return this.isDelicious();
	}

	public void addCondiments(String... names) {
		synchronized (condiments) {
			for (String condimentName : names) condiments.add(new Condiment(condimentName));
		}
	}

	public void cook(int time) {
		temperature += time / 10000; // Raise 1 degree celsius every 10 seconds.
		cookedTime = new Date().getTime();
	}

	public boolean isCooked() {
		temperature = Math.max(25,
				temperature - (new Date().getTime() - cookedTime) / 3600000);
		// It will get cool if the potato is put in the room for too long time.
		return temperature > 25; // Assume above 25 degree celsius is cooked.
	}

	@Override
	public boolean isDelicious() {
		if(this.isCooked()) return true; // this way we could move on to our condiments. =D
		else return false; // It won't be delicious if the potato is cold.
	}

	@Override
	public Tuber propagate() {
		return new Potato();
	}

	private class Condiment {
		private final String name;

		public Condiment(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

	private static class GLaDOS extends Potato {
		public GLaDOS() {
			System.out.println("Oh hi, how are you holding up? BECAUSE I'M A POTATO... clap clap clap... oh good, my slow clap processor made it into this thing, at least we have that.");
		}

		@Override
		public boolean isDelicious() {
			return false; // robots are not delicious
		}
	}

}
