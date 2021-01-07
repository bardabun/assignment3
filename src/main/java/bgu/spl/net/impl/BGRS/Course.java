package bgu.spl.net.impl.BGRS;

import java.util.Vector;

public class Course {
    private int courseNum;
    private String courseName;
    private Vector<Integer> KdamCoursesList;
    private int numOfMaxStudents;
    private int numRegistered;
    private Vector<User> listOfStudents;

    public Course(int courseNum, String courseName, Vector<Integer> kdamCoursesList, int numOfMaxStudents, int studentsRegistered) {
        this.courseNum = courseNum;
        this.courseName = courseName;
        KdamCoursesList = kdamCoursesList;
        this.numOfMaxStudents = numOfMaxStudents;
        listOfStudents=new Vector<>();
        this.numRegistered = studentsRegistered;
    }

    public int getNumOfMaxStudents() {
        return numOfMaxStudents;
    }

    public int getCourseNum() {
        return courseNum;
    }



    public Vector<Integer> getKdamCoursesList() {
        return KdamCoursesList;
    }

    public int getStudentsRegistered() {
        return numRegistered;
    }

    public void setStudentsRegistered(int studentsRegistered) {
        this.numRegistered = studentsRegistered;
    }

    public Vector<User> getListOfStudents() {
        return listOfStudents;
    }

    public void addUser(User user) {
        listOfStudents.add(user);
    }
}
