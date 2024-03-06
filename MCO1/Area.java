import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Area class represents the game area where the player moves and encounters creatures.
 */
public class Area {
		private int nAreaNum; // Area number
		private int nRowPos; // Row position of the creature in the area
		private int nColPos; // Column position of the creature in the area
		private int nRows; // Number of rows of tiles for the area
		private int nColumns; // Number of columns of tiles for the area
		private Inventory CInventory; // Inventory object
		private BattlePhase CBattlePhase; // Battlephase object
		private ArrayList<Creature> aCreatures; // ArrayList of creatures
		private Random CRandom; // Random number generator
		private Utils CUtils; // Utils object

		/**
		 * Constructor to initialize the Area object.
	 	 * @param nAreaNum Number of area
		 * @param nRows Number of rows in the area
		 * @param nColumns Number of columns in the area
		 * @param CInventory Inventory object
		 * @param CRandom Random number generator
		 * @param aCreatures ArrayList of creatures in the area
		 * @param CUtils Utility object for game functionality
		 * @param CScanner Scanner object for user input
		 */
		public Area(int nAreaNum, int nRows, int nColumns, Inventory CInventory, Random CRandom, ArrayList<Creature> aCreatures, Utils CUtils, Scanner CScanner) {
				// Initialize instance variables with provided values
				this.nAreaNum = nAreaNum;
				this.nRowPos = 0;
				this.nColPos = 0;
				this.nRows = nRows;
				this.nColumns = nColumns;
				this.CInventory = CInventory;
				this.CRandom = CRandom;
				this.aCreatures = new ArrayList<Creature>();
				this.aCreatures.addAll(aCreatures); // Sets CCreatures with passed ArrayList of creatures (Evolution levels depends on area)
				this.CUtils = CUtils;
				this.CBattlePhase = new BattlePhase(this.CInventory, this.CRandom, CUtils, CScanner);
		}

		// Getter and setter methods for various instance variables

		/**
		 * Setter for row position.
		 * @param nRowPos Row position to set
		 */
		public void setRowPos(int nRowPos) {
				this.nRowPos = nRowPos;
		}

		/**
		 * Setter for column position.
		 * @param nColPos Column position to set
		 */
		public void setColPos(int nColPos) {
				this.nColPos = nColPos;
		}

		/**
		 * Getter for row position.
		 * @return Current row position
		 */
		public int getRowPos() {
				return this.nRowPos;
		}

		/**
		 * Getter for column position.
		 * @return Current column position
		 */
		public int getColPos() {
				return this.nColPos;
		}

		/**
		 * Getter for the number of rows.
		 * @return Number of rows in the area
		 */
		public int getRows() {
				return this.nRows;
		}

		/**
		 * Getter for the number of columns.
		 * @return Number of columns in the area
		 */
		public int getColumns() {
				return this.nColumns;
		}

		/**
		 * Display the game area with the character's position.
		 * @param strCharacter Character representing the player's position
		 */
		public void display(String strCharacter) {
				/*
				Example of a 1x1 tile with character inside

				+-----------+
				|           |
				|     *     |
				|           |
				+-----------+

				Example of a 1x1 tile without a character

				+-----------+
				|           |
				|           |
				|           |
				+-----------+
				*/
				int j = 0;
	
				this.CUtils.header(" AREA " + String.valueOf(this.nAreaNum));
				System.out.println();
				System.out.println();

				for (int i = 0; i < this.nRows; i++) {
						if (i == 0) {
								System.out.print("+"); // First line

								for (j = 0; j < this.nColumns; j++) {
										CUtils.border(11, "-"); // First line
										System.out.print("+"); // First line
								}
						}

						System.out.println(); // Second line
						System.out.print("|"); // Second line
						for (j = 0; j < this.nColumns; j++) {
								CUtils.border(11, " "); // Second line
								System.out.print("|"); // Second line
						}

						System.out.println(); // Third line
						System.out.print("|"); // Third line
						for (j = 0; j < this.nColumns; j++) {
								CUtils.border(5, " "); // Third line
								if (this.nRowPos == i && this.nColPos == j) {
										System.out.print(strCharacter); // Third line
								} else {
										System.out.print(" ");
								}
								CUtils.border(5, " "); // Third line
								System.out.print("|"); // Third line
						}

						System.out.println(); // Fourth line
						System.out.print("|"); // Fourth line
						for (j = 0; j < this.nColumns; j++) {
								CUtils.border(11, " "); // Fourth line
								System.out.print("|"); // Fourth line
						}

						System.out.println(); // Fifth line
						System.out.print("+"); // Fifth line
						for (j = 0; j < this.nColumns; j++) {
								CUtils.border(11, "-"); // Fifth line
								System.out.print("+"); // Fifth line
						}
				}

				System.out.println();
				System.out.println();
		}

		/**
		 * Check for encounters in the area and start a battle phase if conditions are met.
		 */
		public void encounter() {
				// Check if a random number falls within the encounter probability (40% chance)
				if(CRandom.nextInt(100) + 1 <= 40) {
						// Create a new enemy creature from the available creatures
						Creature CEnemyCreature = new Creature(this.CRandom, this.aCreatures);
						// Set the enemy creature for the battle phase
						CBattlePhase.setEnemyCreature(CEnemyCreature);
						// Start the battle phase
						CBattlePhase.battle();
				}
		}
}
