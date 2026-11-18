package packagepal.model;

import junit.framework.TestCase;

/**
 * Tests Mailroom.
 *
 * @author Colin Fahmy
 * @version Nov 18, 2026
 */
public class MailroomTest extends TestCase
{
    private Mailroom mailroom;


    /**
     * Creates a mailroom with sample packages.
     */
    public void setUp()
    {
        mailroom = new Mailroom();

        mailroom.addPackage(
            new Package("P1", "Alice", PackageStatus.READY));

        mailroom.addPackage(
            new Package("P2", "Bob", PackageStatus.READY));

        mailroom.addPackage(
            new Package("P3", "Cara", PackageStatus.READY));

        mailroom.addPackage(
            new Package("P4", "Dan", PackageStatus.NOT_READY));
    }


    /**
     * Tests adding a package.
     */
    public void testAddPackage()
    {
        mailroom.addPackage(
            new Package("P5", "Eve", PackageStatus.READY));

        CheckInResult result =
            mailroom.checkIn("Eve", "P5");

        assertEquals(CheckInStatus.ADDED, result.getStatus());
    }


    /**
     * Tests adding null.
     */
    public void testAddPackageNull()
    {
        try
        {
            mailroom.addPackage(null);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(0, mailroom.getQueueSize());
        }
    }


    /**
     * Tests duplicate package ID.
     */
    public void testDuplicatePackage()
    {
        try
        {
            mailroom.addPackage(
                new Package("P1", "Other", PackageStatus.READY));
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(0, mailroom.getQueueSize());
        }
    }


    /**
     * Tests normal check-in.
     */
    public void testCheckIn()
    {
        CheckInResult result =
            mailroom.checkIn("Alice", "P1");

        assertEquals(CheckInStatus.ADDED, result.getStatus());
        assertEquals(0, result.getStudentsAhead());
        assertEquals(1, mailroom.getQueueSize());
    }


    /**
     * Tests priority check-in.
     */
    public void testPriorityCheckIn()
    {
        mailroom.checkIn("Alice", "P1");
        mailroom.checkIn("Bob", "P2");

        CheckInResult result =
            mailroom.checkIn("Cara", "P3", true);

        assertEquals(CheckInStatus.ADDED, result.getStatus());
        assertEquals(0, result.getStudentsAhead());
        assertEquals("Cara",
            mailroom.getWaitingList()
                .get(0)
                .getPackage()
                .getStudentName());
    }


    /**
     * Tests priority order.
     */
    public void testPriorityOrder()
    {
        mailroom.checkIn("Alice", "P1");
        mailroom.checkIn("Bob", "P2", true);
        mailroom.checkIn("Cara", "P3", true);

        assertEquals("Bob",
            mailroom.processNext()
                .getPackage()
                .getStudentName());

        assertEquals("Cara",
            mailroom.processNext()
                .getPackage()
                .getStudentName());

        assertEquals("Alice",
            mailroom.processNext()
                .getPackage()
                .getStudentName());
    }


    /**
     * Tests duplicate check-in.
     */
    public void testAlreadyWaiting()
    {
        mailroom.checkIn("Alice", "P1");

        CheckInResult result =
            mailroom.checkIn("Alice", "P1");

        assertEquals(
            CheckInStatus.ALREADY_WAITING,
            result.getStatus());

        assertEquals(1, mailroom.getQueueSize());
    }


    /**
     * Tests package that is not ready.
     */
    public void testNotReady()
    {
        CheckInResult result =
            mailroom.checkIn("Dan", "P4");

        assertEquals(
            CheckInStatus.NOT_READY,
            result.getStatus());

        assertEquals(0, mailroom.getQueueSize());
    }


    /**
     * Tests wrong student name.
     */
    public void testWrongStudent()
    {
        CheckInResult result =
            mailroom.checkIn("Wrong", "P1");

        assertEquals(
            CheckInStatus.NO_PACKAGE,
            result.getStatus());
    }


    /**
     * Tests nonexistent package.
     */
    public void testNoPackage()
    {
        CheckInResult result =
            mailroom.checkIn("Alex", "NOPE");

        assertEquals(
            CheckInStatus.NO_PACKAGE,
            result.getStatus());
    }


    /**
     * Tests blank check-in input.
     */
    public void testBlankCheckIn()
    {
        try
        {
            mailroom.checkIn("", "P1");
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(0, mailroom.getQueueSize());
        }
    }


    /**
     * Tests null check-in input.
     */
    public void testNullCheckIn()
    {
        try
        {
            mailroom.checkIn(null, "P1");
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(0, mailroom.getQueueSize());
        }
    }


    /**
     * Tests processing the next request.
     */
    public void testProcessNext()
    {
        mailroom.checkIn("Alice", "P1");

        PickupRequest request =
            mailroom.processNext();

        assertEquals(
            "Alice",
            request.getPackage().getStudentName());

        assertEquals(
            PackageStatus.PICKED_UP,
            request.getPackage().getStatus());

        assertEquals(0, mailroom.getQueueSize());
    }


    /**
     * Tests processing an empty queue.
     */
    public void testProcessNextEmpty()
    {
        assertNull(mailroom.processNext());
    }


    /**
     * Tests waiting list.
     */
    public void testGetWaitingList()
    {
        assertTrue(mailroom.getWaitingList().isEmpty());

        mailroom.checkIn("Alice", "P1");
        mailroom.checkIn("Bob", "P2");

        assertEquals(2, mailroom.getWaitingList().size());
        assertEquals(
            "Alice",
            mailroom.getWaitingList()
                .get(0)
                .getPackage()
                .getStudentName());
    }


    /**
     * Tests queue size.
     */
    public void testGetQueueSize()
    {
        assertEquals(0, mailroom.getQueueSize());

        mailroom.checkIn("Alice", "P1");

        assertEquals(1, mailroom.getQueueSize());
    }


    /**
     * Tests getting student position.
     */
    public void testGetPosition()
    {
        mailroom.checkIn("Alice", "P1");
        mailroom.checkIn("Bob", "P2");

        assertEquals(0, mailroom.getPosition("Alice"));
        assertEquals(1, mailroom.getPosition("Bob"));
        assertEquals(-1, mailroom.getPosition("Nobody"));
    }


    /**
     * Tests bad input for getPosition.
     */
    public void testGetPositionBlank()
    {
        try
        {
            mailroom.getPosition("");
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(0, mailroom.getQueueSize());
        }
    }


    /**
     * Tests marking a package ready.
     */
    public void testMarkReady()
    {
        assertTrue(mailroom.markReady("P4"));

        CheckInResult result =
            mailroom.checkIn("Dan", "P4");

        assertEquals(CheckInStatus.ADDED, result.getStatus());
    }


    /**
     * Tests markReady with nonexistent package.
     */
    public void testMarkReadyMissing()
    {
        assertFalse(mailroom.markReady("NONE"));
    }


    /**
     * Tests invalid markReady input.
     */
    public void testMarkReadyBlank()
    {
        try
        {
            mailroom.markReady("");
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(0, mailroom.getQueueSize());
        }
    }


    /**
     * Tests checking in after pickup.
     */
    public void testAlreadyPickedUp()
    {
        mailroom.checkIn("Alice", "P1");
        mailroom.processNext();

        CheckInResult result =
            mailroom.checkIn("Alice", "P1");

        assertEquals(
            CheckInStatus.ALREADY_PICKED_UP,
            result.getStatus());
    }
}