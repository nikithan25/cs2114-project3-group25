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
    

    // ----------------------------------------------------------
    /**
     * Create a new PickupRequest object.
     * @param pkg the package being requested 
     * @throws IllegalArgumentException if package is null
     */
    //~ Constructors ........
    public PickupRequest (Package pkg) {
        if (pkg == null) {
            throw new IllegalArgumentException("package cannot be null");
        }
        this.pkg = pkg;
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

}
