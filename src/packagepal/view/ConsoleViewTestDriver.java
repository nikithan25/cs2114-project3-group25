package packagepal.view;
import java.util.ArrayList;
import packagepal.model.PickupRequest;
/**
 * A simple test driver for the ConsoleView class.
 * Tests the menu, messages, empty queue display,
 * and user input without needing the controller.
 *
 * @author Muhammad Zenoor Hayat Bhatti (muhammadzenoor05)
 * @version 2026.11.10
 */
public class ConsoleViewTestDriver
{
    /**
     * Runs basic tests for the ConsoleView.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
    
        view.showMenu();
    
        view.showMessage("Package checked in successfully!");
    
        view.showError("Package is not ready for pickup.");
    
        view.showQueue(new ArrayList<PickupRequest>(), 0);
    
        String name = view.prompt("Enter student name: ");
    
        view.showMessage("Welcome, " + name + "!");
    }
}
