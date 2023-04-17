import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    private int studId;
    private String name;
    private ArrayList<Course> courseList = new ArrayList<>();
    private char overallGrade;
    private int percentile;

}

class Course{
    private String courseName;
    private int totalCredit;
    private int attainedCredit;
    private char grades;
}

