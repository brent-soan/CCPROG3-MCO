import java.awt.event.*;
import java.awt.*;

/**
 * Controller class for managing the inventory during the battle phase.
 * Extends the InventoryController class and provides additional functionality.
 */
public class InventoryBPController extends InventoryController {
    // Reference to the BattlePhaseController for coordination
    private BattlePhaseController CBattlePhaseController;

    /**
     * Constructor for the InventoryBPController class.
     *
     * @param CInventoryView         The inventory view associated with the controller.
     * @param CInventoryModel        The inventory model associated with the controller.
     * @param CBattlePhaseController The BattlePhaseController for coordination during the battle phase.
     */
    public InventoryBPController(InventoryView CInventoryView, InventoryModel CInventoryModel, BattlePhaseController CBattlePhaseController) {
        super(CInventoryView, CInventoryModel, "BattlePhaseView");
        this.CInventoryView = CInventoryView;
        this.CBattlePhaseController = CBattlePhaseController;
    }

    /**
     * Initializes the back button listener for returning from the inventory to the battle phase.
     *
     * @return ActionListener for the back button.
     */
    public ActionListener initializeBackBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch the card layout to the battle phase view
                ((CardLayout) CInventoryView.getMainPanel().getLayout()).show(CInventoryView.getMainPanel(), strDestination);

                // Decrease turns if the active creature in battle is not the same as the one in the inventory
                if (CBattlePhaseController.getBattlePhaseModel().getActive() != CInventoryModel.getActiveCreature()) {
                    CBattlePhaseController.getBattlePhaseModel().decreaseTurns();
                }

                // Swap the view to reflect the change in active creature
                CBattlePhaseController.getBattlePhaseView().swap();

                // Set the active creature in battle to the one in the inventory if turns are remaining
                if (CBattlePhaseController.getBattlePhaseModel().getActive() != CInventoryModel.getActiveCreature() &&
                        CBattlePhaseController.getBattlePhaseModel().getTurns() > 0) {
                    CBattlePhaseController.getBattlePhaseModel().setActive(CInventoryModel.getActiveCreature());
                }
            }
        };
    }
}
