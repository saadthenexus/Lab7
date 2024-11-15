package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

    public boolean authenticateUser(String username, String password) {
        if (validateCredentials(userFileManager.getUserFile(), username, password)) {
            return true;
        }

        if (validateCredentials(userFileManager.getAdminFile(), username, password)) {
            return true;
        }

        return false;
    }

    public boolean validateCredentials(String filePath, String username, String password) {
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


