package bgu.spl.net.impl.BGRS;

import java.util.Vector;

public class Course {
    private int courseNum;
    private String courseName;
    private Vector<Integer> KdamCoursesList;
    private int numOfMaxStudents;
    private int studentsRegistered;


    public Course(int courseNum, String courseName, Vector<Integer> kdamCoursesList, int numOfMaxStudents, int studentsRegistered) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        KdamCoursesList = kdamCoursesList;
        this.numOfMaxStudents = numOfMaxStudents;

        this.studentsRegistered = studentsRegistered;
    }

    public int getNumOfMaxStudents() {
        return numOfMaxStudents;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public int getStudentsRegistered() {
        return studentsRegistered;
    }
}
