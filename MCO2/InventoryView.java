import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * View class representing the inventory display, allowing users to manage and view their creatures.
 * Extends the base View class and provides methods to update and interact with the inventory UI.
 * 
 * @author Brent Jan F. Soan
 */
public class InventoryView extends View {
    // Label displaying instructional prompts to the user
    protected JLabel promptLbl;
    // Button to navigate back to the previous view
    protected JButton backBtn;
    // Model representing the inventory data
    protected InventoryModel CInventoryModel;
    // List of CreatureView instances to display individual creatures in the inventory
    protected ArrayList<CreatureView> aCreatureViews;

    /**
     * Constructor for the InventoryView class.
     *
     * @param mainPanel        The main panel where this view will be displayed.
     * @param CInventoryModel  The inventory model associated with this view.
     * @param strName          The identifier for this view.
     */
    public InventoryView(JPanel mainPanel, InventoryModel CInventoryModel, String strName) {
        // Call the base constructor with a title label
        super(mainPanel, new JLabel("INVENTORY"));
        this.CInventoryModel = CInventoryModel;
        this.aCreatureViews = new ArrayList<CreatureView>();
        // Add a CreatureView for the active Pokémon
        this.aCreatureViews.add(new CreatureView(mainPanel, CInventoryModel, "ACTIVE POKIMON", true));

        // Customize the appearance of the title label
        this.titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize and customize the prompt label
        this.promptLbl = new JLabel("CLICK ON IMAGE TO CHANGE ACTIVE POKIMON");
        this.promptLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.promptLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize and customize the back button
        this.backBtn = new JButton("BACK");
        this.backBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set up the layout of the subpanel
        this.subPanel.setLayout(new BoxLayout(this.subPanel, BoxLayout.Y_AXIS));
        this.subPanel.add(this.titleLbl);
        this.subPanel.add(this.promptLbl);
        this.subPanel.add(this.aCreatureViews.get(0).getWholePanel());
        this.subPanel.add(this.backBtn);
        this.mainPanel.add(this.subPanel, strName);
    }

    /**
     * Constructor for the InventoryView class without additional customization.
     *
     * @param mainPanel  The main panel where this view will be displayed.
     */
    public InventoryView(JPanel mainPanel) {
        // Call the base constructor without a title label
        super(mainPanel, new JLabel("EVOLUTION"));
    }

    /**
     * Updates the displayed creature information in the specified CreatureView.
     *
     * @param nIndex  The index of the CreatureView to update.
     */
    public void changeCreature(int nIndex) {
        // Check if the displayed creature is the active Pokémon
        if (this.CInventoryModel.getInventoryPosition() == this.CInventoryModel.getActivePosition()) {
            this.aCreatureViews.get(nIndex).getCreatureLbl().setText("ACTIVE POKIMON");
        } else {
            this.aCreatureViews.get(nIndex).getCreatureLbl().setText("NON ACTIVE POKIMON");
        }

        // Update the information in the CreatureView based on the inventory position
        this.aCreatureViews.get(nIndex).getImageLbl().setIcon(this.CInventoryModel.getInventory().get(this.CInventoryModel.getInventoryPosition()).getImage());
        this.aCreatureViews.get(nIndex).getNameLbl().setText("NAME: " + this.CInventoryModel.getInventory().get(this.CInventoryModel.getInventoryPosition()).getName());
        this.aCreatureViews.get(nIndex).getTypeLbl().setText("TYPE: " + this.CInventoryModel.getInventory().get(this.CInventoryModel.getInventoryPosition()).getType());
        this.aCreatureViews.get(nIndex).getFamilyLbl().setText("FAMILY: " + String.valueOf(this.CInventoryModel.getInventory().get(this.CInventoryModel.getInventoryPosition()).getFamily()));
        this.aCreatureViews.get(nIndex).getEvoLbl().setText("EVOLUTION LEVEL: " + String.valueOf(this.CInventoryModel.getInventory().get(this.CInventoryModel.getInventoryPosition()).getEvolutionLevel()));
    }

    /**
     * Gets the CreatureView at the specified index in the list.
     *
     * @param nIndex  The index of the CreatureView to retrieve.
     * @return The CreatureView at the specified index.
     */
    public CreatureView getCreatureView(int nIndex) {
        return this.aCreatureViews.get(nIndex);
    }

    /**
     * Sets the action listener for the back button.
     *
     * @param actionListener  The action listener for the back button.
     */
    public void setBackBtnListener(ActionListener actionListener) {
        this.backBtn.addActionListener(actionListener);
    }
}
