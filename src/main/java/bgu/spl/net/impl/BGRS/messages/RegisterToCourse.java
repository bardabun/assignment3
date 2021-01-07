package bgu.spl.net.impl.BGRS.messages;

import bgu.spl.net.impl.BGRS.messages.BasicMessage.MessageUsername;

public class RegisterToCourse extends MessageUsername {
    private int courseNumber;
    public RegisterToCourse(String user, int courseNum) {
        super(user);
        this.courseNumber=courseNum;
        opcode=5;
    }

    @Override
    public String execute() {
        if(DB.CourseRegister(userName, courseNumber))
            return new Acknowledgement(opcode).execute();
        else
            return new Error(opcode).execute();
        
    }
}
