package packagepal.model;

// -------------------------------------------------------------------------
/**
 * A test driver for priority handling in the model.
 * Tests PickupQueue.enqueuePriority, the urgent flag in PickupRequest,
 * and urgent check-ins in Mailroom. Prints PASS or FAIL for each
 * check and a summary at the end.
 *
 * @author Nikitha Nivas
 * @version 2026.11.17
 */
public class PriorityTestDriver
{
    private static int passed = 0;
    private static int failed = 0;

    /**
     * Runs all the priority handling tests.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("===== PickupQueue priority tests =====");
        testQueueUrgentIntoEmpty();
        testQueueUrgentAheadOfNormal();
        testQueueUrgentBehindEarlierUrgent();
        testQueueAllUrgent();
        testQueueLastPriorityReset();
        testQueueAfterEmptied();
        testQueueNull();

        System.out.println();
        System.out.println("===== PickupRequest tests =====");
        testRequestUrgentFlag();
        testRequestNullPackage();

        System.out.println();
        System.out.println("===== Mailroom priority tests =====");
        testMailroomUrgentGoesToFront();
        testMailroomUrgentBehindUrgent();
        testMailroomPositionShifts();
        testMailroomProcessNextUrgentFirst();
        testMailroomOldCheckInGoesToBack();
        testMailroomUrgentStillValidated();

        System.out.println();
        System.out.println("===== " + passed + " passed, " + failed
            + " failed =====");
    }

    // ----------------------------------------------------------
    /**
     * Prints PASS or FAIL for one check.
     *
     * @param name what is being checked
     * @param condition true if the check passed
     */
    private static void check(String name, boolean condition) {
        if (condition) {
            System.out.println("PASS: " + name);
            passed++;
        }
        else {
            System.out.println("FAIL: " + name);
            failed++;
        }
    }

    // ----------------------------------------------------------
    /**
     * Makes a mailroom with four ready packages and one that is
     * not ready.
     *
     * @return the new mailroom
     */
    private static Mailroom makeMailroom() {
        Mailroom mailroom = new Mailroom();
        mailroom.addPackage(new Package("P1", "Alice", PackageStatus.READY));
        mailroom.addPackage(new Package("P2", "Bob", PackageStatus.READY));
        mailroom.addPackage(new Package("P3", "Cara", PackageStatus.READY));
        mailroom.addPackage(new Package("P4", "Dan", PackageStatus.READY));
        mailroom.addPackage(
            new Package("P5", "Eve", PackageStatus.NOT_READY));
        return mailroom;
    }

    // ---------------- PickupQueue ----------------

    /**
     * Urgent item into an empty queue, then a normal item behind it.
     */
    private static void testQueueUrgentIntoEmpty() {
        PickupQueue<String> q = new PickupQueue<>();
        q.enqueuePriority("U1");
        q.enqueue("N1");
        check("urgent into empty queue, normal goes behind it",
            q.size() == 2 && q.toList().toString().equals("[U1, N1]"));
    }

    /**
     * Urgent item goes ahead of normal items.
     */
    private static void testQueueUrgentAheadOfNormal() {
        PickupQueue<String> q = new PickupQueue<>();
        q.enqueue("N1");
        q.enqueue("N2");
        q.enqueuePriority("U1");
        check("urgent goes ahead of normal items",
            q.toList().toString().equals("[U1, N1, N2]"));
    }

    /**
     * Urgent items keep their arrival order, ahead of normal items.
     */
    private static void testQueueUrgentBehindEarlierUrgent() {
        PickupQueue<String> q = new PickupQueue<>();
        q.enqueue("N1");
        q.enqueuePriority("U1");
        q.enqueuePriority("U2");
        q.enqueue("N2");
        check("urgent goes behind earlier urgent",
            q.toList().toString().equals("[U1, U2, N1, N2]"));
    }

    /**
     * All-urgent queue, then a normal item goes to the back.
     */
    private static void testQueueAllUrgent() {
        PickupQueue<String> q = new PickupQueue<>();
        q.enqueuePriority("U1");
        q.enqueuePriority("U2");
        q.enqueue("N1");
        check("all-urgent queue, normal goes to the back",
            q.toList().toString().equals("[U1, U2, N1]"));
    }

    /**
     * After the urgent items are dequeued, a new urgent item goes
     * to the front.
     */
    private static void testQueueLastPriorityReset() {
        PickupQueue<String> q = new PickupQueue<>();
        q.enqueuePriority("U1");
        q.enqueue("N1");
        String first = q.dequeue();
        q.enqueuePriority("U2");
        check("lastPriority resets after urgent items are dequeued",
            first.equals("U1")
            && q.toList().toString().equals("[U2, N1]"));
    }

    /**
     * Queue still works after being emptied.
     */
    private static void testQueueAfterEmptied() {
        PickupQueue<String> q = new PickupQueue<>();
        q.enqueuePriority("U1");
        q.dequeue();
        boolean wasEmpty = q.isEmpty();
        q.enqueuePriority("U2");
        q.enqueue("N1");
        check("queue works after being emptied",
            wasEmpty && q.dequeue().equals("U2")
            && q.dequeue().equals("N1") && q.isEmpty());
    }

    /**
     * enqueuePriority rejects null.
     */
    private static void testQueueNull() {
        PickupQueue<String> q = new PickupQueue<>();
        boolean threw = false;
        try {
            q.enqueuePriority(null);
        }
        catch (IllegalArgumentException e) {
            threw = true;
        }
        check("enqueuePriority(null) throws", threw && q.size() == 0);
    }

    // ---------------- PickupRequest ----------------

    /**
     * Default is not urgent, and the urgent constructor stores true.
     */
    private static void testRequestUrgentFlag() {
        Package pkg = new Package("P1", "Alice", PackageStatus.READY);
        PickupRequest normal = new PickupRequest(pkg);
        PickupRequest urgent = new PickupRequest(pkg, true);
        check("one-argument constructor is not urgent",
            !normal.getIsUrgent());
        check("two-argument constructor stores urgent",
            urgent.getIsUrgent() && urgent.getPackage() == pkg);
    }

    /**
     * The urgent constructor rejects a null package.
     */
    private static void testRequestNullPackage() {
        boolean threw = false;
        try {
            new PickupRequest(null, true);
        }
        catch (IllegalArgumentException e) {
            threw = true;
        }
        check("null package throws", threw);
    }

    // ---------------- Mailroom ----------------

    /**
     * Urgent check-in goes to the front even with others waiting.
     */
    private static void testMailroomUrgentGoesToFront() {
        Mailroom m = makeMailroom();
        m.checkIn("Alice", "P1");
        m.checkIn("Bob", "P2");
        CheckInResult r = m.checkIn("Cara", "P3", true);
        check("urgent check-in is ADDED with 0 ahead",
            r.getStatus() == CheckInStatus.ADDED
            && r.getStudentsAhead() == 0 && m.getQueueSize() == 3);
    }

    /**
     * Second urgent check-in goes behind the first urgent one.
     */
    private static void testMailroomUrgentBehindUrgent() {
        Mailroom m = makeMailroom();
        m.checkIn("Alice", "P1");
        m.checkIn("Cara", "P3", true);
        CheckInResult r = m.checkIn("Dan", "P4", true);
        check("second urgent has 1 ahead, normal is now last",
            r.getStudentsAhead() == 1 && m.getPosition("Alice") == 2);
    }

    /**
     * A normal student's position goes up when an urgent student
     * checks in.
     */
    private static void testMailroomPositionShifts() {
        Mailroom m = makeMailroom();
        m.checkIn("Alice", "P1");
        m.checkIn("Bob", "P2");
        int before = m.getPosition("Bob");
        m.checkIn("Cara", "P3", true);
        int after = m.getPosition("Bob");
        check("normal student's position goes from 1 to 2",
            before == 1 && after == 2);
    }

    /**
     * processNext serves the urgent student first.
     */
    private static void testMailroomProcessNextUrgentFirst() {
        Mailroom m = makeMailroom();
        m.checkIn("Alice", "P1");
        m.checkIn("Cara", "P3", true);
        PickupRequest first = m.processNext();
        PickupRequest second = m.processNext();
        check("processNext serves urgent first",
            first.getPackage().getStudentName().equals("Cara")
            && first.getPackage().getStatus() == PackageStatus.PICKED_UP
            && second.getPackage().getStudentName().equals("Alice")
            && m.processNext() == null);
    }

    /**
     * The old two-argument checkIn still goes to the back.
     */
    private static void testMailroomOldCheckInGoesToBack() {
        Mailroom m = makeMailroom();
        m.checkIn("Cara", "P3", true);
        CheckInResult r = m.checkIn("Alice", "P1");
        check("old checkIn goes behind urgent",
            r.getStatus() == CheckInStatus.ADDED
            && r.getStudentsAhead() == 1);
    }

    /**
     * Urgent check-ins go through the same validation.
     */
    private static void testMailroomUrgentStillValidated() {
        Mailroom m = makeMailroom();
        CheckInResult notReady = m.checkIn("Eve", "P5", true);
        CheckInResult wrongName = m.checkIn("Alice", "P2", true);
        m.checkIn("Alice", "P1", true);
        CheckInResult again = m.checkIn("Alice", "P1", true);
        boolean threw = false;
        try {
            m.checkIn("", "P1", true);
        }
        catch (IllegalArgumentException e) {
            threw = true;
        }
        check("urgent NOT_READY is rejected",
            notReady.getStatus() == CheckInStatus.NOT_READY);
        check("urgent wrong name is NO_PACKAGE",
            wrongName.getStatus() == CheckInStatus.NO_PACKAGE);
        check("urgent duplicate is ALREADY_WAITING",
            again.getStatus() == CheckInStatus.ALREADY_WAITING
            && m.getQueueSize() == 1);
        check("urgent blank name throws", threw);
    }
}
