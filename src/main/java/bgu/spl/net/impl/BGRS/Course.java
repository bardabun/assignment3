package bgu.spl.net.impl.BGRS;

import java.util.Vector;

public class Course {
    private int courseNum;
    private String courseName;
    private Vector<Integer> KdamCoursesList;
    private int numOfMaxStudents;

    public Course(int courseNum, String courseName, Vector<Integer> kdamCoursesList, int numOfMaxStudents) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        KdamCoursesList = kdamCoursesList;
        this.numOfMaxStudents = numOfMaxStudents;

    }

    public int getNumOfMaxStudents() {
        return numOfMaxStudents;
    }
}
