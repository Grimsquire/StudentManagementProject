import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        int menuSelection;

        //TODO
        //Need to make sure that objects are in fact being updated when we alter them. I haven't exactly got a good grasp
        //on pass by reference and/or pass by value so i'm not positive that an object is being properly updated after it's
        //put into an ArrayList or Hashmap. Might have to manually 'resubmit' a new object with the changes into those lists.

        //List of preset entries for testing/demonstration.
        Teacher TeacherUser = new Teacher("Dube", "hdh@fsd", 42);
        Student StudentUser = new Student("John Doe", "sdfsdf@aaaaa", 99);
        Student matt = new Student("Matt", "grg@dfdf", 31);
        Student brody = new Student("Brody","qwert@qwerty", 24);
        Student jack = new Student("Jack","rrrrr@rrrrr",22);
        Student kate = new Student("Kate","ffff@ffff",23);

        TeacherUser.createCourse("CIS210", "Intro to computer science", "MWF 2:00 - 3:20", 3413);
        matt.enroll("CIS210");

        TeacherUser.setSelectedCourse("CIS210");
        TeacherUser.addAssignment("Homework1", 100);
        TeacherUser.selectedCourse.setSelectedAssignment("Homework1");
        TeacherUser.addStudentScore("Matt", 50);
        TeacherUser.addStudentScore("Brody",80);


        System.out.println("Please input a user ID.");
        int inputID;
        do {
            try {
                inputID = scnr.nextInt();
                scnr.nextLine();    //Clears the line left after the last input.
            } catch (InputMismatchException e) {
                throw new IllegalArgumentException("Invalid input: Please enter a number ID.");
            }
        } while (inputID == 0);

        if (inputID < 10001 && inputID > 0) {
            do {
                System.out.println();
                System.out.println("Please select a menu option:");
                System.out.println("1: Create a new course.");
                System.out.println("2: Add an assignment to a course.");
                System.out.println("3: Input a student assignment score.");
                System.out.println("4: Display the average grade for a course.");
                System.out.println("5: Display the average grade for an assignment.");
                System.out.println("6: Display a specific student grade.");
                System.out.println("7: Export all course grades.");
                System.out.println("8: Exit.");
                menuSelection = scnr.nextInt();
                scnr.nextLine();
                //TODO
                //These prompts that ask for user input can be placed in the actual methods themselves,
                //such as Teacher.addAssignment. The prompt can be placed there which would save space here.
                //Would also need to make sure that we call selectCourse and selectAssignment when we do it.
                String studentName;
                String assignmentName;
                int score;
                int maxScore;
                String courseName;
                switch (menuSelection) {
                    case 1:
                        System.out.println();
                        System.out.println("Input a name for the course.");
                        TeacherUser.createCourse();
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("Input the name of the course.");
                        TeacherUser.setSelectedCourse(scnr.nextLine());
                        System.out.println("Input a name for the assignment.");
                        assignmentName = scnr.nextLine();
                        System.out.println("Input a max score for the assignment.");
                        maxScore = scnr.nextInt();
                        TeacherUser.addAssignment(assignmentName, maxScore);
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("Input the name of the course.");
                        TeacherUser.setSelectedCourse(scnr.nextLine());
                        System.out.println("Input the name of the assignment.");
                        TeacherUser.selectedCourse.setSelectedAssignment(scnr.nextLine());
                        System.out.println("Input a student name.");
                        studentName = scnr.nextLine();
                        System.out.println("Input a score for the student.");
                        score = scnr.nextInt();
                        TeacherUser.addStudentScore(studentName, score);
                        break;
                    case 4:
                        //This does not behave properly.
                        System.out.println();
                        System.out.println("Input the name of the course that you want to display the grades for.");
                        TeacherUser.setSelectedCourse(scnr.nextLine());
                        System.out.println("Average score for " + TeacherUser.selectedCourse.courseName + " is " + TeacherUser.displayCourseAvg());
                        break;
                    case 5:
                        System.out.println();
                        System.out.println("Input the name of the assignment that you want to display the average for.");
                        TeacherUser.selectedCourse.setSelectedAssignment(scnr.nextLine());
                        System.out.println("Average score for " + TeacherUser.selectedCourse.selectedAssignment.assignmentName + " is " + TeacherUser.displayAssignmentAvg());
                    case 6:
                        System.out.println();
                        System.out.println("Input the name of the course.");
                        TeacherUser.setSelectedCourse(scnr.nextLine());
                        System.out.println("Input the name of the assignment.");
                        TeacherUser.selectedCourse.setSelectedAssignment(scnr.nextLine());
                        System.out.println("Input a student name to display their grade.");
                        studentName = scnr.nextLine();
                        System.out.println(TeacherUser.selectedCourse.selectedAssignment.getScore(studentName));
                        break;
                    case 7:
                        System.out.println();
                        System.out.println("Input a course to export all course grades.");
                        TeacherUser.setSelectedCourse(scnr.nextLine());
                        TeacherUser.exportCourseGrades(TeacherUser.selectedCourse);
                        break;
                    case 8:
                        System.out.println();
                        continue;
                    default:
                        System.out.println();
                        System.out.println("Invalid selection.");
                        break;
                }
            } while (menuSelection != 7);
        } else if (inputID > 10000) {
            do {
                System.out.println("Please select a menu option:");
                System.out.println("1: Enroll in a course.");
                System.out.println("2: Check a course grade.");
                System.out.println("3: Check all course grades.");
                System.out.println("4: Export my course grades.");
                System.out.println("5: Exit");
                menuSelection = scnr.nextInt();

                String courseName;
                switch (menuSelection) {
                    case 1:
                        System.out.println();
                        System.out.println("Enter a course name to enroll.");
                        courseName = scnr.nextLine();
                        StudentUser.enroll(courseName);
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("Enter a course name to view grade.");
                        StudentUser.setSelectedCourse(scnr.nextLine());
                        StudentUser.displayCourseGrade(StudentUser.selectedCourse.courseName);
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("Displaying all course grades.");
                        StudentUser.displayAllCourseGrades();
                        break;
                    case 4:
                        System.out.println();
                        System.out.println("Enter a course name to export grades to a file.");
                        StudentUser.exportCourseGrades(StudentUser.selectedCourse);
                        break;
                    case 5:
                        System.out.println();
                        continue;
                    default:
                        System.out.println();
                        System.out.println("Invalid selection.");
                }
                System.out.println();
            } while (menuSelection != 6);
        } else {
            System.out.println("Invalid ID.");
        }
    }
}
