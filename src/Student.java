import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Student {
    
    // Attributes
    private String ID;
    private String name; 
    private ArrayList<String> pastCourses;
    private ArrayList<String> futureCourses;
    private int numCourses; 
    private String filePath;
    private ArrayList<String> availableCourses;  // For courses from file1
    


    // Constructor
    Student() {
        this.ID = "No Student ID";
        this.name = "No Student name";
        this.pastCourses = new ArrayList<>();
        this.futureCourses = new ArrayList<>();
        this.numCourses = 5;
        this.filePath = "";
        this.availableCourses = new ArrayList<>();
    }

    Student(String ID, String name, ArrayList<String> pastCourses, ArrayList<String> futureCourses, int numCourses, String filePath) {
        this.ID = ID;
        this.name = name;
        this. pastCourses = pastCourses;
        this.futureCourses = futureCourses;
        this.numCourses = numCourses;
        this.filePath = filePath;
    }

    

    // Accessors
    public String getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<String> getPastCourses() {
        return this.pastCourses;
    }

    public ArrayList<String> getFutureCourses() {
        return this.futureCourses;
    }

    public int getNumCourses() {
        return this.numCourses;
    }

    public ArrayList<String> getAvailableCourses() {
        return this.availableCourses;
    }

    // Mutators
    public void setID(String newID) {
        this.ID = newID;
    }
    
    public void setName(String newName) {
        this.name = newName;
    }

    public void setNumCourses(int numCourses) {
        this.numCourses = numCourses;
    }


    // Methods
    public boolean readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            
            // Check if it's file1 (has column headers) or file2 (starts with student name)
            if (line.contains("ID Name Status")) {  // File1 - Available courses
                // Skip header
                line = reader.readLine();
                while (line != null) {
                    String[] parts = line.split("\t");
                    if (parts.length > 0) {
                        // Store the full course information
                        this.availableCourses.add(line.trim());
                    }
                    line = reader.readLine();
                }
            } else {  // File2 - Student info
                String[] studentInfo = line.split("\t");
                if (studentInfo.length >= 3) {
                    this.name = studentInfo[0].trim();
                    this.ID = studentInfo[1].trim();
                    String[] courses = studentInfo[2].split(",");
                    this.pastCourses.clear();
                    for (String course : courses) {
                        this.pastCourses.add(course.trim());
                    }
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }
    }

    public List<String> getPastCoursesFormatted() {
        List<String> formattedCourses = new ArrayList<>();
        formattedCourses.addAll(this.pastCourses);
        return formattedCourses;
    }

    public List<String> getFutureCoursesFormatted() {
        List<String> formattedCourses = new ArrayList<>();
        for(String element : this.futureCourses) {
            formattedCourses.add(element);
        }
        return formattedCourses;
    }

    public boolean checkRegistrationForCourses(String course) {
        // Check max courses (should allow registration if numCourses > 0)
        if (this.numCourses <= 0) {
            return false;
        }
        
        // Check past courses using formatted list
        for (String pastCourse : getPastCoursesFormatted()) {
            if (pastCourse.equals(course)) {
                return false;
            }
        }

        // Check future courses
        for (String element : this.futureCourses) {
            if (element.equals(course)) {
                return false;
            }
        }

        this.futureCourses.add(course);
        this.numCourses--;
        return true;
    }

    public boolean cancelCourse(String course) {
        if (this.futureCourses.remove(course)) {
            this.numCourses++;
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder student = new StringBuilder();
        student.append("The student's name and id number is: ").append(this.name)
              .append(" ").append(this.ID).append(" and is taking ")
              .append(this.numCourses).append(" amount of courses.\n");
        
        student.append("Past courses: ").append(String.join(", ", getPastCoursesFormatted())).append("\n");
        student.append("Future courses: ").append(String.join(", ", getFutureCoursesFormatted()));
        
        return student.toString();
    }


    


}