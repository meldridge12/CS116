import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    // This method is to add the first courses from the course file into a
    // courseManager object
    // This also returns a coursemanager object that the user is able to access
    // nicely.
    public static CourseManager courseFileToCourseManager(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        CourseManager courseList = new CourseManager();

        scan.nextLine();
        while (scan.hasNextLine()) {
            scan.useDelimiter("\t");

            String courseID = scan.next();

            String name = scan.next();

            scan.useDelimiter("/");
            int currentNumSeats = scan.nextInt();

            scan.useDelimiter("\t");
            int totalNumSeats = scan.nextInt();

            String preReqs = scan.nextLine();
            String[] preReqsList = preReqs.split(",");
            ArrayList<String> preReqsArrayList = new ArrayList<>();

            for (int i = 0; i < preReqsList.length; i++) {
                preReqsArrayList.add(preReqsList[i]);
            }

            courseList.addCourse(courseID, name, currentNumSeats, totalNumSeats, preReqsArrayList, false);
        }

        courseList.numOrder(courseList.coursesArray());
        scan.close();

        String newCourseFile = "ID\tName\tStatus\tPrerequisite\n" + courseList.toString();
        try (FileWriter courseWriter = new FileWriter(fileName)) {
            courseWriter.write(newCourseFile);
            System.out.println("The course file has been rewritten succesfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    // This method is to add the first courses from the student's file into a
    // student object
    // This also returns a student object that the user is able to access nicely.
    public static Student stuFileToStudent(CourseManager courseList, String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));

        String name = scan.nextLine();
        String id = scan.nextLine();

        CourseManager pastCoursesList = new CourseManager();
        String pastCoursesString = scan.nextLine();
        String[] pastCoursesArray = pastCoursesString.split(",");

        for (int i = 0; i < pastCoursesArray.length; i++) {
            int index = courseList.indexSearch(pastCoursesArray[i]);

            if (index != -1) {
                // pC stands for: past Course
                Course pC = courseList.getCourseArrayList().get(index);

                pastCoursesList.addCourse(pC.getID(), pC.getName(), pC.getCurrentSeats(),
                        pC.getTotalSeats(), pC.getpreReqs(), false);
            }
        }

        CourseManager futureCoursesList = new CourseManager();
        String futureCoursesString = scan.nextLine();
        String[] futureCoursesArray = futureCoursesString.split(",");

        for (int i = 0; i < futureCoursesArray.length; i++) {
            int index = courseList.indexSearch(futureCoursesArray[i]);

            if (index != -1) {
                // fC stands for: future Course
                Course fC = courseList.getCourseArrayList().get(index);

                pastCoursesList.addCourse(fC.getID(), fC.getName(), fC.getCurrentSeats(),
                        fC.getTotalSeats(), fC.getpreReqs(), false);
            }
        }

        Student studentFile = new Student(id, name, pastCoursesList, futureCoursesList);
        scan.close();

        String newCourseFile = studentFile.toString();
        try (FileWriter courseWriter = new FileWriter(fileName)) {
            courseWriter.write(newCourseFile);
            System.out.println("The student file has been rewritten succesfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return studentFile;
    }

    // This changes the course and student file based on
    // the info of a student registering a course.
    public static void registerStuFutureCrs(String studentFile, String courseFile, String courseID,
            Student stuInfo, CourseManager courseInfo) throws FileNotFoundException {

        // If every requirement is met, checkRegistration does AddCourse method by
        // itself.
        String register = stuInfo.checkRegistration(courseInfo, courseID);
        Scanner scan = new Scanner(register);
        System.out.print(register);

        if (scan.nextLine() != "Course registration has been rejected.") {

            String newStuFile = stuInfo.toString();
            try (FileWriter courseWriter = new FileWriter(studentFile)) {
                courseWriter.write(newStuFile);
                System.out.println("The student's course is added!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            String newCourseFile = "ID\tName\tStatus\tPrerequisite\n" + courseInfo.toString();
            try (FileWriter courseWriter = new FileWriter(courseFile)) {
                courseWriter.write(newCourseFile);
                System.out.println("Course registration is updated!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scan.close();
    }

    // This changes the course and student file based on
    // the info of a student canceling a course.
    public static void cancelStuFutureCrs(String studentFile, String courseFile, String courseID,
            Student stuInfo, CourseManager courseInfo) throws FileNotFoundException {
        stuInfo.cancelCourse(courseInfo, courseID);

        if (stuInfo.getFutureCourses().indexSearch(courseID) != -1) {

            String newStuFile = stuInfo.toString();
            try (FileWriter courseWriter = new FileWriter(studentFile)) {
                courseWriter.write(newStuFile);
                System.out.println("The student's course is canceled!");
            } catch (IOException e) {
                e.printStackTrace();
            }

            String newCourseFile = "ID\tName\tStatus\tPrerequisite\n" + courseInfo.toString();
            try (FileWriter courseWriter = new FileWriter(courseFile)) {
                courseWriter.write(newCourseFile);
                System.out.println("Course registration is updated!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello user! Welcome to the course and student tracker.");

        System.out.println(
                "To get started, including the .txt at the end, \ntype the name of your Course file, not your student file:");
        File file;
        String courseFile;
        CourseManager courseArrayList;

        while (true) {
            String filename = reader.nextLine();

            file = new File(filename);

            if (file.exists()) {
                System.out.println("File found.");
                courseFile = filename;
                break; // Exit the loop if the file exists
            } else {
                System.out.println("File does not exist. Please try again.");
            }
        }
        courseArrayList = courseFileToCourseManager(courseFile);

        System.out.println(
                "Now, includin the .txt at the end, \ntype the name of your Student file, not your course file:");
        String studentFile;
        Student studentInfo;

        while (true) {
            String filename = reader.nextLine();

            file = new File(filename);

            if (file.exists()) {
                System.out.println("File found.");
                studentFile = filename;
                break; // Exit the loop if the file exists
            } else {
                System.out.println("File does not exist. Please try again.");
            }
        }
        studentInfo = stuFileToStudent(courseArrayList, studentFile);

        System.out.println("Thank you for inputting both sources of information!");
        boolean run = true;

        while (run) {
            System.out.println("\n---------------------------------------------");
            System.out.println("What would you like to do? Please only type the number:");
            System.out.println("[1] View Available Courses");
            System.out.println("[2] View Past Courses Taken");
            System.out.println("[3] Search Courses");
            System.out.println("[4] Register for a Course");
            System.out.println("[5] Remove a Registered Course");
            System.out.println("[6] View Registered Courses");
            System.out.println("[7] Exit");
            System.out.println("---------------------------------------------\n");
            // Add input validation
            while (!reader.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                reader.next(); // Clear invalid input
            }
            int choice = reader.nextInt();
            reader.nextLine(); // Clear the newline character
            String courseID;

            switch (choice) {
                case 1:
                    System.out.println(courseArrayList);
                    break;
                case 2:
                    System.out.println(studentInfo.getPastCourses());
                    break;
                case 3:
                    System.out.print("What is the Course ID of the course you are looking for:");
                    courseID = reader.nextLine();
                    int courseIDindex = courseArrayList.indexSearch(courseID);

                    if (courseIDindex == -1) {
                        System.out.println("A course with that course ID does not exist in this list.");
                    } else {
                        System.out.println("Here's information on the course:\n" +
                                courseArrayList.getCourseArrayList().get(courseIDindex));
                    }
                    break;
                case 4:
                    System.out.print("Enter course ID to register: ");
                    courseID = reader.nextLine();
                    registerStuFutureCrs(studentFile, courseFile, courseID, studentInfo, courseArrayList);
                    break;
                case 5:
                    System.out.print("Enter course ID to remove: ");
                    courseID = reader.nextLine();
                    cancelStuFutureCrs(studentFile, courseFile, courseID, studentInfo, courseArrayList);
                    break;
                case 6:
                    System.out.println(studentInfo.getFutureCourses());
                    break;
                case 7:
                    run = false;
                    break;
                default:
                    System.out.println("That isn't a choice.");
            }
        }

        System.out.println("Student Files and Course Files are up-to-date. Have a good one!");
        reader.close();
    }
}
