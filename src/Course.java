import java.util.ArrayList;

public class Course {

    // attributes
    private String id;
    private String name;
    private int currentNumSeats;
    private int totalNumSeats;
    private ArrayList<String> preReqs;

    // constants

    // constructors
    public Course(String id, String name, int currentNumSeats, int totalNumSeats, ArrayList<String> preReqs) {
        this.id = id;
        this.name = name;
        this.currentNumSeats = currentNumSeats;
        this.totalNumSeats = totalNumSeats;
        this.preReqs = preReqs;
    }

    // accessors
    public String getID() {
        return id;
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

    public ArrayList<String> getpreReqs() {
        return preReqs;
    }

    // mutators
    public void setID(String newID) {
        id = newID;
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

    public void setPreReqs(ArrayList<String> newPreReqs) {
        preReqs = newPreReqs;
    }

    // additional methods

    // 2String
    public String toString() {
        String course = id + "\t" + name + "\t" + currentNumSeats + "/" + totalNumSeats + "\t";
        for (String preReq : preReqs) {
            course += preReq + ",";
        }
        return course;
    }
}
