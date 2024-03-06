package Creatures;

import javax.swing.ImageIcon;

/**
 * The FamilyD class represents creatures of family D in the game.
 * It extends the Creature class and provides specific details for FamilyD creatures.
 * 
 * @author Brent Jan F. Soan
 */
public class FamilyD extends Creature {

    /**
     * Constructs a FamilyD creature with a specified evolution level.
     * Sets the name, image, elemental type, and family based on the evolution level.
     * 
     * @param nEvolutionLevel The evolution level of the creature
     */
    public FamilyD(int nEvolutionLevel) {
        super("Grass", 'D');

        if (nEvolutionLevel == 1) {
            this.strName = "Brownisaur";
            this.CImage = new ImageIcon("Assets/Creatures/Brownisaur.png");
        } else if (nEvolutionLevel == 2) {
            this.strName = "Chocosaur";
            this.CImage = new ImageIcon("Assets/Creatures/Chocosaur.png");
        } else if (nEvolutionLevel == 3) {
            this.strName = "Fudgasaur";
            this.CImage = new ImageIcon("Assets/Creatures/Fudgasaur.png");
        }

        this.nEvolutionLevel = nEvolutionLevel;
    }
}
