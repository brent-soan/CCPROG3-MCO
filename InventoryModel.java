import java.util.ArrayList;
import Creatures.Creature;

/**
 * The Inventory class represents a collection of creatures that a player can carry.
 * 
 * @author Brent Jan F. Soan
 */
public class InventoryModel {
    private ArrayList<Creature> aInventory; // Inventory of creatures
    private int nActivePosition; // Index of active creature in alInventory
    private int nInventoryPosition; // Index of inventory position
    /**
     * Constructor to initialize nActivePosition with 0, nInventoryPosition with 0, and aInventory with new array list of creatures.
     */
    public InventoryModel() {
        this.aInventory = new ArrayList<Creature>();
        this.nActivePosition = 0; // Set the position of the active creature to the starting index of aInventory
        this.nInventoryPosition = 0;
    }

    /**
     * Constructor to initialize nActivePosition with 0 and aInventory with passed array list of creatures.
     * @param aInventory is passed to aInventory field
     */
    public InventoryModel(ArrayList<Creature> aInventory) {
        this.aInventory = aInventory;
        this.nActivePosition = 0;
    }

    /**
     * Get the active creature from the inventory.
     * @return The active creature in the inventory
     */
    public Creature getActiveCreature() {
        return this.aInventory.get(this.nActivePosition);
    }

    /**
     * Get the entire inventory of creatures.
     * @return ArrayList containing all creatures in the inventory
     */
    public ArrayList<Creature> getInventory() {
        return this.aInventory;
    }

    /**
     * Get the position of the active creature in the inventory.
     * @return The position of active creature
     */
    public int getActivePosition() {
        return this.nActivePosition;
    }

    /**
     * Get the position of creature that is being displayed.
     * @return The inventory position
     */
    public int getInventoryPosition() {
        return this.nInventoryPosition;
    }

    /**
     * Get the creature in the last position of inventory
     * @return The inventory position
     */
    public Creature getLastCreature() {
        return this.getInventory().get(this.getInventory().size() - 1);
    }

    /**
     * Set the position of the active creature in the inventory.
     * @param nActivePosition Index of the new active creature in aInventory
     */
    public void setActivePosition(int nActivePosition) {
        this.nActivePosition = nActivePosition;
    }

    /**
     * Set the position of creature that is being displayed.
     * @param nInventoryPosition Index of creature in aInventory
     */
    public void setInventoryPosition(int nInventoryPosition) {
        this.nInventoryPosition = nInventoryPosition;
    }
}
