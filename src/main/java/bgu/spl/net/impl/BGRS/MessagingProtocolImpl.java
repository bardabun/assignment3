package bgu.spl.net.impl.BGRS;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.impl.BGRS.messages.*;
import bgu.spl.net.impl.BGRS.messages.Error;

public class MessagingProtocolImpl<T> implements MessagingProtocol<String> {
    private boolean shouldTerminate = false;
    //when given logout - shouldterminate = true;  - > - like "bye" ..
    private String username;

    @Override
    public String process(String msg) {
        System.out.print(msg);
        int opCode = Integer.parseInt(msg.substring(1, 2));

        Error ERR = new Error(opCode);
        String[] usernameAndPassword;

        switch (opCode) {
            case 1:
                if(!isThereClientLoggedIn()) {
                    usernameAndPassword = getUserNameOrPassword(msg);
                    AdminRegister admin = new AdminRegister(usernameAndPassword[0], usernameAndPassword[1]);
                    return admin.execute();
                }
                return ERR.execute();
            case 2:
                if(!isThereClientLoggedIn()) {
                    usernameAndPassword = getUserNameOrPassword(msg);
                    StudentRegister student = new StudentRegister(usernameAndPassword[0], usernameAndPassword[1]);
                    return student.execute();
                }
                return ERR.execute();
            case 3:
                if(!isThereClientLoggedIn()) {
                    usernameAndPassword = getUserNameOrPassword(msg);
                    LoginRequest loginToSystem = new LoginRequest(usernameAndPassword[0], usernameAndPassword[1]);
                    String isLoggedIn = loginToSystem.execute();
                    this.username = isLoggedIn.equals("123") ? usernameAndPassword[0] : null;     //<---is there a better condition?
                    return isLoggedIn;
                }
                return ERR.execute();
            case 4:
                if(isThereClientLoggedIn())
                    return new LogoutRequest(username).execute();

                return ERR.execute();
            case 5:
                if(isThereClientLoggedIn())
                    return new RegisterToCourse(username, getCourseNumber(msg)).execute();

                return ERR.execute();
            case 6:
                if(isThereClientLoggedIn())
                    return new CheckKdamCourse(username, getCourseNumber(msg)).execute();

                return ERR.execute();
            case 7:
                if(isThereClientLoggedIn())
                    return new PrintCourseStatus(username,getCourseNumber(msg)).execute();

                return ERR.execute();
            case 8:
                if(isThereClientLoggedIn())
                    return new PrintStudentStatus(username).execute();

                return ERR.execute();
            case 9:
                if(isThereClientLoggedIn())
                    return new CheckIfRegistered(username, getCourseNumber(msg)).execute();

                return ERR.execute();
            case 10:
                if(isThereClientLoggedIn())
                    return new UnregisterToCourse(username, getCourseNumber(msg)).execute();

                return ERR.execute();
            case 11:
                if(isThereClientLoggedIn())
                    return new CheckMyCurrentCourses(username).execute();

                return ERR.execute();
        }
        return null;
    }

    //Return the course number out of the message
    private int getCourseNumber(String msg){
        return Integer.parseInt(msg.substring(2));
    }

    private boolean isThereClientLoggedIn() {
        return username != null;
    }
    //Return an array of username and password that client sent to the server
    private String[] getUserNameOrPassword(String msg){
        String[] userNameAndPassword = new String[2];
        userNameAndPassword[0] = msg.substring(2,msg.indexOf(0,2)); //<---~if I have an issue with this line check that~ "ch:" input should be- Unicode code point
        userNameAndPassword[1] = msg.substring(2 + userNameAndPassword[0].length(), msg.length()-1);

        return userNameAndPassword;
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
