package Creatures;

import javax.swing.*;

/**
 * The Creature class represents the characters in the game, each with a name, elemental type, family, evolution level, and health.
 *
 * @author Brent Jan F. Soan
 */
public class Creature {
    protected String strName; // Creature name
    protected String strType; // Creature elemental type
    protected char cFamily; // Family of creature
    protected int nEvolutionLevel; // Evolution level
    protected double dHealth; // Health of creature
    protected ImageIcon CImage; // Image of creature

    /**
     * Constructor for creating creatures with specific data.
     *
     * @param strType Elemental type of the creature
     * @param cFamily Family of the creature
     */
    public Creature(String strType, char cFamily) {
        this.strType = strType;
        this.cFamily = cFamily;
        this.dHealth = 50;
    }

    /**
     * Getter for the name of the creature.
     *
     * @return The name of the creature
     */
    public String getName() {
        return this.strName;
    }

    /**
     * Getter for the elemental type of the creature.
     *
     * @return The elemental type of the creature
     */
    public String getType() {
        return this.strType;
    }

    /**
     * Getter for the family of the creature.
     *
     * @return The family of the creature
     */
    public char getFamily() {
        return this.cFamily;
    }

    /**
     * Getter for the evolution level of the creature.
     *
     * @return The evolution level of the creature
     */
    public int getEvolutionLevel() {
        return this.nEvolutionLevel;
    }

    /**
     * Getter for the health of the creature.
     *
     * @return The health of the creature
     */
    public double getHealth() {
        return this.dHealth;
    }

    /**
     * Getter for the image icon of the creature.
     *
     * @return The image icon of the creature
     */
    public ImageIcon getImage() {
        return this.CImage;
    }

    /**
     * Setter for the health of the creature.
     *
     * @param dHealth The health value to set
     */
    public void setHealth(double dHealth) {
        this.dHealth = dHealth;
    }
}
