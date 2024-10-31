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
        numCourses = 0;
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
        for(int i = 0; i < pastCourses.size(); i ++) {
            System.out.println(pastCourses.indexOf(i));
        }
    } 

    public boolean checkRegistrationForCourses(String course) {
        if(numCourses >= 5) {
            System.out.println("Student has registered the max amount of courses");
            return false;
        }
        
        for(String element : pastCourses) {
            if(element.equals(course)) {
                System.out.println("The student has already taken this course");
                return false;
            }
        }

        futureCourses.add(course);
        numCourses ++;
        
        return true;
    }



}