
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Encrypt {
	private int attempts = 0;
	private int maxAttempts = 3;
	private int usrAttempts = 0;
	private int maxUsrAttempts = 3;
	
    //using MD5 and BigInteger to encrypt and make hash into 32 length
    public String encryptString(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger bigInt = new BigInteger(1, messageDigest);
        return bigInt.toString(16);
    }

    // check if username is in our database
    public boolean checkUsername(Scanner scanner, String user) throws NoSuchAlgorithmException {
        System.out.print("Enter username:");
        String username = scanner.nextLine();

        while (!encryptString(username).equals(user)) {
        	 usrAttempts++;
             System.out.println("username is invalid, please try again\n");
             // making the username attempts restricted to three before the code exits
             if (usrAttempts == maxUsrAttempts) {
                 System.out.println("Too many incorrect login attempts. \nExiting the application");
                 System.exit(maxUsrAttempts);
             }

             System.out.println("Incorrect login attempt " + usrAttempts + "/" + maxUsrAttempts);
             System.out.print("Enter username:");
             username = scanner.nextLine();
         }
        

        return true; // Username is correct
    }

    // checking if password matches
    public boolean checkPassword(Scanner scanner, String hashed) {
        try {
            System.out.print("\nPlease enter your password: ");
            String passwordString = scanner.nextLine();

            while (!encryptString(passwordString).equals(hashed)) {
                attempts++;
                System.out.println("Password is invalid, please try again\n");
                // making the Password attempts restricted to three before the code exits
                if (attempts == maxAttempts) {
                    System.out.println("Too many incorrect login attempts. \nExiting the application");
                    System.exit(maxAttempts);
                }

                System.out.println("Incorrect login attempt " + attempts + "/" + maxAttempts);
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