import java.awt.*;
import java.awt.event.*;

import Creatures.*;

/**
 * Controller class responsible for handling user interactions in the start phase of the game.
 * Manages transitions between start views and initializes the options controller.
 * 
 * @author Brent Jan F. Soan
 */
public class StartController extends Controller {
    // Reference to the views and controllers used in the start phase
    private StartViewOne CStartViewOne;
    private StartViewTwo CStartViewTwo;
    private MessageView CMessageView;
    private OptionsController COptionsController;

    /**
     * Constructor for the StartController class.
     *
     * @param CStartViewOne The first view in the start phase.
     * @param CStartViewTwo The second view in the start phase.
     * @param CMessageView The message view displayed after selecting a starter creature.
     * @param CInventoryModel The inventory model used throughout the game.
     */
    public StartController(StartViewOne CStartViewOne, StartViewTwo CStartViewTwo, MessageView CMessageView, InventoryModel CInventoryModel) {
        // Call the constructor of the superclass (Controller)
        super(CInventoryModel);

        // Initialize references to views
        this.CStartViewOne = CStartViewOne;
        this.CStartViewTwo = CStartViewTwo;
        this.CMessageView = CMessageView;

        // Set ActionListener for the "Start" button in the first view
        this.CStartViewOne.setStartBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Go to picking a starter Pokemon page
                ((CardLayout) CStartViewOne.getMainPanel().getLayout()).show(CStartViewOne.getMainPanel(), "HomeViewTwo");
            }
        });

        // Set ActionListener for the "Exit" button in the first view
        this.CStartViewOne.setExitBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        // Set MouseListeners for selecting starter creatures in the second view
        this.CStartViewTwo.setCreaturePanel0Listener(this.initializeMouseListener('A'));
        this.CStartViewTwo.setCreaturePanel1Listener(this.initializeMouseListener('D'));
        this.CStartViewTwo.setCreaturePanel2Listener(this.initializeMouseListener('G'));

        // Set ActionListener for the "Continue" button in the message view
        this.CMessageView.setContinueBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Transition to the Options view
                ((CardLayout) CMessageView.getMainPanel().getLayout()).show(CMessageView.getMainPanel(), "OptionsView");
            }
        });
    }

    /**
     * Gets the OptionsController instance associated with this StartController.
     *
     * @return The OptionsController instance.
     */
    public OptionsController getOptionsController() {
        return this.COptionsController;
    }

    /**
     * Initializes a MouseListener for selecting a starter creature.
     *
     * @param cFamily The family identifier of the creature.
     * @return The configured MouseListener.
     */
    public MouseListener initializeMouseListener(char cFamily) {
        return new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                // Transition to the MessageView after selecting a starter creature
                ((CardLayout) CStartViewTwo.getMainPanel().getLayout()).show(CStartViewTwo.getMainPanel(), "MessageView");

                // Add the selected creature to the inventory based on its family
                if (cFamily == 'A') {
                    CInventoryModel.getInventory().add(new FamilyA(1));
                } else if (cFamily == 'D') {
                    CInventoryModel.getInventory().add(new FamilyD(1));
                } else if (cFamily == 'G') {
                    CInventoryModel.getInventory().add(new FamilyG(1));
                }

                // Initialize the OptionsController after selecting a starter creature
                COptionsController = new OptionsController(
                    new OptionsView(CStartViewOne.getMainPanel()),
                    new InventoryView(CStartViewOne.getMainPanel(), CInventoryModel, "InventoryView"),
                    CInventoryModel
                );
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {}

            public void mouseClicked(MouseEvent e) {}
        };
    }
}
