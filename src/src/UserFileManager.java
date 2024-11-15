package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class UserFileManager {
    private static UserFileManager instance;
    private static final String USER_FILE = "User.csv";
    private static final String ADMIN_FILE = "Admin.csv";

    private UserFileManager() {}

    public static UserFileManager getInstance() {
        if (instance == null) {
            instance = new UserFileManager();
        }
        return instance;
    }

    public String getUserFile() {
        return USER_FILE;
    }

    public String getAdminFile() {
        return ADMIN_FILE;
    }

    public boolean doesUserExist(String userID) {
        return findUserInFile(USER_FILE, userID) || findUserInFile(ADMIN_FILE, userID);
    }

    public void viewUserDetails() {
        System.out.println("User details:");
        displayUsersFromFile(USER_FILE);
        displayUsersFromFile(ADMIN_FILE);
    }

    private void displayUsersFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String userID = userData[0];
                String username = userData[1];
                String email = userData[2];
                String userType = (filePath.equals(ADMIN_FILE)) ? "Admin" : userData[4];
                System.out.println("UserID: " + userID + ", Username: " + username + ", Email: " + email + ", UserType: " + userType);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void addUser(User newUser) {
        String filePath = newUser instanceof AdminUser ? ADMIN_FILE : USER_FILE;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String userData = String.join(",", newUser.userID, newUser.username, newUser.email, newUser.password, newUser.userType);
            writer.write(userData);
            writer.newLine();
            System.out.println("New user added: " + newUser.username);
        } catch (IOException e) {
            System.err.println("Error adding user to file: " + e.getMessage());
        }
    }

    public void updateUser(String userID, String newUsername, String newEmail) {
        List<String> lines = new ArrayList<>();
        boolean userUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(userID)) { 
                    userData[1] = newUsername; 
                    userData[2] = newEmail;    
                    line = String.join(",", userData);
                    userUpdated = true;
                }
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        if (userUpdated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
                System.out.println("User updated: " + newUsername);
            } catch (IOException e) {
                System.err.println("Error writing file: " + e.getMessage());
            }
        } else {
            System.out.println("User not found for update.");
        }
    }

    public boolean renameFile(String oldFileName, String newFileName) {
        File file = new File(oldFileName);
        if (file.exists() && file.renameTo(new File(newFileName))) {
            System.out.println("File renamed to: " + newFileName);
            return true;
        } else {
            System.out.println("Failed to rename file.");
            return false;
        }
    }

    private boolean findUserInFile(String filePath, String userID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(userID)) { 
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return false;
    }
}


