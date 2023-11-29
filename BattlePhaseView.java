import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The BattlePhaseView class represents the graphical user interface for the battle phase of the game.
 * It displays information about the player's active creature, the enemy creature, and provides buttons for various actions
 * such as attacking, swapping creatures, capturing enemies, and running from battles.
 * The view updates based on changes in the BattlePhaseModel and InventoryModel.
 *
 * @author Brent Jan F. Soan
 */
public class BattlePhaseView extends View {
    private JLabel enemyImageLbl, enemyNameLbl, enemyHealthLbl, enemyEvoLevelLbl, enemyTypeLbl;
    private JLabel imageLbl, nameLbl, evoLevelLbl, typeLbl, turnsLbl;
    private JLabel promptLbl;
    private JButton attackBtn, swapBtn, catchBtn, runBtn, continueBtn;
    private JPanel enemyPanel, enemyInfoPanel, buttonsPanel;
    private BattlePhaseModel CBattlePhaseModel;
    private InventoryModel CInventoryModel;

    /**
     * Constructs a new BattlePhaseView for the display of battling pokimons.
     *
     * @param mainPanel              The JPanel responsible for handling the subpanels.
     * @param CBattlePhaseModel      The BattlePhaseModel associated with this controller.
     * @param CInventoryModel        The InventoryModel associated with the game's inventory.
     */
    public BattlePhaseView(JPanel mainPanel, BattlePhaseModel CBattlePhaseModel, InventoryModel CInventoryModel) {
        super(mainPanel, new JLabel("BATTLE"));

        this.CBattlePhaseModel = CBattlePhaseModel;
        this.CInventoryModel = CInventoryModel;

        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));

        this.promptLbl = new JLabel("ENEMY ENCOUNTERED");
        this.promptLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.promptLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        this.enemyPanel = new JPanel();
        this.enemyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.enemyInfoPanel = new JPanel();
        this.enemyInfoPanel.setLayout(new BoxLayout(this.enemyInfoPanel, BoxLayout.Y_AXIS));

        this.enemyImageLbl = new JLabel(this.CBattlePhaseModel.getEnemy().getImage());
        this.enemyNameLbl = new JLabel("NAME: " + this.CBattlePhaseModel.getEnemy().getName());
        this.enemyNameLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.enemyHealthLbl = new JLabel("HEALTH: " + String.valueOf(this.CBattlePhaseModel.getEnemy().getHealth()));
        this.enemyHealthLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.enemyEvoLevelLbl = new JLabel("EVOLUTION LEVEL: " + String.valueOf(this.CBattlePhaseModel.getEnemy().getEvolutionLevel()));
        this.enemyEvoLevelLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.enemyTypeLbl = new JLabel("TYPE: " + this.CBattlePhaseModel.getEnemy().getType());
        this.enemyTypeLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        this.enemyInfoPanel.add(this.enemyNameLbl);
        this.enemyInfoPanel.add(this.enemyHealthLbl);
        this.enemyInfoPanel.add(this.enemyEvoLevelLbl);
        this.enemyInfoPanel.add(this.enemyTypeLbl);
        this.enemyPanel.add(this.enemyImageLbl);
        this.enemyPanel.add(this.enemyInfoPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        this.imageLbl = new JLabel(CInventoryModel.getActiveCreature().getImage());
        this.nameLbl = new JLabel("NAME: " + CInventoryModel.getActiveCreature().getName());
        this.nameLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.evoLevelLbl = new JLabel("EVOLUTION LEVEL: " + String.valueOf(CInventoryModel.getActiveCreature().getEvolutionLevel()));
        this.evoLevelLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.typeLbl = new JLabel("TYPE: " + CInventoryModel.getActiveCreature().getType());
        this.typeLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.turnsLbl = new JLabel("TURNS: " + CBattlePhaseModel.getTurns());
        this.turnsLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        infoPanel.add(this.nameLbl);
        infoPanel.add(this.evoLevelLbl);
        infoPanel.add(this.typeLbl);
        infoPanel.add(this.turnsLbl);

        this.attackBtn = new JButton("ATTACK");
        this.attackBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.swapBtn = new JButton("SWAP");
        this.swapBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.catchBtn = new JButton("CATCH");
        this.catchBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.runBtn = new JButton("RUN");
        this.runBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        bottomPanel.add(this.imageLbl);
        bottomPanel.add(infoPanel);
        upperPanel.add(this.attackBtn);
        upperPanel.add(this.swapBtn);
        lowerPanel.add(this.catchBtn);
        lowerPanel.add(this.runBtn);

        this.buttonsPanel = new JPanel();
        this.buttonsPanel.setLayout(new BoxLayout(this.buttonsPanel, BoxLayout.Y_AXIS));
        this.buttonsPanel.add(upperPanel);
        this.buttonsPanel.add(lowerPanel);
        bottomPanel.add(this.buttonsPanel);

        this.continueBtn = new JButton("CONTINUE");
        this.continueBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        this.continueBtn.setVisible(false);
        bottomPanel.add(this.continueBtn);

        this.subPanel.setLayout(new BoxLayout(this.subPanel, BoxLayout.Y_AXIS));
        this.subPanel.add(this.titleLbl);
        this.subPanel.add(this.promptLbl);
        this.subPanel.add(this.enemyPanel);
        this.subPanel.add(bottomPanel);

        this.mainPanel.add(this.subPanel, "BattlePhaseView");
    }

    /**
     * Updates the view to reflect the result of an attack action in the battle.
     * It displays the updated enemy health, remaining turns, and a prompt indicating the outcome of the attack.
     */
    public void attack() {
        this.enemyHealthLbl.setText("HEALTH: " + String.valueOf(this.CBattlePhaseModel.getEnemy().getHealth()));
        this.turnsLbl.setText("TURNS: " + this.CBattlePhaseModel.getTurns());
        
        if(this.CBattlePhaseModel.getEnemy().getHealth() > 0 && this.CBattlePhaseModel.getTurns() > 0) {
            this.promptLbl.setText("DEALT " + Double.toString(this.CBattlePhaseModel.getDamage()) + " DAMAGE");
        }
        else if(this.CBattlePhaseModel.getEnemy().getHealth() == 0) {
            this.promptLbl.setText("ENEMY IS DEFEATED");
            this.buttonsPanel.setVisible(false);
            this.continueBtn.setVisible(true);
        }
        else {
            this.promptLbl.setText("ENEMY RAN AWAY");
            this.buttonsPanel.setVisible(false);
            this.continueBtn.setVisible(true);
        }
    }

    /**
     * Updates the view to reflect the result of a creature swapping action in the battle.
     * It displays the updated active creature information, remaining turns, and a prompt indicating the outcome of the swap.
     */
    public void swap() {
        this.turnsLbl.setText("TURNS: " + this.CBattlePhaseModel.getTurns());

        if(this.CInventoryModel.getInventory().size() > 1) {
            if(this.CBattlePhaseModel.getActive() != this.CInventoryModel.getActiveCreature()) {
                if(this.CBattlePhaseModel.getTurns() > 0) {
                    this.promptLbl.setText("ACTIVE POKIMON SWAPPED TO " + this.CInventoryModel.getActiveCreature().getName().toUpperCase());
                }
                else {
                    this.promptLbl.setText("ENEMY RAN AWAY");
                    this.buttonsPanel.setVisible(false);
                    this.continueBtn.setVisible(true);
                }
                this.imageLbl.setIcon(this.CInventoryModel.getActiveCreature().getImage());
                this.nameLbl.setText("NAME: " + this.CInventoryModel.getActiveCreature().getName());
                this.evoLevelLbl.setText("EVOLUTION LEVEL: " + Integer.toString(this.CInventoryModel.getActiveCreature().getEvolutionLevel()));
                this.typeLbl.setText("TYPE: " + this.CInventoryModel.getActiveCreature().getType());
            }
            else {
                this.promptLbl.setText("NO SWAPPING OCCURED");
            }
        }
        else if(this.CInventoryModel.getInventory().size() == 1) {
            this.promptLbl.setText("ONLY ONE POKIMON IN INVENTORY");
        }
    }

    /**
     * Updates the view to reflect the result of a capture action in the battle.
     * It displays the updated remaining turns and a prompt indicating whether the enemy was successfully captured or not.
     */
    public void capture() {
        this.turnsLbl.setText("TURNS: " + this.CBattlePhaseModel.getTurns());

        if(this.CBattlePhaseModel.getCaptured() == true) {
            this.promptLbl.setText("ENEMY CAUGHT");
            this.buttonsPanel.setVisible(false);
            this.continueBtn.setVisible(true);
        }
        else if(this.CBattlePhaseModel.getCaptured() == false) {
            this.promptLbl.setText("FAILED TO CATCH ENEMY");

            if(this.CBattlePhaseModel.getTurns() > 0) {
                this.catchBtn.setVisible(false);
            }
            else {
                this.buttonsPanel.setVisible(false);
                this.continueBtn.setVisible(true);
            }
        }
    }

    /**
     * Sets the ActionListener for the attack button.
     *
     * @param actionListener ActionListener to be set for the attack button
     */
    public void setAttackBtnListener(ActionListener actionListener) {
        this.attackBtn.addActionListener(actionListener);
    }

    /**
     * Sets the ActionListener for the swap button.
     *
     * @param actionListener ActionListener to be set for the swap button
     */
    public void setSwapBtnListener(ActionListener actionListener) {
        this.swapBtn.addActionListener(actionListener);
    }

    /**
     * Sets the ActionListener for the catch button.
     *
     * @param actionListener ActionListener to be set for the catch button
     */
    public void setCatchBtnListener(ActionListener actionListener) {
        this.catchBtn.addActionListener(actionListener);
    }

    /**
     * Sets the ActionListener for the run button.
     *
     * @param actionListener ActionListener to be set for the run button
     */
    public void setRunBtnListener(ActionListener actionListener) {
        this.runBtn.addActionListener(actionListener);
    }

    /**
     * Sets the ActionListener for the continue button.
     *
     * @param actionListener ActionListener to be set for the continue button
     */
    public void setContinueBtnListener(ActionListener actionListener) {
        this.continueBtn.addActionListener(actionListener);
    }

    /**
     * Gets the sub-panel associated with the BattlePhaseView.
     *
     * @return JPanel representing the sub-panel of the BattlePhaseView
     */
    public JPanel getSubPanel() {
        return this.subPanel;
    }
}
