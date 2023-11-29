package Creatures;

import javax.swing.ImageIcon;

/**
 * The FamilyH class represents creatures of family H in the game.
 * It extends the Creature class and provides specific details for FamilyH creatures.
 * 
 * @author Brent Jan F. Soan
 */
public class FamilyH extends Creature {

    /**
     * Constructs a FamilyH creature with a specified evolution level.
     * Sets the name, image, elemental type, and family based on the evolution level.
     * 
     * @param nEvolutionLevel The evolution level of the creature
     */
    public FamilyH(int nEvolutionLevel) {
        super("Water", 'H');

        if (nEvolutionLevel == 1) {
            this.strName = "Chocolite";
            this.CImage = new ImageIcon("Assets/Creatures/Chocolite.png");
        } else if (nEvolutionLevel == 2) {
            this.strName = "Chocolish";
            this.CImage = new ImageIcon("Assets/Creatures/Chocolish.png");
        } else if (nEvolutionLevel == 3) {
            this.strName = "Icesundae";
            this.CImage = new ImageIcon("Assets/Creatures/Icesundae.png");
        }

        this.nEvolutionLevel = nEvolutionLevel;
    }
}
