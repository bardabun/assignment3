package bgu.spl.net.impl.BGRS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
     * Passive object representing the Database where all courses and users are stored.
     * <p>
     * This class must be implemented safely as a thread-safe singleton.
     * You must not alter any of the given public methods of this class.
     * <p>
     * You can add private fields and methods to this class as you see fit.
     */
    public class Database {


        //to prevent user from creating new Database
        private Database() {
            // TODO: implement

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


    }
}
