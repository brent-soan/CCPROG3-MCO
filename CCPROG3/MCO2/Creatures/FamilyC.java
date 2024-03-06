package Creatures;

import javax.swing.ImageIcon;

/**
 * The FamilyC class represents creatures of family C in the game.
 * It extends the Creature class and provides specific details for FamilyC creatures.
 * 
 * @author Brent Jan F. Soan
 */
public class FamilyC extends Creature {

    /**
     * Constructs a FamilyC creature with a specified evolution level.
     * Sets the name, image, elemental type, and family based on the evolution level.
     * 
     * @param nEvolutionLevel The evolution level of the creature
     */
    public FamilyC(int nEvolutionLevel) {
        super("Fire", 'C');

        if (nEvolutionLevel == 1) {
            this.strName = "Parfwit";
            this.CImage = new ImageIcon("Assets/Creatures/Parfwit.png");
        } else if (nEvolutionLevel == 2) {
            this.strName = "Parfure";
            this.CImage = new ImageIcon("Assets/Creatures/Parfure.png");
        } else if (nEvolutionLevel == 3) {
            this.strName = "Parfelure";
            this.CImage = new ImageIcon("Assets/Creatures/Parfelure.png");
        }

        this.nEvolutionLevel = nEvolutionLevel;
    }
}
