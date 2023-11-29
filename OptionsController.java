import java.awt.*;
import java.awt.event.*;

/**
 * Controller class responsible for handling user interactions in the OptionsView.
 * Manages the transition between different views (Inventory, Areas, Evolution).
 * 
 * @author Brent Jan F. Soan
 */
public class OptionsController extends Controller {
    // The associated OptionsView
    private OptionsView COptionsView;

    /**
     * Constructor for the OptionsController class.
     *
     * @param COptionsView     The OptionsView instance associated with this controller.
     * @param CInventoryView   The InventoryView instance for managing inventory-related actions.
     * @param CInventoryModel  The InventoryModel instance for managing inventory data.
     */
    public OptionsController(OptionsView COptionsView, InventoryView CInventoryView, InventoryModel CInventoryModel) {
        // Initialize the OptionsView instance
        this.COptionsView = COptionsView;

        // Initialize controllers for Area and Evolution views
        new AreaController(new AreaOptionsView(COptionsView.getMainPanel()), new InventoryController(CInventoryView, CInventoryModel, "OptionsView"));
        EvolutionModel CEvolutionModel = new EvolutionModel(CInventoryModel);
        new EvolutionController(new EvolutionView(COptionsView.getMainPanel(), CInventoryModel, CEvolutionModel), CEvolutionModel);

        // Set listeners for the buttons in OptionsView
        this.COptionsView.setInventoryBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the InventoryView when the "Inventory" button is clicked
                ((CardLayout) COptionsView.getMainPanel().getLayout()).show(COptionsView.getMainPanel(), "InventoryView");
                
                // Update the displayed creature in the InventoryView
                CInventoryView.changeCreature(0);
            }
        });

        this.COptionsView.setAreasBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the AreaOptionsView when the "Areas" button is clicked
                ((CardLayout) COptionsView.getMainPanel().getLayout()).show(COptionsView.getMainPanel(), "AreaOptionsView");
            }
        });

        this.COptionsView.setEvolveBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Switch to the EvolutionView when the "Evolve" button is clicked
                ((CardLayout) COptionsView.getMainPanel().getLayout()).show(COptionsView.getMainPanel(), "EvolutionView");
            }
        });
    }
}
