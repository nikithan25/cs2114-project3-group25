# cs2114-project3-group25


# How to Run

# Reflection
Instead of throwing an EmptyQueueException for dequeue() and peek() methods in PickupQueue class, we changed it to a NoSuchElementException. We did this because throwing an EmptyQueueException would have required creating a class for it. 

We added a new value called ALREADY_PICKED_UP to CheckInStatus, because there was no outcome for a student that checks in a package that's already picked up. 

We added a new method called markReady to the Mailroom class, because there wasn't anything in the Mailroom class that moved a package from NOT_READY to READY. 

# Next Steps

# Attribution and GenAI Note
