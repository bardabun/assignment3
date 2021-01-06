package bgu.spl.net.impl.BGRS.messages;

import bgu.spl.net.impl.BGRS.Database;
import bgu.spl.net.impl.BGRS.messages.BasicMessage.MessageUsernamePassword;

public class AdminRegister extends MessageUsernamePassword {

    public AdminRegister(String userName, String password){
        super(userName,password);
        //opcode=1;
    }
    @Override
    public boolean execute() {

        return  DB.addUser(userName, pass , true);
    }

}
