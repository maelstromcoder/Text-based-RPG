package BattleGame;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class FileIO {
	
	// Arrays to stop values static
	static String[] player = new String[4];
	static String[] monster = new String[4];
	static String[] spell1 = new String[4];
	static String[] spell2 = new String[4];
	static String[] spell3 = new String[4];
	static String[] spell4 = new String[4];
	
	// ArrayList to store Object
	static ArrayList<Object> spells = new ArrayList<Object>();
	
	// Variable to save current win count
	static int playerCounter;
	static int monsterCounter;
	
	// Read file for player or monster
	public void readCharacter(String parameter) throws IOException {
		FileReader(parameter);
	}
	
	// Read file for spells
	public void readSpells(String parameter) throws IOException {
		FileReader(parameter);
	}
	
	// Write win counts
	public void writeCharacter(String parameter, int win) throws IOException {
		FileWriter(parameter, win);
	}
	
	// Writing the win counts in file
	public static void FileWriter(String filename, int win) throws IOException {
		// Initialize the BufferedWriter to write in file with FileWriter
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		
		// Write same info but with increment of win transfered through method call
		if (filename.equalsIgnoreCase("player.txt")) {
			if (playerCounter < win) {
				bw.write(player[0]);
				bw.newLine();
				bw.write(player[1]);
				bw.newLine();
				bw.write(player[2]);
				bw.newLine();
				bw.write(String.valueOf(win));
				bw.close();
			}
			// If there is no player, no game start, system exit
			else {
				System.exit(0);
			}
		}
		
		// Write same info but with increment of win transfered through method call
		else if (filename.equalsIgnoreCase("monster.txt")) {
			if (monsterCounter < win) {
				bw.write(monster[0]);
				bw.newLine();
				bw.write(monster[1]);
				bw.newLine();
				bw.write(monster[2]);
				bw.newLine();
				bw.write(String.valueOf(win));
				bw.close();
			}
			// If there is no player, no game start, system exit
			else {
				System.exit(0);
			}
		}
	}
	
	// Reading of file, storing of object info and storing current win count
	public static void FileReader(String filename) throws IOException {
		try {
			// Initialize the BufferedReader to read files with FileReader
			BufferedReader br = new BufferedReader(new FileReader(filename));

			// Read player and assign values to player
			if (filename.equalsIgnoreCase("player.txt")) {
				int i = 0;
				// Run BufferedReader as long as it is ready to read
				while (br.ready()) {
					player[i] = br.readLine();
					// Save win count at read
					if (i == 4) {
						playerCounter = Integer.parseInt(br.readLine());
					}
					i++;

				}
				br.close();
			}
			// Read monster and assign values to monster
			else if (filename.equalsIgnoreCase("monster.txt")) {
				int i = 0;
				// Run BufferedReader as long as it is ready to read
				while (br.ready()) {
					monster[i] = br.readLine();
					// Save win count at read
					if (i == 4) {
						monsterCounter = Integer.parseInt(br.readLine());
					}
					i++;

				}
				br.close();
			}
			// Read spell and assign values to spell1 to spell4 arrays
			else if (filename.contentEquals("spells.txt")) {
					int i = 0;
					// Run BufferedReader as long as it is ready to read
					while (br.ready()) {
						String first = br.readLine();
						String[] second = first.split("\t");
						for (int j = 0; j < second.length; j++) {
							switch(i) {
							case 0: spell1[j] = second[j]; 
							case 1: spell2[j] = second[j];
							case 2: spell3[j] = second[j];
							case 3: spell4[j] = second[j];
						}		
					}
					i++;
				}
				br.close();		
			}
			
			else {
				throw new FileNotFoundException();
			}
		}
		
		// First catch to say there are no spells
		catch (FileNotFoundException e) {
			if (!filename.contentEquals("spells.txt"))
			{			
				System.out.println();
			}
			// Error sayings no files are found
			else {
			    System.err.println("Caught FileNotFoundException: " + e.getMessage());
			    System.exit(0);
			}
		}
		catch (IOException e) {
		    System.err.println("Caught IOException: " + e.getMessage());
		    System.exit(0);
		}
	}
	
	

}
