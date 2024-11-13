package src;

class AdminUser extends User implements Administrable {
    public AdminUser(String userID, String username, String email, String password) {
        super(userID, username, email, password, "Admin");
    }

    @Override
    public void viewUserDetails() {
        System.out.println("Viewing and modifying user details:");
        UserFileManager.getInstance().viewUserDetails();
    }

    @Override
    public void addUser(String userID, String username, String email, String password) {
        User newUser = new RegularUser(userID, username, email, password);
        UserFileManager.getInstance().addUser(newUser);
    }

    @Override
    public void updateUser(String userID, String newUsername, String newEmail) {
        UserFileManager.getInstance().updateUser(userID, newUsername, newEmail);
    }

    @Override
    public void renameFile(String oldFileName, String newFileName) {
        UserFileManager.getInstance().renameFile(oldFileName, newFileName);
    }

}

