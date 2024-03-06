import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The AreaView class represents the graphical user interface for displaying and navigating game areas.
 * It includes buttons for movement (up, down, left, right), a back button, and a visual representation of the area.
 * 
 * @author Brent Jan F. Soan
 */
public class AreaView extends View {
    private JButton backBtn, upBtn, downBtn, leftBtn, rightBtn;
    private JPanel areaPanel, lowerPanel;
    private AreaModel CAreaModel;

    /**
     * Constructor for creating an AreaView object.
     * 
     * @param mainPanel The main JPanel to which this view will be added.
     * @param titleLbl The JLabel representing the title of the area view.
     * @param CAreaModel The AreaModel object associated with this view.
     */
    public AreaView(JPanel mainPanel, JLabel titleLbl, AreaModel CAreaModel) {
        super(mainPanel, titleLbl);
        this.CAreaModel = CAreaModel;

        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));

        this.upBtn = new JButton("  ^  ");
        this.upBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.upBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        this.downBtn = new JButton("  v  ");
        this.downBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.downBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        this.leftBtn = new JButton("  <  ");
        this.leftBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.leftBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        this.rightBtn = new JButton("  >  ");
        this.rightBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.rightBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        this.backBtn = new JButton("BACK");
        this.backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.backBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(this.upBtn);
        JPanel subButtonsPanel = new JPanel();
        subButtonsPanel.setLayout(new BoxLayout(subButtonsPanel, BoxLayout.X_AXIS));
        subButtonsPanel.add(this.leftBtn);
        subButtonsPanel.add(this.downBtn);
        subButtonsPanel.add(this.rightBtn);
        buttonsPanel.add(subButtonsPanel);
        buttonsPanel.add(this.backBtn);

        this.lowerPanel = new JPanel();
        this.lowerPanel.setLayout(new BoxLayout(this.lowerPanel, BoxLayout.X_AXIS));
        this.lowerPanel.setMaximumSize(new Dimension(700, 500));
        this.lowerPanel.add(buttonsPanel);

        this.areaPanel = new JPanel();
        this.areaPanel.setLayout(new GridLayout(this.CAreaModel.getRows(), this.CAreaModel.getColumns()));
        this.areaPanel.setBackground(Color.green);

        for(int i = 0; i < this.CAreaModel.getRows(); i++) {
            for(int j = 0; j < this.CAreaModel.getColumns(); j++) {
                if(this.CAreaModel.getRowPos() == i && this.CAreaModel.getColPos() == j) {
                    JLabel characterLbl = new JLabel(new ImageIcon("Assets/Character.png"));
                    this.areaPanel.add(characterLbl);
                }
                else {
                    JLabel backgroundLbl = new JLabel(new ImageIcon("Assets/Dirt.png"));
                    this.areaPanel.add(backgroundLbl);
                }
            }
        }

        this.lowerPanel.add(this.areaPanel);

        this.subPanel.setLayout(new BoxLayout(this.subPanel, BoxLayout.Y_AXIS));
        this.subPanel.add(this.titleLbl);
        this.subPanel.add(this.lowerPanel);

        this.mainPanel.add(this.subPanel, "AreaView");
    }

    /**
     * Updates the visual representation of the area after movement.
     */
    public void move() {
        int nCtr = 0;

        for(int i = 0; i < this.CAreaModel.getRows(); i++) {
            for(int j = 0; j < this.CAreaModel.getColumns(); j++) {
                if(this.CAreaModel.getRowPos() == i && this.CAreaModel.getColPos() == j) {
                    ((JLabel) this.areaPanel.getComponent(nCtr)).setIcon(new ImageIcon("Assets/Character.png"));
                }
                else {
                    ((JLabel) this.areaPanel.getComponent(nCtr)).setIcon(new ImageIcon("Assets/Dirt.png"));
                }

                nCtr++;
            }
        }
    }

    /**
     * Sets the ActionListener for the upBtn.
     * 
     * @param actionListener The ActionListener to be set.
     */
    public void setUpBtnListener(ActionListener actionListener) {
        this.upBtn.addActionListener(actionListener);
    }

    /**
     * Sets the ActionListener for the downBtn.
     * 
     * @param actionListener The ActionListener to be set.
     */
    public void setDownBtnListener(ActionListener actionListener) {
        this.downBtn.addActionListener(actionListener);
    }

    /**
     * Sets the ActionListener for the leftBtn.
     * 
     * @param actionListener The ActionListener to be set.
     */
    public void setLeftBtnListener(ActionListener actionListener) {
        this.leftBtn.addActionListener(actionListener);
    }

    /**
     * Sets the ActionListener for the rightBtn.
     * 
     * @param actionListener The ActionListener to be set.
     */
    public void setRightBtnListener(ActionListener actionListener) {
        this.rightBtn.addActionListener(actionListener);
    }

    /**
     * Sets the ActionListener for the backBtn.
     * 
     * @param actionListener The ActionListener to be set.
     */
    public void setBackBtnListener(ActionListener actionListener) {
        this.backBtn.addActionListener(actionListener);
    }

    /**
     * Gets the subPanel of this view.
     * 
     * @return The subPanel.
     */
    public JPanel getSubPanel() {
        return this.subPanel;
    }
}
