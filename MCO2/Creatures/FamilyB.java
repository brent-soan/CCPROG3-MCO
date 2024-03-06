package Creatures;

import javax.swing.ImageIcon;

/**
 * The FamilyB class represents creatures of family B in the game.
 * It extends the Creature class and provides specific details for FamilyB creatures.
 * 
 * @author Brent Jan F. Soan
 */
public class FamilyB extends Creature {

    /**
     * Constructs a FamilyB creature with a specified evolution level.
     * Sets the name, image, elemental type, and family based on the evolution level.
     * 
     * @param nEvolutionLevel The evolution level of the creature
     */
    public FamilyB(int nEvolutionLevel) {
        super("Fire", 'B');

        if (nEvolutionLevel == 1) {
            this.strName = "Chocowool";
            this.CImage = new ImageIcon("Assets/Creatures/Chocowool.png");
        } else if (nEvolutionLevel == 2) {
            this.strName = "Chocofluff";
            this.CImage = new ImageIcon("Assets/Creatures/Chocofluff.png");
        } else if (nEvolutionLevel == 3) {
            this.strName = "Candaros";
            this.CImage = new ImageIcon("Assets/Creatures/Candaros.png");
        }

        this.nEvolutionLevel = nEvolutionLevel;
    }
}
