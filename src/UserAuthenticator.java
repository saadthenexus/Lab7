public class UserAuthenticator {
    private static UserAuthenticator instance;
    private UserFileManager userFileManager;

    private UserAuthenticator() {
        userFileManager = UserFileManager.getInstance();
    }

    public static UserAuthenticator getInstance() {
        if (instance == null) {
            instance = new UserAuthenticator();
        }
        return instance;
    }

    public boolean authenticateUser(String username) {
        return userFileManager.doesUserExist(username);
    }
}


