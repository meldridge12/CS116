import java.util.ArrayList;

public class Student {

    // Attributes
    private String ID;
    private String name;
    private CourseManager pastCourses;
    private CourseManager futureCourses;

    // Constructor

    Student(String ID, String name, CourseManager pastCourses, CourseManager futureCourses) {
        this.ID = ID;
        this.name = name;
        this.pastCourses = pastCourses;
        this.futureCourses = futureCourses;
    }

    // Accessors
    public String getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public CourseManager getPastCourses() {
        return this.pastCourses;
    }

    public CourseManager getFutureCourses() {
        return this.futureCourses;
    }

    // Mutators
    public void setID(String newID) {
        this.ID = newID;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    // Additional Methods

    /*
     * This checks if the student is able to register.
     * If they are, then information is edited.
     * Also this is a System.out.print without the ln.
     */
    public String checkRegistration(CourseManager courseArrayList, String course) {
        String rejection = "Course registration has been rejected.\n";

        if (courseArrayList.indexSearch(course) == -1) {
            return rejection + "This course doesn't exist.\n";
        }

        if (courseArrayList.checkAvailability(course)) {
            return rejection + "This class is full.\n";
        }

        if (pastCourses.indexSearch(course) != -1) {
            return rejection + "This student already has taken this class.\n";
        }

        boolean requirementsFulfilled = true;
        String registrationReqs = new String();

        if (futureCourses.getCourseArrayList().size() >= 5) {
            registrationReqs += "This student is already taking five or more courses.\n";
            requirementsFulfilled = false;
        }

        // FIX THIS PLS ITS A STRING ARRAY NOT A COURSE ARRAY
        if (courseArrayList.sameCoursesList(pastCourses)) {
            registrationReqs += "This student doesn't have the required prerequisites for the course.\n";
            requirementsFulfilled = false;
        }

        if (requirementsFulfilled) {
            int courseIndex = courseArrayList.indexSearch(course);
            Course analyzedCourse = courseArrayList.getCourseArrayList().get(courseIndex);
            String id = analyzedCourse.getID();
            String name = analyzedCourse.getName();
            int currentNumSeats = analyzedCourse.getCurrentSeats();
            int totalNumSeats = analyzedCourse.getTotalSeats();
            ArrayList<String> preReqs = analyzedCourse.getpreReqs();

            futureCourses.addCourse(id, name, currentNumSeats, totalNumSeats, preReqs);

            return "This student has been registered for this course.\n";
        }

        return rejection + registrationReqs;
    }

    public void cancelCourse(CourseManager courseArrayList, String course) {
        if (futureCourses.indexSearch(course) == -1) {
            System.out.println("This student isn't scheduled to take this course next semester.");
        }

        /*
         * No need to check to see if it exists in courseArrayList because it can't be
         * added
         * to the student Course list in the first place if it doesn't exist in
         * courseArrayList.
         */

        futureCourses.removeCourse(course);
        System.out.println("Course has been removed.");

        int index = courseArrayList.indexSearch(course);
        Course courseAnalyzed = courseArrayList.getCourseArrayList().get(index);
        courseAnalyzed.setCurrentSeats(courseAnalyzed.getCurrentSeats() - 1);
    }

    public String toString() {
        String studentInfo = name + "\t" + ID + "\t";

        for (Course pastCourse : pastCourses.getCourseArrayList()) {
            studentInfo += pastCourse + ",";
        }
        studentInfo += "\t";

        for (Course futureCourse : futureCourses.getCourseArrayList()) {
            studentInfo += futureCourse + ",";
        }

        return studentInfo;
    }

}