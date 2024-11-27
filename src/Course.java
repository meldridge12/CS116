import java.util.ArrayList;

public class Course {

    // attributes
    private String id;
    private String name;
    private int currentNumSeats;
    private int totalNumSeats;
    private ArrayList<String> preReqs;

    private static ArrayList<Course> availableCourses = new ArrayList<>();

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

    public void setNewpreReqs(ArrayList<String> newPreeqs) {
        preReqs = newPreeqs;
    }

    // additional methods
    public static void addCourse(String id, String name, int currentNumSeats, int totalNumSeats,
            ArrayList<String> preReqs) {
        Course newCourse = new Course(id, name, currentNumSeats, totalNumSeats, preReqs);
        availableCourses.add(newCourse);
    }

    public static void removeCourse(String course) {
        int indexOfCourse = -1;

        for (int i = 0; i < availableCourses.size(); i++) {
            if (availableCourses.get(i).getID().equals(course)) {
                indexOfCourse = i;
                break;
            }
        }

        if (indexOfCourse == -1) {
            System.out.println("This course does not exist.");
        } else {
            availableCourses.remove(indexOfCourse);
        }

    }

    public boolean classFull() {
        return currentNumSeats / totalNumSeats == 1;
    }

    public boolean preReqsTaken(ArrayList<Course> oldCourses) {
        boolean nopreReqs = false;

        for (Course oldCourse : oldCourses) {
            if (nopreReqs) {
                return false;
            }

            nopreReqs = true;
            for (int i = 0; i < preReqs.size(); i++) {
                if (oldCourse.getID() != preReqs.get(i))
                    nopreReqs = false;
            }
        }
        return true;
    }

    // 2String
    public String toString() {
        String course = id + "/t" + name + "/t" + currentNumSeats + "/" + totalNumSeats + "/t";
        for (String singlePrereq : preReqs) {
            course += singlePrereq + ",";
        }
        return course;
    }
}
