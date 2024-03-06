import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The CreatureView class represents the view for displaying information about creatures in the game.
 * 
 * @author Brent Jan F. Soan
 */
public class CreatureView extends View {
    private JLabel creatureLbl, imageLbl, nameLbl, typeLbl, familyLbl, evoLbl;
    private JButton prevBtn, nextBtn;
    private JPanel creaturePanel, buttonsPanel, wholePanel;
    private InventoryModel CInventoryModel;

    /**
     * Constructs a CreatureView with the specified parameters.
     *
     * @param mainPanel        The main panel to which this view belongs
     * @param CInventoryModel  The InventoryModel associated with the view
     * @param strCreatureLbl   The label for the creature view
     * @param bSet             A boolean indicating whether to set creature information or not
     */
    public CreatureView(JPanel mainPanel, InventoryModel CInventoryModel, String strCreatureLbl, boolean bSet) {
        super(mainPanel);

        this.CInventoryModel = CInventoryModel;

        this.wholePanel = new JPanel();
        this.wholePanel.setLayout(new BoxLayout(wholePanel, BoxLayout.Y_AXIS));
        
        this.creaturePanel = new JPanel();
        this.creaturePanel.setLayout(new BoxLayout(this.creaturePanel, BoxLayout.Y_AXIS));
        
        this.creatureLbl = new JLabel(strCreatureLbl);

        if(bSet) {
            this.imageLbl = new JLabel(this.CInventoryModel.getActiveCreature().getImage());
            this.nameLbl = new JLabel("NAME: " + this.CInventoryModel.getActiveCreature().getName());
            this.typeLbl = new JLabel("TYPE: " + this.CInventoryModel.getActiveCreature().getType());
            this.familyLbl = new JLabel("FAMILY: " + String.valueOf(this.CInventoryModel.getActiveCreature().getFamily()));
            this.evoLbl = new JLabel("EVOLUTION LEVEL: " + String.valueOf(this.CInventoryModel.getActiveCreature().getEvolutionLevel()));
        }
        else {
            this.imageLbl = new JLabel();
            this.nameLbl = new JLabel();
            this.typeLbl = new JLabel();
            this.familyLbl = new JLabel();
            this.evoLbl = new JLabel();
        }
        
        this.creatureLbl.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
        this.nameLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.typeLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.familyLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.evoLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

        this.creatureLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.imageLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.nameLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.typeLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.familyLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.evoLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.creaturePanel.add(this.creatureLbl);
        this.creaturePanel.add(this.imageLbl);
        this.creaturePanel.add(this.nameLbl);
        this.creaturePanel.add(this.typeLbl);
        this.creaturePanel.add(this.familyLbl);
        this.creaturePanel.add(this.evoLbl);

        this.wholePanel.add(this.creaturePanel);

        if(bSet) {
            this.buttonsPanel = new JPanel();
            this.buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

            this.prevBtn = new JButton("  <  ");
            this.nextBtn = new JButton("  >  ");

            this.nextBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
            this.prevBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
            
            this.buttonsPanel.add(this.prevBtn);
            this.buttonsPanel.add(this.nextBtn);
            this.buttonsPanel.setMaximumSize(new Dimension(800, 50));
            
            this.wholePanel.add(this.buttonsPanel);
        }
    }

    /**
     * Gets the creature label.
     *
     * @return The creature label
     */
    public JLabel getCreatureLbl() {
        return this.creatureLbl;
    }

    /**
     * Gets the image label.
     *
     * @return The image label
     */
    public JLabel getImageLbl() {
        return this.imageLbl;
    }

    /**
     * Gets the name label.
     *
     * @return The name label
     */
    public JLabel getNameLbl() {
        return this.nameLbl;
    }

    /**
     * Gets the type label.
     *
     * @return The type label
     */
    public JLabel getTypeLbl() {
        return this.typeLbl;
    }

    /**
     * Gets the family label.
     *
     * @return The family label
     */
    public JLabel getFamilyLbl() {
        return this.familyLbl;
    }

    /**
     * Gets the evolution level label.
     *
     * @return The evolution level label
     */
    public JLabel getEvoLbl() {
        return this.evoLbl;
    }

    /**
     * Gets the whole panel.
     *
     * @return The whole panel
     */
    public JPanel getWholePanel() {
        return this.wholePanel;
    }

    /**
     * Sets a mouse listener for the creature panel.
     *
     * @param mouseListener The mouse listener to be set
     */
    public void setCreaturePanelListener(MouseListener mouseListener) {
        this.creaturePanel.addMouseListener(mouseListener);
    }

    /**
     * Sets an action listener for the previous button.
     *
     * @param actionListener The action listener to be set
     */
    public void setPrevBtnListener(ActionListener actionListener) {
        this.prevBtn.addActionListener(actionListener);
    }

    /**
     * Sets an action listener for the next button.
     *
     * @param actionListener The action listener to be set
     */
    public void setNextBtnListener(ActionListener actionListener) {
        this.nextBtn.addActionListener(actionListener);
    }
}
