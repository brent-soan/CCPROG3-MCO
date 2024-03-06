// Import necessary libraries for ArrayList, random numbers, and user input.
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The App class encapsulates the main functionality of the game.
 */
public class App {

		/**
         * The run method is the main logic of the game.
         */
		public void run() {
				// Initialize scanner for user input, utility class, and random number generator.
				Scanner CScanner = new Scanner(System.in);
				Utils CUtils = new Utils();
				Random CRandom = new Random();

				// Initialize variables for user input, termination condition, and creature lists.
				String strInput = ""; 
				int nInput = 0;
				boolean bTerminate = false; 
				ArrayList<Creature> aEL1Creatures = new ArrayList<Creature>(); 
				ArrayList<Creature> aStarterCreatures = new ArrayList<Creature>(); 
				ArrayList<String> aIntro = new ArrayList<String>();

				// Initialize starter creatures and add them to appropriate lists.
				Creature CStrawander = new Creature("Strawander", "Fire", 'A', 1); 
				Creature CChocowool = new Creature("Chocowool", "Fire", 'B', 1); 
				Creature CParfwit = new Creature("Parfwit", "Fire", 'C', 1); 
				Creature CBrownisaur = new Creature("Brownisaur", "Grass", 'D', 1); 
				Creature CFrubat = new Creature("Frubat", "Grass", 'E', 1); 
				Creature CMalts = new Creature("Malts", "Grass", 'F', 1); 
				Creature CSquirpie = new Creature("Squirpie", "Water", 'G', 1); 
				Creature CChocolite = new Creature("Chocolite", "Water", 'H', 1); 
				Creature COshacone = new Creature("Oshacone", "Water", 'I', 1); 

				// Add objects to ArrayLists
				aEL1Creatures.add(CStrawander);
				aEL1Creatures.add(CChocowool);
				aEL1Creatures.add(CParfwit);
				aEL1Creatures.add(CBrownisaur);
				aEL1Creatures.add(CFrubat);
				aEL1Creatures.add(CMalts);
				aEL1Creatures.add(CSquirpie);
				aEL1Creatures.add(CChocolite);
				aEL1Creatures.add(COshacone);
				aStarterCreatures.add(CStrawander);
				aStarterCreatures.add(CBrownisaur);
				aStarterCreatures.add(CSquirpie);

				// Introduction messages for the game.
				aIntro.add("Pick a starter creature from the following:");
				aIntro.add("1. Strawander");
				aIntro.add("2. Brownisaur");
				aIntro.add("3. Squirpie");

				// Main game loop.
				do {
						// Display intro
						System.out.println("$$$$$$$\\   $$$$$$\\  $$\\   $$\\ $$$$$$\\ $$\\      $$\\  $$$$$$\\  $$\\   $$\\        $$$$$$\\  $$\\   $$\\ $$$$$$$$\\ ");
						System.out.println("$$  __$$\\ $$  __$$\\ $$ | $$  |\\_$$  _|$$$\\    $$$ |$$  __$$\\ $$$\\  $$ |      $$  __$$\\ $$$\\  $$ |$$  _____|");
						System.out.println("$$ |  $$ |$$ /  $$ |$$ |$$  /   $$ |  $$$$\\  $$$$ |$$ /  $$ |$$$$\\ $$ |      $$ /  $$ |$$$$\\ $$ |$$ |      ");
						System.out.println("$$$$$$$  |$$ |  $$ |$$$$$  /    $$ |  $$\\$$\\$$ $$ |$$ |  $$ |$$ $$\\$$ |      $$ |  $$ |$$ $$\\$$ |$$$$$\\    ");
						System.out.println("$$  ____/ $$ |  $$ |$$  $$<     $$ |  $$ \\$$$  $$ |$$ |  $$ |$$ \\$$$$ |      $$ |  $$ |$$ \\$$$$ |$$  __|   ");
						System.out.println("$$ |      $$ |  $$ |$$ |\\$$\\    $$ |  $$ |\\$  /$$ |$$ |  $$ |$$ |\\$$$ |      $$ |  $$ |$$ |\\$$$ |$$ |      ");
						System.out.println("$$ |       $$$$$$  |$$ | \\$$\\ $$$$$$\\ $$ | \\_/ $$ | $$$$$$  |$$ | \\$$ |       $$$$$$  |$$ | \\$$ |$$$$$$$$\\ ");
						System.out.println("\\__|       \\______/ \\__|  \\__|\\______|\\__|     \\__| \\______/ \\__|  \\__|       \\______/ \\__|  \\__|\\________|");
						System.out.println();
						CUtils.header(" START"); 
						CUtils.body(aIntro, "FREE");
						System.out.println();
						System.out.print("[INPUT] : ");
						strInput = CScanner.nextLine(); // Accept user input

						// Input validation: check if the input is numeric.
						if(!(CUtils.isNumeric(strInput))) {
								System.out.print("\033c");
								CUtils.header(" ERROR! Enter the number of your chosen creature.");
								System.out.println();
								System.out.println();

								continue;
						}

						// Parse the input as an integer.
						nInput = Integer.parseInt(strInput);

						// Input validation: check if the input is a valid creature number.
						if (!(nInput == 1 || nInput == 2 || nInput == 3)) { 
								System.out.print("\033c");
								CUtils.header(" ERROR! Enter the number of your chosen creature.");
								System.out.println();
								System.out.println();
						}
				} while (!(nInput == 1 || nInput == 2 || nInput == 3)); // Loop until correct name is entered

				// Adjust input index to match ArrayList indexing.
				nInput -= 1;
				// Create a starter creature object based on user's choice.
				Creature CStarter = new Creature(aStarterCreatures.get(nInput));
				// Create inventory and area objects.
				Inventory CInventory = new Inventory(CStarter); 
				Area CArea = new Area(1, 5, 1, CInventory, CRandom, aEL1Creatures, CUtils, CScanner); 
				int nPosition = 0;

				System.out.print("\033c");
				CUtils.instructions(); // Show instructions

				// Main game loop for movement and other actions.
				while (bTerminate == false) { 
						CArea.display("X"); // Show area

						// User input for movement or other actions.
						do {
								System.out.print("[INPUT] : ");
								strInput = CScanner.nextLine();
								System.out.print("\033c");

								// Check user input for various actions.
								if (strInput.equals("m") || strInput.equals("M")) { 
										// Show menu and update termination condition.
										bTerminate = CUtils.menu(CScanner);
								} 
								else if (strInput.equals("i") || strInput.equals("I")) { 
										// Show instructions.
										CUtils.instructions();
								} 
								else if (strInput.equals("w") || strInput.equals("W") || strInput.equals("a") || strInput.equals("A")
										|| strInput.equals("s") || strInput.equals("S") || strInput.equals("d") || strInput.equals("D")) { 
										// Movement actions.
										if (strInput.equals("w") || strInput.equals("W")) { 
												nPosition = CArea.getRowPos() - 1; 

												// Check if movement is within bounds.
												if (nPosition >= 0) { 
														CArea.setRowPos(nPosition); 
														CArea.encounter(); 
												} 
												else { 
														System.out.print("\033c");
														CUtils.header(" ERROR! Out of bounds.");
														System.out.println();
														System.out.println();
												}
										} 
										// Similar checks for other movement directions.
										else if (strInput.equals("s") || strInput.equals("S")) {
												nPosition = CArea.getRowPos() + 1; 

												if (nPosition < CArea.getRows()) { 
														CArea.setRowPos(nPosition); 
														CArea.encounter(); 
												} 
												else {
														System.out.print("\033c");
														CUtils.header(" ERROR! Out of bounds.");
														System.out.println();
														System.out.println();
												}
										} 
										else if (strInput.equals("a") || strInput.equals("A")) {
												nPosition = CArea.getColPos() - 1; 

												if (nPosition >= 0) { 
														CArea.setColPos(nPosition); 
														CArea.encounter(); 
												} 
												else { 
														System.out.print("\033c");
														CUtils.header(" ERROR! Out of bounds.");
														System.out.println();
														System.out.println();
												}
										} 
										else if (strInput.equals("d") || strInput.equals("D")) {
												nPosition = CArea.getColPos() + 1; 

												if (nPosition < CArea.getColumns()) { 
														CArea.setColPos(nPosition); 
														CArea.encounter(); 
												} 
												else { 
														System.out.print("\033c");
														CUtils.header(" ERROR! Out of bounds.");
														System.out.println();
														System.out.println();
												}
										}
								} 
								else { 
										// Show error if user enters an incorrect input.
										System.out.print("\033c");
										CUtils.header(" ERROR! Enter the indicated keys.");
										System.out.println();
										System.out.println();
										CArea.display("X");
								}
						} while (!(strInput.equals("m") || strInput.equals("M") || strInput.equals("i") || strInput.equals("I")
								|| strInput.equals("w") || strInput.equals("W") || strInput.equals("a") || strInput.equals("A")
								|| strInput.equals("s") || strInput.equals("S") || strInput.equals("d") || strInput.equals("D"))); 
				}

				// End of the game, show a closing message.
				System.out.print("\033c");
				CUtils.header(" Thank you for playing POKIMON ONE!");
				System.out.println();
				System.out.println("   _________    __  _________   ____ _    ____________  __"); 
				System.out.println("  / ____/   |  /  |/  / ____/  / __ \\ |  / / ____/ __ \\/ /");
				System.out.println(" / / __/ /| | / /|_/ / __/    / / / / | / / __/ / /_/ / / ");
				System.out.println("/ /_/ / ___ |/ /  / / /___   / /_/ /| |/ / /___/ _, _/_/  ");
				System.out.println("\\____/_/  |_/_/  /_/_____/   \\____/ |___/_____/_/ |_(_)   ");
		}
}
