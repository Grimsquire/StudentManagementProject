import java.util.HashMap;

public class AvailableCourses {
    //Stores all created Course objects in a hashmap with Course.name as a key.
    static HashMap<String, Course> courseList = new HashMap<>();

    //Puts the actual course into the HashMap.
    public static void addNewCourse(String courseName, String description, String meetingTime, int roomNumber, String instructor) {
        Course newCourse = new Course(courseName, description, meetingTime, roomNumber, instructor);
        courseList.put(courseName, newCourse);
    }

    public static void displayAll() {
        for (String key : courseList.keySet()) {
            System.out.println(key);
        }
    }

    //Takes in a String courseName and uses that as a key to search the HashMap courseList and return the corresponding Course object.
    public static Course findCourseObj(String courseName) {
        return courseList.get(courseName);
    }

}
