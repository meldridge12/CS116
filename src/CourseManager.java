import java.util.ArrayList;

public class CourseManager {
    private ArrayList<Course> courses;

    // Constructor
    public CourseManager() {
        this.courses = new ArrayList<>();
    }

    public Course[] coursesArray() {
        Course[] courseArray = new Course[courses.size()];

        for (int i = 0; i < courses.size(); i++) {
            courseArray[i] = courses.get(i);
        }

        return courseArray;
    }

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

    // This checks to see if a class is full, or even overbooked
    public boolean checkAvailability(String course, ArrayList<Course> theArrayList) {
        int index = indexSearch(course, theArrayList);
        Course courseAvailable = theArrayList.get(index);

        return courseAvailable.getCurrentSeats() < courseAvailable.getTotalSeats();
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
        // Insertion sort more effective than the mergeSort algorithm I created.
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

    public boolean sameCoursesList(ArrayList<Course> first, ArrayList<Course> second) {
        boolean sameCourses = true;

        for (Course firstCourses : first) {
            if (!sameCourses) {
                return false;
            }

            sameCourses = true;
            for (Course secondCourses : second) {
                if (firstCourses.getID().equals(secondCourses.getID())) {
                    sameCourses = true;
                    break;
                }
            }
        }

        return true;
    }

}
