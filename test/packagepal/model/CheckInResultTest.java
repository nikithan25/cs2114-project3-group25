package packagepal.model;

import junit.framework.TestCase;

/**
 * Tests CheckInResult.
 *
 * @author Colin Fahmy
 * @version Nov 18, 2026
 */
public class CheckInResultTest extends TestCase
{
    /**
     * Tests normal result creation.
     */
    public void testResult()
    {
        CheckInResult result =
            new CheckInResult(CheckInStatus.ADDED, 2);

        assertEquals(CheckInStatus.ADDED, result.getStatus());
        assertEquals(2, result.getStudentsAhead());
    }


    /**
     * Tests null status.
     */
    public void testNullStatus()
    {
        try
        {
            new CheckInResult(null, 0);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(true);
        }
    }
}
