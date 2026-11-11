package packagepal.model;

/**
 * Small driver used to exercise the PackagePal model
 * without the controller or view.
 */
public class PackagePalModelDriver
{
    public static void main(String[] args)
    {
        Mailroom mailroom = new Mailroom();

        mailroom.addPackage(
            new Package("P100", "Alex", PackageStatus.READY));

        CheckInResult result =
            mailroom.checkIn("Alex", "P100");

        System.out.println("Check-in result: "
            + result.getStatus());

        System.out.println("Queue size: "
            + mailroom.getQueueSize());

        PickupRequest request =
            mailroom.processNext();

        System.out.println("Processed: "
            + request.getPackage().getStudentName());

        System.out.println("Queue size after pickup: "
            + mailroom.getQueueSize());
    }
}