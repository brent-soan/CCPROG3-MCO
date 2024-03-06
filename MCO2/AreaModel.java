import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import Creatures.Creature;
import Creatures.CreatureModel;

/**
 * The AreaModel class represents the game area where the player moves and encounters creatures.
 * It contains information about the area, such as its number, dimensions, and the player's position.
 * Additionally, it handles encounters and provides getters and setters for its properties.
 * 
 * @author Brent Jan F. Soan
 */
public class AreaModel {
	private int nAreaNum; // Area number
	private int nRowPos; // Row position of the creature in the area
	private int nColPos; // Column position of the creature in the area
	private int nRows; // Number of rows of tiles for the area
	private int nColumns; // Number of columns of tiles for the area
	private InventoryModel CInventoryModel; // Inventory object
	private Random CRandom; // Random number generator
	private CreatureModel CCreatureModel; // Model for creature

	/**
	 * Constructor to initialize the Area object.
	 * @param nAreaNum Number of area
	 * @param nRows Number of rows in the area
	 * @param nColumns Number of columns in the area
	 * @param CInventoryModel InventoryModel object
	 * @param CCreatureModel CreatureModel object
	 */
	public AreaModel(int nAreaNum, int nRows, int nColumns, InventoryModel CInventoryModel, CreatureModel CCreatureModel) {
		// Initialize instance variables with provided values
		this.nAreaNum = nAreaNum;
		this.nRowPos = 0;
		this.nColPos = 0;
		this.nRows = nRows;
		this.nColumns = nColumns;
		this.CInventoryModel = CInventoryModel;
		this.CRandom = new Random();
		this.CCreatureModel = CCreatureModel;
	}

	/**
	 * Check for encounters in the area and start a battle phase if conditions are met.
	 * 
	 * @return True if an encounter occurs, false otherwise
	 */
	public boolean encounter() {
		// Check if a random number falls within the encounter probability (40% chance)
		boolean bEncountered = false;

		if (this.CRandom.nextInt(100) + 1 <= 40) {
			bEncountered = true;
		}

		return bEncountered;
	}

	// Getter and setter methods for various instance variables

	/**
	 * Setter for row position.
	 * 
	 * @param nRowPos Row position to set
	 */
	public void setRowPos(int nRowPos) {
		this.nRowPos = nRowPos;
	}

	/**
	 * Setter for column position.
	 * 
	 * @param nColPos Column position to set
	 */
	public void setColPos(int nColPos) {
		this.nColPos = nColPos;
	}

	/**
	 * Getter for area number.
	 * 
	 * @return Number of area
	 */
	public int getAreaNum() {
		return this.nAreaNum;
	}

	/**
	 * Getter for row position.
	 * 
	 * @return Current row position
	 */
	public int getRowPos() {
		return this.nRowPos;
	}

	/**
	 * Getter for column position.
	 * 
	 * @return Current column position
	 */
	public int getColPos() {
		return this.nColPos;
	}

	/**
	 * Getter for the number of rows.
	 * 
	 * @return Number of rows in the area
	 */
	public int getRows() {
		return this.nRows;
	}

	/**
	 * Getter for the number of columns.
	 * 
	 * @return Number of columns in the area
	 */
	public int getColumns() {
		return this.nColumns;
	}
}