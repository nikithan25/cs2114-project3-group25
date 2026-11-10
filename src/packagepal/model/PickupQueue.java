package packagepal.model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A first-in, first-out queue backed by a singly linked list.
 * It knows nothing about students or packages.
 *
 * @param <T> the type of item stored in the queue
 * @author Nikitha Nivas
 * @version 2026.11.09
 */

public class PickupQueue<T> {
    
    /**
     * One link in the chain. Each node holds an item and
     * points to the node behind it.
     */
    private static class Node<T> {
        private T data;
        private Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node<T> head; // front of the line (next to be served)
    private Node<T> tail; // back of the line (most recently added)
    private int size;
    
    // ----------------------------------------------------------
    /**
     * Create a new PickupQueue object.
     */
    public PickupQueue() {
        head = null;
        tail = null;
        size = 0;
    }
    
    
    // ----------------------------------------------------------
    /**
     * Adds a new item to the back of queue
     * @param item the item to add
     * @throws IllegalArgumentException if item is null
     */
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot enqueue a null item");
        }
        
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            head = newNode;
        }
        else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }
    
    // ----------------------------------------------------------
    /**
     * removes and returns the front item from 
     * the queue
     * @return the front item
     * @throws NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from an empty queue");
        }
        T item = head.data;
        head = head.next;
        if (head == null) {
            tail = null; 
        }
        size--;
        return item;  
    }
    
    // ----------------------------------------------------------
    /**
     * Looks and returns the item at the front
     * of the queue without removing it
     * @return the first item
     * @throws NoSuchElementException if the queue is empty
     * 
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot look at an empty queue");
        }
        return head.data;
    }
    
    // ----------------------------------------------------------
    /**
     * Shows whether the queue is empty or not
     * @return whether the queue is empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    // ----------------------------------------------------------
    /**
     * Returns how many items are in the queue
     * @return the size of the queue
     */
    public int size() {
        return size;
    }
    
    // ----------------------------------------------------------
    /**
     * Returns a copy of the items from front to 
     * back, for display.
     * Changing the returned list does not affect the queue.
     * @return a new list of the items in order
     */
    public ArrayList<T> toList() {
        
        ArrayList<T> list = new ArrayList<>(size);
        Node<T> current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
        
    }

}


