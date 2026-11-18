package packagepal.model;

import junit.framework.TestCase;

/**
 * Tests PickupRequest.
 *
 * @author Colin Fahmy
 * @version Nov 18, 2026
 */
public class PickupRequestTest extends TestCase
{
    /**
     * Tests a normal request.
     */
    public void testNormalRequest()
    {
        Package pkg =
            new Package("P1", "Alex", PackageStatus.READY);

        PickupRequest request =
            new PickupRequest(pkg);

        assertEquals(pkg, request.getPackage());
        assertFalse(request.getIsUrgent());
    }


    /**
     * Tests an urgent request.
     */
    public void testUrgentRequest()
    {
        Package pkg =
            new Package("P1", "Alex", PackageStatus.READY);

        PickupRequest request =
            new PickupRequest(pkg, true);

        assertEquals(pkg, request.getPackage());
        assertTrue(request.getIsUrgent());
    }


    /**
     * Tests null package input.
     */
    public void testNullPackage()
    {
        try
        {
            new PickupRequest(null);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }
    }


    /**
     * Tests null package for urgent request.
     */
    public void testUrgentNullPackage()
    {
        try
        {
            new PickupRequest(null, true);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }
    }
}
