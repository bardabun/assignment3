package bgu.spl.net.impl.BGRS;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.impl.BGRS.messages.*;

public class MessagingProtocolImpl<T> implements MessagingProtocol<String> {
    private boolean shouldTerminate = false;
    String opCode;
    private String username;

    @Override
    public String process(String msg) {
       // shouldTerminate ;
        int opCode = Integer.parseInt(msg.substring(1,2));
        String[] usernameAndPassword;
        final String ERR = "13";
        switch (opCode) {
            case 1:
                if(!isThereClientLoggedIn()) {
                    usernameAndPassword = getUserNameOrPassword(msg);
                    AdminRegister admin = new AdminRegister(usernameAndPassword[0], usernameAndPassword[1]);
                    return isComplete(admin.execute());
                }
                return ERR;
            case 2:
                if(!isThereClientLoggedIn()) {
                    usernameAndPassword = getUserNameOrPassword(msg);
                    StudentRegister student = new StudentRegister(usernameAndPassword[0], usernameAndPassword[1]);
                    return isComplete(student.execute());
                }
                return ERR;
            case 3:
                if(!isThereClientLoggedIn()) {
                    usernameAndPassword = getUserNameOrPassword(msg);
                    LoginRequest loginToSystem = new LoginRequest(usernameAndPassword[0], usernameAndPassword[1]);
                    boolean isLoggedIn = loginToSystem.execute();
                    this.username = isLoggedIn ? usernameAndPassword[0] : null;
                    return isComplete(isLoggedIn);
                }
                return ERR;
            case 4:
                if(!isThereClientLoggedIn())
                    return isComplete(new LogoutRequest(username).execute());       //<------ why 'username' always null?!
                return ERR;
            case 5:
                if(!isThereClientLoggedIn())
                    return isComplete(new RegisterToCourse(username).execute());
                return ERR;

            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
        }
        return null;
    }

    private boolean isThereClientLoggedIn() {
        return username != null;
    }

    //return an array of username and password that client sent to the server
    private String[] getUserNameOrPassword(String msg){
        String[] userNameAndPassword = new String[2];
        userNameAndPassword[0] = msg.substring(2,msg.indexOf(0,2)); //<---~if I have an issue with this line check that~ "ch:" input should be- Unicode code point
        userNameAndPassword[1] = msg.substring(2 + userNameAndPassword[0].length(), msg.length()-1);

        return userNameAndPassword;
    };
    //return the suitable message for the registration process
    private String isComplete(boolean answerTypeMessage){
        return(answerTypeMessage ? "12" : "13");
    }



    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
