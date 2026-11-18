# cs2114-project3-group25

**Deliverable 4**: 

**How to Run**

Run PackagePalApp.java as a Java application to start the full program.

To run the automated tests, run the JUnit test classes under test/packagepal.model. The main test classes are PickupQueueTest, MailroomTest, PackageTest, PickupRequestTest, and CheckInResultTest.

**1. Reflection**

**How the Design Absorbed the Change:**

Our feature was to add priority handling. So, to do that, we had to edit PickupRequest, PickupQueue, Mailroom, ConsoleView, and PackagePalController.. Comparing this to the next steps, the things that matched were that PickupRequest and PickupQueue needed to be changed to implement this priority handling feature, but one thing that surprised us was that Mailroom also needed to be changed. 

The change did not cross the model/view line. The priority logic stayed in the model: PickupRequest stores whether a request is urgent, PickupQueue decides where an urgent request goes with enqueuePriority, and Mailroom picks between enqueue and enqueuePriority. The view never decides the order of the line. It only passes along whether a student said their pickup is urgent.

In terms of the view having to learn new things about the model, there was very little things it had to learn. . To mark a request as urgent, the view and controller need to call the new checkIn(name, packageID, urgent) overload instead of the two-argument version. To show urgent requests in the queue display, the view can call getIsUrgent() on each request it gets from getWaitingList().

Also, the change did not ripple anywhere it shouldn't have. 

**What Changed from our D3 Plan:**

Field location: D3 said the priority field could go on PickupRequest or Package. We chose PickupRequest.

Re-checking in as urgent: A student who is already waiting and checks in again as urgent gets ALREADY_WAITING and stays where they are. We decided students shouldn’t be able to jump the line by checking in a second time.

Tests: We added actual JUnit test classes under test/packagepal.model. These cover the core Queue and model methods, bad-input cases, and the new priority feature. PickupQueueTest also tests order after interleaved enqueue and dequeue operations for the Exceeds requirement.


**What Doesn't Work Yet:**

We still haven't implemented our stretch goals of search and filtering and pickup history.


**2. Next Steps**

**What's left for the final build:**

1. Implement our stretch goals (search and filtering/pickup history)
2. Write test cases for any remaining bad inputs and fix anything that fails
3. Final README updates


**Stretch Goals:**

We are committing to search and filtering and to pickup history. We are dropping package location tracking, pickup notifications, and wait-time stats.

**3-Minute Demo:**

We will run PackagePalApp and check in two students with normal requests, then view the queue to show they’re in first-come, first-served order. Next, we will check in a third student as urgent and view the queue again to show the urgent student moved to the front. Then we will process the next pickup to show the urgent student is served first, followed by the normal students in order. We’ll finish with bad input: a blank name, a student with no package, a package that isn’t ready, and processing an empty queue, to show the program handles each case without crashing.


**Who does what:**

1. Implement the stretch goals: Nikitha
2. Write the remaining test cases: Colin
3. Final README updates: Zenoor


**3. Attribution & GenAI Note:**

**Attribution:**

PickupRequest.java — Nikitha — Wrote the new methods for priority handling feature; GenAI-assisted; group reviewed

PickupQueue.java — Nikitha — Wrote the new methods for priority handling feature; GenAI-assisted; group reviewed.

Mailroom.java — Nikitha — Wrote the new methods for priority handling feature; GenAI-assisted; group reviewed.

PriorityTestDriver.java - Nikitha - Wrote tests for the new priority methods; GenAI-assisted; group reviewed 

ConsoleView.java — Zenoor — Wrote the new methods for priority handling feature; group reviewed.

ConsoleViewTestDriver.java - Zenoor - Wrote tests for the new ConsoleView priority methods; GenAI assisted; group reviewed 

Package.java — Nikitha — Wrote original model class; GenAI-assisted; group reviewed.

PackageStatus.java — Nikitha — Wrote package status enum; GenAI-assisted; group reviewed.

CheckInResult.java — Nikitha — Wrote model result class; GenAI-assisted; group reviewed.

CheckInStatus.java — Nikitha — Wrote check-in status enum; GenAI-assisted; group reviewed.

PackagePalApp.java — Colin — Wrote application entry point and integration support; GenAI-assisted.

PackagePalModelDriver.java — Colin — Wrote model-only driver; GenAI-assisted.

PackagePalController.java — Colin — Wrote the new methods for priority handling feature; GenAI-assisted.

README.md — Group — Drafted and revised collaboratively; GenAI-assisted.

PickupQueueTest.java — Colin — Wrote JUnit tests for Queue behavior, bad input, priority order, and interleaved enqueue/dequeue operations; GenAI-assisted; group reviewed.

PackageTest.java — Colin — Wrote JUnit tests for package creation, getters, status changes, and invalid input; GenAI-assisted; group reviewed.

PickupRequestTest.java — Colin — Wrote JUnit tests for normal and urgent requests and invalid input; GenAI-assisted; group reviewed.

CheckInResultTest.java — Colin — Wrote JUnit tests for check-in results and invalid status input; GenAI-assisted; group reviewed.

MailroomTest.java — Colin — Wrote JUnit tests for check-in behavior, priority handling, processing, positions, markReady, and bad input; GenAI-assisted; group reviewed.

**GenAI Note:**

All three group members used ChatGPT to implement the new priority handling feature. GenAI was used to help review class responsibilities, identify inconsistencies between the specification and implementation, suggest validation and testing approaches, check the model/view boundary, and assist with implementation and README organization. Each group member reviewed and modified the suggestions to fit the group's design and code, and the group made the final decisions about what to implement.

