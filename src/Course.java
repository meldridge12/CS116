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

    public Course[] coursesArray(ArrayList<Course> theArrayList) {
        Course[] courseArray = new Course[theArrayList.size()];

        for (int i = 0; i < theArrayList.size(); i++) {
            courseArray[i] = theArrayList.get(i);
        }

        return courseArray;
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
    public void numOrder(ArrayList<Course> theArrayList, Course[] classIDarray) {
        int length = classIDarray.length;

        if (length <= 1) {
            return;
        }

        int middle = length / 2;
        Course[] left = new Course[middle];
        Course[] right = new Course[length - middle];

        int j = 0;

        for (int i = 0; i < length; i++) {
            if (i < middle) {
                left[i] = classIDarray[i];
            } else {
                right[j] = classIDarray[i];
                j++;
            }

        }

        numOrder(theArrayList, left);
        numOrder(theArrayList, right);
        merge(left, right, classIDarray);

        if (length == theArrayList.size()) {
            for (int i = 0; i < length; i++) {
                theArrayList.set(i, classIDarray[i]);
            }
        }
    }

    // this is the second part of the mergeSort method.
    public void merge(Course[] leftArray, Course[] rightArray, Course[] classIDarray) {
        int leftSize = classIDarray.length / 2;
        int rightSize = classIDarray.length - leftSize;

        int index = 0, l = 0, r = 0;

        while (l < leftSize && r < rightSize) {
            String leftString = leftArray[l].getID().substring(2);
            int leftID = Integer.parseInt(leftString);

            String rightString = rightArray[r].getID().substring(2);
            int rightID = Integer.parseInt(rightString);

            if (leftID < rightID) {
                classIDarray[index] = leftArray[l];
                l++;
            } else {
                classIDarray[index] = rightArray[r];
                r++;
            }
            index++;
        }

        while (l < leftSize) {
            classIDarray[index] = leftArray[l];
            l++;
            index++;
        }

        while (r < rightSize) {
            classIDarray[index] = rightArray[r];
            r++;
            index++;
        }
    }

    // This adds a course
    public void addCourse(ArrayList<Course> theArrayList, String id, String name, int currentNumSeats,
            int totalNumSeats,
            ArrayList<String> preReqs) {
        if (indexSearch(id, theArrayList) != -1) {
            System.out.println("This course ID is taken.");
            return;
        }

        Course newCourse = new Course(id, name, currentNumSeats, totalNumSeats, preReqs);

        // This uses insertion sort to add the course.
        String newID = id.substring(2);
        int newIDnum = Integer.parseInt(newID);

        for (int i = 0; i < theArrayList.size(); i++) {
            String compared = theArrayList.get(i).getID().substring(2);
            int comparedID = Integer.parseInt(compared);

            if (comparedID > newIDnum) {
                theArrayList.add(i, newCourse);
                return;
            }
        }
        theArrayList.add(newCourse);
    }

    // This uses binary search to remove the course.
    public void removeCourse(String course, ArrayList<Course> coursesSearched) {
        int index = indexSearch(course, coursesSearched);

        if (index > -1) {
            coursesSearched.remove(index);
        } else {
            System.out.println("This course does not exist.");
        }
    }

    // This uses binary search to see if a course ID exists or not.
    public int indexSearch(String course, ArrayList<Course> theArrayList) {
        int left = 0;
        int right = theArrayList.size() - 1;

        int courseEvaluatedID = Integer.parseInt(course.substring(2));

        while (right >= left) {
            int center = (right + left) / 2;

            String centerString = theArrayList.get(center).getID().substring(2);
            int centerID = Integer.parseInt(centerString);

            if (courseEvaluatedID == centerID) {
                return center;
            } else if (courseEvaluatedID > centerID) {
                left = center + 1;
            } else {
                right = center - 1;
            }

        }

        return -1;
    }

    // 2String
    public String toString() {
        String course = id + "\t" + name + "\t" + currentNumSeats + "/" + totalNumSeats + "\t";
        for (String singlePrereq : preReqs) {
            course += singlePrereq + ",";
        }
        return course;
    }
}
