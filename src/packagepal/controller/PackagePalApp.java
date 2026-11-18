package packagepal.controller;

import packagepal.model.Mailroom;
import packagepal.model.Package;
import packagepal.model.PackageStatus;
import packagepal.view.ConsoleView;

/**
 * Starts the PackagePal application.
 *
 * @author Colin Fahmy
 * @version Nov 11, 2026
 */
public class PackagePalApp
{
    /**
     * Starts the program.
     *
     * @param args
     *            command-line arguments
     */
    public static void main(String[] args)
    {
        Mailroom mailroom = new Mailroom();
        ConsoleView view = new ConsoleView();

        PackagePalController controller =
            new PackagePalController(mailroom, view);

        mailroom.addPackage(
            new Package(
                "P100",
                "Alex",
                PackageStatus.READY));

        mailroom.addPackage(
            new Package(
                "P200",
                "Jordan",
                PackageStatus.NOT_READY));

        controller.run();
    }
}
