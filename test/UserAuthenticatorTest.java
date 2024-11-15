package test;

import org.junit.jupiter.api.*;

import src.UserAuthenticator;
import src.UserFileManager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class UserAuthenticatorTest {
    private UserAuthenticator userAuthenticator;
    private static final String TEST_USER_FILE = "User.csv";
    private static final String TEST_ADMIN_FILE = "Admin.csv";
    private UserFileManager userFileManager;

 @BeforeEach
    void setUp() throws IOException {
        userFileManager = UserFileManager.getInstance();

        createFile(TEST_USER_FILE, 
            "1,johndoe,johndoe@example.com,pass123,Regular\n" +
            "2,janedoe,janedoe@example.com,password456,Regular");
        createFile(TEST_ADMIN_FILE, "2,admin,admin@example.com,adminpass,Admin");
        userAuthenticator = UserAuthenticator.getInstance();
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
    void testAuthenticateUser() {
        assertTrue(userAuthenticator.authenticateUser("1"), "User 'johndoe' should authenticate successfully");
        assertFalse(userAuthenticator.authenticateUser("3"), "User 'unknownuser' should not authenticate");
    }

    @Test
    void testAuthenticateUser2() {
        assertTrue(userAuthenticator.authenticateUser("johndoe", "pass123"), "Valid Regular user should authenticate");
        assertTrue(userAuthenticator.authenticateUser("admin", "adminpass"), "Valid Admin user should authenticate");

        assertFalse(userAuthenticator.authenticateUser("johndoe", "wrongpass"), "Invalid password should fail authentication");
        assertFalse(userAuthenticator.authenticateUser("unknown", "pass123"), "Unknown user should fail authentication");
    }

    @Test
    void testValidateCredentials() {
        assertTrue(userAuthenticator.validateCredentials(TEST_USER_FILE, "johndoe", "pass123"), 
            "Valid credentials for 'johndoe' should return true");
        assertTrue(userAuthenticator.validateCredentials(TEST_USER_FILE, "janedoe", "password456"), 
            "Valid credentials for 'janedoe' should return true");
        assertFalse(userAuthenticator.validateCredentials(TEST_USER_FILE, "johndoe", "wrongpass"), 
            "Invalid password for 'johndoe' should return false");
        assertFalse(userAuthenticator.validateCredentials(TEST_USER_FILE, "nonexistent", "pass123"), 
            "Nonexistent username should return false");
    }
}

