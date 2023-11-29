/**
 * The Controller class serves as the base class for various controllers in the application.
 * It provides a reference to the InventoryModel, allowing controllers to interact with the inventory.
 * 
 * @author Brent Jan F. Soan
 */
public class Controller {
    /**
     * The InventoryModel associated with the controller.
     */
    protected InventoryModel CInventoryModel;

    /**
     * Constructs a Controller with a reference to an InventoryModel.
     *
     * @param CInventoryModel The InventoryModel to be associated with the controller
     */
    public Controller(InventoryModel CInventoryModel) {
        this.CInventoryModel = CInventoryModel;
    }

    /**
     * Constructs a default Controller.
     * This constructor is provided for cases where a controller doesn't need an InventoryModel reference.
     */
    public Controller() {}
}
