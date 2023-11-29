package Creatures;

import javax.swing.ImageIcon;

/**
 * The FamilyE class represents creatures of family E in the game.
 * It extends the Creature class and provides specific details for FamilyE creatures.
 * 
 * @author Brent Jan F. Soan
 */
public class FamilyE extends Creature {

    /**
     * Constructs a FamilyE creature with a specified evolution level.
     * Sets the name, image, elemental type, and family based on the evolution level.
     * 
     * @param nEvolutionLevel The evolution level of the creature
     */
    public FamilyE(int nEvolutionLevel) {
        super("Grass", 'E');

        if (nEvolutionLevel == 1) {
            this.strName = "Frubat";
            this.CImage = new ImageIcon("Assets/Creatures/Frubat.png");
        } else if (nEvolutionLevel == 2) {
            this.strName = "Golberry";
            this.CImage = new ImageIcon("Assets/Creatures/Golberry.png");
        } else if (nEvolutionLevel == 3) {
            this.strName = "Croberry";
            this.CImage = new ImageIcon("Assets/Creatures/Croberry.png");
        }

        this.nEvolutionLevel = nEvolutionLevel;
    }
}
