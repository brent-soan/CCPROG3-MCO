import java.util.*;
import Creatures.*;

/**
 * The EvolutionModel class represents the model for creature evolution in the game.
 * 
 * @author Brent Jan F. Soan
 */
public class EvolutionModel {
    private ArrayList<InventoryModel> aInventoryModels;
    private InventoryModel CInventoryModel;
    private Creature CCreatureOne, CCreatureTwo;

    /**
     * Constructs an EvolutionModel with the specified InventoryModel.
     *
     * @param CInventoryModel The InventoryModel associated with this evolution model.
     */
    public EvolutionModel(InventoryModel CInventoryModel) {
        this.aInventoryModels = new ArrayList<InventoryModel>();
        this.aInventoryModels.add(new InventoryModel(CInventoryModel.getInventory()));
        this.aInventoryModels.add(new InventoryModel(CInventoryModel.getInventory()));
        this.CInventoryModel = CInventoryModel;
        this.CCreatureOne = CInventoryModel.getActiveCreature();
        this.CCreatureTwo = CInventoryModel.getActiveCreature();
    }

    /**
     * Checks if the current creatures are eligible for evolution.
     *
     * @return true if the creatures are eligible for evolution, false otherwise.
     */
    public boolean check() {
        boolean bIsEvolveable = false;

        if(this.CCreatureOne != null && this.CCreatureTwo != null) {
            if(this.CCreatureOne.getEvolutionLevel() < 3 && this.CCreatureTwo.getEvolutionLevel() < 3 && this.CCreatureOne.getEvolutionLevel() == this.CCreatureTwo.getEvolutionLevel() && this.CCreatureOne.getFamily() == this.CCreatureTwo.getFamily() && this.CCreatureOne != this.CCreatureTwo) {
                this.evolve();
                bIsEvolveable = true;
            }
        }   

        return bIsEvolveable;
    }

    /**
     * Performs the evolution process.
     */
    public void evolve() {
        // Implement the evolution logic for each creature family
        if(this.CCreatureOne instanceof FamilyA && this.CCreatureTwo instanceof FamilyA) {
            this.CInventoryModel.getInventory().add(new FamilyA(this.CCreatureOne.getEvolutionLevel() + 1));
        }
        else if(this.CCreatureOne instanceof FamilyB && this.CCreatureTwo instanceof FamilyB) {
            this.CInventoryModel.getInventory().add(new FamilyB(this.CCreatureOne.getEvolutionLevel() + 1));
        }
        else if(this.CCreatureOne instanceof FamilyC && this.CCreatureTwo instanceof FamilyC) {
            this.CInventoryModel.getInventory().add(new FamilyC(this.CCreatureOne.getEvolutionLevel() + 1));
        }
        else if(this.CCreatureOne instanceof FamilyD && this.CCreatureTwo instanceof FamilyD) {
            this.CInventoryModel.getInventory().add(new FamilyD(this.CCreatureOne.getEvolutionLevel() + 1));
        }
        else if(this.CCreatureOne instanceof FamilyE && this.CCreatureTwo instanceof FamilyE) {
            this.CInventoryModel.getInventory().add(new FamilyE(this.CCreatureOne.getEvolutionLevel() + 1));
        }
        else if(this.CCreatureOne instanceof FamilyF && this.CCreatureTwo instanceof FamilyF) {
            this.CInventoryModel.getInventory().add(new FamilyF(this.CCreatureOne.getEvolutionLevel() + 1));
        }
        else if(this.CCreatureOne instanceof FamilyG && this.CCreatureTwo instanceof FamilyG) {
            this.CInventoryModel.getInventory().add(new FamilyG(this.CCreatureOne.getEvolutionLevel() + 1));
        }
        else if(this.CCreatureOne instanceof FamilyH && this.CCreatureTwo instanceof FamilyH) {
            this.CInventoryModel.getInventory().add(new FamilyH(this.CCreatureOne.getEvolutionLevel() + 1));
        }
        else if(this.CCreatureOne instanceof FamilyI && this.CCreatureTwo instanceof FamilyI) {
            this.CInventoryModel.getInventory().add(new FamilyI(this.CCreatureOne.getEvolutionLevel() + 1));
        }
        
        // Remove the creatures used for evolution from the inventory
        this.CInventoryModel.getInventory().remove(this.CCreatureOne);
        this.CInventoryModel.getInventory().remove(this.CCreatureTwo);

        // Reset active position and inventory position
        this.CInventoryModel.setActivePosition(0);
        this.CInventoryModel.setInventoryPosition(0);
    }

    /**
     * Gets the inventory model at the specified index.
     *
     * @param nIndex The index of the inventory model.
     * @return The InventoryModel at the specified index.
     */
    public InventoryModel getInventoryModel(int nIndex) {
        return aInventoryModels.get(nIndex);
    }

    /**
     * Gets the first creature used for evolution.
     *
     * @return The first creature used for evolution.
     */
    public Creature getCCreatureOne() {
        return CCreatureOne;
    }

    /**
     * Gets the second creature used for evolution.
     *
     * @return The second creature used for evolution.
     */
    public Creature getCCreatureTwo() {
        return CCreatureTwo;
    }

    /**
     * Sets the first creature used for evolution.
     *
     * @param cCreatureOne The first creature used for evolution.
     */
    public void setCCreatureOne(Creature cCreatureOne) {
        this.CCreatureOne = cCreatureOne;
    }

    /**
     * Sets the second creature used for evolution.
     *
     * @param cCreatureTwo The second creature used for evolution.
     */
    public void setCCreatureTwo(Creature cCreatureTwo) {
        this.CCreatureTwo = cCreatureTwo;
    }
}