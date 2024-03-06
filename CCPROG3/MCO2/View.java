import javax.swing.*;

/**
 * A base class representing a view in the application's user interface.
 * It provides a main frame, main panel, title label, and sub panel for creating user interfaces.
 */
public class View {
    // The main frame for the view
    protected JFrame mainFrame;

    // The main panel for the view
    protected JPanel mainPanel;

    // The title label for the view
    protected JLabel titleLbl;

    // The sub panel for additional components in the view
    protected JPanel subPanel;

    /**
     * Constructor for the View class with a specified main frame, main panel, and title label.
     *
     * @param mainFrame The main frame of the view.
     * @param mainPanel The main panel of the view.
     * @param titleLbl The title label of the view.
     */
    public View(JFrame mainFrame, JPanel mainPanel, JLabel titleLbl) {
        this.mainFrame = mainFrame;
        this.mainPanel = mainPanel;
        this.titleLbl = titleLbl;
        this.subPanel = new JPanel();
    }

    /**
     * Constructor for the View class with a specified main panel and title label.
     *
     * @param mainPanel The main panel of the view.
     * @param titleLbl The title label of the view.
     */
    public View(JPanel mainPanel, JLabel titleLbl) {
        this.mainFrame = null;
        this.mainPanel = mainPanel;
        this.titleLbl = titleLbl;
        this.subPanel = new JPanel();
    }

    /**
     * Constructor for the View class with a specified main panel.
     *
     * @param mainPanel The main panel of the view.
     */
    public View(JPanel mainPanel) {
        this.mainFrame = null;
        this.mainPanel = mainPanel;
        this.titleLbl = null;
    }

    /**
     * Gets the main panel of the view.
     *
     * @return The main panel.
     */
    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}
