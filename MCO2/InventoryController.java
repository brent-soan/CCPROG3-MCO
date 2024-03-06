import java.awt.event.*;
import java.awt.*;


/**
 * Controller class for managing the inventory and its interaction with views during various phases.
 * Provides methods to handle events and update the associated views and models.
 * 
 * @author Brent Jan F. Soan
 */
public class InventoryController extends Controller {
    // Reference to the associated InventoryView
    protected InventoryView CInventoryView;
    // Destination identifier for switching to another view
    protected String strDestination; 

    /**
     * Constructor for the InventoryController class.
     *
     * @param CInventoryView    The inventory view associated with the controller.
     * @param CInventoryModel   The inventory model associated with the controller.
     * @param strDestination    The identifier for the destination view.
     */
    public InventoryController(InventoryView CInventoryView, InventoryModel CInventoryModel, String strDestination) {
        super(CInventoryModel);
        this.CInventoryView = CInventoryView;
        this.strDestination = strDestination;

        // Set up listeners for the creature panel and navigation buttons
        this.CInventoryView.getCreatureView(0).setCreaturePanelListener(this.initializeCreaturePanelListener());

        this.CInventoryView.getCreatureView(0).setPrevBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle previous button click
                int nNewPosition = CInventoryModel.getInventoryPosition() - 1;

                if(nNewPosition >= 0 && nNewPosition < CInventoryModel.getInventory().size()) {
                    CInventoryModel.setInventoryPosition(nNewPosition);
                    CInventoryView.changeCreature(0);
                }
            }
        });

        this.CInventoryView.getCreatureView(0).setNextBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle next button click
                int nNewPosition = CInventoryModel.getInventoryPosition() + 1;

                if(nNewPosition >= 0 && nNewPosition < CInventoryModel.getInventory().size()) {
                    CInventoryModel.setInventoryPosition(nNewPosition);
                    CInventoryView.changeCreature(0);
                }
            }
        });

        this.CInventoryView.setBackBtnListener(this.initializeBackBtnListener());
    }

    /**
     * Initializes the mouse listener for the creature panel.
     * 
     * @return Mouse listener for creature panel.
     */
    public MouseListener initializeCreaturePanelListener() {
        return new MouseListener() {
            // Handle mouse release event
            public void mouseReleased(MouseEvent e) {
                if(CInventoryModel.getActivePosition() != CInventoryModel.getInventoryPosition()) {
                    CInventoryModel.setActivePosition(CInventoryModel.getInventoryPosition());
                    CInventoryView.changeCreature(0);
                }
            }

            // Other mouse events (not used)
            public void mousePressed(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {}
        };
    }

    /**
     * Initializes the action listener for the back button.
     * 
     * @return Action listener for back button.
     */
    public ActionListener initializeBackBtnListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) CInventoryView.getMainPanel().getLayout()).show(CInventoryView.getMainPanel(), strDestination);
            }
        };
    }

    /**
     * Gets the associated InventoryView.
     *
     * @return The InventoryView.
     */
    public InventoryView getInventoryView() {
        return this.CInventoryView;
    }

    /**
     * Gets the associated InventoryModel.
     *
     * @return The InventoryModel.
     */
    public InventoryModel getInventoryModel() {
        return this.CInventoryModel;
    }
}
