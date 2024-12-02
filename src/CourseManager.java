import java.util.ArrayList;

public class CourseManager {
    private ArrayList<Course> coursesArrayList;

    // Constructor, no need for one with explicit parameters
    // because of addCourse method.
    public CourseManager() {
        coursesArrayList = new ArrayList<>();
    }

    public ArrayList<Course> getCourseArrayList() {
        return coursesArrayList;
    }

    public Course[] coursesArray() {
        Course[] courseArray = new Course[coursesArrayList.size()];

        for (int i = 0; i < coursesArrayList.size(); i++) {
            courseArray[i] = coursesArrayList.get(i);
        }

        return courseArray;
    }

    // This uses merge sort to allign the class ID's in numerical order.
    public void numOrder(Course[] classIDarray) {
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

        numOrder(left);
        numOrder(right);
        merge(left, right, classIDarray);

        if (length == coursesArrayList.size()) {
            for (int i = 0; i < length; i++) {
                coursesArrayList.set(i, classIDarray[i]);
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
    public boolean checkAvailability(String course) {
        int index = indexSearch(course);
        Course courseAvailable = coursesArrayList.get(index);

        return courseAvailable.getCurrentSeats() < courseAvailable.getTotalSeats();
    }

    // This uses binary search to see if a course ID exists or not.
    public int indexSearch(String course) {
        int left = 0;
        int right = coursesArrayList.size() - 1;

        int courseEvaluatedID = Integer.parseInt(course.substring(2));

        while (right >= left) {
            int center = (right + left) / 2;

            String centerString = coursesArrayList.get(center).getID().substring(2);
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
    public void addCourse(String id, String name, int currentNumSeats,
            int totalNumSeats, ArrayList<String> preReqs, boolean insertion) {

        Course newCourse = new Course(id, name, currentNumSeats, totalNumSeats, preReqs);

        if (insertion) {
            // This uses insertion sort to add the course.
            // Insertion sort more effective than the mergeSort algorithm I created.
            String newID = id.substring(2);
            int newIDnum = Integer.parseInt(newID);

            for (int i = 0; i < coursesArrayList.size(); i++) {
                String compared = coursesArrayList.get(i).getID().substring(2);
                int comparedID = Integer.parseInt(compared);

                if (comparedID > newIDnum) {
                    coursesArrayList.add(i, newCourse);
                    return;
                }
            }
        }
        coursesArrayList.add(newCourse);
    }

    // This uses binary search to remove the course.
    public void removeCourse(String course) {
        int index = indexSearch(course);
        if (index != -1) {
            coursesArrayList.remove(index);
        } else {
            System.out.println("Course not found.");
        }
    }

    public boolean sameCoursesList(CourseManager comparedArray) {
        boolean sameCourses = true;

        for (Course firstCourses : coursesArrayList) {
            if (!sameCourses) {
                return false;
            }

            sameCourses = true;
            for (Course comparedCourses : comparedArray.getCourseArrayList()) {
                if (firstCourses.getID().equals(comparedCourses.getID())) {
                    sameCourses = true;
                    break;
                }
            }
        }

        return true;
    }

    public String samePreReqs(ArrayList<String> reqCourses) {
        String missingPreReqs = "";

        ArrayList<String> pastCourses = new ArrayList<>();
        for (Course pastCourse : coursesArrayList) {
            pastCourses.add(pastCourse.getID());
        }

        for (String reqCourse : reqCourses) {
            boolean preReqPresent = false;
            for (String pastCourse : pastCourses) {
                if (reqCourse.equals(pastCourse)) {
                    preReqPresent = true;
                    break;
                }
            }

            if (!preReqPresent) {
                missingPreReqs += reqCourse + ",";
            }
        }

        return missingPreReqs;
    }

    public String toString() {
        String courses = "";
        for (Course course : coursesArrayList) {
            courses += course + "\n";
        }

        return courses;
    }
}
