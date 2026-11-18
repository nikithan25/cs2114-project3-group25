package packagepal.model;

import java.util.NoSuchElementException;
import junit.framework.TestCase;

/**
 * Tests PickupQueue.
 *
 * @author Colin Fahmy
 * @version Nov 18, 2026
 */
public class PickupQueueTest extends TestCase
{
    private PickupQueue<String> queue;


    /**
     * Creates a new queue before each test.
     */
    public void setUp()
    {
        queue = new PickupQueue<String>();
    }


    /**
     * Tests normal enqueue behavior.
     */
    public void testEnqueue()
    {
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals(2, queue.size());
        assertEquals("A", queue.peek());
    }


    /**
     * Tests enqueue with null.
     */
    public void testEnqueueNull()
    {
        try
        {
            queue.enqueue(null);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(queue.isEmpty());
        }
    }


    /**
     * Tests normal dequeue behavior.
     */
    public void testDequeue()
    {
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals("A", queue.dequeue());
        assertEquals("B", queue.peek());
        assertEquals(1, queue.size());
    }


    /**
     * Tests dequeue on an empty queue.
     */
    public void testDequeueEmpty()
    {
        try
        {
            queue.dequeue();
            fail();
        }
        catch (NoSuchElementException e)
        {
            assertTrue(queue.isEmpty());
        }
    }


    /**
     * Tests peek.
     */
    public void testPeek()
    {
        queue.enqueue("A");

        assertEquals("A", queue.peek());
        assertEquals(1, queue.size());
    }


    /**
     * Tests peek on an empty queue.
     */
    public void testPeekEmpty()
    {
        try
        {
            queue.peek();
            fail();
        }
        catch (NoSuchElementException e)
        {
            assertTrue(queue.isEmpty());
        }
    }


    /**
     * Tests isEmpty.
     */
    public void testIsEmpty()
    {
        assertTrue(queue.isEmpty());

        queue.enqueue("A");

        assertFalse(queue.isEmpty());

        queue.dequeue();

        assertTrue(queue.isEmpty());
    }


    /**
     * Tests size.
     */
    public void testSize()
    {
        assertEquals(0, queue.size());

        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals(2, queue.size());
    }


    /**
     * Tests conversion to a list.
     */
    public void testToList()
    {
        assertTrue(queue.toList().isEmpty());

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        assertEquals("[A, B, C]", queue.toList().toString());
    }


    /**
     * Tests priority insertion.
     */
    public void testEnqueuePriority()
    {
        queue.enqueue("N1");
        queue.enqueue("N2");
        queue.enqueuePriority("U1");

        assertEquals("[U1, N1, N2]", queue.toList().toString());
    }


    /**
     * Tests multiple priority requests.
     */
    public void testMultiplePriority()
    {
        queue.enqueue("N1");
        queue.enqueuePriority("U1");
        queue.enqueuePriority("U2");
        queue.enqueue("N2");

        assertEquals(
            "[U1, U2, N1, N2]",
            queue.toList().toString());
    }


    /**
     * Tests null priority insertion.
     */
    public void testPriorityNull()
    {
        try
        {
            queue.enqueuePriority(null);
            fail();
        }
        catch (IllegalArgumentException e)
        {
            assertTrue(queue.isEmpty());
        }
    }


    /**
     * Tests interleaved enqueue and dequeue operations.
     */
    public void testInterleavedOrder()
    {
        queue.enqueue("A");
        queue.enqueue("B");

        assertEquals("A", queue.dequeue());

        queue.enqueue("C");

        assertEquals("B", queue.dequeue());
        assertEquals("C", queue.dequeue());

        assertTrue(queue.isEmpty());
    }


    /**
     * Tests interleaved priority queue behavior.
     */
    public void testPriorityInterleavedOrder()
    {
        queue.enqueue("N1");
        queue.enqueue("N2");
        queue.enqueuePriority("U1");

        assertEquals("U1", queue.dequeue());

        queue.enqueuePriority("U2");

        assertEquals("U2", queue.dequeue());
        assertEquals("N1", queue.dequeue());
        assertEquals("N2", queue.dequeue());

        assertTrue(queue.isEmpty());
    }
}