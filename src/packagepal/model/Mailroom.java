package packagepal.model;

import java.util.ArrayList;
import java.util.List;

public class Mailroom
{

//~Fields .................
ArrayList<Package> packages;
PickupQueue<PickupRequest> waitingQueue;

//~Constructor .................
// ----------------------------------------------------------
/**
 * Create a new Mailroom object.
 */
public Mailroom() {
    packages = new ArrayList<>();
    waitingQueue = new PickupQueue<>();
}

//~Public  Methods ........

   // ----------------------------------------------------------
   /**
   * This method adds a new package 
   * @param pkg the package to be added 
   */
    public void addPackage(Package pkg) {
        if (pkg == null) {
            throw new IllegalArgumentException("Package cannot be null");
        }
        if (findPackage(pkg.getPackageID()) != null) {
            throw new IllegalArgumentException(
                "A package with that package ID already exists"); 
        }
        packages.add(pkg);
    }
    
    /**
     * Checks a student in for pickup. Verifies the 
     * student has a package
     * under that ID, checks whether they 
     * are already waiting, checks
     * readiness, and adds a valid request 
     * to the back of the line.
     *
     * @param studentName the student checking in
     * @param packageID the package ID they are picking up
     * @return the outcome and the number of students ahead
     *         (-1 when the student was not placed in line)
     * @throws IllegalArgumentException if either argument is null or blank
     */
    public CheckInResult checkIn(String studentName, String packageID) {
        if (studentName == null || studentName.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be null or blank");
        }
        if (packageID == null || packageID.isBlank()) {
            throw new IllegalArgumentException("Package ID cannot be null or blank");
        }
        
        Package pkg = findPackage(packageID);
        if (pkg == null || !pkg.getStudentName().equalsIgnoreCase(studentName)) {
            return new CheckInResult(CheckInStatus.NO_PACKAGE, -1);
        }
        
        int position = positionOfPackage(packageID);
        
        if (position != -1) {
            return new CheckInResult(CheckInStatus.ALREADY_WAITING, position);
        }

        if (pkg.getStatus() == PackageStatus.NOT_READY) {
            return new CheckInResult(CheckInStatus.NOT_READY, -1);
        }

        if (pkg.getStatus() == PackageStatus.PICKED_UP) {
            return new CheckInResult(CheckInStatus.ALREADY_PICKED_UP, -1);
        }
        
        int studentsAhead = waitingQueue.size();
        waitingQueue.enqueue(new PickupRequest(pkg));
        return new CheckInResult(CheckInStatus.ADDED, studentsAhead);
    }
    
    /**
     * Serves the student at the front of the line and marks their
     * package as picked up.
     *
     * @return the request that was served, or null if no one is waiting
     */
    public PickupRequest processNext() {
        if (waitingQueue.isEmpty()) {
            return null;
        }
        PickupRequest request = waitingQueue.dequeue();
        request.getPackage().setStatus(PackageStatus.PICKED_UP);
        return request;
    }


    /**
     * Returns the students waiting, front of the line first.
     * Changing the returned list does not affect the line.
     *
     * @return a copy of the waiting requests in order
     */
    public List<PickupRequest> getWaitingList() {
        return waitingQueue.toList();
    }


    /**
     * Returns the number of students waiting in line.
     *
     * @return the number of waiting students
     */
    public int getQueueSize() {
        return waitingQueue.size();
    }


    /**
     * Finds how many students are ahead of the given student.
     * Name matching ignores case, the same as checkIn.
     *
     * @param studentName the student to look for
     * @return the number of students ahead, or -1 if they are not in line
     * @throws IllegalArgumentException if studentName is null or blank
     */
    public int getPosition(String studentName) {
        if (studentName == null || studentName.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be null or blank");
        }
        List<PickupRequest> line = waitingQueue.toList();
        for (int i = 0; i < line.size(); i++) {
            if (line.get(i).getPackage().getStudentName()
                .equalsIgnoreCase(studentName)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Marks a package as ready for pickup. Only a package that is
     * currently NOT_READY can be marked ready.
     *
     * @param packageID the package to mark ready
     * @return true if the package was marked ready, false if no such
     *         package exists or it is not currently NOT_READY
     * @throws IllegalArgumentException if packageID is null or blank
     */
    public boolean markReady(String packageID) {
        if (packageID == null || packageID.isBlank()) {
            throw new IllegalArgumentException("Package ID cannot be null or blank");
        }
        Package pkg = findPackage(packageID);
        if (pkg == null || pkg.getStatus() != PackageStatus.NOT_READY) {
            return false;
        }
        pkg.setStatus(PackageStatus.READY);
        return true;
    }
    
    
    
    /**
     * Finds the package with the given ID.
     *
     * @param packageID the ID to search for
     * @return the matching package, or null if none exists
     */
    private Package findPackage(String packageID) {
        for (Package p : packages) {
            if (p.getPackageID().equals(packageID)) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Finds how many requests are ahead of the request for a package.
     *
     * @param packageID the package ID to look for in the line
     * @return the number of requests ahead of it, or -1 if it is not in line
     */
    private int positionOfPackage(String packageID) {
        List<PickupRequest> line = waitingQueue.toList();
        for (int i = 0; i < line.size(); i++) {
            if (line.get(i).getPackage().getPackageID().equals(packageID)) {
                return i;
            }
        }
        return -1;
    }

}
