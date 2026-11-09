package packagepal.model;

/**
 * The possible states of a package in the mailroom.
 *
 * @author Nikitha Nivas
 * @version 2026.11.09
 */
public enum PackageStatus {
    /** The package has been logged but is not ready 
     * for pickup yet. */
    NOT_READY,

    /** The package is ready and can be picked up. */
    READY,

    /** The package has been picked up by the student. */
    PICKED_UP
}
