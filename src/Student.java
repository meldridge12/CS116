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
                    
                    // Read next line for registered courses
                    line = reader.readLine();
                    if (line != null && line.startsWith("Registered Courses:")) {
                        String[] registeredCourses = line.substring("Registered Courses:".length()).trim().split(",");
                        this.futureCourses.clear();
                        for (String course : registeredCourses) {
                            if (!course.trim().isEmpty()) {
                                this.futureCourses.add(course.trim());
                            }
                        }
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

    public boolean checkRegistrationForCourses(String courseId) {
        // Check if student has reached max courses (5)
        if (this.numCourses <= 0) {
            System.out.println("Registration failed: Maximum number of courses (5) reached");
            return false;
        }
        
        // Find the course in available courses
        String selectedCourse = null;
        for (String course : availableCourses) {
            if (course.split(" ")[0].equals(courseId)) {
                selectedCourse = course;
                break;
            }
        }
        
        if (selectedCourse == null) {
            System.out.println("Registration failed: Course " + courseId + " not found");
            return false;
        }
        
        // Check if course is already taken or registered
        for (String pastCourse : pastCourses) {
            if (pastCourse.trim().equals(courseId)) {
                System.out.println("Registration failed: Course already taken in the past");
                return false;
            }
        }
        
        for (String futureCourse : futureCourses) {
            if (futureCourse.equals(courseId)) {
                System.out.println("Registration failed: Course already registered");
                return false;
            }
        }
        
        // Check if course has available seats
        String[] parts = selectedCourse.split(" ");
        String[] seatsInfo = null;
        for (String part : parts) {
            if (part.contains("/")) {
                seatsInfo = part.split("/");
                break;
            }
        }
        
        if (seatsInfo != null) {
            int currentSeats = Integer.parseInt(seatsInfo[0]);
            int totalSeats = Integer.parseInt(seatsInfo[1]);
            if (currentSeats >= totalSeats) {
                System.out.println("Registration failed: Course is full");
                return false;
            }
        }
        
        // Check prerequisites
        String[] courseParts = selectedCourse.split(" ");
        for (int i = 0; i < courseParts.length; i++) {
            if (courseParts[i].contains(",")) {  // Found prerequisites
                String[] prerequisites = courseParts[i].split(",");
                for (String prereq : prerequisites) {
                    boolean hasPrereq = false;
                    for (String pastCourse : pastCourses) {
                        if (pastCourse.trim().equals(prereq.trim())) {
                            hasPrereq = true;
                            break;
                        }
                    }
                    if (!hasPrereq) {
                        System.out.println("Registration failed: Missing prerequisite " + prereq);
                        return false;
                    }
                }
                break;
            }
        }
        
        // If all checks pass, register the course
        this.futureCourses.add(courseId);
        this.numCourses--;
        return true;
    }

    public boolean cancelCourse(String courseId) {
        // Check if the course is in future courses (registered for next semester)
        if (!this.futureCourses.contains(courseId)) {
            System.out.println("Cancellation failed: Course " + courseId + " is not registered for next semester");
            return false;
        }
        
        // Remove the course and increase available course slots
        this.futureCourses.remove(courseId);
        this.numCourses++;
        System.out.println("Course " + courseId + " has been successfully cancelled");
        System.out.println("You now have " + this.numCourses + " available course slots");
        return true;
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

    public void writeToFiles(String file1Name, String file2Name) {
        writeToFile1(file1Name);
        writeToFile2(file2Name);
    }

    private void writeToFile1(String fileName) {
        try {
            ArrayList<String> updatedCourses = new ArrayList<>();
            // First line is header
            updatedCourses.add("ID Name Status Prerequisite");
            
            // Update each course
            for (String course : availableCourses) {
                String[] parts = course.split(" ");
                String courseId = parts[0];
                
                // If course is registered, increment current seats
                if (futureCourses.contains(courseId)) {
                    // Find the seats part and update it
                    String updatedCourse = course;
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].contains("/")) {
                            String[] seats = parts[i].split("/");
                            int current = Integer.parseInt(seats[0]);
                            updatedCourse = course.replace(parts[i], (current + 1) + "/" + seats[1]);
                            break;
                        }
                    }
                    updatedCourses.add(updatedCourse);
                } else {
                    updatedCourses.add(course);
                }
            }
            
            // Write back to file
            java.io.PrintWriter writer = new java.io.PrintWriter(fileName);
            for (String course : updatedCourses) {
                writer.println(course);
            }
            writer.close();
            
        } catch (IOException e) {
            System.out.println("Error writing to file1: " + e.getMessage());
        }
    }

    private void writeToFile2(String fileName) {
        try {
            java.io.PrintWriter writer = new java.io.PrintWriter(fileName);
            
            // Write student info and past courses
            StringBuilder line = new StringBuilder();
            line.append(this.name).append("\t")
                .append(this.ID).append("\t");
            
            // Write past courses
            line.append(String.join(",", pastCourses));
            writer.println(line.toString());
            
            // Write registered courses on a new line if there are any
            if (!futureCourses.isEmpty()) {
                writer.println("Registered Courses: " + String.join(",", futureCourses));
            }
            
            writer.close();
            
        } catch (IOException e) {
            System.out.println("Error writing to file2: " + e.getMessage());
        }
    }

    public boolean canGraduate() {
        // Check if student has completed core requirements
        String[] coreRequirements = {"CS115", "CS116", "CS330", "CS331"};
        for (String req : coreRequirements) {
            boolean found = false;
            for (int i = 0; i < pastCourses.size(); i++) {
                if (pastCourses.get(i).equals(req)) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    public double calculateCourseLoad() {
        // Calculate total credit hours for registered courses
        int totalCredits = 0;
        for (int i = 0; i < futureCourses.size(); i++) {
            String courseId = futureCourses.get(i);
            // Assume each course is 3 credits
            totalCredits += 3;
        }
        return totalCredits;
    }


    


}