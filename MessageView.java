import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * View class representing a message display to communicate information or instructions to the player.
 * Extends the base View class and provides a "CONTINUE" button for user interaction.
 * 
 * @author Brent Jan F. Soan
 */
public class MessageView extends View {
    // Button to continue to the next screen or action
    private JButton continueBtn;

    /**
     * Constructor for the MessageView class.
     *
     * @param mainPanel  The main panel where this view will be displayed.
     */
    public MessageView(JPanel mainPanel) {
        // Call the base constructor with a title label
        super(mainPanel, new JLabel("MESSAGE FROM 13RENT THE CREATOR"));

        // Customize the appearance of the title label
        this.titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create and customize a list of JLabels to display the message prompts
        ArrayList<JLabel> promptLbl = new ArrayList<JLabel>();
        promptLbl.add(new JLabel("The game's objective is to become better than ever. You will need to explore areas, fight enemy creatures,"));
        promptLbl.add(new JLabel("capture enemy creatures, evolve your own creatures, and expand your collection of creatures."));
        promptLbl.add(new JLabel("The game is now in its second phase, which means that this is the final version of the POKIMON series."));
        promptLbl.add(new JLabel("GLHF!"));
        for (int i = 0; i < promptLbl.size(); i++) {
            promptLbl.get(i).setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
            promptLbl.get(i).setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        // Initialize and customize the "CONTINUE" button
        this.continueBtn = new JButton("CONTINUE");
        this.continueBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.continueBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

        // Set up the layout of the subpanel
        this.subPanel.setLayout(new BoxLayout(this.subPanel, BoxLayout.Y_AXIS));
        this.subPanel.add(this.titleLbl);
        for (int i = 0; i < promptLbl.size(); i++) {
            this.subPanel.add(promptLbl.get(i));
        }
        this.subPanel.add(this.continueBtn);

        // Add the subpanel to the main panel with the specified name
        this.mainPanel.add(this.subPanel, "MessageView");
    }

    /**
     * Sets the action listener for the "CONTINUE" button.
     *
     * @param actionListener  The action listener for the "CONTINUE" button.
     */
    public void setContinueBtnListener(ActionListener actionListener) {
        this.continueBtn.addActionListener(actionListener);
    }
}
