import java.util.ArrayList;
import java.util.Random;

/**
 * The Creature class represents the characters in the game, each with a name, elemental type, family, evolution level, and health.
 */
public class Creature {
		private String strName; // Creature name
		private String strType; // Creature elemental type
		private char cFamily; // Family of creature
		private int nEvolutionLevel; // Evolution level
		private double dHealth; // Health of creature

		/**
		 * Copy constructor for creating a new creature based on an existing creature.
		 * @param CCreature The creature to be copied
		 */
		public Creature(Creature CCreature) {
				this.strName = CCreature.strName;
				this.strType = CCreature.strType;
				this.cFamily = CCreature.cFamily;
				this.nEvolutionLevel = CCreature.nEvolutionLevel;
		}

		/**
		 * Constructor for creating enemy creatures.
		 * @param CRandom Random object for generating random indices
		 * @param aCreatures ArrayList containing available creatures
		 */
		public Creature(Random CRandom, ArrayList<Creature> aCreatures) {
				int index = CRandom.nextInt(9);
				this.dHealth = 50;
				// Set randomly selected creature's fields to enemy creature's fields
				this.strName = aCreatures.get(index).getName();
				this.strType = aCreatures.get(index).getType();
				this.cFamily = aCreatures.get(index).getFamily();
				this.nEvolutionLevel = aCreatures.get(index).getEvolutionLevel();
		}

		/**
		 * Constructor for creating creatures with specific data.
		 * @param strName Name of the creature
		 * @param strType Elemental type of the creature
		 * @param cFamily Family of the creature
		 * @param nEvolutionLevel Evolution level of the creature
		 */
		public Creature(String strName, String strType, char cFamily, int nEvolutionLevel) {
				this.strName = strName;
				this.strType = strType;
				this.cFamily = cFamily;
				this.nEvolutionLevel = nEvolutionLevel;
		}

		/**
		 * Setter for the health of the creature.
		 * @param dHealth The health value to set
		 */
		public void setHealth(double dHealth) {
				this.dHealth = dHealth;
		}

		/**
		 * Getter for the health of the creature.
		 * @return The health of the creature
		 */
		public double getHealth() {
				return this.dHealth;
		}

		/**
		 * Getter for the name of the creature.
		 * @return The name of the creature
		 */
		public String getName() {
				return this.strName;
		}

		/**
		 * Getter for the evolution level of the creature.
		 * @return The evolution level of the creature
		 */
		public int getEvolutionLevel() {
				return this.nEvolutionLevel;
		}

		/**
		 * Getter for the elemental type of the creature.
		 * @return The elemental type of the creature
		 */
		public String getType() {
				return this.strType;
		}

		/**
		 * Getter for the family of the creature.
		 * @return The family of the creature
		 */
		public char getFamily() {
				return this.cFamily;
		}
}
