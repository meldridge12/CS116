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

    // This uses merge sort to allign the class ID's in numerical order.
    public static void numOrder(Course[] array) {
        int size = availableCourses.size();

        if (size <= 1) {
            return;
        }

        int middle = size / 2;
        Course[] left = new Course[middle];
        Course[] right = new Course[size - middle];

        int i = 0;
        int j = 0;

        for (; i < size; i++) {
            if (i < middle) {
                left[i] = availableCourses.get(i);
            } else {
                right[i] = availableCourses.get(i);
                j++;
            }

        }

        numOrder(left);
        numOrder(right);
        merge(left, right, array);
    }

    public static void merge(Course[] leftArray, Course[] rightArray, Course[] array) {
        int left = array.length / 2;
        int right = array.length - left;

        int index = 0, l = 0, r = 0;

        while (l < left && r < right) {
            String leftString = leftArray[l].getID().substring(2);
            int leftID = Integer.parseInt(leftString);

            String rightString = rightArray[r].getID().substring(2);
            int rightID = Integer.parseInt(rightString);

            if (leftID < rightID) {
                array[index] = leftArray[l];
                l++;
            } else {
                array[index] = rightArray[r];
                r++;
            }
            index++;
        }

        while (l < left) {
            array[index] = leftArray[l];
            l++;
            index++;
        }

        while (r < right) {
            array[index] = rightArray[r];
            r++;
            index++;
        }
    }

    // This uses binary search to match a course's ID and its index.
    public static int indexSearch(String course) {

    }

    // This uses binary search to add the class ID at the right spot so that the
    // ID's are in numerical order.
    public static void addCourse(String id, String name, int currentNumSeats, int totalNumSeats,
            ArrayList<String> preReqs) {
        Course newCourse = new Course(id, name, currentNumSeats, totalNumSeats, preReqs);

        availableCourses.add(newCourse);
    }

    // This uses binary search
    public static void removeCourse(String course) {
        if (doesCourseExist(course)) {
            for (int i = 0; i < availableCourses.size(); i++) {
                if (availableCourses.get(i).getID().equals(course)) {
                    availableCourses.remove(i);
                    break;
                }
            }
        } else {
            System.out.println("This course does not exist.");
        }
    }

    public static boolean doesCourseExist(String course) {
        for (Course courseEvaluated : availableCourses) {
            if (courseEvaluated.getID().equals(course)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Course> availCourses() {
        return availableCourses;
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
