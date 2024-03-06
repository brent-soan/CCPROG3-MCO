package Creatures;

import javax.swing.ImageIcon;

/**
 * The FamilyA class represents creatures of family A in the game.
 * It extends the Creature class and provides specific details for FamilyA creatures.
 * 
 * @author Brent Jan F. Soan
 */
public class FamilyA extends Creature {

    /**
     * Constructs a FamilyA creature with a specified evolution level.
     * Sets the name, image, elemental type, and family based on the evolution level.
     * 
     * @param nEvolutionLevel The evolution level of the creature
     */
    public FamilyA(int nEvolutionLevel) {
        super("Fire", 'A');

        if (nEvolutionLevel == 1) {
            this.strName = "Strawander";
            this.CImage = new ImageIcon("Assets/Creatures/Strawander.png");
        } else if (nEvolutionLevel == 2) {
            this.strName = "Strawleon";
            this.CImage = new ImageIcon("Assets/Creatures/Strawleon.png");
        } else if (nEvolutionLevel == 3) {
            this.strName = "Strawizard";
            this.CImage = new ImageIcon("Assets/Creatures/Strawizard.png");
        }

        this.nEvolutionLevel = nEvolutionLevel;
    }
}
