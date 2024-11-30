import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.File;

public class Main {
    public static CourseManager fileReader(String fileName) throws FileNotFoundException {

    }

    public static void removeStuFutureCrs(String studentFile, String courseFile, String courseID)
            throws FileNotFoundException {

    }

    public static void addStuFutureCrs(String studentFile, String courseFile, String courseID)
            throws FileNotFoundException {

    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        // These create a student object.
        String[] studentInfo = gatherStudentInfo("2.txt");
        String id = studentInfo[0];
        String name = studentInfo[1];
        String[] past = studentInfo[2].split(",");
        ArrayList<String> pastCourses = new ArrayList<>(Arrays.asList(past));
        String[] future = studentInfo[3].split(",");
        ArrayList<String> futureCourses = new ArrayList<>(Arrays.asList(future));
        int numCourses = futureCourses.size();

        Student student = new Student(id, name, pastCourses, futureCourses, numCourses);

        // These create a course object.

        boolean run = true;

        while (run) {
            System.out.println("\n---------------------------------------------");
            System.out.println("Please select an option:");
            System.out.println("1. Input Files");
            System.out.println("2. View All Courses");
            System.out.println("3. View Past Courses Taken");
            System.out.println("4. Search Courses");
            System.out.println("5. Register for a Course");
            System.out.println("6. Remove a Registered Course");
            System.out.println("7. View Registered Courses");
            System.out.println("8. Exit");
            System.out.println("---------------------------------------------\n");
            // Add input validation
            while (!reader.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                reader.next(); // Clear invalid input
            }
            int choice = reader.nextInt();
            reader.nextLine(); // Clear the newline character

            switch (choice) {
                case 1:
                    student = new Student();
                    boolean fileRead = false;

                    while (!fileRead) {
                        System.out.print("Please enter the name of file 1: ");
                        String file1 = reader.next();
                        if (student.readFile(file1)) {
                            System.out.println("Successfully read file 1: " + file1);
                            fileRead = true;
                        } else {
                            System.out.println("Error: File 1 cannot be found. Please try again.");
                        }
                    }

                    try {
                        System.out.print("Please enter the name of file 2: ");
                        String file2 = reader.next();
                        student.readFile(file2);
                        System.out.println("Successfully read file 2: " + file2);
                    } catch (Exception e) {
                        System.out.println("Error: File 2 cannot be found");
                    }
                    break;
                case 2:
                    if (student != null) {
                        System.out.println("Available courses:");
                        for (String course : student.getAvailableCourses()) {
                            String[] parts = course.split("\t");
                            if (parts.length >= 2) {
                                System.out.println(parts[0] + " - " + parts[1]);
                            } else {
                                System.out.println(course);
                            }
                        }
                    } else {
                        System.out.println("Please input files first (Option 1)");
                    }
                    break;
                case 3:
                    if (student != null) {
                        System.out.println("The student's past courses are:");
                        for (String course : student.getPastCoursesFormatted()) {
                            System.out.println(course);
                        }
                    } else {
                        System.out.println("Please input files first (Option 1)");
                    }
                    break;
                case 4:
                    if (student != null) {
                        System.out.print("Enter course ID to search: ");
                        String searchCourse = reader.nextLine();
                        boolean found = false;
                        for (String course : student.getPastCourses()) {
                            if (course.contains(searchCourse)) {
                                System.out.println("Found: " + course);
                                found = true;
                            }
                        }
                        for (String course : student.getFutureCourses()) {
                            if (course.contains(searchCourse)) {
                                System.out.println("Found: " + course);
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("No courses found matching: " + searchCourse);
                        }
                    } else {
                        System.out.println("Please input files first (Option 1)");
                    }
                    break;
                case 5:
                    if (student != null) {
                        System.out.print("Enter course ID to register: ");
                        String courseToRegister = reader.nextLine();
                        if (student.checkRegistrationForCourses(courseToRegister)) {
                            System.out.println("The course registration was successful, the student has " +
                                    student.getNumCourses() + " open courses");
                        } else {
                            System.out
                                    .println("Registration failed. Please check course availability and requirements.");
                        }
                    } else {
                        System.out.println("Please input files first (Option 1)");
                    }
                    break;
                case 6:
                    if (student != null) {
                        System.out.print("Enter course ID to remove: ");
                        String courseToRemove = reader.nextLine();
                        if (student.cancelCourse(courseToRemove)) {
                            student.getFutureCourses().remove(courseToRemove);
                            student.setNumCourses(student.getNumCourses() + 1);
                            System.out.println("Course successfully removed");
                        }
                    } else {
                        System.out.println("Please input files first (Option 1)");
                    }
                    break;
                case 7:
                    if (student != null) {
                        System.out.println("Registered courses:");
                        for (String course : student.getFutureCoursesFormatted()) {
                            System.out.println(course);
                        }
                    } else {
                        System.out.println("Please input files first (Option 1)");
                    }
                    break;
                case 8:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }

        reader.close();
    }
}
