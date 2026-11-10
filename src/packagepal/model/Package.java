package packagepal.model;
// -------------------------------------------------------------------------
/**
 *  The Package class gets the package, package id, 
 *  student name, package status, and it also changes
 *  the package status accordingly.
 * 
 *  @author nikithanivas
 *  @version Nov 9, 2026
 */
public class Package
{
    //~ Fields ................
    private String packageID;
    private String studentName;
    PackageStatus status;

    // ----------------------------------------------------------
    /**
     * Create a new Package object.
     * @param packageID
     * @param studentName
     * @param status
     * @throws IllegalArgumentException if id, name, and status are null/blank
     */
    //~ Constructors ..........
    public Package(String id, String name, PackageStatus status) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Package ID cannot be null or blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be null or blank");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.packageID = id;
        this.studentName = name;
        this.status = status;
    }
    
    
    //~Public  Methods .................
    // ----------------------------------------------------------
    /**
     * getter method for packageID
     * @return the package id
     */
    
    public String getPackageID() {
        return packageID;
    }
    // ----------------------------------------------------------
    /**
     * getter method for studentName
     * @return the student's name
     */
    public String getStudentName() {
        return studentName;
    }
    
    // ----------------------------------------------------------
    /**
     * getter method for status
     * @return the package status 
     */
    public PackageStatus getStatus() {
        return status;
    }
    
    // ----------------------------------------------------------
    /**
     * sets the package status
     * @param status
     */
    public void setStatus(PackageStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
        
    }
    

}
