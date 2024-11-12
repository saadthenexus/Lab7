public class PowerUser extends User implements Writeable {
    public PowerUser(String userID, String username, String email, String password) {
        super(userID, username, email, password, "Power");
    }

    @Override
    public void viewUserDetails() {
        
    }

    @Override
    public void addUser(String newUserId, String username, String email, String password) {
        
    }

}

