package Creatures;

import javax.swing.ImageIcon;

/**
 * The FamilyF class represents creatures of family F in the game.
 * It extends the Creature class and provides specific details for FamilyF creatures.
 * 
 * @author Brent Jan F. Soan
 */
public class FamilyF extends Creature {

    /**
     * Constructs a FamilyF creature with a specified evolution level.
     * Sets the name, image, elemental type, and family based on the evolution level.
     * 
     * @param nEvolutionLevel The evolution level of the creature
     */
    public FamilyF(int nEvolutionLevel) {
        super("Grass", 'F');

        if (nEvolutionLevel == 1) {
            this.strName = "Malts";
            this.CImage = new ImageIcon("Assets/Creatures/Malts.png");
        } else if (nEvolutionLevel == 2) {
            this.strName = "Kirlicake";
            this.CImage = new ImageIcon("Assets/Creatures/Kirlicake.png");
        } else if (nEvolutionLevel == 3) {
            this.strName = "Velvevoir";
            this.CImage = new ImageIcon("Assets/Creatures/Velvevoir.png");
        }

        this.nEvolutionLevel = nEvolutionLevel;
    }
}
