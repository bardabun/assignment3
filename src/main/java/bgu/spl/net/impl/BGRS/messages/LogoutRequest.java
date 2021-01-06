package bgu.spl.net.impl.BGRS.messages;

import bgu.spl.net.impl.BGRS.messages.BasicMessage.MessageUsername;

public class LogoutRequest extends MessageUsername {
    public LogoutRequest(String user){
        super(user);
    }
    @Override
    public boolean execute() {
        return DB.Logout(userName);
    }
}
