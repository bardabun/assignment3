package bgu.spl.net.impl.BGRS;

public class User {
    final private String username;
    final private String password;
     private boolean isAdmin;
    private boolean isLoggedIn;

    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isLoggedIn = false;
    }

    public void LogIn(){
        isLoggedIn=true;
    }
    public void LogOut(){
        isLoggedIn=false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getPassword() {
        return password;
    }
}
