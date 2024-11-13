package src;

public class PowerUser extends User implements Writeable {
    public PowerUser(String userID, String username, String email, String password) {
        super(userID, username, email, password, "Power");
    }

    @Override
    public void viewUserDetails() {
        System.out.println("Viewing user details (read-write access):");
        UserFileManager.getInstance().viewUserDetails();
    }

    @Override
    public void addUser(String userID, String username, String email, String password) {
        User newUser = new RegularUser(userID, username, email, password);
        UserFileManager.getInstance().addUser(newUser);
    }

}

