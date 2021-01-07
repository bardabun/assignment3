package bgu.spl.net.impl.BGRS.messages;

import bgu.spl.net.impl.BGRS.Database;
import bgu.spl.net.impl.BGRS.User;

public class UnregisterToCourse {
    private String username;
    private int courseNumber;
    private int opCode;
    private Database DB = Database.getInstance();

    public UnregisterToCourse(String username, int courseNumber) {
        this.username = username;
        this.courseNumber = courseNumber;
        this.opCode = 10;
    }

    public String execute() {
        return DB.unregisterToCourse(username, courseNumber) ?
                new Acknowledgement(opCode).execute(): new Error(opCode).execute();
    }
}
