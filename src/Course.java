import java.util.ArrayList;

public class Course {

    // attributes
    private String iD;
    private String name;
    private int currentNumSeats;
    private int totalNumSeats;
    private ArrayList<String> preReqs;

    // constants

    // constructors
    public Course() {
        iD = "N/A";
        name = "N/A";
        currentNumSeats = 0;
        totalNumSeats = 0;
        preReqs = new ArrayList<String>();
    }

    public Course(String tempID, String tempName, int tempCurrent, int tempTotal, ArrayList<String> tempReqs) {
        iD = tempID;
        name = tempName;
        currentNumSeats = tempCurrent;
        totalNumSeats = tempTotal;
        preReqs = tempReqs;
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
        return preReqs;
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
        preReqs = newPreeqs;
    }

    // additional methods
    public boolean classFull() {
        return currentNumSeats / totalNumSeats == 1;
    }

    public boolean preReqsTaken(ArrayList<String> oldCourses) {
        boolean noPrereqs = false;

        for (String oldCourse : oldCourses) {
            if (noPrereqs) {
                return false;
            }

            noPrereqs = true;
            for (int i = 0; i < preReqs.size(); i++) {
                if (oldCourse != preReqs.get(i))
                    noPrereqs = false;
            }
        }
        return true;
    }

    // 2String
    public String toString() {
        String course = iD + "/t" + name + "/t" + currentNumSeats + "/" + totalNumSeats + "/t";
        for (String singlePrereq : preReqs) {
            course += singlePrereq + ",";
        }
        return course;
    }
}
