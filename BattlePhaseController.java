import java.awt.*;
import java.awt.event.*;

/**
 * The BattlePhaseController class handles the user interactions in the battle phase of the game.
 * It connects the BattlePhaseView and BattlePhaseModel, ensuring proper communication between the UI and the game logic.
 * 
 * @author Brent Jan F. Soan
 */
public class BattlePhaseController extends Controller {
    private BattlePhaseView CBattlePhaseView;
    private BattlePhaseModel CBattlePhaseModel;

    /**
     * Constructor for the BattlePhaseController.
     * Initializes the controller with the associated BattlePhaseView and BattlePhaseModel.
     *
     * @param CBattlePhaseView       The associated BattlePhaseView.
     * @param CBattlePhaseModel      The associated BattlePhaseModel.
     * @param CInventoryController   The associated InventoryController for inventory management.
     */
    public BattlePhaseController(BattlePhaseView CBattlePhaseView, BattlePhaseModel CBattlePhaseModel, InventoryController CInventoryController) {
        this.CBattlePhaseView = CBattlePhaseView;
        this.CBattlePhaseModel = CBattlePhaseModel;
        InventoryBPController CInventoryBPController = new InventoryBPController(new InventoryView(this.CBattlePhaseView.getMainPanel(), CInventoryController.getInventoryModel(), "InventoryBPView"), CInventoryController.getInventoryModel(), this);

        /**
         * Sets up the ActionListener for the attack button in the battle phase.
         */
        this.CBattlePhaseView.setAttackBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CBattlePhaseModel.getEnemy().getHealth() > 0 && CBattlePhaseModel.getTurns() > 0) {
                    boolean isDead = CBattlePhaseModel.attack();
                    if(!isDead) {
                        CBattlePhaseModel.decreaseTurns();
                    }
                    CBattlePhaseView.attack();
                }

                if(CBattlePhaseModel.getTurns() == 0) {
                    CBattlePhaseView.attack();
                }
            }
        });
  
        /**
         * Sets up the ActionListener for the swap button in the battle phase.
         */
        this.CBattlePhaseView.setSwapBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CInventoryBPController.getInventoryModel().getInventory().size() > 1 && CBattlePhaseModel.getTurns() > 0) {
                    ((CardLayout) CBattlePhaseView.getMainPanel().getLayout()).show(CBattlePhaseView.getMainPanel(), "InventoryBPView");
                }
                else {
                    CBattlePhaseView.swap();
                }
            }
        });

        /**
         * Sets up the ActionListener for the catch button in the battle phase.
         */
        this.CBattlePhaseView.setCatchBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CBattlePhaseModel.getTurns() > 0) {
                    boolean isCaptured = CBattlePhaseModel.capture();
                    if(!isCaptured) {
                        CBattlePhaseModel.decreaseTurns();
                    }
                    CBattlePhaseView.capture();
                }

                if(CBattlePhaseModel.getTurns() == 0) {
                    CBattlePhaseView.capture();
                }
            }
        });

        /**
         * Sets up the ActionListener for the run button in the battle phase.
         */
        this.CBattlePhaseView.setRunBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) CBattlePhaseView.getMainPanel().getLayout()).show(CBattlePhaseView.getMainPanel(), "AreaView");
            }
        });

        /**
         * Sets up the ActionListener for the continue button in the battle phase.
         */
        this.CBattlePhaseView.setContinueBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) CBattlePhaseView.getMainPanel().getLayout()).show(CBattlePhaseView.getMainPanel(), "AreaView");
            }
        });
    }

    /**
     * Retrieves the BattlePhaseModel associated with this controller.
     *
     * @return The BattlePhaseModel.
     */
    public BattlePhaseModel getBattlePhaseModel() {
        return CBattlePhaseModel;
    }

    /**
     * Retrieves the BattlePhaseView associated with this controller.
     *
     * @return The BattlePhaseView.
     */
    public BattlePhaseView getBattlePhaseView() {
        return CBattlePhaseView;
    }
}
