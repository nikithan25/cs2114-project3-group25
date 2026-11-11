package packagepal.controller;

import packagepal.model.CheckInResult;
import packagepal.model.CheckInStatus;
import packagepal.model.Mailroom;
import packagepal.model.PickupRequest;
import packagepal.view.ConsoleView;

/**
 * Connects the PackagePal view and model.
 *
 * @author Colin Fahmy
 * @version Nov 10, 2026
 */
public class PackagePalController
{
    private Mailroom mailroom;
    private ConsoleView view;
    private boolean running;


    /**
     * Creates a new PackagePalController.
     *
     * @param mailroom
     *            the PackagePal model
     * @param view
     *            the console view
     */
    public PackagePalController(Mailroom mailroom, ConsoleView view)
    {
        this.mailroom = mailroom;
        this.view = view;
        running = true;
    }


    /**
     * Runs the main menu until the user exits.
     */
    public void run()
    {
        while (running)
        {
            view.showMenu();

            String choice = view.prompt("Enter your choice: ");

            if (choice.equals("1"))
            {
                handleCheckIn();
            }
            else if (choice.equals("2"))
            {
                handleViewQueue();
            }
            else if (choice.equals("3"))
            {
                handleProcessNext();
            }
            else if (choice.equals("4"))
            {
                handleExit();
            }
            else
            {
                view.showError("Invalid menu choice.");
            }
        }
    }


    /**
     * Handles a student checking in for package pickup.
     */
    public void handleCheckIn()
    {
        String name = view.prompt("Enter student name: ");
        String packageID = view.prompt("Enter package ID: ");

        if (name.isBlank() || packageID.isBlank())
        {
            view.showError("Name and package ID cannot be blank.");
            return;
        }

        CheckInResult result = mailroom.checkIn(name, packageID);

        if (result.getStatus() == CheckInStatus.ADDED)
        {
            view.showMessage("You have been added to the pickup line.");
        }
        else if (result.getStatus() == CheckInStatus.ALREADY_WAITING)
        {
            view.showMessage("You are already waiting in line.");
        }
        else if (result.getStatus() == CheckInStatus.NOT_READY)
        {
            view.showError("Your package is not ready for pickup.");
        }
        else if (result.getStatus() == CheckInStatus.NO_PACKAGE)
        {
            view.showError("No matching package was found.");
        }
        else if (result.getStatus() == CheckInStatus.ALREADY_PICKED_UP)
        {
            view.showError("This package has already been picked up.");
        }
    }


    /**
     * Displays the current pickup queue.
     */
    public void handleViewQueue()
    {
        view.showQueue(
            mailroom.getWaitingList(),
            mailroom.getQueueSize());
    }


    /**
     * Processes the next student in the pickup queue.
     */
    public void handleProcessNext()
    {
        PickupRequest request = mailroom.processNext();

        if (request == null)
        {
            view.showMessage("No pending pickups.");
        }
        else
        {
            String studentName =
                request.getPackage().getStudentName();

            view.showMessage(
                "Processed pickup for " + studentName + ".");
        }
    }


    /**
     * Ends the application.
     */
    public void handleExit()
    {
        running = false;
        view.showMessage("Goodbye.");
    }
}