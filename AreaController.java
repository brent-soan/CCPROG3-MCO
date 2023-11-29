import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import Creatures.*;

/**
 * The AreaController class manages the control flow for the game's areas and encounters.
 * It handles the interaction between the AreaOptionsView, AreaView, BattlePhaseView, and other components.
 * 
 * @author Brent Jan F. Soan
 */
public class AreaController extends Controller {
    private AreaOptionsView CAreaOptionsView;
    private AreaView CAreaView;
    private ArrayList<AreaModel> CAreaModel;
    private CreatureModel CCreatureModel;
    private BattlePhaseModel CBattlePhaseModel;
    private BattlePhaseView CBattlePhaseView;
    private BattlePhaseController CBattlePhaseController;
    private InventoryController CInventoryController;

    /**
     * Constructs an AreaController with the specified AreaOptionsView and InventoryController.
     * Initializes necessary models and sets up event listeners.
     * 
     * @param CAreaOptionsView The view for area options
     * @param CInventoryController The controller for the inventory
     */
    public AreaController(AreaOptionsView CAreaOptionsView, InventoryController CInventoryController) {
        this.CAreaOptionsView = CAreaOptionsView;
        this.CAreaModel = new ArrayList<AreaModel>();
        this.CCreatureModel = new CreatureModel();
        this.CInventoryController = CInventoryController;

        // Initialize area models for different areas.
        this.CAreaModel.add(new AreaModel(1, 5, 1, this.CInventoryController.getInventoryModel(), this.CCreatureModel));
        this.CAreaModel.add(new AreaModel(2, 3, 3, this.CInventoryController.getInventoryModel(), this.CCreatureModel));
        this.CAreaModel.add(new AreaModel(3, 4, 4, this.CInventoryController.getInventoryModel(), this.CCreatureModel));

        // Set up event listeners for area buttons.
        this.CAreaOptionsView.setAreaOneBtnListener(this.initializeMovementBtnsListener(0));
        this.CAreaOptionsView.setAreaTwoBtnListener(this.initializeMovementBtnsListener(1));
        this.CAreaOptionsView.setAreaThreeBtnListener(this.initializeMovementBtnsListener(2));

        // Set up back button listener.
        this.CAreaOptionsView.setBackBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) CAreaOptionsView.getMainPanel().getLayout()).show(CAreaOptionsView.getMainPanel(), "OptionsView");
            }
        });
    }

    /**
     * Initializes the action listener for movement buttons in the specified area.
     * 
     * @param nIndex The index of the area
     * @return The action listener for movement buttons
     */
    public ActionListener initializeMovementBtnsListener(int nIndex) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CAreaView = new AreaView(CAreaOptionsView.getMainPanel(), new JLabel("AREA " + Integer.toString(nIndex + 1)), CAreaModel.get(nIndex));

                ((CardLayout) CAreaOptionsView.getMainPanel().getLayout()).show(CAreaOptionsView.getMainPanel(), "AreaView");

                CAreaView.setUpBtnListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int nPosition = CAreaModel.get(nIndex).getRowPos() - 1; 

                        // Check if movement is within bounds.
                        if(nPosition >= 0) { 
                            CAreaModel.get(nIndex).setRowPos(nPosition);
                            CAreaView.move();
                            encounter(nIndex);
                        }
                    }
                });

                CAreaView.setDownBtnListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int nPosition = CAreaModel.get(nIndex).getRowPos() + 1; 

                        // Check if movement is within bounds.
                        if(nPosition < CAreaModel.get(nIndex).getRows()) { 
                            CAreaModel.get(nIndex).setRowPos(nPosition);
                            CAreaView.move();
                            encounter(nIndex);
                        }
                    }
                });

                CAreaView.setLeftBtnListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int nPosition = CAreaModel.get(nIndex).getColPos() - 1; 

                        // Check if movement is within bounds.
                        if(nPosition >= 0) { 
                            CAreaModel.get(nIndex).setColPos(nPosition); 
                            CAreaView.move();
                            encounter(nIndex);
                        }
                    }
                });

                CAreaView.setRightBtnListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int nPosition = CAreaModel.get(nIndex).getColPos() + 1; 

                        // Check if movement is within bounds.
                        if(nPosition < CAreaModel.get(nIndex).getColumns()) { 
                            CAreaModel.get(nIndex).setColPos(nPosition); 
                            CAreaView.move();
                            encounter(nIndex);
                        }
                    }
                });

                CAreaView.setBackBtnListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((CardLayout) CAreaView.getMainPanel().getLayout()).show(CAreaView.getMainPanel(), "AreaOptionsView");
                        CAreaView.getMainPanel().remove(CAreaView.getSubPanel());
                        CAreaModel.get(nIndex).setRowPos(0);
                        CAreaModel.get(nIndex).setColPos(0);
                    }
                });
            }
        };
    }

    /**
     * Handles encounters in the specified area.
     * If an encounter occurs, initiates the Battle Phase.
     * 
     * @param nIndex The index of the area
     */
    public void encounter(int nIndex) {
        if(CAreaModel.get(nIndex).encounter() == true) {
            this.CBattlePhaseModel = new BattlePhaseModel(CCreatureModel, CAreaModel.get(nIndex).getAreaNum(), this.CInventoryController.getInventoryModel());
            this.CBattlePhaseView = new BattlePhaseView(CAreaView.getMainPanel(), this.CBattlePhaseModel, this.CInventoryController.getInventoryModel());
            this.CBattlePhaseController = new BattlePhaseController(this.CBattlePhaseView, this.CBattlePhaseModel, this.CInventoryController);
            ((CardLayout) CAreaView.getMainPanel().getLayout()).show(CAreaView.getMainPanel(), "BattlePhaseView");
        }
    }
}
