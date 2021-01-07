package bgu.spl.net.impl.BGRS.messages;

import bgu.spl.net.impl.BGRS.messages.BasicMessage.MessageUsernamePassword;

public class LoginRequest extends MessageUsernamePassword {
    private int opCode;
    public  LoginRequest(String user, String pass){
        super(user,pass);
        opCode = 3;
    }
    @Override
    public String execute() {
        if(DB.Login(userName,pass))
            return new Acknowledgement(opCode).execute();
        else
            return new Error(opCode).execute();
    }
}
