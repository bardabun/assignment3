package bgu.spl.net.impl.BGRS.messages;

import bgu.spl.net.impl.BGRS.Database;
import bgu.spl.net.impl.BGRS.messages.BasicMessage.MessageUsernamePassword;

public class StudentRegister extends MessageUsernamePassword {

    public StudentRegister(String userName, String password) {
        super(userName, password);
        //opcode = 2;
    }

    @Override
    public boolean execute() {
        return DB.addUser(userName, pass, false);
    }
}
