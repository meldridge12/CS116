import java.util.Scanner;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        Student student = null;
        
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
            System.out.println("8. Check Graduation Status");
            System.out.println("9. Calculate Course Load");
            System.out.println("10. Exit");
            System.out.println("---------------------------------------------\n");
            int choice;
            String input = reader.nextLine().trim();
            while (input.isEmpty() || !input.matches("\\d+")) {
                System.out.println("Please enter a valid number.");
                input = reader.nextLine().trim();
            }
            choice = Integer.parseInt(input);
            if (choice < 1 || choice > 10) {
                throw new IllegalArgumentException("Invalid menu option");
            }
            
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
                    System.out.println("\nPress Enter to continue...");
                    reader.nextLine(); // Clear the current line
                    reader.nextLine(); // Wait for Enter
                    break;
                case 2:
                    if (student != null) {
                        System.out.println("Available courses:");
                        // Convert to array for sorting
                        ArrayList<String> courseList = new ArrayList<>(student.getAvailableCourses());
                        String[] courses = courseList.toArray(new String[0]);
                        
                        // Bubble sort
                        for (int i = 0; i < courses.length - 1; i++) {
                            for (int j = 0; j < courses.length - i - 1; j++) {
                                String[] parts1 = courses[j].split(" ");
                                String[] parts2 = courses[j + 1].split(" ");
                                if (parts1[0].compareTo(parts2[0]) > 0) {
                                    // Swap
                                    String temp = courses[j];
                                    courses[j] = courses[j + 1];
                                    courses[j + 1] = temp;
                                }
                            }
                        }
                        
                        // Display sorted courses
                        for (String course : courses) {
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
                    System.out.println("\nPress Enter to continue...");
                    reader.nextLine();
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
                    System.out.println("\nPress Enter to continue...");
                    reader.nextLine();
                    break;
                case 4:
                    if (student != null) {
                        System.out.print("Enter course ID to search: ");
                        String searchCourse = reader.nextLine().toUpperCase();
                        boolean found = false;
                        
                        for (String course : student.getAvailableCourses()) {
                            String[] parts = course.split(" ");
                            if (parts[0].startsWith(searchCourse)) {
                                System.out.println(course);
                                found = true;
                            }
                        }
                        
                        if (!found) {
                            System.out.println("No courses found matching: " + searchCourse);
                        }
                    } else {
                        System.out.println("Please input files first (Option 1)");
                    }
                    System.out.println("\nPress Enter to continue...");
                    reader.nextLine();
                    break;
                case 5:
                    if (student != null) {
                        System.out.print("Enter course ID to register: ");
                        String courseToRegister = reader.nextLine().toUpperCase();
                        if (student.checkRegistrationForCourses(courseToRegister)) {
                            System.out.println("Registration successful! You have " + 
                                student.getNumCourses() + " remaining course slots");
                        }
                    } else {
                        System.out.println("Please input files first (Option 1)");
                    }
                    System.out.println("\nPress Enter to continue...");
                    reader.nextLine();
                    break;
                case 6:
                    if (student != null) {
                        // First show currently registered courses
                        System.out.println("Currently registered courses:");
                        if (student.getFutureCourses().isEmpty()) {
                            System.out.println("No courses registered for next semester");
                        } else {
                            for (String course : student.getFutureCoursesFormatted()) {
                                System.out.println(course);
                            }
                        }
                        
                        System.out.print("\nEnter course ID to cancel: ");
                        String courseToRemove = reader.nextLine().toUpperCase();
                        student.cancelCourse(courseToRemove);
                    } else {
                        System.out.println("Please input files first (Option 1)");
                    }
                    System.out.println("\nPress Enter to continue...");
                    reader.nextLine();
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
                    System.out.println("\nPress Enter to continue...");
                    reader.nextLine();
                    break;
                case 8:
                    if (student != null) {
                        if (student.canGraduate()) {
                            System.out.println("Student has completed all core requirements for graduation!");
                        } else {
                            System.out.println("Student has not yet completed all core requirements.");
                            System.out.println("Core requirements: CS115, CS116, CS330, CS331");
                        }
                    } else {
                        System.out.println("Please input files first (Option 1)");
                    }
                    System.out.println("\nPress Enter to continue...");
                    reader.nextLine();
                    break;
                case 9:
                    if (student != null) {
                        double credits = student.calculateCourseLoad();
                        System.out.println("Total credit hours for registered courses: " + credits);
                        System.out.println("(Assuming 3 credits per course)");
                    } else {
                        System.out.println("Please input files first (Option 1)");
                    }
                    System.out.println("\nPress Enter to continue...");
                    reader.nextLine();
                    break;
                case 10:
                    if (student != null) {
                        System.out.println("Writing registration information to files...");
                        student.writeToFiles("1.txt", "2.txt");
                        System.out.println("Files updated successfully!");
                    }
                    run = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }
        
        reader.close();
    }
}