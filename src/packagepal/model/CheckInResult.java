package packagepal.model;
// -------------------------------------------------------------------------
/**
 *  this class checks the package status, and also shows the 
 *  number of students ahead of a particular student
 * 
 *  @author nikithanivas
 *  @version Nov 9, 2026
 */
public class CheckInResult
{
    //~ Fields ............................................................
    private final CheckInStatus status;
    private final int studentsAhead;
    
    // ----------------------------------------------------------
    /**
     * Create a new CheckInResult object.
     * @param status the status of the package
     * @param studentsAhead the number of students ahead of a particular student
     */
    //~ Constructors ..........................................................
    public CheckInResult(CheckInStatus status, int studentsAhead) {
        if (status == null) {
            throw new IllegalArgumentException("The status cannot be null");
        }
        this.status = status;
        this.studentsAhead = studentsAhead;
    }
    
    // ----------------------------------------------------------
    /**
     * getter method for status 
     * @return the status 
     */
    //~Public  Methods ........................................................
    public CheckInStatus getStatus() {
        return status;
    }
    // ----------------------------------------------------------
    /**
     * getter method for studentsAhead
     * @return the number of students ahead 
     */
    public int getStudentsAhead() {
        return studentsAhead;
    }

}
