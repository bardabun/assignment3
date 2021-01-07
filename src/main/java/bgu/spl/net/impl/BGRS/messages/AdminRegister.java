package bgu.spl.net.impl.BGRS.messages;

import bgu.spl.net.impl.BGRS.Database;
import bgu.spl.net.impl.BGRS.messages.BasicMessage.MessageUsernamePassword;

public class AdminRegister extends MessageUsernamePassword {
    private int opCode;
    public AdminRegister(String userName, String password){
        super(userName,password);
        opCode = 1;
    }
    @Override
    public String execute() {

        if(DB.addUser(userName, pass , true))
            return new Acknowledgement(opCode).execute();
        else
            return new Error(opCode).execute();
    }

}
