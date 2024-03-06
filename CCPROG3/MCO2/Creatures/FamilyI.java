package Creatures;

import javax.swing.ImageIcon;

/**
 * The FamilyI class represents creatures of family I in the game.
 * It extends the Creature class and provides specific details for FamilyI creatures.
 * 
 * @author Brent Jan F. Soan
 */
public class FamilyI extends Creature {

    /**
     * Constructs a FamilyI creature with a specified evolution level.
     * Sets the name, image, elemental type, and family based on the evolution level.
     * 
     * @param nEvolutionLevel The evolution level of the creature
     */
    public FamilyI(int nEvolutionLevel) {
        super("Water", 'I');

        if (nEvolutionLevel == 1) {
            this.strName = "Oshacone";
            this.CImage = new ImageIcon("Assets/Creatures/Oshacone.png");
        } else if (nEvolutionLevel == 2) {
            this.strName = "Dewice";
            this.CImage = new ImageIcon("Assets/Creatures/Dewice.png");
        } else if (nEvolutionLevel == 3) {
            this.strName = "Samurcone";
            this.CImage = new ImageIcon("Assets/Creatures/Samurcone.png");
        }

        this.nEvolutionLevel = nEvolutionLevel;
    }
}
