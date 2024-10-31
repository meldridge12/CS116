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
    


    // Constructor
    Student() {
        this.ID = "No Student ID";
        this.name = "No Student name";
        this.pastCourses = new ArrayList<>();
        this.futureCourses = new ArrayList<>();
        this.numCourses = 5;
        this.filePath = "";
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

    // Mutators
    public void setID(String newID) {
        this.ID = newID;
    }
    
    public void setName(String newName) {
        this.name = newName;
    }


    // Methods
    public void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
         } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
                e.printStackTrace();
            }

    }

    public void printPastCourses() {
    
        // Goes through all the past courses and prints them out 
        for(String element : this.pastCourses) {
            System.out.println(element);
        }
    } 

    public boolean checkRegistrationForCourses(String course) {
        


        
        // Still needs to check if the student has completed the prerequisit courses needs to call course class
        
        

        // Still needs to check if there is an open seat needs to call course class


        
        // Checks to see if the student has enough room in their schedule 
        if(this.numCourses <= 5) {
            System.out.println("Student has registered the max amount of courses");
            return false;
        }
        
        // Goes through past courses to see if student has already taken the course
        for(String element : this.pastCourses) {
            if(element.equals(course)) {
                System.out.println("The student has already taken this course");
                return false;
            }
        }

        // Goes through future courses to check if student has registered for this couse already
        for(String element : this.futureCourses) {
            if(element.equals(course)) {
                System.out.println("The student has already registered for this course");
                return false;
            }
        }


        this.futureCourses.add(course);
        this.numCourses --;
        System.out.println("The course registration was sucessful, the student has " + this.numCourses + " open courses");
        
        return true;
    }

    public boolean cancelCourse(String course) {
        
        // Goes through future courses to check if student has that course regiestered in order
        // to be able to cancel it, still needs to check if student has registered in previous 
        for(String element : this.futureCourses) {
            if(element.equals(course)) {
                System.out.println("The student is able to cancel this course");
                return true;
            }
        }  

        System.out.println("Student is not able to cancel course");
        return false;
    }


}