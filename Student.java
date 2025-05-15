import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;

public class Student extends Person {
    public Student(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.idNumber = nextId + 10000;
        //Increments static variable nextId so that no 2 Student/Teacher objects have the same idNumber
        ++nextId;
    }

    protected void enroll(String courseName) {
        Course courseEnrolling = getCourseObj(courseName); //Sets course obj to course wanting to be enrolled in
        if (courseEnrolling != null) {
            //Use course wanted, add to both schedule and student roster
            this.schedule.add(courseEnrolling);
            courseEnrolling.addStudent(this.name, this.getIdNumber());
        } else {
            //Check if course selected is valid
            System.out.println("Cancelled.");
        }
    }

    //Prints out all the Courses from the Students schedule ArrayList.
    protected void displaySchedule() {
        System.out.print(this.name + "'s schedule: ");
        for (Course newCourse : schedule) {
            System.out.print(" - " + newCourse.courseName);
        }
    }

    //Prints out a Students letter and number grade for the Course provided.
    protected void displayCourseGrade(String courseName) throws Exception {
        //Waiting for specific method that allows for individual course grades to be fetched
        for (Course course : schedule) {
            if (course.getCourseName().equals(courseName)) {
                selectedCourse = course;
            }
        }
        if (selectedCourse != null) {
            double courseGrade = selectedCourse.getStudentGrade(this.name); //Update method with specific method
            if (courseGrade >= 90) {
                System.out.println("You have a: " + courseGrade + " (A), in " + courseName);
            } else if (courseGrade >= 80) {
                System.out.println("You have a: " + courseGrade + " (B), in " + courseName);
            } else if (courseGrade >= 70) {
                System.out.println("You have a: " + courseGrade + " (C), in " + courseName);
            } else if (courseGrade >= 60) {
                System.out.println("You have a: " + courseGrade + " (D), in " + courseName);
            } else {
                System.out.println("You have a: " + courseGrade + " (F), in " + courseName);
            }
        } else {
            System.out.println("Course " + courseName + " not found.");
        }
    }

    //Prints out all of a Students letter and number grades for each Course.
    protected void displayAllCourseGrades() throws Exception {
        //Needs unit testing
        for (Course course : schedule) {
            String courseName = course.getCourseName();
            displayCourseGrade(courseName);
        }
    }

    //Export a Students grades for a given Course to a .txt file.
    protected void exportCourseGrades(Course course) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedCourse.getCourseName().replaceAll(" ", "_") + "_" + this.name.replaceAll(" ", "_") + "_grades" + ".txt"))) {
            for (Map.Entry<String, Integer> entry : selectedCourse.studentRoster.entrySet()) { // Iterate through student roster
                String studentName = entry.getKey();
                if (this.name == studentName) {
                    // Retrieve student's grade for selected assignment, if available
                    double studentGrade = selectedCourse.selectedAssignment.grades.containsKey(studentName)
                            ? selectedCourse.selectedAssignment.grades.get(studentName)
                            : 0.0; // Default to 0 if not found

                    writer.write(studentName + ": " + studentGrade);
                    writer.newLine(); // Move to the next line in the file
                }
            }
        }
    }


    protected void exportAllCourseGrades(Student student) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.name.replaceAll(" ", "_") + "_all_course_grades" + ".txt"))) {
            for (Course course : student.schedule) { // Iterate through student roster
                writer.write("Course: " + course.getCourseName()); //Iterate through course list
                writer.newLine();

                if (course.selectedAssignment != null) { //Goes through every assignment, gets grade and prints it
                    writer.write("Assignment: " + course.selectedAssignment.assignmentName);

                    double studentGrade = course.selectedAssignment.grades.getOrDefault(student.getName(), 0);
                    writer.write(" Grade: " + studentGrade);
                    writer.newLine();

                    writer.write("---");
                    writer.newLine();
                }
            }
        }
    }
}