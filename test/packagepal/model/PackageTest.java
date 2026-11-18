package packagepal.model;

import junit.framework.TestCase;

/**
 * Tests Package.
 *
 * @author Colin Fahmy
 * @version Nov 18, 2026
 */
public class PackageTest extends TestCase
{
    /**
     * Tests normal package creation and getters.
     */
    public void testPackage()
    {
        Package pkg =
            new Package("P1", "Alex", PackageStatus.READY);

        assertEquals("P1", pkg.getPackageID());
        assertEquals("Alex", pkg.getStudentName());
        assertEquals(PackageStatus.READY, pkg.getStatus());
    }


    /**
     * Tests changing package status.
     */
    public void testSetStatus()
    {
        Package pkg =
            new Package("P1", "Alex", PackageStatus.READY);

        pkg.setStatus(PackageStatus.PICKED_UP);

        assertEquals(PackageStatus.PICKED_UP, pkg.getStatus());
    }


    /**
     * Tests null package ID.
     */
    public void testNullId()
    {
        try
        {
            new Package(null, "Alex", PackageStatus.READY);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }
    }


    /**
     * Tests blank student name.
     */
    public void testBlankName()
    {
        try
        {
            new Package("P1", "", PackageStatus.READY);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }
    }


    /**
     * Tests null package status.
     */
    public void testNullStatus()
    {
        try
        {
            new Package("P1", "Alex", null);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }
    }


    /**
     * Tests setting status to null.
     */
    public void testSetStatusNull()
    {
        Package pkg =
            new Package("P1", "Alex", PackageStatus.READY);

        try
        {
            pkg.setStatus(null);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertEquals(PackageStatus.READY, pkg.getStatus());
        }
    }
}