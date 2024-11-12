class AdminUser extends User implements Administrable {
    public AdminUser(String userID, String username, String email, String password) {
        super(userID, username, email, password, "Admin");
    }

    @Override
    public void viewUserDetails() {
        
    }

    @Override
    public void addUser(String newUserId, String username, String email, String password) {
        
    }

    @Override
    public void updateUser(String userId, String newUsername, String newEmail) {
        
    }

    @Override
    public void renameFile(String oldFileName, String newFileName) {
        
    }
  
}

