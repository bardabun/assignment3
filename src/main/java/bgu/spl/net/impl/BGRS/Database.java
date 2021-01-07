package bgu.spl.net.impl.BGRS;

import bgu.spl.net.impl.BGRS.messages.Acknowledgement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleToIntFunction;

/**
     * Passive object representing the Database where all courses and users are stored.
     * <p>
     * This class must be implemented safely as a thread-safe singleton.
     * You must not alter any of the given public methods of this class.
     * <p>
     * You can add private fields and methods to this class as you see fit.
     */
    public class Database {

        private HashMap<Integer, Course> courseHashMap; //number course to course

        private ConcurrentHashMap <String, User> userConcurrentHashMap; // username to user

        //to prevent user from creating new Database
        private Database() {
            // TODO: implement
            courseHashMap = new HashMap<>();
            userConcurrentHashMap=new ConcurrentHashMap<>();

            initialize("Courses.txt");

        }


    private static class SingletonHolder {
            private static Database instance = new Database();
        }

        /**
         * Retrieves the single instance of this class.
         */
        public static Database getInstance() {
            return SingletonHolder.instance;        }

        /**
         * loades the courses from the file path specified
         * into the Database, returns true if successful.
         */
        boolean initialize(String coursesFilePath) {
            // TODO: implement
            try {
                File courseFile = new File(coursesFilePath);
                Scanner reader = new Scanner(courseFile);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return false;
        }


        public boolean addUser(String user, String pass, boolean isAdmin) {
            if (userConcurrentHashMap.containsKey(user)){
                //username already exists
                return  false;
             }
            else{
                if( isAdmin) {
                    User admin = new User(user, pass, true);
                    userConcurrentHashMap.put(user, admin);
                    return true;
                }
                else //not admin
                {
                    User user1 = new User(user,pass,false);
                    userConcurrentHashMap.put(user, user1);
                    return true;
                }
            }
    }

    public boolean Login(String user, String pass){
            User login = userConcurrentHashMap.get(user);
            if(login == null || login.isLoggedIn() | !(login.getPassword().equals(pass)) ){
                //mo such username || already logged in | password is not correct
                return  false;
            }
            else
            {
                login.LogIn();
                return true;
            }
    }

    public boolean Logout(String user){
            User logout = userConcurrentHashMap.get(user);
            if(logout ==null || !(logout.isLoggedIn()) ){
                //no such username || not logged in ..
                return false;
            }
            else
            {
                logout.LogOut();
                return true;
            }
    }
    public boolean CourseRegister(String user, Integer courseNum){
        User userReg = userConcurrentHashMap.get(user);
        Course toRegister = courseHashMap.get(courseNum);
        if(userReg== null || userReg.getIsAdmin())return false;
        //user isn't exist or admin can't register to a course
        if(!userReg.isLoggedIn())return false; //the user is not logged in
        if(toRegister==null)return false; //no such course is exist
        if(isFull(toRegister))return false; //there's no place in the course.
        if( ! hasFinishedKdam(userReg,toRegister)) return false; //the student does not have all the Kdam courses

        int numOfStudentRegistered = toRegister.getStudentsRegistered();
        numOfStudentRegistered++;
        toRegister.setStudentsRegistered(numOfStudentRegistered);
        toRegister.addUser(userReg);
        userReg.getKdamCoursesList().add(courseNum); //register to the course



        return true;
    }

    private boolean hasFinishedKdam(User userReg, Course toRegister) {
        Vector<Integer> userKdamCourses = userReg.getKdamCoursesList();
        for(Integer i: toRegister.getKdamCoursesList()){ //go throw the kdam courses for the specific course
            if(!userKdamCourses.contains(i))
                return false;
        }
        return true;
    }

    private boolean isFull(Course toRegister) {
            return (toRegister.getNumOfMaxStudents() - toRegister.getStudentsRegistered() == 0 ) ;
    }

    //Return true if removed user registration from specific course
    public boolean unregisterToCourse(String username, int courseNumber){
            User user = userConcurrentHashMap.get(username);
            if(!user.getIsAdmin()){
                Integer removed = user.getKdamCoursesList().remove(courseNumber);
                return removed .equals(courseNumber);
            }
            return false;
    }
    //Return user's course list
    public String CheckMyCurrentCourses(String username) {
        return userConcurrentHashMap.get(username).getKdamCoursesList().toString();
    }


    public String kdamCheck(String userName) {
        User user = userConcurrentHashMap.get(userName);
        String output = null;
        if(!user.getIsAdmin()) { //only student
                output =  user.getKdamCoursesList().toString();
            }
            return output;
    }
}
