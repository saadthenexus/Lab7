package src;

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

    public boolean authenticateUser(String userID) {
        return userFileManager.doesUserExist(userID);
    }

    private boolean validateCredentials(String filePath, String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[1].equals(username) && userData[3].equals(password)) {
                    return true; 
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return false;
    }
}


