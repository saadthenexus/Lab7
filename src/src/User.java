package src;

public abstract class User {
    protected String userID;
    protected String username;
    protected String email;
    protected String password;
    protected String userType;

    public User(String userID, String username, String email, String password, String userType) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
}
