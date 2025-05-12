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

        Teacher dube = new Teacher("Dube", "hdh@fsd", 42);
        Student matt = new Student("Matt", "grg@dfdf", 31);
        dube.createCourse("CIS210", "Intro to computer science", "MWF 2:00 - 3:20", 3413);
        matt.enroll("CIS210");
        dube.setSelectedCourse("CIS210");
        dube.addAssignment("Homework1", 100);
        dube.selectedCourse.setSelectedAssignment("Homework1");
        dube.addStudentScore("Matt", 50);
        dube.addStudentScore("Brody",80);


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
                System.out.println("5: Display a specific student grade.");
                System.out.println("6: Export all course grades.");
                System.out.println("7: Exit.");
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
                        dube.createCourse();
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("Input the name of the course.");
                        dube.setSelectedCourse(scnr.nextLine());
                        System.out.println("Input a name for the assignment.");
                        assignmentName = scnr.nextLine();
                        System.out.println("Input a max score for the assignment.");
                        maxScore = scnr.nextInt();
                        dube.addAssignment(assignmentName, maxScore);
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("Input the name of the course.");
                        dube.setSelectedCourse(scnr.nextLine());
                        System.out.println("Input the name of the assignment.");
                        dube.selectedCourse.setSelectedAssignment(scnr.nextLine());
                        System.out.println("Input a student name.");
                        studentName = scnr.nextLine();
                        System.out.println("Input a score for the student.");
                        score = scnr.nextInt();
                        dube.addStudentScore(studentName, score);
                        break;
                    case 4:
                        //This does not behave properly.
                        System.out.println();
                        System.out.println("Input the name of the course that you want to display the grades for.");
                        dube.selectedCourse = dube.getCourseObj(scnr.nextLine());
                        dube.displayCourseAvg();
                        break;
                    case 5:
                        System.out.println();
                        System.out.println("Input the name of the course.");
                        dube.setSelectedCourse(scnr.nextLine());
                        System.out.println("Input the name of the assignment.");
                        dube.selectedCourse.setSelectedAssignment(scnr.nextLine());
                        System.out.println("Input a student name to display their grade.");
                        studentName = scnr.nextLine();
                        //This shows the students grade, not the grade for the assignment.
                        System.out.println(dube.selectedCourse.selectedAssignment.getScore(studentName));
                        break;
                    case 6:
                        System.out.println();
                        System.out.println("Input a course to export all course grades.");
                        courseName = scnr.nextLine();
//                        Teacher.exportCourseGrades(courseName);
                        break;
                    case 7:
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
                        matt.enroll(courseName);
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("Enter a course name to view grade.");
                        matt.setSelectedCourse(scnr.nextLine());
                        matt.displayCourseGrade(matt.selectedCourse.courseName);
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("Displaying all course grades.");
                        matt.displayAllCourseGrades();
                        break;
                    case 4:
                        System.out.println();
                        System.out.println("Enter a course name to export grades to a file.");
                        matt.exportCourseGrades(matt.selectedCourse);
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
