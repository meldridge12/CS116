import java.util.ArrayList;

public class Course {

    // attributes
    private String iD;
    private String name;
    private int currentNumSeats;
    private int totalNumSeats;
    private ArrayList<String> prerequisites;

    // constants

    // constructors
    public Course() {
        iD = "N/A";
        name = "N/A";
        currentNumSeats = 0;
        totalNumSeats = 0;
        prerequisites = new ArrayList<String>();
    }

    public Course(String tempID, String tempName, int tempCurrent, int tempTotal, ArrayList<String> tempReqs) {
        iD = tempID;
        name = tempName;
        currentNumSeats = tempCurrent;
        totalNumSeats = tempTotal;
        prerequisites = tempReqs;
    }

    // accessors
    public String getID() {
        return iD;
    }

    public String getName() {
        return name;
    }

    public int getCurrentSeats() {
        return currentNumSeats;
    }

    public int getTotalSeats() {
        return totalNumSeats;
    }

    public ArrayList<String> getPrereqs() {
        return prerequisites;
    }

    // mutators
    public void setID(String newID) {
        iD = newID;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setCurrentSeats(int newCurrent) {
        currentNumSeats = newCurrent;
    }

    public void setTotalSeats(int newTotal) {
        totalNumSeats = newTotal;
    }

    public void setNewPrereqs(ArrayList<String> newPreeqs) {
        prerequisites = newPreeqs;
    }

    // additional methods

    // 2String
    public String toString() {
        String course = iD + "/t" + name + "/t" + currentNumSeats + "/" + totalNumSeats + "/t";
        for (String singlePrereq : prerequisites) {
            course += singlePrereq + ",";
        }
        return course;
    }
}
