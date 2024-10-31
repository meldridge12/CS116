
public class Student {
    
    // Attributes
    private String ID;
    private String name; 
    private String[] pastCourses;
    private String[] futureCourses;

    


    // Constructor
    Student() {
        ID = "No Student ID";
        name = "No Student name";
        pastCourses = new String[0];
        futureCourses = new String[0];
    }

    

    // Accessors
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String[] getPastCourses() {
        return pastCourses;
    }

    public String[] getFutureCourses() {
        return futureCourses;
    }

    // Mutators
    public void setID(String newID) {
        ID = newID;
    }
    
    public void setName(String newName) {
        name = newName;
    }



}