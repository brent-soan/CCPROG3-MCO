import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * View class responsible for displaying the Options menu.
 * Provides buttons to access Inventory, Areas, and Evolution views.
 * 
 * @author Brent Jan F. Soan
 */
public class OptionsView extends View {
    // Buttons for navigating to different views
    private JButton inventoryBtn, areasBtn, evolvebtn;

    /**
     * Constructor for the OptionsView class.
     *
     * @param mainPanel The main panel to which this view will be added.
     */
    public OptionsView(JPanel mainPanel) {
        // Initialize the View with a title label
        super(mainPanel, new JLabel("OPTIONS"));

        // Set the appearance and alignment of the title label
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));

        // Create buttons for Inventory, Areas, and Evolution
        this.inventoryBtn = new JButton("VIEW INVENTORY");
        this.inventoryBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.inventoryBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        this.areasBtn = new JButton("EXPLORE AN AREA");
        this.areasBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.areasBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        this.evolvebtn = new JButton("EVOLVE POKIMON");
        this.evolvebtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.evolvebtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        // Set the layout of the sub-panel and add components
        this.subPanel.setLayout(new BoxLayout(this.subPanel, BoxLayout.Y_AXIS));
        this.subPanel.add(this.titleLbl);
        this.subPanel.add(this.inventoryBtn);
        this.subPanel.add(this.areasBtn);
        this.subPanel.add(this.evolvebtn);

        // Add the sub-panel to the main panel with the name "OptionsView"
        this.mainPanel.add(this.subPanel, "OptionsView");
    }

    /**
     * Sets the ActionListener for the Inventory button.
     *
     * @param actionListener The ActionListener to be set for the Inventory button.
     */
    public void setInventoryBtnListener(ActionListener actionListener) {
        this.inventoryBtn.addActionListener(actionListener);
    }

    /**
     * Sets the ActionListener for the Areas button.
     *
     * @param actionListener The ActionListener to be set for the Areas button.
     */
    public void setAreasBtnListener(ActionListener actionListener) {
        this.areasBtn.addActionListener(actionListener);
    }

    /**
     * Sets the ActionListener for the Evolve button.
     *
     * @param actionListener The ActionListener to be set for the Evolve button.
     */
    public void setEvolveBtnListener(ActionListener actionListener) {
        this.evolvebtn.addActionListener(actionListener);
    }
}
