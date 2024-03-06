import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The AreaOptionsView class represents the graphical user interface for selecting different game areas.
 * It includes buttons for navigating to specific areas and a back button to return to the main options view.
 * 
 * @author Brent Jan F. Soan
 */
public class AreaOptionsView extends View {
    private JButton areaOneBtn, areaTwoBtn, areaThreeBtn, backBtn;

    /**
     * Constructor for creating an AreaOptionsView object.
     * 
     * @param mainPanel The main JPanel to which this view will be added.
     */
    public AreaOptionsView(JPanel mainPanel) {
        super(mainPanel, new JLabel("AREAS"));

        this.titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.titleLbl.setFont(new Font("Comic Sans MS", Font.PLAIN, 50));

        this.areaOneBtn = new JButton("AREA 1");
        this.areaOneBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.areaOneBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        this.areaTwoBtn = new JButton("AREA 2");
        this.areaTwoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.areaTwoBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        this.areaThreeBtn = new JButton("AREA 3");
        this.areaThreeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.areaThreeBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        this.backBtn = new JButton("BACK");
        this.backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.backBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
        subPanel.add(this.titleLbl);
        subPanel.add(this.areaOneBtn);
        subPanel.add(this.areaTwoBtn);
        subPanel.add(this.areaThreeBtn);
        subPanel.add(this.backBtn);

        this.mainPanel.add(subPanel, "AreaOptionsView");
    }

    /**
     * Sets the action listener for the "AREA 1" button.
     * 
     * @param actionListener The ActionListener to be set.
     */
    public void setAreaOneBtnListener(ActionListener actionListener) {
        this.areaOneBtn.addActionListener(actionListener);
    }

    /**
     * Sets the action listener for the "AREA 2" button.
     * 
     * @param actionListener The ActionListener to be set.
     */
    public void setAreaTwoBtnListener(ActionListener actionListener) {
        this.areaTwoBtn.addActionListener(actionListener);
    }

    /**
     * Sets the action listener for the "AREA 3" button.
     * 
     * @param actionListener The ActionListener to be set.
     */
    public void setAreaThreeBtnListener(ActionListener actionListener) {
        this.areaThreeBtn.addActionListener(actionListener);
    }

    /**
     * Sets the action listener for the "BACK" button.
     * 
     * @param actionListener The ActionListener to be set.
     */
    public void setBackBtnListener(ActionListener actionListener) {
        this.backBtn.addActionListener(actionListener);
    }
}
