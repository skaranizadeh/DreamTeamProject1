import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class LoginManager {
    private Encrypt encryptor;

    public LoginManager() {
        this.encryptor = new Encrypt();
    }

    // Method to perform user login
    public boolean login(Scanner sc, String expectedUser, String expectedPassword) {
        try {
            // Check username
            boolean isUsernameCorrect = encryptor.checkUsername(sc, expectedUser);

            // If the username is correct, check password
            if (isUsernameCorrect) {
                return encryptor.checkPassword(sc, expectedPassword);
            } else {
                return false;
            }
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
    }
}

