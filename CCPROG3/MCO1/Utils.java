import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Utils class contains utility methods for displaying messages, borders, and handling user input.
 */
public class Utils {
		private ArrayList<String> aInstructions = new ArrayList<String>();
		private ArrayList<String> aMenu = new ArrayList<String>();

		/**
		 * Constructor initializes instruction and menu messages.
		 */
		public Utils() {
				aInstructions.add("The game's objective is to become better than ever.");
				aInstructions.add("You will need to explore areas, fight enemy creatures,");
				aInstructions.add("capture enemy creatures, evolve your own creatures,");
				aInstructions.add("and expand your collection of creatures.");
				aInstructions.add("The game is currently in its first phase which means");
				aInstructions.add("that there are stuff that aren't yet developed.");
				aInstructions.add("Second phase will be coming soon!");
				aInstructions.add("Enter one of the following keys to move your character:");
				aInstructions.add("\"W\" - Up");
				aInstructions.add("\"A\" - Left");
				aInstructions.add("\"S\" - Down");
				aInstructions.add("\"D\" - Right");
				aInstructions.add("Enter one of the following keys during an encounter:");
				aInstructions.add("\"A\" - To attack the enemy creature.");
				aInstructions.add("\"S\" - To change your active creature.");
				aInstructions.add("\"C\" - To catch the enemy creature.");
				aInstructions.add("To go to menu, enter \"M\".");
				aInstructions.add("To show instructions, enter \"I\".");
				aMenu.add("\"B\" - Go back");
				aMenu.add("\"Q\" - Quit");
		}

		/**
		 * Prints a specified number of characters to create a border.
		 * @param nNumber Number of characters to print
		 * @param strCharacter The character to print
		 */
		public void border(int nNumber, String strCharacter) {
				for(int i = 0; i < nNumber; i++) {
						System.out.print(strCharacter);
				}
		}

		/**
		 * Displays a header with the specified title.
		 * @param strTitle The title for the header
		 */
		public void header(String strTitle) {    
				System.out.print("+"); // Header first line
				this.border(58, "-"); // Header first line
				System.out.print("+"); // Header first line

				System.out.println(); // Header second line
				System.out.print("|"); // Header second line
				System.out.print(strTitle); // Header second line
				for(int i = 0; i < 58 - strTitle.length(); i++) {
						System.out.print(" "); // Header second line
				}
				System.out.print("|"); // Header second line

				System.out.println(); // Header third line
				System.out.print("+"); // Header third line
				this.border(58, "-"); // Header third line
				System.out.print("+"); // Header third line
		}

		/**
		 * Displays a menu or instruction body based on the mode and content provided.
		 * @param aContent The content to be displayed
		 * @param strMode The mode indicating the format of the content (SYMBOLS, NUMBERS, or FREE)
		 */
		public void body(ArrayList<String> aContent, String strMode) {
			if(strMode.equals("NUMBERS")) {
					String strSymbol = "";
					int nLength = 0;
	
					for(int i = 0; i < aContent.size(); i++) {
							strSymbol = String.valueOf(i + 1);
							nLength = strSymbol.length();
							System.out.println();
							System.out.print("| " + strSymbol + ". " + aContent.get(i));
							this.border(53 + nLength - aContent.get(i).length(), " ");
							System.out.print("|");
					}
			}
			else if(strMode.equals("FREE")) {
					for(int i = 0; i < aContent.size(); i++) {
							System.out.println();
							System.out.print("| " + aContent.get(i));
							this.border(57 - aContent.get(i).length(), " ");
							System.out.print("|");
					}
			}

			System.out.println();
			System.out.print("+");
			this.border(58, "-"); 
			System.out.print("+");
			System.out.println();
	}  

		/**
		 * Displays game instructions.
		 */
		public void instructions() {
				this.header(" INSTRUCTIONS");
				this.body(aInstructions, "FREE");
				System.out.println();
		}

		/**
		 * Displays the game menu and handles user input.
		 * @param CScanner Scanner object for user input
		 * @return True if the user chooses to quit, false otherwise
		 */
		public boolean menu(Scanner CScanner) {
			String strInput = ""; // String variable for input
			boolean bReturn = false; // boolean variable for do while loop

			do { 
					this.header(" MENU");
					this.body(aMenu, "FREE");
					System.out.println();

					System.out.print("[INPUT] : ");
					strInput = CScanner.nextLine();

					if(strInput.equals("q") || strInput.equals("Q")) { // Set bReturn to true if condition is met
							bReturn = true;
					}
					else if(!(strInput.equals("B") || strInput.equals("b"))) { // Print if user enters an incorrect input
							System.out.print("\033c");
							this.header(" ERROR! Enter the indicated keys.");
							System.out.println();
							System.out.println();
					}
			} while(!(strInput.equals("b") || strInput.equals("B") || strInput.equals("q") || strInput.equals("Q"))); // Loop until user enters a correct variable

			System.out.print("\033c");
			return bReturn; // Return boolean value
		}  

		/**
         * Checks if the given string is numeric
         * @param strInput User input 
         * @return boolean value if string is numeric or not
         */
		public boolean isNumeric(String strInput) {
				try {
						Double.parseDouble(strInput);
						return true;
				} catch (NumberFormatException e) {
						return false;
				}
		}
}
