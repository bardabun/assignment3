package bgu.spl.net.impl.BGRS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

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
        if(!userReg.isLoggedIn())return false; //the user is not connected
        if(toRegister==null)return false; //no such course is exist
        if(isFull(toRegister))return false; //there's no place in the course.



    }

    private boolean isFull(Course toRegister) {
            return (toRegister.getNumOfMaxStudents() - toRegister.getStudentsRegistered() == 0 ) ;
    }

}
