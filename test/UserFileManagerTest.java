package test;

import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

import src.UserFileManager;
import src.RegularUser;
import src.User;

class UserFileManagerTest {
    private static final String TEST_USER_FILE = "User.csv";
    private static final String TEST_ADMIN_FILE = "Admin.csv";
    private UserFileManager userFileManager;

    @BeforeEach
    void setUp() throws IOException {
        userFileManager = UserFileManager.getInstance();

        createFile(TEST_USER_FILE, "1,johndoe,johndoe@example.com,pass123,Regular");
        createFile(TEST_ADMIN_FILE, "2,admin,admin@example.com,adminpass,Admin");
    }

    @AfterEach
    void tearDown() {
        new File(TEST_USER_FILE).delete();
        new File(TEST_ADMIN_FILE).delete();
    }

    private void createFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }

    @Test
    void testDoesUserExist() {
        assertTrue(userFileManager.doesUserExist("1"), "User 'johndoe' should exist");
        assertTrue(userFileManager.doesUserExist("2"), "User 'admin' should exist");
        assertFalse(userFileManager.doesUserExist("3"), "User 'nonexistent' should not exist");
    }

    @Test
    void testAddUser() {
        User newUser = new RegularUser("3", "newuser", "newuser@example.com", "newpass");
        userFileManager.addUser(newUser);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_USER_FILE))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("3") && line.contains("newuser") && line.contains("newuser@example.com")) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "New user 'newuser' should be added to TestUser.csv");
        } catch (IOException e) {
            fail("Exception occurred while verifying addUser: " + e.getMessage());
        }
    
    }

    @Test
    void testUpdateUser() {
        userFileManager.updateUser("1", "johnsmith", "johnsmith@example.com");

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_USER_FILE))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("johnsmith") && line.contains("johnsmith@example.com")) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "User 'johndoe' should be updated to 'johnsmith'");
        } catch (IOException e) {
            fail("Exception occurred during update test: " + e.getMessage());
        }
    }

    @Test
    void testRenameFile() {
        String newFileName = "RenamedUser.csv";
        boolean result = userFileManager.renameFile(TEST_USER_FILE, newFileName);

        assertTrue(result, "File should be renamed successfully");
        assertTrue(new File(newFileName).exists(), "Renamed file should exist");
        
        new File(newFileName).delete();
    }

    @Test
    void testViewUserDetails() {
        assertDoesNotThrow(() -> userFileManager.viewUserDetails(), "viewUserDetails() should not throw exceptions");
    }
}

