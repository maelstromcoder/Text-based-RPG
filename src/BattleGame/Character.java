package BattleGame;

import java.util.ArrayList;
import java.util.Random;

public class Character {

	private String name;
	private double attackValue;
	private double MaxHealth;
	private double Health;
	private int winCount;
	private static ArrayList<Spell> spells;
	
//	A constructor: The constructor for the Character class takes one String, two doubles, and
//	one int as input. These parameters represent the name, attack value, maximum health, and
//	number of wins in the battle game for the character, in that order. Note that the current health
//	of a new character is the same as the maximum health.
	
	public Character(String username, double maxHP, double attack, int win) {
		// Assign character info
		name = username;
		MaxHealth = maxHP;
		Health = maxHP;
		attackValue = attack;
		winCount = win;
		
	}

	// Set spells from BattleGame into spells for instantiated ArrayList 
	public static void setSpells(ArrayList list) {
		Character.spells = list;
	}
	
	// Display spells by print 
	// Can also use Character.spells.get(i).toString();
	public void displaySpells() {
		for (int i = 0; i < 4; i++) {
			System.out.println("Name: " + Character.spells.get(i).getName() +
					" Damage: " + Character.spells.get(i).getMinDmg() + "-" + Character.spells.get(i).getMaxDmg() +
					" Chance: " + ((Character.spells.get(i).getChance()) * 100) + "%");	
		}
	}
	
	// Cast spells and return double damage to caller
	public double castSpells(String spell, int seed) {

		for (int i = 0; i < Character.spells.size(); i++) {
			if (Character.spells.get(i).getName().contains(spell)) {
				return Character.spells.get(i).getMagicDamage(seed);
			}
		}
		return -1;
	}
	
	// Call character current health formatted to 2 decimals
	public String toString() { 
		return name + " current health is " + String.format("%1$.2f", getCurrHealth());
	}
	
	// Call character name
	public String getName() {
		return name;
	}

	// Call character attack value
	public double getAttackValue() {
		return attackValue;		
	}
	
	// Call character max health
	public double getMaxHealth() {
		return MaxHealth;		
	}
	
	// Call character current health
	public double getCurrHealth() {
		return Health;
	}
	
	// Call character current number of win
	public int getNumWins() {
		return winCount;		
	}
	
	// Call character attack damage when using Attack
	public String getAttackDamage(int seed) {
		Random rnd = new Random();
		double Dmg = (attackValue * (rnd.nextDouble(1.0 - 0.7) + 0.7));
		String attackStr = String.format("%1$.2f", Dmg);
		return attackStr;
	}
	
	// Call character health after taking damage
	public double takeDamage(double dmg) {
		return Health - dmg; 
	}
	
	// Call character win and increase by 1
	public void increaseWins() {
		winCount++;
	}
	
	
}
