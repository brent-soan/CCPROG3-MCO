import Creatures.*;
import java.util.*;

/**
 * The BattlePhaseModel class represents the game logic for the battle phase, managing interactions between the player's active creature and the enemy creature.
 * 
 * @author Brent Jan F. Soan
 */
public class BattlePhaseModel {
    private Creature CActive; // Active creature controlled by the player
    private Creature CEnemy; // Enemy creature encountered in battle
    private CreatureModel CCreatureModel; // Model for creating and managing creatures
    private Random CRandom; // Random number generator for various calculations
    private int nTurns; // Number of turns available in the battle phase
    private InventoryModel CInventoryModel; // Inventory model for managing creatures
    private boolean bCaptured; // Flag indicating whether the enemy creature was captured
    private double dDamage; // Amount of damage dealt to the enemy creature

    /**
     * Constructor for the BattlePhaseModel.
     *
     * @param CCreatureModel   The CreatureModel for creating enemy creatures.
     * @param nAreaNum         The area number, used for creating an enemy creature.
     * @param CInventoryModel  The InventoryModel for managing the player's creatures.
     */
    public BattlePhaseModel(CreatureModel CCreatureModel, int nAreaNum, InventoryModel CInventoryModel) {
        this.CActive = CInventoryModel.getActiveCreature();
        this.CEnemy = CCreatureModel.createEnemyCreature(nAreaNum);
        this.CCreatureModel = CCreatureModel;
        this.CRandom = new Random();
        this.nTurns = 3;
        this.CInventoryModel = CInventoryModel;
        this.bCaptured = false;
    }

    /**
     * Performs an attack action during the battle phase.
     *
     * @return True if the enemy creature is defeated, false otherwise.
     */
    public boolean attack() {
        double dHealth = 0;
        this.dDamage = 0;
        boolean isDead = false;

        // Calculating damage based on creature types
        if((this.CInventoryModel.getActiveCreature().getType().equals("Fire") && this.CEnemy.getType().equals("Grass")) || (this.CInventoryModel.getActiveCreature().getType().equals("Grass") & this.CEnemy.getType().equals("Water")) || (this.CInventoryModel.getActiveCreature().getType().equals("Water") & this.CEnemy.getType().equals("Fire"))) {
            dDamage = (this.CRandom.nextInt(10) + 1) * this.CInventoryModel.getActiveCreature().getEvolutionLevel() * 1.5;
        } 
        else {
            dDamage = (this.CRandom.nextInt(10) + 1) * this.CInventoryModel.getActiveCreature().getEvolutionLevel();
        }

        dHealth = this.CEnemy.getHealth() - dDamage;
        if (dHealth <= 0) {
            dHealth = 0;
            this.nTurns = 0;
            isDead = true;
        }

        this.CEnemy.setHealth(dHealth);
        return isDead;
    }

    /**
     * Attempts to capture the enemy creature during the battle phase.
     *
     * @return True if the capture is successful, false otherwise.
     */
    public boolean capture() {
        double dCatchRate = 40 + 50 - this.CEnemy.getHealth();
        boolean isCaptured = false;
				
        if(this.CRandom.nextDouble() * 100 <= dCatchRate) {
            this.CInventoryModel.getInventory().add(this.CEnemy);
            bCaptured = true;
            this.nTurns = 0;
            isCaptured = true;
        }

        return isCaptured;
    }

    /**
     * Decreases the remaining turns in the battle phase.
     */
    public void decreaseTurns() {
        this.nTurns--;
    }

    /**
     * Retrieves the player's active creature.
     *
     * @return The active creature controlled by the player.
     */
    public Creature getActive() {
        return this.CActive;
    }

    /**
     * Retrieves the enemy creature in the battle.
     *
     * @return The enemy creature encountered in battle.
     */
    public Creature getEnemy() {
        return this.CEnemy;
    }

    /**
     * Retrieves the remaining turns in the battle phase.
     *
     * @return The number of turns remaining.
     */
    public int getTurns() {
        return this.nTurns;
    }

    /**
     * Checks if the enemy creature was captured.
     *
     * @return True if the enemy creature was captured, false otherwise.
     */
    public boolean getCaptured() {
        return this.bCaptured;
    }

    /**
     * Retrieves the amount of damage dealt in the last attack.
     *
     * @return The amount of damage dealt to the enemy creature.
     */
    public double getDamage() {
        return this.dDamage;
    }

    /**
     * Sets the player's active creature.
     *
     * @param CActive The new active creature for the player.
     */
    public void setActive(Creature CActive) {
        this.CActive = CActive;
    }
}
