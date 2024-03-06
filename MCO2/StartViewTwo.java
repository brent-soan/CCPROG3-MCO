import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * View class representing the second screen of the game where players choose their starter POKIMON.
 * Displays a list of creature panels with images and names for players to choose from.
 */
public class StartViewTwo extends View {
    // List to store creature panels
    private ArrayList<JPanel> aCreaturePanels;

    /**
     * Constructor for the StartViewTwo class.
     * Initializes the creature panels with images and names, and sets up the main and sub panels.
     *
     * @param mainPanel The main panel to which this view is added.
     */
    public StartViewTwo(JPanel mainPanel) {
        // Call the constructor of the superclass (View) with the main panel and a title label
        super(mainPanel, new JLabel("CHOOSE YOUR STARTER POKIMON"));
        
        // Initialize the list to store creature panels
        this.aCreaturePanels = new ArrayList<JPanel>();

        // Set the font and alignment for the title label
        this.titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a panel to hold creature panels
        JPanel creaturesPanel = new JPanel();
        creaturesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Initialize and configure creature panels with images and names
        initializeCreaturePanel(0, "Strawander");
        initializeCreaturePanel(1, "Brownisaur");
        initializeCreaturePanel(2, "Squirpie");

        // Add creature panels to the creatures panel
        creaturesPanel.add(this.aCreaturePanels.get(0));
        creaturesPanel.add(this.aCreaturePanels.get(1));
        creaturesPanel.add(this.aCreaturePanels.get(2));

        // Set the layout of the sub panel to BoxLayout
        this.subPanel.setLayout(new BoxLayout(this.subPanel, BoxLayout.Y_AXIS));

        // Add components to the sub panel
        this.subPanel.add(this.titleLbl);
        this.subPanel.add(creaturesPanel);

        // Add the sub panel to the main panel with the name "HomeViewTwo"
        this.mainPanel.add(this.subPanel, "HomeViewTwo");
    }

    /**
     * Initializes a creature panel with the specified index, image, and name.
     *
     * @param index The index of the creature panel in the list.
     * @param name The name of the creature.
     */
    private void initializeCreaturePanel(int index, String name) {
        this.aCreaturePanels.add(new JPanel());
        this.aCreaturePanels.get(index).setLayout(new FlowLayout(FlowLayout.CENTER));
        this.aCreaturePanels.get(index).add(new JLabel(new ImageIcon("Assets/Creatures/" + name + ".png")));
        JLabel nameLbl = new JLabel();
        nameLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        nameLbl.setText(name);
        this.aCreaturePanels.get(index).add(nameLbl);
    }

    /**
     * Sets a MouseListener for the creature panel at index 0.
     *
     * @param mouseListener The MouseListener to be set for the creature panel.
     */
    public void setCreaturePanel0Listener(MouseListener mouseListener) {
        this.aCreaturePanels.get(0).addMouseListener(mouseListener);
    }

    /**
     * Sets a MouseListener for the creature panel at index 1.
     *
     * @param mouseListener The MouseListener to be set for the creature panel.
     */
    public void setCreaturePanel1Listener(MouseListener mouseListener) {
        this.aCreaturePanels.get(1).addMouseListener(mouseListener);
    }

    /**
     * Sets a MouseListener for the creature panel at index 2.
     *
     * @param mouseListener The MouseListener to be set for the creature panel.
     */
    public void setCreaturePanel2Listener(MouseListener mouseListener) {
        this.aCreaturePanels.get(2).addMouseListener(mouseListener);
    }
}
