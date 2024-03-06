import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * View class representing the first screen of the game.
 * Displays the game title and provides options to start or exit the game.
 * 
 * @author Brent Jan F. Soan
 */
public class StartViewOne extends View {
    // Buttons for starting or exiting the game
    private JButton startBtn, exitBtn;

    /**
     * Constructor for the StartViewOne class.
     * Sets up the main frame, title label, and buttons for starting or exiting the game.
     */
    public StartViewOne() {
        // Call the constructor of the superclass (View) with a new JFrame, JPanel, and title label
        super(new JFrame(), new JPanel(), new JLabel("POKIMON 2"));

        // Initialize the main frame properties
        this.mainFrame = new JFrame("POKIMON 2");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(800, 600);
        this.mainFrame.setResizable(false);
        this.mainFrame.setVisible(true);

        // Set the font and alignment for the title label
        this.titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize and configure the start button
        this.startBtn = new JButton("START");
        this.startBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize and configure the exit button
        this.exitBtn = new JButton("EXIT");
        this.exitBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set the layout of the main panel to CardLayout
        this.mainPanel.setLayout(new CardLayout());

        // Set the layout of the sub panel to BoxLayout
        this.subPanel.setLayout(new BoxLayout(this.subPanel, BoxLayout.Y_AXIS));

        // Add components to the sub panel
        this.subPanel.add(this.titleLbl);
        this.subPanel.add(this.startBtn);
        this.subPanel.add(this.exitBtn);

        // Add the sub panel to the main panel with the name "HomeViewOne"
        mainPanel.add(this.subPanel, "HomeViewOne");

        // Add the main panel to the main frame
        this.mainFrame.add(this.mainPanel);
    }   

    /**
     * Sets an ActionListener for the start button.
     *
     * @param actionListener The ActionListener to be set for the start button.
     */
    public void setStartBtnListener(ActionListener actionListener) {
        this.startBtn.addActionListener(actionListener);
    }

    /**
     * Sets an ActionListener for the exit button.
     *
     * @param actionListener The ActionListener to be set for the exit button.
     */
    public void setExitBtnListener(ActionListener actionListener) {
        this.exitBtn.addActionListener(actionListener);
    }
}
