public interface Administrable extends Writeable {
    void updateUser(String userId, String newUsername, String newEmail);
    void renameFile(String newFileName);
}
