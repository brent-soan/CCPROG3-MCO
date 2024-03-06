import java.util.ArrayList;

/**
 * The Inventory class represents a collection of creatures that a player can carry.
 */
public class Inventory {
		private ArrayList<Creature> aInventory = new ArrayList<Creature>(); // Inventory of creatures
		private int nActivePosition; // Index of active creature in alInventory

		/**
		 * Constructor to initialize the Inventory object with a starting creature.
		 * @param CCreature The starting creature to be added to the inventory
		 */
		public Inventory(Creature CCreature) {
				this.aInventory.add(CCreature); // Add start creature to the inventory
				this.nActivePosition = 0; // Set the position of the active creature to the starting index of alInventory
		}

		/**
		 * Get the active creature from the inventory.
		 * @return The active creature in the inventory
		 */
		public Creature getActiveCreature() {
				return this.aInventory.get(nActivePosition);
		}

		/**
		 * Get the entire inventory of creatures.
		 * @return ArrayList containing all creatures in the inventory
		 */
		public ArrayList<Creature> getInventory() {
				return this.aInventory;
		}

		/**
		 * Set the position of the active creature in the inventory.
		 * @param nActivePosition Index of the new active creature in alInventory
		 */
		public void setActivePosition(int nActivePosition) {
				this.nActivePosition = nActivePosition;
		}
}
