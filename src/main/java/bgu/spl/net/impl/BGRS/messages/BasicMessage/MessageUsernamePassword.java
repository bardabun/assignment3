package bgu.spl.net.impl.BGRS.messages.BasicMessage;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.api.MessagingProtocolImpl;
import bgu.spl.net.impl.BGRS.Database;
import sun.plugin2.message.Message;

public abstract  class MessageUsernamePassword {
    protected String userName;
    protected String pass;
   // protected short opcode;
    protected Database DB=Database.getInstance();
    public MessageUsernamePassword(String username , String password){
        this.userName=username;
        this.pass=password;
    }
    public abstract boolean execute();
}
