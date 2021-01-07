package bgu.spl.net.impl.BGRS.messages;

import bgu.spl.net.impl.BGRS.messages.BasicMessage.MessageUsername;

public class LogoutRequest extends MessageUsername {
    private int opCode;

    public LogoutRequest(String user){
        super(user);
        opCode = 4;
    }

    @Override
    public String execute() {

         if(DB.Logout(userName))
             return new Acknowledgement(opCode).execute();
         else
             return new Error(opCode).execute();
    }
}
