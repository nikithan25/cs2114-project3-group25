package packagepal.model;
// -------------------------------------------------------------------------
/**
 * Represents a student who has checked in and 
 * is waiting in line.
 * Holds a reference to the student's Package, 
 * so the Mailroom can
 * mark it PICKED_UP when the request is processed.
 * 
 *  @author nikithanivas
 *  @version Nov 9, 2026
 */
public class PickupRequest
{
    //~ Fields ..............
    private final Package pkg;
    private final boolean isUrgent;
    

    // ----------------------------------------------------------
    /**
     * Create a new PickupRequest object.
     * @param pkg the package being requested 
     */
    //~ Constructors ........
    public PickupRequest(Package pkg) {
        this(pkg, false);
    }
    
    // ----------------------------------------------------------
    /**
     * Create a new PickupRequest object with a priority level. 
     * @param pkg the package being requested
     * @param urgent true of this package is a priority
     * @throws IllegalArgumentException if package is null
     */
    public PickupRequest (Package pkg, boolean urgent) {
        if (pkg == null) {
            throw new IllegalArgumentException("package cannot be null");
        }
        this.pkg = pkg;
        this.isUrgent = urgent;
    }
    
    //~Public  Methods ......
    // ----------------------------------------------------------
    /**
     * getter method for package 
     * @return the package for the request 
     */
    public Package getPackage() {
        return pkg;
    }
    
    // ----------------------------------------------------------
    /**
     * getter method for isUrgent
     * @return whether the package is urgent or not
     */
    public boolean getIsUrgent() {
        return isUrgent;
    }

}
