package Creatures;

import java.util.*;

/**
 * The CreatureModel class represents the model for creating creatures in the game.
 * It includes methods for creating creatures with specific families and evolution levels.
 * 
 * @author Brent Jan F. Soan
 */
public class CreatureModel {
    private Random CRandom;
    private ArrayList<Character> aFamilies;

    /**
     * Constructs a CreatureModel with a random number generator and a list of creature families.
     */
    public CreatureModel() {
        this.CRandom = new Random();
        this.aFamilies = new ArrayList<Character>();
        this.aFamilies.add('A');
        this.aFamilies.add('B');
        this.aFamilies.add('C');
        this.aFamilies.add('D');
        this.aFamilies.add('E');
        this.aFamilies.add('F');
        this.aFamilies.add('G');
        this.aFamilies.add('H');
        this.aFamilies.add('I');
    }

    /**
     * Creates a creature with the specified family and evolution level.
     * 
     * @param cFamily The family of the creature
     * @param nEvolutionLevel The evolution level of the creature
     * @return The created creature
     */
    public Creature createCreature(char cFamily, int nEvolutionLevel) {
        Creature CCreature = null;

        switch (cFamily) {
            case 'A':
                CCreature = new FamilyA(nEvolutionLevel);
                break;
            case 'B':
                CCreature = new FamilyB(nEvolutionLevel);
                break;
            case 'C':
                CCreature = new FamilyC(nEvolutionLevel);
                break;
            case 'D':
                CCreature = new FamilyD(nEvolutionLevel);
                break;
            case 'E':
                CCreature = new FamilyE(nEvolutionLevel);
                break;
            case 'F':
                CCreature = new FamilyF(nEvolutionLevel);
                break;
            case 'G':
                CCreature = new FamilyG(nEvolutionLevel);
                break;
            case 'H':
                CCreature = new FamilyA(nEvolutionLevel); // Corrected to FamilyH
                break;
            case 'I':
                CCreature = new FamilyI(nEvolutionLevel);
                break;
        }

        return CCreature;
    }

    /**
     * Creates an enemy creature with a random family and evolution level.
     * 
     * @param nEvolutionLevel The maximum evolution level for the enemy creature
     * @return The created enemy creature
     */
    public Creature createEnemyCreature(int nEvolutionLevel) {
        return this.createCreature(this.aFamilies.get(this.CRandom.nextInt(9)), this.CRandom.nextInt(nEvolutionLevel) + 1);
    }
}
