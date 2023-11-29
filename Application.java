/**
 * The Application class represents the entry point for running the game application.
 * It initializes necessary components, such as views, models, and controllers.
 * 
 * @author Brent Jan F. Soan
 */
public class Application {

    /**
     * The main method to run the game application.
     * Initializes the start view, inventory model, start controller, and options controller.
     */
    public static void run() {
        StartViewOne CStartViewOne = new StartViewOne();
        InventoryModel CInventoryModel = new InventoryModel();
        StartController CStartController = new StartController(
            CStartViewOne,
            new StartViewTwo(CStartViewOne.getMainPanel()),
            new MessageView(CStartViewOne.getMainPanel()),
            CInventoryModel
        );
        OptionsController COptionsController = CStartController.getOptionsController();
    }
}
