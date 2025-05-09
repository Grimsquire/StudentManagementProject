import java.util.HashMap;

public class Course {
    //TODO
    //Make these private?
    public Assignment selectedAssignment;
    public String courseName;
    public String courseDescription;
    public String meetingTime;
    public int roomNumber;
    public String instructorInfo;

    //HashMaps store the current Students, their current class grades, and Assignment objects respectively.
    public HashMap<String, Integer> studentRoster = new HashMap<>();
    public HashMap<String, Double> studentGrades = new HashMap<>();
    public HashMap<String, Assignment> coursework = new HashMap<>();

    //Simple Constructor
    public Course (String courseName) {
        this.courseName = courseName;
        this.courseDescription = "No description given.";
        String meetingTime = "Time to be determined.";
        String roomNumber = "Room number unknown.";
    }

    //Parameterized constructor used in Teacher class when using createCourse method.
    public Course (String name, String description, String meetingTime, int roomNumber, String instructor) {
        this.courseName = name;
        this.courseDescription = description;
        this.meetingTime = meetingTime;
        this.roomNumber = roomNumber;
        this.instructorInfo = instructor;
    }

    //Creates a new Assignment object and adds it to the coursework HashMap. The assignmentName and maxScore parameters
    //are used in the constructor for a new Assignment, and assignmentName is also used as a key to find the Assignment object.
    public void addAssignment(String assignmentName, int maxScore) {
        Assignment classwork = new Assignment(assignmentName, maxScore);
        classwork.courseName = this.courseName;
        coursework.put(assignmentName, classwork);
    }

    //Removes an assignment from the coursework HashMap
    public void removeAssignment(String assignmentName) {
        coursework.remove(assignmentName);
    }

    //Searches the coursework HashMap for assignmentName and returns the corresponding Assignment object.
    public Assignment findAssignmentObj(String assignmentName) {
        return coursework.get(assignmentName);
    }

    //Sets the selectedAssignment as the one returned by the findAssignmentObj method.
    public void setSelectedAssignment(String assignmentName) {
        this.selectedAssignment = this.findAssignmentObj(assignmentName);
    }

    //Takes a studentName as a key and adds studentID to the studentRoster HashMap. These parameters
    //are actually defined by this.____ when called by a Students enroll method.
    public void addStudent(String studentName, int studentID) {
        studentRoster.put(studentName,studentID);
    }

    //Removes the given student object from the studentRoster HashMap.
    public void removeStudent(String studentName) {
        studentRoster.remove(studentName);
        //TODO
        //Make sure to remove all instances of that student name (i.e. key) from all assignment HashMaps.
    }

    //Simple print out of all keys (i.e. studentName) from the studentRoster HashMap.
    public void showStudentRoster() {
        System.out.println(studentRoster.keySet());
    }

    //Calls a method from an Assignment Object to add a student name and score into a grade HashMap.
    public void addStudentScore(String assignmentName, String studentName, int score) {
        findAssignmentObj(assignmentName).addScore(studentName, score);
    }

    public double getStudentGrade(String username) throws Exception {
        double studGrade = studentGrades.get(username);
        if (studGrade == -1) {
            throw new Exception("Student not found!");
        }
        return studGrade;
    }

    //TODO
    //Finds the average grade from all Students and finds what the average grade is for the selectedAssignment.
    //this should be moved over to the Assignment class for the sake of encapsulation and clarity.

    public double getAssignmentAvg() throws Exception {
        double avgScre = selectedAssignment.calcAvgScore();
        if (avgScre == -1) {
            throw new Exception("No grades are submitted for this assignment.");
        }
        return avgScre;
    }

    //Finds the average grade from all Students and finds what the average grade is for the Course.
    public double getCourseAvg() {
        if (studentGrades.isEmpty()) {
            return -1; // empty
        }

        double sum = 0;
        for (double i : studentGrades.values()) {
            sum += i;
        }
        return ((sum) / studentGrades.size());
    }

    public void displayAllInfo() {
        System.out.println("-" + this.courseName + "-");
        System.out.println("Instructor: " + this.instructorInfo);
        System.out.println("Description: " + this.courseDescription);
        System.out.println("Meeting times: " + this.meetingTime);
        System.out.println("Room Number: " + this.roomNumber);
    }

    //Getters and Setters
    public String getCourseName() {
        return courseName;
    }
    public String getCourseDescription() {
        return courseDescription;
    }
    public String getMeetingTime() {
        return meetingTime;
    }
    public String getInstructorInfo() {
        return instructorInfo;
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setInstructor(String instructor) {
        this.instructorInfo = instructorInfo;
    }


}