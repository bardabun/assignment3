package bgu.spl.net.impl.BGRS.messages;

import bgu.spl.net.impl.BGRS.Database;
import bgu.spl.net.impl.BGRS.messages.BasicMessage.MessageUsername;

public class CheckIfRegistered extends MessageUsername {
    private String username;
    private int courseName;
    private int opCode;
    public CheckIfRegistered(String username, int courseNumber) {

    }

    @Override
    public String execute() {
        return null;
    }
}
