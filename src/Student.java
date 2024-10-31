import java.util.ArrayList;

public class Student {
    
    // Attributes
    private String ID;
    private String name; 
    private ArrayList<String> pastCourses;
    private ArrayList<String> futureCourses;
    private int numCourses; 
    


    // Constructor
    Student() {
        ID = "No Student ID";
        name = "No Student name";
        pastCourses = new ArrayList<>();
        futureCourses = new ArrayList<>();
        numCourses = 5;
    }

    Student(String ID, String name, ArrayList<String> pastCourses, ArrayList<String> futureCourses, int numCourses) {
        this.ID = ID;
        this.name = name;
        this. pastCourses = pastCourses;
        this.futureCourses = futureCourses;
        this.numCourses = numCourses;
    }

    

    // Accessors
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getPastCourses() {
        return pastCourses;
    }

    public ArrayList<String> getFutureCourses() {
        return futureCourses;
    }

    // Mutators
    public void setID(String newID) {
        ID = newID;
    }
    
    public void setName(String newName) {
        name = newName;
    }


    // Methods
    public void printPastCourses() {
    
        // Goes through all the past courses and prints them out 
        for(String element : pastCourses) {
            System.out.println(element);
        }
    } 

    public boolean checkRegistrationForCourses(String course) {
        
        
        // Still needs to check if the student has completed the prerequisit courses
        
        

        // Still needs to check if there is an open seat


        
        // Checks to see if the student has enough room in their schedule 
        if(numCourses <= 5) {
            System.out.println("Student has registered the max amount of courses");
            return false;
        }
        
        // Goes through past courses to see if student has already taken the course
        for(String element : pastCourses) {
            if(element.equals(course)) {
                System.out.println("The student has already taken this course");
                return false;
            }
        }

        // Goes through future courses to check if student has registered for this couse already
        for(String element : futureCourses) {
            if(element.equals(course)) {
                System.out.println("The student has already registered for this course");
                return false;
            }
        }


        futureCourses.add(course);
        numCourses --;
        System.out.println("The course registration was sucessful, the student has " + numCourses + " open courses");
        
        return true;
    }

    public boolean cancelCourse(String course) {
        
        // Goes through future courses to check if student has that course regiestered in order
        // to be able to cancel it, still needs to check if student has registered in previous 
        for(String element : futureCourses) {
            if(element.equals(course)) {
                System.out.println("The student is able to cancel this course");
                return true;
            }
        }  

        System.out.println("Student is not able to cancel course");
        return false;
    }


}