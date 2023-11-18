
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Encrypt {
    //using MD5 and BigInteger to encrypt and make hash into 32 length
    public String encryptString(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger bigInt = new BigInteger(1, messageDigest);
        return bigInt.toString(16);
    }

    // check if username is in our database
    public boolean checkUsername(Scanner scanner, String user) throws NoSuchAlgorithmException {
        System.out.println("Enter username:");
        String username = scanner.nextLine();

        while (!encryptString(username).equals(user)) {
            System.out.println("Wrong");
            System.out.println("Enter username:");
            username = scanner.nextLine();
        }

        return true; // Username is correct
    }

    // checking if password matches
    public boolean checkPassword(Scanner scanner, String hashed) {
        try {
            System.out.print("Please enter your password: ");
            String passwordString = scanner.nextLine();

            while (!encryptString(passwordString).equals(hashed)) {
                System.out.println("Try again\n");
                System.out.print("Enter password:");
                passwordString = scanner.nextLine();
            }

            return true; // Password is correct
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
            return false; // Indicate failure due to the exception
        }
    }
}



/* Testing to see if it works
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Encrypt encrypt = new Encrypt();
        Console console = System.console();

        String hasheduser = "21232f297a57a5a743894a0e4a801fc3";
        
        String hashed = "77230fb5224b2316d0b4c7f4e5e5f482";



        if (encrypt.checkUsername(console, hasheduser) && encrypt.checkPassword(console, hashed)) {
            System.out.println("Correct");
        } else {
            System.out.println("Incorrect");
        }
    }
} */