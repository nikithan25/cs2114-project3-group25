# cs2114-project3-group25

**1. How to Run**

**2. Testing**

**3. Reflection**
What Changed from Spec:
Instead of throwing an EmptyQueueException for dequeue() and peek() methods in PickupQueue class, we changed it to Java's built-in NoSuchElementException. We did this because NoSuchElementException is the standard exception for removing or looking through an empty collection, and also throwing an EmptyQueueException would have required creating a class for it. We also added a new value called ALREADY_PICKED_UP to CheckInStatus. Our scope covered packages that weren’t ready and students with no package, but not a student checking in for a package that had already been picked up, so the model had no correct way to report that case. Plus, we added a new method called markReady to the Mailroom class. Our MVP checks whether a package is ready, but nothing in the spec actually moved a package from NOT_READY to READY, so a package could never become available. So, we added markReady() to Mailroom to handle that transition.

Model Stands Alone: 
Yes, the model stands alone. We didn’t have to move anything across the model/view line to get the program running. The model classes don’t import anything from the view and don’t read input or print output. Instead, methods return values like CheckInStatus and the view decides what message to display.

Unknowns: 
Out of the things listed in our scope, we figured out how to implement the singly linked queue, separate the model and view, and preventing multiple requests for the same package. But, we still don't know how to search and cancel a specific request without changing the order.

What doesn't Work Yet: 


**4. Next Steps**
What is Left to finish the MVP:

Change Feature: 
Our change feature will be priority handling. Some things that are predicted the change are the PickupQueue class, since it needs a way to place priority requests ahead of normal ones, and the PickupRequest or Package class needs a priority field. Also, the view needs a way to mark the request as urgent and to show priority in the queue display. The thing that is predicted to stay the same is Mailroom’s check-in and markReady() logic, and CheckInStatus. They ask the queue for the next request and shouldn't rely on how the queue decided which one is next.

Stretch Goals: 
We are attempting to do our stretch goals of search and filtering and pickup history. But, we are dropping package location tracking, pickup notifications, and wait-time stats. 

New Tests Needed: 

Who Does What:
Finish MVP features and bad-input handling: Colin 
Priority handling (model: PickupQueue, priority field): Nikitha
Priority handling (view and controller): Zenoor 
Search, cancel, and pickup history: Zenoor 
New tests: Colin 
README updates: Nikitha 


**5. Attribution and GenAI Note**

