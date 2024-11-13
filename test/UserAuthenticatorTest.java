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

        createFile(TEST_USER_FILE, "1,johndoe,johndoe@example.com,pass123,Regular");
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
}

