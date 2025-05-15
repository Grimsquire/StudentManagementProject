import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scnr = new Scanner(System.in);
        int menuSelection;

        //List of preset entries for testing/demonstration.
        Teacher genericTeacher= new Teacher("Jane Doe", "hdh@fsd", 99);
        Teacher dube = new Teacher("Tafadzwa Dube","best@ever",35);

        Student genericStudent = new Student("John Doe", "sdfsdf@aaaaa", 99);
        Student matt = new Student("Matt", "grg@dfdf", 31);
        Student brody = new Student("Brody","qwert@qwerty", 24);
        Student jack = new Student("Jack","rrrrr@rrrrr",22);
        Student kate = new Student("Kate","ffff@ffff",23);

        dube.createCourse("CIS210", "Intro to computer science", "MWF 2:00 - 3:20", 3413);

        matt.enroll("CIS210");
        brody.enroll("CIS210");
        jack.enroll("CIS210");
        kate.enroll("CIS210");

        dube.setSelectedCourse("CIS210");
        matt.setSelectedCourse("CIS210");
        dube.addAssignment("Homework1", 100);
        dube.addAssignment("Group Project",100);


        dube.selectedCourse.setSelectedAssignment("Homework1");
        dube.addStudentScore("Matt", 50);
        dube.addStudentScore("Brody",80);
        dube.addStudentScore("Jack",99);
        dube.addStudentScore("Kate",81);
        dube.selectedCourse.setSelectedAssignment("Group Project");
        dube.addStudentScore("Matt",100);
        dube.addStudentScore("Brody",100);
        dube.addStudentScore("Jack",100);
        dube.addStudentScore("Kate",100);

        //TODO
        //Make a while loop.
        System.out.println("Please input a user ID.");
        int inputID = scnr.nextInt();
        scnr.nextLine();    //Clears the line left after the last input.

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
                        System.out.println();
                        System.out.println("Input the name of the course that you want to display the grades for.");
                        dube.setSelectedCourse(scnr.nextLine());
                        System.out.println("Average score for " + dube.selectedCourse.courseName + " is " + dube.displayCourseAvg());
                        break;
                    case 5:
                        System.out.println();
                        System.out.println("Input the name of the assignment that you want to display the average for.");
                        dube.selectedCourse.setSelectedAssignment(scnr.nextLine());
                        System.out.println("Average score for " + dube.selectedCourse.selectedAssignment.assignmentName + " is " + dube.displayAssignmentAvg());
                        break;
                    case 6:
                        System.out.println();
                        System.out.println("Input the name of the course.");
                        dube.setSelectedCourse(scnr.nextLine());
                        System.out.println("Input the name of the assignment.");
                        dube.selectedCourse.setSelectedAssignment(scnr.nextLine());
                        System.out.println("Input a student name to display their grade.");
                        studentName = scnr.nextLine();
                        System.out.println(dube.selectedCourse.selectedAssignment.getScore(studentName));
                        break;
                    case 7:
                        System.out.println();
                        System.out.println("Input a course to export all course grades.");
                        dube.setSelectedCourse(scnr.nextLine());
                        dube.exportCourseGrades(dube.selectedCourse);
                        System.out.println("Done!");
                        break;
                    case 8:
                        System.out.println();
                        continue;
                    default:
                        System.out.println();
                        System.out.println("Invalid selection.");
                        break;
                }
            } while (menuSelection != 8);
        } else if (inputID > 10000) {
            do {
                System.out.println("Please select a menu option:");
                System.out.println("1: Enroll in a course.");
                System.out.println("2: Check a course grade.");
                System.out.println("3: Check all course grades.");
                System.out.println("4: Export my course grades.");
                System.out.println("5: Exit");
                menuSelection = scnr.nextInt();

                scnr.nextLine();
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
                        matt.setSelectedCourse(scnr.nextLine());
                        matt.exportCourseGrades(genericStudent.selectedCourse);
                        break;
                    case 5:
                        System.out.println();
                        continue;
                    default:
                        System.out.println();
                        System.out.println("Invalid selection.");
                }
                System.out.println();
            } while (menuSelection != 5);
        } else {
            System.out.println("Invalid ID.");
        }
    }
}
