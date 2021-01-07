package bgu.spl.net.impl.BGRS.messages;

import bgu.spl.net.impl.BGRS.messages.BasicMessage.MessageUsername;

public class CheckKdamCourse extends MessageUsername {

    public CheckKdamCourse(String user) {
        super(user);
        opcode=6;
    }

    @Override
    public String execute() {
        String output = DB.kdamCheck(userName);
        if(output!=null)
            return new Acknowledgement(opcode).execute() + "/n" + output;
        else
            return new Error(opcode).execute();

    }
}
