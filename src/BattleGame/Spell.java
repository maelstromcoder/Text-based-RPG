package BattleGame;

import java.util.Random;

public class Spell {

	private String name;
	private double minDmg;
	private double maxDmg;
	private double chance;
	
	// Constructor for spell 
	public Spell(String user, double min, double max, double success) throws Exception {
		try { 
			// Assign name to spell
			name = user;
			
			// If damage is less than 0 or min damage is higher than max dmg, fail and throw IllegalArgumentException
			if (min < 0 || min > max) {
				throw new IllegalArgumentException();
			}
			else {
				// Assign min and max dmg to spell
				minDmg = min;
				maxDmg = max;
			}
			// If success rate is less than 0 or higher than 1 (100%+), fail and throw IllegalArgumentException
			if (success < 0 || success > 1) {
				throw new IllegalArgumentException();
			}
			else {
				// Assign success rate to spell
				chance = success;
			}
		}
		catch (IllegalArgumentException e) {
			throw new Exception("");
		}
	}
	
	// ToString used for debugging or calling spells
	public String toString() {
		return "Name: " + name + " Damage: " + minDmg + "-" + maxDmg + " Chance: " + (chance * 100) + "%";
	}
	
	// Get spell name
	public String getName() {
		return name;
	}
	
	// Get spell minimum damage
	public double getMinDmg() {
		return minDmg;
	}
	
	// Get spell maximum damage
	public double getMaxDmg() {
		return maxDmg;
	}
	
	// Get random damage between minimum and maximum
	public double getMagicDamage(int seed) {
		// Create random number
		Random rnd = new Random();
		double rando = Math.random();
		double magicDmg=0;

		// If random number is higher than chance, fail, if not, succeed
		// Fail
		if (rando > chance) {
			return -2;
		}
		// Succeed
		else if (rando < chance) { 
			magicDmg = (rnd.nextDouble(maxDmg - minDmg + 1) + minDmg);

			return magicDmg;
		}
		// Return value
		return magicDmg;
	}
	
	// Get spell chance rate
	public double getChance() {
		return chance;
	}
	
}
