package packagepal.view;
import java.util.List;
import java.util.Scanner;
import packagepal.model.PickupRequest;
/**
 * Console-based user interface for Package Pal.
 *
 * Displays menus, reads user input, and shows
 * package pickup information.
 *
 * The view does not change package data or
 * manage the pickup queue.
 */
public class ConsoleView
{
    private Scanner scanner;

    /**
     * Creates a new ConsoleView.
     */
    public ConsoleView() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu.
     */
    public void showMenu() {
        System.out.println();
        System.out.println("========== PACKAGE PAL ==========");
        System.out.println("1. Check in for package pickup");
        System.out.println("2. View waiting queue");
        System.out.println("3. Process next pickup");
        System.out.println("4. Exit");
        System.out.println("=================================");
    }

    /**
     * Displays a prompt and reads user input.
     *
     * @param message the prompt to display
     * @return the user's input
     */
    public String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * Displays the pickup queue and its size.
     *
     * @param waiting the list of pickup requests
     * @param size the number of students waiting
     */
    public void showQueue(
        List<PickupRequest> waiting, int size) {

        System.out.println();
        System.out.println("========== WAITING QUEUE ==========");
        System.out.println("Total students waiting: " + size);

        if (waiting == null || waiting.isEmpty()) {
            System.out.println(
                "No students are currently waiting.");
            System.out.println("===================================");
            return;
        }

        int position = 1;

        for (PickupRequest request : waiting) {
            packagepal.model.Package pkg = request.getPackage();

            System.out.println();
            System.out.println("Position: " + position);
            System.out.println(
                "Student: " + pkg.getStudentName());
            System.out.println(
                "Package ID: " + pkg.getPackageID());
            System.out.println(
                "Status: " + pkg.getStatus());

            position++;
        }

        System.out.println("===================================");
    }

    /**
     * Displays a success or information message.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    /**
     * Displays an error message.
     *
     * @param message the error to display
     */
    public void showError(String message) {
        System.out.println();
        System.out.println("Error: " + message);
    }
}
