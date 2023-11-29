import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * View class for the evolution screen, extending the InventoryView.
 * Handles the display and interaction related to creature evolution.
 * 
 * @author Brent Jan F. Soan
 */
public class EvolutionView extends InventoryView {
    private JButton evolveBtn, continueBtn, backBtn;
    private EvolutionModel CEvolutionModel;

    /**
     * Constructor for the EvolutionView class.
     *
     * @param mainPanel         The main JPanel to which this view will be added.
     * @param CInventoryModel  The inventory model associated with the view.
     * @param CEvolutionModel  The evolution model associated with the view.
     */
    public EvolutionView(JPanel mainPanel, InventoryModel CInventoryModel, EvolutionModel CEvolutionModel) {
        super(mainPanel);
        this.CEvolutionModel = CEvolutionModel;

        this.CInventoryModel = CInventoryModel;
        this.aCreatureViews = new ArrayList<CreatureView>();

        JPanel creaturesPanel = new JPanel();
        creaturesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        creaturesPanel.setMaximumSize(new Dimension(800, 300));
        
        this.aCreatureViews.add(new CreatureView(mainPanel, this.CEvolutionModel.getInventoryModel(0), "SELECTED", true));
        this.aCreatureViews.add(new CreatureView(mainPanel, CInventoryModel, "POKIMON CREATED", false));
        this.aCreatureViews.add(new CreatureView(mainPanel, this.CEvolutionModel.getInventoryModel(1), "SELECTED", true));
        this.aCreatureViews.get(1).getWholePanel().setVisible(false);
        
        for(int i = 0; i < this.aCreatureViews.size(); i++) {
            creaturesPanel.add(this.aCreatureViews.get(i).getWholePanel());
        }
        
        this.titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.promptLbl = new JLabel("CLICK ON BUTTONS TO CHANGE POKIMON TO EVOLVE");
        this.promptLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.promptLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.evolveBtn = new JButton("EVOLVE");
        this.evolveBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.evolveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.backBtn = new JButton("BACK");
        this.backBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.continueBtn = new JButton("CONTINUE");
        this.continueBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        this.continueBtn.setAlignmentX(Component.CENTER_ALIGNMENT); 
        this.continueBtn.setVisible(false);

        this.subPanel.setLayout(new BoxLayout(this.subPanel, BoxLayout.Y_AXIS));
        this.subPanel.add(this.titleLbl);
        this.subPanel.add(this.promptLbl);
        this.subPanel.add(creaturesPanel);
        this.subPanel.add(this.continueBtn);
        this.subPanel.add(this.evolveBtn);
        this.subPanel.add(this.backBtn);
        this.mainPanel.add(this.subPanel, "EvolutionView");
    }

    /**
     * Changes the display depending on the boolean value of bIsEvolveable.
     *
     * @param bIsEvolveable The boolean value that determines if selected creatures are evolveable.
     */
    public void evolve(boolean bIsEvolveable) {
        if(bIsEvolveable == true) {
            this.getCreatureView(1).getImageLbl().setIcon(this.CInventoryModel.getLastCreature().getImage());
            this.getCreatureView(1).getNameLbl().setText("NAME: " + this.CInventoryModel.getLastCreature().getName());
            this.getCreatureView(1).getTypeLbl().setText("TYPE: " + this.CInventoryModel.getLastCreature().getType());
            this.getCreatureView(1).getFamilyLbl().setText("FAMILY: " + Character.toString(this.CInventoryModel.getLastCreature().getFamily()));
            this.getCreatureView(1).getEvoLbl().setText("TYPE: " + Integer.toString(this.CInventoryModel.getLastCreature().getEvolutionLevel()));
            this.getCreatureView(1).getWholePanel().setVisible(true);

            this.continueBtn.setVisible(true);
            this.evolveBtn.setVisible(false);
            this.promptLbl.setText("SUCCESSFULLY EVOLVED");
        }
        else {
            if(this.CEvolutionModel.getCCreatureOne() == this.CEvolutionModel.getCCreatureTwo()) {
                this.promptLbl.setText("CAN'T EVOLVE SAME UNIQUE CREATURE");
            }
            else if(this.CEvolutionModel.getCCreatureOne().getEvolutionLevel() > 2 || this.CEvolutionModel.getCCreatureTwo().getEvolutionLevel() > 2) {
                this.promptLbl.setText("CAN'T EVOLVE EVOLUTION LEVEL 3 CREATURES");
            }
            else if(this.CEvolutionModel.getCCreatureOne() != this.CEvolutionModel.getCCreatureTwo()) {
                if(this.CEvolutionModel.getCCreatureOne().getEvolutionLevel() != this.CEvolutionModel.getCCreatureTwo().getEvolutionLevel()) {
                    this.promptLbl.setText("CAN'T EVOLVE CREATURES WITH DIFFERENT EVOLUTION LEVELS");
                }
                else if(this.CEvolutionModel.getCCreatureOne().getFamily() != this.CEvolutionModel.getCCreatureTwo().getFamily()) {
                    this.promptLbl.setText("CAN'T EVOLVE CREATURES WITH DIFFERENT FAMILIES");
                }
            }
        }
    }

    /**
     * Restarts the display after clicking the continue button.
     */
    public void continueAgain() {
        this.getCreatureView(1).getWholePanel().setVisible(false);
        this.continueBtn.setVisible(false);
        this.evolveBtn.setVisible(true);
    }

    /**
     * Changes the information of creatures when clicking the arrow buttons.
     */
    public void changeCreature(int nIndex) {
        int nInventoryModelIndex = nIndex;
        
        if(nInventoryModelIndex == 1) {
            nIndex++;
        }

        this.aCreatureViews.get(nIndex).getImageLbl().setIcon(this.CInventoryModel.getInventory().get(this.CEvolutionModel.getInventoryModel(nInventoryModelIndex).getActivePosition()).getImage());
        this.aCreatureViews.get(nIndex).getNameLbl().setText("NAME: " + this.CInventoryModel.getInventory().get(this.CEvolutionModel.getInventoryModel(nInventoryModelIndex).getActivePosition()).getName());
        this.aCreatureViews.get(nIndex).getTypeLbl().setText("TYPE: " + this.CInventoryModel.getInventory().get(this.CEvolutionModel.getInventoryModel(nInventoryModelIndex).getActivePosition()).getType());
        this.aCreatureViews.get(nIndex).getFamilyLbl().setText("FAMILY: " + String.valueOf(this.CInventoryModel.getInventory().get(this.CEvolutionModel.getInventoryModel(nInventoryModelIndex).getActivePosition()).getFamily()));
        this.aCreatureViews.get(nIndex).getEvoLbl().setText("EVOLUTION LEVEL: " + String.valueOf(this.CInventoryModel.getInventory().get(this.CEvolutionModel.getInventoryModel(nInventoryModelIndex).getActivePosition()).getEvolutionLevel()));
    }

    /**
     * Change the prompt label back to its original text after clicking the back button.
     */
    public void back() {
        this.promptLbl.setText("CLICK ON BUTTONS TO CHANGE POKIMON TO EVOLVE");
    }

    /*
     * Sets the actionListener of the buttons.
     */
    public void setEvolveBtnListener(ActionListener actionListener) {
        this.evolveBtn.addActionListener(actionListener);
    }

    public void setContinueBtnListener(ActionListener actionListener) {
        this.continueBtn.addActionListener(actionListener);
    }

    public void setBackBtnListener(ActionListener actionListener) {
        this.backBtn.addActionListener(actionListener);
    }
}
