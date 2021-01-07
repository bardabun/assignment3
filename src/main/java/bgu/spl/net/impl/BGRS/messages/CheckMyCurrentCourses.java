package bgu.spl.net.impl.BGRS.messages;

import bgu.spl.net.impl.BGRS.Database;
import bgu.spl.net.impl.BGRS.User;

public class CheckMyCurrentCourses {
    private String username;
    private int opCode;
    private Database DB = Database.getInstance();

    public CheckMyCurrentCourses(String username) {
        this.username = username;
        this.opCode = 11;
    }

    public String execute() {
        return DB.CheckMyCurrentCourses(username);
    }
}
