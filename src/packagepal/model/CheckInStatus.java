package packagepal.model;

/**
 * The possible outcomes of a student's check-in attempt.
 *
 * @author Nikitha Nivas
 * @version 2026.11.09
 */
public enum CheckInStatus {
    /** The student was added to the back of the 
     * pickup line. */
    ADDED,

    /** The student is already waiting in line for 
     * this package. */
    ALREADY_WAITING,

    /** The student has a package, but it is not 
     * ready for pickup yet. */
    NOT_READY,

    /** No package was found for this student and 
     * package ID. */
    NO_PACKAGE,
    
    /** The package was already picked up. */
    ALREADY_PICKED_UP
}
