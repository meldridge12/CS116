public class Course {

    // attributes
    private String iD;
    private String name;
    private int currentNumSeats;
    private int totalNumSeats;
    private String[] prerequisites;

    // constants

    // constructors
    public Course() {
        iD = "This course has no ID.";
        name = "This course has no name.";
        currentNumSeats = 0;
        totalNumSeats = 0;
        prerequisites = new String[0];
    }

    public Course(String tempID, String tempName, int tempCurrent, int tempTotal, String[] tempReqs) {
        iD = tempID;
        name = tempName;
        currentNumSeats = tempCurrent;
        totalNumSeats = tempTotal;
        prerequisites = tempReqs;
    }

    // accessors

}
