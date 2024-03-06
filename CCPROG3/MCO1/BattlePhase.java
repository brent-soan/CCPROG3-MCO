import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * The BattlePhase class contains the functionalities for in-game battles
 */
public class BattlePhase {
		private Creature CActiveCreature; // Active creature object in inventory
		private Creature CEnemyCreature; // Enemy creature object
		private Inventory CInventory; // Inventory object
		private Random CRandom;
		private Utils CUtils;
		private ArrayList<String> aDisplayEnemy;
		private ArrayList<String> aDisplayActive;
		private ArrayList<String> aDisplayActions;
		private ArrayList<ArrayList<String>> aDisplayCreatureDetails;
		private ArrayList<String> aChoices;
		private Scanner CScanner;

		/**
         * Constructor for BattlePhase
         * @param CInventory Inventory object
         * @param CRandom Random number generator
         * @param CUtils Utils object
         * @param CScanner Scanner object
         * @param CEnemyCreature Enemy creature object
         */
		public BattlePhase(Inventory CInventory, Random CRandom, Utils CUtils, Scanner CScanner) {
				this.CInventory = CInventory;
				this.CActiveCreature = this.CInventory.getActiveCreature();
				this.CRandom = CRandom;
				this.CUtils = CUtils;
				this.CScanner = CScanner;
				this.aDisplayEnemy = new ArrayList<String>();
				this.aDisplayActive = new ArrayList<String>();
				this.aDisplayActions = new ArrayList<String>();
				this.aDisplayActive.add("NAME: " + CActiveCreature.getName());
				this.aDisplayActive.add("TYPE: " + CActiveCreature.getType());
				this.aDisplayActive.add("EVOLUTION LEVEL: " + Integer.toString(CActiveCreature.getEvolutionLevel()));
				this.aDisplayCreatureDetails = new ArrayList<ArrayList<String>>();
				this.aChoices = new ArrayList<String>();
				this.aChoices.add("\"C\" - Catch");
				this.aChoices.add("\"R\" - Run");
		}

		/**
         * Method for battle phase
         */
		public void battle() {
				boolean bSwap = true;
				int nCapture = 1;
				double dDamage = 0;
				int nActions = 3;

				// Setting up enemy creature's information for display
				this.aDisplayEnemy.add("NAME: " + CEnemyCreature.getName());
				this.aDisplayEnemy.add("TYPE: " + CEnemyCreature.getType());
				this.aDisplayEnemy.add("EVOLUTION LEVEL: " + Integer.toString(CEnemyCreature.getEvolutionLevel()));
				this.aDisplayEnemy.add("HP: " + Double.toString(CEnemyCreature.getHealth()));

				// Determining available actions based on inventory size
				if (this.CInventory.getInventory().size() <= 1) {
							bSwap = false;
							this.aDisplayActions.add("\"A\" - Attack");
							this.aDisplayActions.add("\"C\" - Catch");
				} else {
							for (int i = 0; i < this.CInventory.getInventory().size(); i++) {
								ArrayList<String> aAdd = new ArrayList<String>();
								aAdd.add("TYPE: " + this.CInventory.getInventory().get(i).getType());
								aAdd.add("FAMILY: " + String.valueOf(this.CInventory.getInventory().get(i).getFamily()));
								aAdd.add("EVOLUTION LEVEL: " + String.valueOf(this.CInventory.getInventory().get(i).getEvolutionLevel()));
								this.aDisplayCreatureDetails.add(aAdd);
							}
							this.aDisplayActions.add("\"A\" - Attack");
							this.aDisplayActions.add("\"S\" - Swap");
							this.aDisplayActions.add("\"C\" - Catch");
				}

				// Displaying encounter message
				this.CUtils.header(" YOU ENCOUNTERED A CREATURE!");
				System.out.println();
				System.out.println();

				// Battle loop
				while (nCapture == 1 || nCapture == 0) {
						// Checking available actions and conditions
						if (CEnemyCreature.getHealth() == 0) {
								System.out.print("\033c");
								this.CUtils.header(" ENEMY IS DEFEATED!");
								System.out.println();
								System.out.println();
								break;
						} else if (nActions < 1) {
								System.out.print("\033c");
								this.CUtils.header(" ENEMY RAN AWAY.");
								System.out.println();
								System.out.println();
								break;
						}

						// Displaying battle information
						this.CUtils.header(" ACTIONS LEFT: " + nActions);
						System.out.println();
						System.out.println();
						this.CUtils.header(" ENEMY CREATURE");
						this.CUtils.body(this.aDisplayEnemy, "FREE");
						System.out.println();
						this.CUtils.header(" ACTIVE CREATURE");
						this.CUtils.body(this.aDisplayActive, "FREE");
						System.out.println();
						this.CUtils.header(" ACTIONS");
						for (int i = 1; i < this.aDisplayActions.size(); i++) {
								if ((this.aDisplayActions.get(i).equals("\"S\" - Swap") && !bSwap) || (this.aDisplayActions.get(i).equals("\"C\" - Catch") && nCapture == 0)) {
										this.aDisplayActions.remove(i);
										i = 1;
								}
						}
						this.CUtils.body(this.aDisplayActions, "FREE");
						System.out.println();

						System.out.print("[INPUT] : ");
						String strInput = this.CScanner.nextLine();

						if(strInput.equals("A") || strInput.equals("a") || ((strInput.equals("S") || strInput.equals("s")) && bSwap) || ((strInput.equals("C") || strInput.equals("c") && nCapture == 1))) {
							nActions--;
						}

						// Handling player input
						if (strInput.equals("A") || strInput.equals("a")) {
								dDamage = this.attack();
								System.out.print("\033c");
								this.CUtils.header(" DAMAGE: " + String.valueOf(dDamage));
								System.out.println();
								System.out.println();
						} else if ((strInput.equals("S") || strInput.equals("s")) && bSwap == true) {
								this.swap();
								System.out.print("\033c");
						} else if ((strInput.equals("C") || strInput.equals("c")) && nCapture == 1) {
								nCapture = this.capture();
								if(nCapture == -2) {
									System.out.print("\033c");
									this.CUtils.header(" YOU RAN AWAY.");
									System.out.println();
									System.out.println();
									break;
								}
						} else {
								System.out.print("\033c");
								this.CUtils.header(" ERROR! Enter the indicated keys.");
								System.out.println();
								System.out.println();
						}
				}

				// Displaying capture result
				if (nCapture == -1) {
						this.CUtils.header(" CREATURE CAPTURED!");
						System.out.println();
						System.out.println();
				}

				this.aDisplayActions.clear();
				this.aDisplayEnemy.clear();
				this.aDisplayCreatureDetails.clear();
		}

		/**
         * Method for calculating damage during attack
         * @return Damage done as a double value
         */
		public double attack() {
				double dHealth = 0;
				double dDamage = 0;

				// Calculating damage based on creature types
				if ((this.CActiveCreature.getType().equals("Fire") && this.CEnemyCreature.getType().equals("Grass"))
								|| (this.CActiveCreature.getType().equals("Grass") && this.CEnemyCreature.getType().equals("Water"))
								|| (this.CActiveCreature.getType().equals("Water") && this.CEnemyCreature.getType().equals("Fire"))) {
						dDamage = (this.CRandom.nextInt(10) + 1) * this.CActiveCreature.getEvolutionLevel() * 1.5;
				} else {
						dDamage = (this.CRandom.nextInt(10) + 1) * this.CActiveCreature.getEvolutionLevel();
				}

				// Applying damage to enemy creature's health
				dHealth = this.CEnemyCreature.getHealth() - dDamage;
				if (dHealth < 0) {
						dHealth = 0;
				}

				// Updating enemy creature's health and display
				this.CEnemyCreature.setHealth(dHealth);
				this.aDisplayEnemy.set(3, "HP: " + Double.toString(CEnemyCreature.getHealth()));

				return dDamage;
		}

		/**
         * Method for swapping active creature from the inventory
         */
		public void swap() {
				int nInput = -1;
				String strInput = "";
				System.out.print("\033c");

				// Swapping loop
				while (nInput == -1) {
						// Displaying available creatures for swapping
						for(int i = 0; i < this.CInventory.getInventory().size(); i++) {
							if(this.CInventory.getInventory().get(i) == this.CInventory.getActiveCreature()) {
								this.CUtils.header(" " + String.valueOf(i + 1) + ". " + this.CInventory.getInventory().get(i).getName() + " [ACTIVE]");
							}
							else {
								this.CUtils.header(" " + String.valueOf(i + 1) + ". " + this.CInventory.getInventory().get(i).getName());
							}
							this.CUtils.body(this.aDisplayCreatureDetails.get(i), "FREE");
							System.out.println();
						}

						System.out.print("[INPUT] : ");
						strInput = this.CScanner.nextLine();

						// Validating input and swapping active creature
						if (!(CUtils.isNumeric(strInput))) {
								System.out.print("\033c");
								this.CUtils.header(" ERROR! Enter the number of your chosen creature.");
								System.out.println();
								System.out.println();
								continue;
						}

						nInput = Integer.parseInt(strInput);
						if (nInput >= 1 && nInput <= this.CInventory.getInventory().size()) {
								this.CInventory.setActivePosition(nInput - 1);
								this.CActiveCreature = this.CInventory.getActiveCreature();
								this.aDisplayActive.set(0, "NAME: " + CActiveCreature.getName());
								this.aDisplayActive.set(1, "TYPE: " + CActiveCreature.getType());
								this.aDisplayActive.set(2, "EVOLUTION LEVEL: " + Integer.toString(CActiveCreature.getEvolutionLevel()));
						} else {
								System.out.print("\033c");
								this.CUtils.header(" ERROR! Enter the number of your chosen creature.");
								System.out.println();
								System.out.println();
								nInput = -1;
						}
				}
		}

		/**
         * Method for attempting to capture the enemy creature
         * @return nAttempt as an int
         */
		public int capture() {
				System.out.print("\033c");
				int nAttempt = -1;
				String strInput = "";

				do {
					this.CUtils.header(" CATCH THE ENEMY OR RUN AWAY?");
					this.CUtils.body(this.aChoices, "FREE");
					System.out.println();
					System.out.print("[INPUT] : ");
					strInput = this.CScanner.nextLine();

					System.out.print("\033c");

					if(!(strInput.equals("C") || strInput.equals("c") || strInput.equals("R") || strInput.equals("r"))) {
						this.CUtils.header(" ERROR! Enter the indicated keys.");
						System.out.println();
						System.out.println();
					}
				} while(!(strInput.equals("C") || strInput.equals("c") || strInput.equals("R") || strInput.equals("r")));
				
				double dCatchRate = 40 + 50 - this.CEnemyCreature.getHealth();
				
				if(strInput.equals("C") || strInput.equals("c")) {
					// Attempting capture based on catch rate
					if (this.CRandom.nextDouble() * 100 <= dCatchRate) {
							CInventory.getInventory().add(this.CEnemyCreature);
							System.out.print("\033c");
					} else {
							System.out.print("\033c");
							this.CUtils.header(" CATCH FAILED!");
							System.out.println();
							System.out.println();
							nAttempt = 0;
					}
				}
				else if(strInput.equals("R") || strInput.equals("r")) {
					nAttempt = -2;
				}

				return nAttempt;
		}

		/**
         * Method for setting enemy creature
         * @param CEnemyCreature Enemy creature object
         */
		public void setEnemyCreature(Creature CEnemyCreature) {
				this.CEnemyCreature = CEnemyCreature;
		}
}