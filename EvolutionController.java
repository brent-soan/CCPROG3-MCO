import java.awt.*;
import java.awt.event.*;


/**
 * The EvolutionController class is responsible for handling user input and updating the EvolutionView and EvolutionModel accordingly.
 * 
 * @author Brent Jan F. Soan
 */
public class EvolutionController extends Controller {
    private EvolutionView CEvolutionView;
    private EvolutionModel CEvolutionModel;

    /**
     * Constructs an EvolutionController with the specified EvolutionView and EvolutionModel.
     *
     * @param CEvolutionView The EvolutionView associated with this controller.
     * @param CEvolutionModel The EvolutionModel associated with this controller.
     */
    public EvolutionController(EvolutionView CEvolutionView, EvolutionModel CEvolutionModel) {
        this.CEvolutionView = CEvolutionView;
        this.CEvolutionModel = CEvolutionModel;

        // Set listeners for creature view navigation buttons
        this.CEvolutionView.getCreatureView(0).setPrevBtnListener(initializeCreatureViewPrevBtns(0));
        this.CEvolutionView.getCreatureView(0).setNextBtnListener(initializeCreatureViewNextBtns(0));
        this.CEvolutionView.getCreatureView(2).setPrevBtnListener(initializeCreatureViewPrevBtns(1));
        this.CEvolutionView.getCreatureView(2).setNextBtnListener(initializeCreatureViewNextBtns(1));

        // Set listener for evolve button
        this.CEvolutionView.setEvolveBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CEvolutionView.evolve(CEvolutionModel.check());
            }
        });

        // Set listener for continue button
        this.CEvolutionView.setContinueBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CEvolutionView.continueAgain();
                CEvolutionModel.getInventoryModel(0).setActivePosition(0);
                CEvolutionModel.getInventoryModel(1).setActivePosition(0);
                CEvolutionModel.setCCreatureOne(CEvolutionModel.getInventoryModel(0).getActiveCreature());
                CEvolutionModel.setCCreatureTwo(CEvolutionModel.getInventoryModel(1).getActiveCreature());
                CEvolutionView.changeCreature(0);
                CEvolutionView.changeCreature(1);
            }
        });

        // Set listener for back button
        this.CEvolutionView.setBackBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)CEvolutionView.getMainPanel().getLayout()).show(CEvolutionView.getMainPanel(), "OptionsView");
                CEvolutionModel.getInventoryModel(0).setActivePosition(0);
                CEvolutionModel.getInventoryModel(1).setActivePosition(0);
                CEvolutionModel.setCCreatureOne(CEvolutionModel.getInventoryModel(0).getActiveCreature());
                CEvolutionModel.setCCreatureTwo(CEvolutionModel.getInventoryModel(1).getActiveCreature());
                CEvolutionView.changeCreature(0);
                CEvolutionView.changeCreature(1);
                CEvolutionView.back();
            }
        });
    }

    /**
     * Initializes an ActionListener for the previous button of a specific creature view.
     *
     * @param nIndex The index of the creature view.
     * @return ActionListener for the previous button.
     */
    public ActionListener initializeCreatureViewPrevBtns(int nIndex) {
        return new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int nNewPosition = CEvolutionModel.getInventoryModel(nIndex).getActivePosition() - 1;

            if(nNewPosition >= 0 && nNewPosition < CEvolutionModel.getInventoryModel(nIndex).getInventory().size()) {
                CEvolutionModel.getInventoryModel(nIndex).setActivePosition(nNewPosition);

                if(nIndex == 0) {
                    CEvolutionModel.setCCreatureOne(CEvolutionModel.getInventoryModel(nIndex).getActiveCreature());
                }
                else {
                    CEvolutionModel.setCCreatureTwo(CEvolutionModel.getInventoryModel(nIndex).getActiveCreature());
                }

                CEvolutionView.changeCreature(nIndex);
            }
        }
        };
    };  

    /**
     * Initializes an ActionListener for the next button of a specific creature view.
     *
     * @param nIndex The index of the creature view.
     * @return ActionListener for the next button.
     */
    public ActionListener initializeCreatureViewNextBtns(int nIndex) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nNewPosition = CEvolutionModel.getInventoryModel(nIndex).getActivePosition() + 1;

                if(nNewPosition >= 0 && nNewPosition < CEvolutionModel.getInventoryModel(nIndex).getInventory().size()) {
                    CEvolutionModel.getInventoryModel(nIndex).setActivePosition(nNewPosition);

                    if(nIndex == 0) {
                        CEvolutionModel.setCCreatureOne(CEvolutionModel.getInventoryModel(nIndex).getActiveCreature());
                    }
                    else {
                        CEvolutionModel.setCCreatureTwo(CEvolutionModel.getInventoryModel(nIndex).getActiveCreature());
                    }

                    CEvolutionView.changeCreature(nIndex);
                }
            }
        };
    };
}
