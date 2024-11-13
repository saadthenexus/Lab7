public class UserAuthenticator {
    private UserFileManager userFileManager;

    public UserAuthenticator(UserFileManager userFileManager) {
        this.userFileManager = userFileManager;
    }

    public boolean authenticateUser(String username) {
        return userFileManager.doesUserExist(username);
    }
}

