package bgu.spl.net.impl.BGRS.messages;

import bgu.spl.net.impl.BGRS.messages.BasicMessage.MessageUsernamePassword;

public class LoginRequest extends MessageUsernamePassword {
    public  LoginRequest(String user, String pass){
        super(user,pass);
        //opcode=3;
    }
    @Override
    public boolean execute() {
        DB.Login(userName,pass);
        return false;
    }
}
