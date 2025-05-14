import java.util.HashMap;

public class Assignment {
    String courseName;
    String assignmentName;
    int maxScore;
    //make private?
    HashMap<String, Double> grades = new HashMap<>();
    static HashMap<String, Assignment> allAssignments = new HashMap<>();

    //Simple Constructor
    public Assignment (String name, int max) {
        this.assignmentName = name;
        this.maxScore = max;
        allAssignments.put(this.assignmentName, this);
    }

    public static Assignment getAssn(String assnName) throws Exception {
        if (allAssignments.containsKey(assnName)) {
            return allAssignments.get(assnName);
        }

        throw new Exception("Invalid assignment name!");
    }

    //Inputs a studentName as a key, and their score as a value into the grades HashMap.
    public void addScore(String studentName, double score) {
        grades.put(studentName, score);
    }

    //Sums up all scores for the Assignment and finds the average.
    public double calcAvgScore() {
        double sum = 0;
        for (double i : grades.values()) {
            sum += i;
        }
        return ((sum) / grades.size());
    }

    //Checks for a student name from the list to pull the right score.
    public double getScore(String studentName) {
        return grades.get(studentName);
    }
}