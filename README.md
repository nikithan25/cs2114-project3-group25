# cs2114-project3-group25

**1. How to Run**

To run the model independently of the view, run PackagePalModelDriver.java as a Java application. The driver creates a Mailroom, adds package data, checks a student into the pickup queue, processes the next request, and prints the results without using the controller or view.

To run the full prototype, run PackagePalApp.java as a Java application. The console menu allows the user to check in for a package, view the waiting queue, process the next pickup, and exit the program.

**2. Testing**

For this prototype, we manually tested the main MVP path through PackagePalApp. We successfully checked in a student with a READY package, viewed the student in the waiting queue, processed the pickup, and confirmed that processing an empty queue did not crash the program. We also tested a student with a NOT_READY package and confirmed that the student was rejected without being added to the queue.

The model can also be exercised independently through PackagePalModelDriver. A complete JUnit test suite has not been finished yet and will be added for Deliverable 4.

**3. Reflection**
What Changed from Spec:
Instead of throwing an EmptyQueueException for dequeue() and peek() methods in PickupQueue class, we changed it to Java's built-in NoSuchElementException. We did this because NoSuchElementException is the standard exception for removing or looking through an empty collection, and also throwing an EmptyQueueException would have required creating a class for it. We also added a new value called ALREADY_PICKED_UP to CheckInStatus. Our scope covered packages that weren’t ready and students with no package, but not a student checking in for a package that had already been picked up, so the model had no correct way to report that case. Plus, we added a new method called markReady to the Mailroom class. Our MVP checks whether a package is ready, but nothing in the spec actually moved a package from NOT_READY to READY, so a package could never become available. So, we added markReady() to Mailroom to handle that transition.

Model Stands Alone: 
Yes, the model stands alone. We didn’t have to move anything across the model/view line to get the program running. The model classes don’t import anything from the view and don’t read input or print output. Instead, methods return values like CheckInStatus and the view decides what message to display.

Unknowns: 
Out of the things listed in our scope, we figured out how to implement the singly linked queue, separate the model and view, and preventing multiple requests for the same package. But, we still don't know how to search and cancel a specific request without changing the order.

What doesn't Work Yet: 
The full JUnit test suite is not complete yet. Search, cancellation of a specific pickup request, pickup history, and priority handling have also not been implemented. The current MVP path works, but more bad-input cases still need to be covered with automated tests.

**4. Next Steps**
What is Left to finish the MVP:
Our first priority is to add JUnit tests for the current Queue and model behavior and make sure the bad-input cases from our specification are handled correctly. After that, we will fix any issues found during testing before adding the priority-handling feature for Deliverable 4.

Change Feature: 
Our change feature will be priority handling. Some things that are predicted the change are the PickupQueue class, since it needs a way to place priority requests ahead of normal ones, and the PickupRequest or Package class needs a priority field. Also, the view needs a way to mark the request as urgent and to show priority in the queue display. The thing that is predicted to stay the same is Mailroom’s check-in and markReady() logic, and CheckInStatus. They ask the queue for the next request and shouldn't rely on how the queue decided which one is next.

Stretch Goals: 
We are attempting to do our stretch goals of search and filtering and pickup history. But, we are dropping package location tracking, pickup notifications, and wait-time stats. 

New Tests Needed: 
For Deliverable 4, we will add at least one normal and one bad-input test for each core Queue and model method. We will also add tests for the priority feature, including adding a normal request, adding a priority request ahead of normal requests, handling multiple priority requests, processing requests in the correct order, and invalid input related to the new feature.

Who Does What:
Finish MVP features and bad-input handling: Colin 
Priority handling (model: PickupQueue, priority field): Nikitha
Priority handling (view and controller): Zenoor 
Search, cancel, and pickup history: Zenoor 
New tests: Colin 
README updates: Nikitha 


**5. Attribution and GenAI Note**
Package.java — Nikitha — Wrote model class; GenAI-assisted; group reviewed.

PackageStatus.java — Nikitha — Wrote enum; GenAI-assisted; group reviewed.

PickupRequest.java — Nikitha — Wrote model class; GenAI-assisted; group reviewed.

CheckInResult.java — Nikitha — Wrote result class; GenAI-assisted; group reviewed.

CheckInStatus.java — Nikitha — Wrote and revised enum; GenAI-assisted; group reviewed.

PickupQueue.java — Nikitha — Wrote queue implementation; GenAI-assisted; group reviewed.

Mailroom.java — Nikitha — Wrote model logic and validation; GenAI-assisted; group reviewed.

ConsoleView.java — Zenoor — Wrote console view; GenAI-assisted; group reviewed.

PackagePalController.java — Colin — Wrote controller and integrated model and view; GenAI-assisted.

PackagePalApp.java — Colin — Wrote application entry point and tested MVP flow; GenAI-assisted.

PackagePalModelDriver.java — Colin — Wrote model-only driver; GenAI-assisted.

README.md — Group — Drafted and revised collaboratively; GenAI-assisted.

GenAI Note

All three group members used ChatGPT during the design and prototype process. GenAI was used to help review class responsibilities, identify inconsistencies between the specification and implementation, suggest validation and testing approaches, check the model/view boundary, and assist with implementation and README organization. Each group member reviewed and modified the suggestions to fit the group's design and code, and the group made the final decisions about what to implement.