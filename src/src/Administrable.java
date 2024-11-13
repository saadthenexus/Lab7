package src;

public interface Administrable extends Writeable {
    public void updateUser(String userId, String newUsername, String newEmail);
    public void renameFile(String oldFileName, String newFileName);
}
