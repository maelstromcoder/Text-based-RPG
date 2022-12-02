package BattleGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BattleGame {

	// Private random
	private static Random rnd = new Random();
	
	// Seed generator
	static int seed = 123;
	
	// List of static objects
	static Scanner console = new Scanner(System.in);
	static Character p, m;
	static FileIO fPlayer = new FileIO();
	static FileIO fMonster = new FileIO();
	static FileIO fSpell = new FileIO();
	static Spell s1,s2,s3,s4;
	
	// List of static ArrayList
	static ArrayList<Spell> list = new ArrayList<Spell>();
	
	// List of static booleans
	static boolean odin = true, fenrir = true;
	static boolean nospell = false;
	
	// List of static Strings
	static String input;
	static String txtPlay = "player.txt", txtMonster = "monster.txt", txtSpells = "spells.txt";
	
	// List of static double
	static double playerDamage, monsterDamage, spelldmg=0;
	
	// Main Method start
	public static void main(String[] args) throws IOException {
		playGame(txtPlay, txtMonster, txtSpells, seed);
	}
	
	// Game start, step 1
	private static void playGame(String player, String monster, String spell, int seed) throws IOException {
		
		// Read Spell txt
		Spell(spell);
		// Read Player txt
		Player(player);		
		// Read Monster txt
		Monster(monster);
		
		// Check is spell file has problems, play game with or without spells
		if (nospell == false) {
			// Display spells, play game with spells
			System.out.println("Here are the available spells:");
			p.displaySpells();
		}
		else {	
			// Do not display spells, play game without spells
			System.out.println("Game will be played without spells.");
			
			// Commence game loop without spells
			gameLogic(seed);
		}
		
		// Commence game loop with spells
		gameLogic(seed);

	}
	
	// Read Spell Method
	private static void Spell(String spell) {
		try {
			fSpell.readCharacter(spell);

			// Add objects to ArrayList through class constructor Spell(name, mindmg, maxdmg, chance)
			// Static spell1 to spell4 created from FileIO readCharacter
			s1 = new Spell(fSpell.spell1[0], Double.parseDouble(fSpell.spell1[1]), Double.parseDouble(fSpell.spell1[2]), Double.parseDouble(fSpell.spell1[3]));
			list.add(s1);
			s2 = new Spell(fSpell.spell2[0], Double.parseDouble(fSpell.spell2[1]), Double.parseDouble(fSpell.spell2[2]), Double.parseDouble(fSpell.spell2[3]));
			list.add(s2);
			s3 = new Spell(fSpell.spell3[0], Double.parseDouble(fSpell.spell3[1]), Double.parseDouble(fSpell.spell3[2]), Double.parseDouble(fSpell.spell3[3]));
			list.add(s3);	
			s4 = new Spell(fSpell.spell4[0], Double.parseDouble(fSpell.spell4[1]), Double.parseDouble(fSpell.spell4[2]), Double.parseDouble(fSpell.spell4[3]));
			list.add(s4);
			
			// Use of Character method setSpells to create Spells in OOP
			Character.setSpells(list);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			nospell = true;
		}
		
	}
	
	// Read Player Method
	private static void Player(String player) {
		try {
			fPlayer.readCharacter(player);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Display Player info
		p = new Character(fPlayer.player[0], Double.parseDouble(fPlayer.player[1]), Double.parseDouble(fPlayer.player[2]), Integer.parseInt(fPlayer.player[3]));
		System.out.println("Name: " + p.getName() +
		"\nHealth: " + p.getMaxHealth() +
		"\nAttack: " + p.getAttackValue() +
		"\nNumber of Wins: " + p.getNumWins());	
		System.out.println();

	}
	
	// Read Monster Method
	private static void Monster(String monster) {
		try {
			fMonster.readCharacter(monster);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Display Monster info
		m = new Character(fMonster.monster[0], Double.parseDouble(fMonster.monster[1]), Double.parseDouble(fMonster.monster[2]), Integer.parseInt(fMonster.monster[3]));
		System.out.println("Name: " + m.getName() +
		"\nHealth: " + m.getMaxHealth() +
		"\nAttack: " + m.getAttackValue() +
		"\nNumber of Wins: " + m.getNumWins());	
		
		System.out.println();
	}
	
	// Main gameLogic to start the loop
	private static void gameLogic(int seed) throws IOException {
		
		input = gameBegin();
		spelldmg = 0;
		playerDamage = 0;
		monsterDamage = 0;
		
		// Option of "attack", "quit", "spell" or anything else is considered "not learned"
		Process(input, seed);
	}
	
	// gameBeing() introduces the game, multiple introduction possible at each restart
	private static String gameBegin() {
		// Skip a line and game begins
		System.out.println();
		System.out.println("Enter a command: ");
		input = console.nextLine();
		return input;
	}
	
	// Direction of Attack
	public static void Process(String input, int seed) throws IOException {
		if (input.contentEquals("attack")) {
			System.out.println();
			Attack(seed);
		}
		else if (input.contentEquals("quit")) {
			System.out.println("\nGoodbye!");
			end();
		}
		else {
			spelldmg = castSpell(input, seed);
			Attack(seed);
		}
	}
	
	// Casting of spell if spell is known
	private static double castSpell(String input, int value) {
		
		// Lowercase all inputs to centralize case switch
		String newInput = input.toLowerCase();
		
		switch (newInput) {
		case "fireball":
			spelldmg = p.castSpells(newInput, value);
			return spelldmg;
		case "icestorm":
			spelldmg = p.castSpells(newInput, value);
			return spelldmg;
		case "meteorstrike":
			spelldmg = p.castSpells(newInput, value);
			return spelldmg;
		case "surge of frostfire": 
			spelldmg = p.castSpells(newInput, value);
			return spelldmg;
		}
		// If anything but the spells at hand, return -1
		return -1;
	}
	
	// Player choice to end, system exit
	private static void end() {
		System.exit(0);
	}
	
	// Begin attack, check if spell was casted and if it succeeded, if true, change damage type to magic damage, if not, continue 
	private static void Attack(int seed) throws IOException {
		
		if (healthCheck() == true) {
		
			MagicDmg(spelldmg, seed);
			AttackMonster(seed);

		}
	}
	
	// Instantiation of magic damage, assign damage type to MAGIC and attack monster with magic damage
	private static void MagicDmg(double value, int seed) throws IOException {
		
		// If no spell damage, assign normal value based of attack value of player
		if (spelldmg == 0) {
			playerDamage = Double.parseDouble(p.getAttackDamage(seed));
		}
		// If spell damage = -1, the spell was unknown from the known list of spells in ArrayList
		else if (spelldmg == -1) {
			System.out.println();
			System.out.println(p.getName() + " tried to cast " + input +  ", but they don't know that spell.");
			AttackContinue(seed);
		}
		// If spell damage = -2, the spell cast chance failed above the cast chance of the spell
		else if (spelldmg == -2) {
			System.out.println();
			System.out.println(p.getName() + " tried to cast " + input +  ", but they failed.");
			spelldmg = -1;
			AttackContinue(seed);
		}
		// If spell damage anything above 0, spell damage was a success and found a spell in the list, change damage type to magic damage
		else if (spelldmg > 0) {
			playerDamage = spelldmg;
			
			// If spell damage is above the remaining health of monster, change health of monster to damage so damage does not exceed the health
			if (spelldmg > m.getCurrHealth()) {
				playerDamage = m.getCurrHealth();
			}
			String attackStr = String.format("%1$.2f", playerDamage);

			// Change current health of monster to after damage taken
			m = new Character(fMonster.monster[0], m.takeDamage(playerDamage), Double.parseDouble(fMonster.monster[2]), Integer.parseInt(fMonster.monster[3]));

			// Write out how much damage the player did
			System.out.println(p.getName() + " casts " + input +  " dealing " + attackStr + " damage!");
			// Continue sequence of attack to monster's turn
			AttackContinue(seed);
		}
	}
	
	// Attack monster with normal damage
	private static void AttackMonster(int seed) throws IOException {
		// If attack damage is above the remaining health of monster, change health of monster to damage so damage does not exceed the health
		if (playerDamage > m.getCurrHealth()) {
			playerDamage = m.getCurrHealth();
			String attackStr = String.format("%1$.2f", playerDamage);
			
			// Change current health of monster to after damage taken
			m = new Character(fMonster.monster[0], m.takeDamage(playerDamage), Double.parseDouble(fMonster.monster[2]), Integer.parseInt(fMonster.monster[3]));
			
			// Write out how much damage the player did and proceed to monster's turn
			System.out.println(p.getName() + " attacks for " + attackStr + " damage!");
			AttackContinue(seed);
		}
		
		// If attack damage below remaining health, normnal full damage attack
		else {
			// Change current health of monster to after damage taken
			m = new Character(fMonster.monster[0], m.takeDamage(playerDamage), Double.parseDouble(fMonster.monster[2]), Integer.parseInt(fMonster.monster[3]));
			
			// Write out how much damage the player did and proceed to monster's turn
			System.out.println(p.getName() + " attacks for " + playerDamage + " damage!");
			AttackContinue(seed);
			
		}
	}
	
	// Normal or magic attack from player to monster done, monster's turn
	private static void AttackContinue(int seed) throws IOException { 
		
		// Check if spell damage was true
		if (healthCheck() == true) {
			// Skips the player's turn if no spell exist
			if (spelldmg != -1) {
				System.out.println(m.toString());
			}
			AttackPlayer(seed);
		}
		winner();
		
	}

	// Attack player with normal damage
	private static void AttackPlayer(int seed) throws IOException {
		
		// Assign normal value based of attack value of monster
		System.out.println();
		monsterDamage = Double.parseDouble(m.getAttackDamage(seed));
		
		// If attack damage is above the remaining health of player, change health of player to damage so damage does not exceed the health
		if (monsterDamage > p.getCurrHealth()) {
			monsterDamage = p.getCurrHealth();
			String attackStr = String.format("%1$.2f", monsterDamage);
			
			p = new Character(fPlayer.player[0], p.takeDamage(monsterDamage), Double.parseDouble(fPlayer.player[2]), Integer.parseInt(fPlayer.player[3]));

			// Write out how much damage the monster did and proceed to see if player is dead and need a winner
			System.out.println(m.getName() + " attacks for " + attackStr + " damage!");
			
			// Check if player is dead, if not- RESET, if yes, go to WINNER
			if (healthCheck() == true) {
				System.out.println(p.toString());
				gameLogic(seed);
			}
			winner();
		}
		// Else if attack damage below remaining health, normnal full damage attack
		else {
			p = new Character(fPlayer.player[0], p.takeDamage(monsterDamage), Double.parseDouble(fPlayer.player[2]), Integer.parseInt(fPlayer.player[3]));
	
			// Write out how much damage the monster did and proceed to see if player is dead and need a winner
			System.out.println(m.getName() + " attacks for " + monsterDamage + " damage!");
			
			// Check if player is dead, if not- RESET, if yes, go to WINNER
			if (healthCheck() == true) {
				System.out.println(p.toString());
				gameLogic(seed);
			}
			winner();
		}
	}
	
	// Verifies if current health of player or monster is at 0
	private static boolean healthCheck() {
		// If Player current health is smaller or equal to 0, confirm player Odin dead with boolean false;
		if (p.getCurrHealth() <= 0) {
			odin = false;
			return false;
		}
		// If Monster current health is smaller or equal to 0, confirm monster Fenrir dead with boolean false;
		else if (m.getCurrHealth() <= 0) {
			fenrir = false;
			return false;
		}
		return true;
	}
	
	// If health is at 0, try to see who won based off booleans
	private static void winner() throws IOException {
		
		// If Odin is false, Odin died
		if (odin == false) {
			// Update player with 0 HP
			p = new Character(fPlayer.player[0], 0, Double.parseDouble(fPlayer.player[2]), Integer.parseInt(fPlayer.player[3]));
			
			System.out.println("Odin was knocked out!");
			System.out.println();
			System.out.println("Oh no! You lost!");
			System.out.println("Successfully wrote to file: " + txtMonster);
			m.increaseWins();
			System.out.println("Fenrir has won: " + m.getNumWins() + " times");
			
			// Increase win count and write in file
			int count = m.getNumWins();
			fMonster.writeCharacter(txtMonster, count);
			
			// System end, loop possible here.
			end();
		}
		
		// If Fenrir is false, Fenrir died
		else if (fenrir == false) {
			// Update monster with 0 HP
			m = new Character(fMonster.monster[0], 0, Double.parseDouble(fMonster.monster[2]), Integer.parseInt(fMonster.monster[3]));
			
			System.out.println("Fenrir was knocked out!");
			System.out.println();
			System.out.println("Fantastic! You killed the monster!");
			System.out.println("Successfully wrote to file: " + txtPlay);
			p.increaseWins();
			System.out.println("Odin has won: " + p.getNumWins() + " times");
			
			// Increase win count and write in file
			int count = p.getNumWins();
			fPlayer.writeCharacter(txtPlay, count);
			
			// System end, loop possible here.
			end();
		}
	}
}
