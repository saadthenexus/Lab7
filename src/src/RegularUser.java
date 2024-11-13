package src;

public class RegularUser extends User implements Readable {
    public RegularUser(String userID, String username, String email, String password) {
        super(userID, username, email, password, "Regular");
    }

    @Override
    public void viewUserDetails() {
        System.out.println("Viewing user details (read-only):");
        UserFileManager.getInstance().viewUserDetails();
    }
}
