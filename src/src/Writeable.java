package src;

public interface Writeable extends Readable {
    public void addUser(String newUserId, String username, String email, String password);
}
