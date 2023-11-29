package Creatures;

import javax.swing.ImageIcon;

/**
 * The FamilyG class represents creatures of family G in the game.
 * It extends the Creature class and provides specific details for FamilyG creatures.
 * 
 * @author Brent Jan F. Soan
 */
public class FamilyG extends Creature {

    /**
     * Constructs a FamilyG creature with a specified evolution level.
     * Sets the name, image, elemental type, and family based on the evolution level.
     * 
     * @param nEvolutionLevel The evolution level of the creature
     */
    public FamilyG(int nEvolutionLevel) {
        super("Water", 'G');

        if (nEvolutionLevel == 1) {
            this.strName = "Squirpie";
            this.CImage = new ImageIcon("Assets/Creatures/Squirpie.png");
        } else if (nEvolutionLevel == 2) {
            this.strName = "Tartortle";
            this.CImage = new ImageIcon("Assets/Creatures/Tartortle.png");
        } else if (nEvolutionLevel == 3) {
            this.strName = "Piestoise";
            this.CImage = new ImageIcon("Assets/Creatures/Piestoise.png");
        }

        this.nEvolutionLevel = nEvolutionLevel;
    }
}
